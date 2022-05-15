package com.shoesstation.cuoikididong.service;

import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface IStorageService {
	public String  StoreFile(MultipartFile file);
	public Stream<Path>  loadAll();
	public byte[]  ReadFileContent(String fileName);
	public void  deleteAllFiles();
	
	
}