package com.example.service;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.form.FileUploadForm;


@Service
public class FileUploadService {
	
	@Autowired
    private HttpServletRequest request;

	
	public String fileUpload(FileUploadForm fileUploadForm,MultipartFile image,String fileName) 
		       throws IOException{
		DateTimeFormatter fm = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
		
		
		String extension = FilenameUtils.getExtension(image.getOriginalFilename()).toLowerCase();
		//画像の新規アップロードはUUID,画像更新は何もしない
		if(fileName == null) {
        	fileName = fileUploadForm.getCreateAt().format(fm) + " " + UUID.randomUUID() +"." + extension;
        }
		
		
		 File uploadDir = new File("/uploads");
	        uploadDir.mkdirs();
	        
	        String uploadsDir = "/uploads/";
	        String realPathToUploads = request.getServletContext().getRealPath(uploadsDir);
	       
	        if (!new File(realPathToUploads).exists()) {
	            new File(realPathToUploads).mkdirs();
	        }
	        
	        String localfileName = image.getOriginalFilename();
	        File destFile = new File(realPathToUploads, localfileName);
	        System.out.println(realPathToUploads + "/" + localfileName );
	        image.transferTo(destFile);
	      
				return fileName;
}
}
