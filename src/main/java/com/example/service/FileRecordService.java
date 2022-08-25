package com.example.service;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class FileRecordService {
	
	
	
	
	
	public static  File saveImageLocal(MultipartFile image, HttpServletRequest request)
			throws IOException{
		File uploadDir = new File("/uploads");
		uploadDir.mkdir();

//		String uploadsDir = "/uploads/";
//		String realPathToUploads = request.getServletContext().getRealPath(uploadsDir);
//		if (!new File(realPathToUploads).exists()) {
//			new File(realPathToUploads).mkdir();
//		}
		String fileName = image.getOriginalFilename();
		File destFile = new File( fileName);
		image.transferTo(destFile);

		

		return destFile;
	}

}
