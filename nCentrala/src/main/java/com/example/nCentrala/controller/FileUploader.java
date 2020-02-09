package com.example.nCentrala.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "http://localhost:1337")
@RestController
@RequestMapping("file")
public class FileUploader {
	
	private final Path fileLocation;
	
	public  FileUploader() {
		// TODO Auto-generated constructor stub
		this.fileLocation = Paths.get("files")
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileLocation);
        } catch (Exception ex) {
            System.out.println("Errorrr while creating dir");
        }
	}

	@PostMapping("/savefile")
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
	
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	     
	        try {
	            
	        	Path targetLocation = this.fileLocation.resolve(fileName);
	            
	            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

	            return new ResponseEntity<>(targetLocation.toString(), HttpStatus.OK);
	        } catch (IOException ex) {
	            System.out.println("Could not store file " + fileName + ". Please try again!");
	            
	        }
	        
	        return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	}
}
