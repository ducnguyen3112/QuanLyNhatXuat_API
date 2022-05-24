package com.shoesstation.cuoikididong.restcontroller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.shoesstation.cuoikididong.exception.RestResponse;
import com.shoesstation.cuoikididong.service.CloudDinaryService;
import com.shoesstation.cuoikididong.service.IStorageService;


@Controller
@CrossOrigin("*")
public class FileUploadController {
	@Autowired
	private IStorageService storageService;
	@Autowired
	private CloudDinaryService cloudDinaryService;
	
	@PostMapping("/fileUpload")
	public ResponseEntity<RestResponse> uploadFile(@RequestParam("file") MultipartFile file){
		try {
			String generatedFileName=storageService.StoreFile(file);
			return ResponseEntity.status(HttpStatus.OK).body(new RestResponse(generatedFileName, HttpStatus.OK,LocalDateTime.now()));
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new RestResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,LocalDateTime.now()));
		}
	}
	@PostMapping("/cloudDinary/fileUpload")
	public ResponseEntity<RestResponse> upLoad(@RequestParam("file") MultipartFile multipartFile) throws IOException{
		Map<?, ?> resultMap=cloudDinaryService.upload(multipartFile);
		return ResponseEntity.status(HttpStatus.OK).body(new RestResponse(resultMap.get("url").toString(), HttpStatus.OK,LocalDateTime.now()));
	}
	
}