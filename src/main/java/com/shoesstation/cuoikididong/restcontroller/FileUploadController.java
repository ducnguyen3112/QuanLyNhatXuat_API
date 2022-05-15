package com.shoesstation.cuoikididong.restcontroller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.shoesstation.cuoikididong.exception.RestErrorResponse;
import com.shoesstation.cuoikididong.service.IStorageService;


@Controller
@RequestMapping(path ="/fileUpload" )
public class FileUploadController {
	@Autowired
	private IStorageService storageService;
	
	@PostMapping("")
	public ResponseEntity<RestErrorResponse> uploadFile(@RequestParam("file") MultipartFile file){
		try {
			
			String generatedFileName=storageService.StoreFile(file);
			return ResponseEntity.status(HttpStatus.OK).body(new RestErrorResponse(generatedFileName, HttpStatus.OK,LocalDateTime.now()));
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new RestErrorResponse(e.getMessage(), HttpStatus.OK,LocalDateTime.now()));
		}
	}
}