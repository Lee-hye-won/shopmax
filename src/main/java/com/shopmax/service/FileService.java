package com.shopmax.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.extern.java.Log;

@Service
@Log
public class FileService {
	// 파일 업로드
	public String uploadFile(String uploadPath, String originalFileName, byte[] fileData) throws Exception {
		UUID uuid = UUID.randomUUID();	// 중복되지 않은 이름을 만든다.
		
		// 이미지1.jpg 에서 .을 기준으로 이미지 확장자명을 구한다.
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
		
		// 파일이름 생성 -> SDJHEKBJ.jpg
		String savedFileName = uuid.toString() + extension;	
		
		
		String fileUploadFullUrl = uploadPath + "/" + savedFileName;
		
		// 파일업로드
		FileOutputStream fos = new FileOutputStream(fileUploadFullUrl);
		fos.write(fileData);
		fos.close();
		
		return savedFileName;
 	}
	
	// 파일 삭제
	public void deleteFile(String filePath) throws Exception {
		
		// 파일이 저장된 경로를 이용해서 파일 객체를 생성
		File deleteFile = new File(filePath) ;
		
		// 파일 삭제
		if(deleteFile.exists()) {
			deleteFile.delete();
			log.info("파일을 삭제하였습니다.");	// 로그 기록을 저장한다.
		} else {
			log.info("파일이 존재하지 않습니다.");
		}
	}
}
