package com.beginend.grozmerbackend;

import com.beginend.grozmerbackend.service.storage.StorageProperties;
import com.beginend.grozmerbackend.service.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class GrozmerBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrozmerBackendApplication.class, args);
	}

/*	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}*/

	@Bean
	MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize(DataSize.ofKilobytes(24956876));
		factory.setMaxRequestSize(DataSize.ofKilobytes(24956876));
		return factory.createMultipartConfig();
	}
}
