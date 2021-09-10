package com.beginend.grozmerbackend.web.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.beginend.grozmerbackend.dao.IPlotDocsDao;
import com.beginend.grozmerbackend.entity.PlotDocsEntity;
import com.beginend.grozmerbackend.service.PlotDocsService;
import com.beginend.grozmerbackend.service.storage.StorageFileNotFoundException;
import com.beginend.grozmerbackend.service.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@RestController
@RequestMapping("doc")
public class FileUploadController {

    private final StorageService storageService;
    private PlotDocsService plotDocsService;

    @Autowired
    public FileUploadController(StorageService storageService, PlotDocsService plotDocsService) {
        this.storageService = storageService;
        this.plotDocsService = plotDocsService;
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
        storageService.store(file);
        PlotDocsEntity plotDocsEntity = new PlotDocsEntity();
        plotDocsEntity.setPlotId(plotId);
        plotDocsEntity.setDocType(docType);
        plotDocsEntity.setName(file.getOriginalFilename());
        plotDocsEntity.setDataType(file.getContentType());
        plotDocsEntity = plotDocsService.save(plotDocsEntity);
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
