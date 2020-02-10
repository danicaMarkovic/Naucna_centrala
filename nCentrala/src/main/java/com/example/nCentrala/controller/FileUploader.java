package com.example.nCentrala.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping(value = "/download/{id}")
	public ResponseEntity<Resource> downloadFile(@PathVariable("id")String name, HttpServletRequest request) throws IOException {
		
        Resource resource = this.loadFile("D:\\MASTER\\UDD\\PROJEKAT\\Naucna_centrala\\nCentrala\\files\\"+name);
        
        String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			System.out.println("Could not determine file type.");
		}

		if(contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .header( HttpHeaders.LOCATION, resource.getURI().toString())
				.body(resource);
	}
	
	public Resource loadFile(String fileName) {
        try {
            Path filePath = this.fileLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            }
            
        } catch (MalformedURLException ex) {
           
        	System.out.println("Nema fajlaaaa");
        	
        }
        
        return null;
    }
}
