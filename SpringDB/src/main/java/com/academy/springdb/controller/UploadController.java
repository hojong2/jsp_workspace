package com.academy.springdb.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.academy.springdb.model.domain.Gallery;

@Controller
public class UploadController {
	//클라이언트가 전송한 이미지 파일을 업로드 처리
	@PostMapping("/upload")
	public ModelAndView save(Gallery gallery, HttpServletRequest request) {
		System.out.println("제목: "+gallery.getTitle());
		System.out.println("작성자: "+gallery.getWriter());
		System.out.println("파일: "+gallery.getPhoto());
		
		//업로드된 파일에 대한 분석s
		MultipartFile multi=gallery.getPhoto();
		System.out.println(multi.getContentType());
		System.out.println(multi.getOriginalFilename());
		System.out.println(multi.getName());
		System.out.println(multi.getSize());
		
		//아직 물리적 파일로 존재시킨 적이 없으므로, 원하는 서버의 디렉토리에 파일을 저장해보자
		String path=request.getServletContext().getRealPath("/resource/data");  //jsp에서의 application 내장 객체
		String filepath=path+"/"+multi.getOriginalFilename();
		System.out.println(filepath);
		try {
			multi.transferTo(new File(filepath));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
