package com.beginend.grozmerbackend.web.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.beginend.grozmerbackend.dao.IPlotDocsDao;
import com.beginend.grozmerbackend.dao.IPlotProgressDao;
import com.beginend.grozmerbackend.entity.PlotDocsEntity;
import com.beginend.grozmerbackend.entity.PlotProgressEntity;
import com.beginend.grozmerbackend.entity.PlotStatusEnum;
import com.beginend.grozmerbackend.model.User;
import com.beginend.grozmerbackend.service.PlotDocsService;
import com.beginend.grozmerbackend.service.UserService;
import com.beginend.grozmerbackend.service.storage.StorageFileNotFoundException;
import com.beginend.grozmerbackend.service.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@RestController
@RequestMapping("doc")
public class FileUploadController {

    private final StorageService storageService;
    private PlotDocsService plotDocsService;
    private IPlotProgressDao plotProgressDao;
    private UserService userService;

    @Autowired
    public FileUploadController(StorageService storageService, PlotDocsService plotDocsService, IPlotProgressDao plotProgressDao, UserService userService) {
        this.storageService = storageService;
        this.plotDocsService = plotDocsService;
        this.plotProgressDao = plotProgressDao;
        this.userService = userService;
    }

    @GetMapping("/")
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                        "serveFile", path.getFileName().toString()).build().toUri().toString())
                .collect(Collectors.toList()));

        return "uploadForm";
    }

    @GetMapping("/files/{id}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable Long id) throws IOException {
        PlotDocsEntity plotDocsEntity = plotDocsService.findById(id);
        Resource file = storageService.loadAsResource(plotDocsEntity.getName());
        return ResponseEntity.ok().header(
                HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=" + file.getFilename())
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION)
                .contentType(new MediaType("application", "force-download"))
                .contentLength(file.getFile().length())
                .body(file);
    }

    @PostMapping("save")
    public PlotDocsEntity handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam Long plotId, @RequestParam String docType) {
        String finename = plotId.toString() + "_" + file.getOriginalFilename().replace(",", ".");
        storageService.store(file, finename);
        PlotDocsEntity plotDocsEntity = new PlotDocsEntity();
        plotDocsEntity.setPlotId(plotId);
        plotDocsEntity.setDocType(docType);
        plotDocsEntity.setName(finename);
        plotDocsEntity.setDataType(file.getContentType());
        plotDocsEntity = plotDocsService.save(plotDocsEntity);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails user = (UserDetails) auth.getPrincipal();
        User user1 = userService.findByLogin(user.getUsername());

        PlotProgressEntity progressEntity = new PlotProgressEntity();
        progressEntity.setPlotId(plotId);
        progressEntity.setStatus(PlotStatusEnum.ADD_DOCUMENT);
        progressEntity.setUserId(user1.getId());
        plotProgressDao.save(progressEntity);
        return plotDocsEntity;
    }

    @GetMapping("all/{plotId}")
    public List<PlotDocsEntity> findAll(@PathVariable Long plotId){
        return this.plotDocsService.findAllByPlotId(plotId);
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
