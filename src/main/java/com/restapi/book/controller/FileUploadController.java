package com.restapi.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.restapi.book.helper.FileUploadHelper;

@RestController
public class FileUploadController {
	
	@Autowired
	private FileUploadHelper fileUploadHelper;
	
	@PostMapping("/upload-file")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file)
	{	

//		file.getName();
//		file.getSize();
//		file.getContentType();
//		file.getOriginalFilename();
		try 
		{
		
		
		if (file.isEmpty()) 
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("request must contain file");
		}
		if(!file.getContentType().equals("image/jpeg"))
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("only jpeg/file contain file are allowed");

		}
		
		//file upload code
	   boolean	f=fileUploadHelper.uploadFile(file);
	   if (f)
	   {
		  //return ResponseEntity.ok("file successfully uploaded");
		   return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("image").path(file.getOriginalFilename()).toUriString());
	   }
		
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something went wrong! try again");
	}

}
