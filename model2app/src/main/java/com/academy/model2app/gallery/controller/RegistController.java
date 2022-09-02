package com.academy.model2app.gallery.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.academy.model2app.controller.Controller;
import com.academy.model2app.model.repository.GalleryDAO;
import com.academy.model2app.notice.domain.Gallery;
import com.academy.model2app.util.FileManager;

/*
 * 텍스트 파라미터 뿐만 아니라, 바이너리 파일이 포함된 업로드 요청을 처리해본다
 * 지난 시간에 이용한 업로드 컴포넌트 cos.jar (Oreilly사 제공)
 * 이번 시간은 apache.org에서 제작한 apache commons 프로젝트 하위 컴포넌트 fileupload 컴포넌트
 */
public class RegistController implements Controller{
	FileManager fileManager=new FileManager();
	GalleryDAO galleryDAO= new GalleryDAO();
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		Gallery gallery = fileManager.getParam(request);
		
		galleryDAO.insert(gallery);
		//mybatis에 의해 기존의 Gallery DTO의 pk값이 채워지게 된다.
		int gallery_id = gallery.getGallery_id();
		
		//파일업로드
		fileManager.saveAs(fileManager.getDest()+"/"+gallery_id+"."+fileManager.getExtension());
		System.out.println(gallery.getGallery_id());
	}

	@Override
	public String getViewName() {
		// TODO Auto-generated method stub
		return "/gallery/result/write";				
	}

	@Override
	public boolean isForward() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
