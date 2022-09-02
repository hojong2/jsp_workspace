package com.academy.model2app.util;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.academy.model2app.notice.domain.Gallery;

import lombok.Data;

@Data
//파일과 관련된 업무처리를 담당하는 유틸리티 클래스
public class FileManager {
	int maxSize=5*1024*1024;
	String savePath;
	DiskFileItemFactory factory = new DiskFileItemFactory();
	FileItem fileItem; //파일 정보가 들어있는 아이템
	private String dest;
	private String extension;
	
	//파일을 지정한 경로에 저장하는 메서드
	public Gallery getParam(HttpServletRequest request) {
		//jsp의 내장객체이자 ServletContext 자료형인 객체를 request객체로부터 얻어올 수 있나? yes
		String savePath=request.getServletContext().getRealPath("/data");
		dest=savePath;
		//업로드 전 설정정보를 관리하는 객체
		factory.setSizeThreshold(maxSize);
		factory.setRepository(new File(savePath));
		//서버 어디에?, 용량 제한은 얼마나? 같은 설정정보가 필요함
		
		//업로드를 담당하는 객체
		ServletFileUpload servletFileUpload=new ServletFileUpload(factory);
		Gallery gallery = new Gallery();  //DTO
		String ext=null; //확장자
		//요청객체를 분석 후, 텍스트와 파일을 FileItem에 담는다
		//우리의 경우 input="text" 3개 + input type="file" 1개 = 4개를 List에 담는다
		try {
			List<FileItem> uploadList=servletFileUpload.parseRequest(request);
			System.out.println("업로드 분석후 발견된 아이템 수는: "+uploadList.size());
			for(int i=0; i<uploadList.size();i++) {
				FileItem item=uploadList.get(i);
				if(item.isFormField()) { //텍스트 파라미터를 받은 경우
					System.out.println("(텍스트) "+item.getFieldName()+"의 값은"+item.getString());
					switch(item.getFieldName()) {
					case "title":gallery.setTitle(item.getString()); break;
					case "writer":gallery.setWriter(item.getString()); break;
					case "content":gallery.setContent(item.getString()); break;
					}
					
				}else {  //바이너리 파일인 경우
					System.out.println("(파일) "+item.getName());
					ext=FileManager.getExt(item.getName());
					extension=ext;
					fileItem = item; //맴버변수에 보관해야, 다른 메서드에서 접근할 수 있으니까
				}
			}
			
			//insert
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return gallery;
	}
	public void saveAs(String path) {
		System.out.println(path);
		try {
			fileItem.write(new File(path));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//파일명 추출
	
	//확장자 추출
	public static String getExt(String path) {
		int index=path.lastIndexOf(".");
		return path.substring(index+1, path.length());
	}
	/*
	public static void main(String[] args) {
		System.out.println(getExt("d:dsdasd/dasdsad/sss.aa.bb.cc.jpg"));
		
	}
	*/
}
