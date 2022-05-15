package com.shoesstation.cuoikididong.service;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Stream;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
	public class ImageStorageService implements IStorageService {

		
		private final Path storageFolder =Paths.get("uploads");
		
		public ImageStorageService() {
			try {
				Files.createDirectories(storageFolder);
			} catch (Exception e) {
				throw new RuntimeException("Cannot initalize storage",e);
				}
		}
		private boolean isImageFile(MultipartFile file) {
			String fileExtention= FilenameUtils.getExtension(file.getOriginalFilename());
			return Arrays.asList(new String[] {"png","jpg","jpeg","bmp"}).contains(fileExtention.trim().toLowerCase());
		}
		@Override
		public String StoreFile(MultipartFile file) {
			try {
				if (file.isEmpty()) {
					throw new RuntimeException("Failed to store emty file.");
				}
				if (!isImageFile(file)) {
					throw new RuntimeException("This is not image file.");
				}
				
				float fileSizeInMegaBytes=file.getSize()/1000000.0f;
				if (fileSizeInMegaBytes>1) {
					throw new RuntimeException("File must be <=1Mb.");
				}
				String fileExtention=FilenameUtils.getExtension(file.getOriginalFilename());
				String generatedFileName=UUID.randomUUID().toString().replace("-", "");
				generatedFileName=generatedFileName+"."+fileExtention;
				Path destinationFilePath=this.storageFolder.resolve(Paths.get(generatedFileName)).normalize().toAbsolutePath();
				if (!destinationFilePath.getParent().equals(this.storageFolder.toAbsolutePath())) {
					throw new RuntimeException("Cannot store file outside current directory.");
				}
				try(InputStream inputStream=file.getInputStream()){
					Files.copy(inputStream, destinationFilePath,StandardCopyOption.REPLACE_EXISTING);
				}
				return destinationFilePath.toString();
			} catch (Exception e) {
					throw new RuntimeException("Failed to store file"+e,e); 
			}
		}

		@Override
		public Stream<Path> loadAll() {
			return null;
		}

		@Override
		public byte[] ReadFileContent(String fileName) {
			return null;
		}

		@Override
		public void deleteAllFiles() {

		}

	}
