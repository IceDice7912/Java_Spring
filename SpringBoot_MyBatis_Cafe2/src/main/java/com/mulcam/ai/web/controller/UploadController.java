package com.mulcam.ai.web.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {

	@PostMapping("/upload")
	public void upload(@RequestParam("file") MultipartFile file) {
		System.out.println(file);
		try {
			file.transferTo(new File("C:\\Users\\dice7\\Documents\\Eclipse_SpringBoot\\SpringBoot_MyBatis_Cafe2\\SnapShot\\snapshot.png"));
		} catch (IllegalStateException e) {
			System.out.println("에러 발생 : " + e);
		} catch (IOException e) {
			System.out.println("에러 발생 : " + e);
		}
		
	}
}
