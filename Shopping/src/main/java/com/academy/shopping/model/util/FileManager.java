package com.academy.shopping.model.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.academy.shopping.exception.UploadException;
import com.academy.shopping.model.domain.Product;

@Component
public class FileManager {
	//확장자를 구한다
	public static String getExt(String path) {
		int index=path.lastIndexOf(".");  //가장 마지막 점의 인덱스 구하기
		String ext=path.substring(index+1, path.length());
		
		return ext;
	}
	
	public String save(Product product,String savePath)  throws UploadException{
		MultipartFile multiFile=product.getPhoto();
		String ext=getExt(multiFile.getOriginalFilename());  //파일명을 전달한 후, 확장자 반환
		long time=System.currentTimeMillis();
		try {
			multiFile.transferTo(new File(savePath+"/"+time+"."+ext));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UploadException("업로드 실패",e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UploadException("업로드 실패",e);
		}
		return time+"."+ext;
	}
	
	//엑셀파일 업로드
	public File saveExcel(String path ,MultipartFile excel) {
		
		//서버에 올라온 엑셀을 읽어보자(업로드 완료)
		File file=null;
		try {
			excel.transferTo(file=new File(path+"/"+excel.getOriginalFilename()));
			System.out.println(file.getAbsolutePath());  //파일의 절대경로
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return file;
	}
	
	public static void main(String[] args) {
		System.out.println(getExt("d://sdad..sadasd..sada.jsp"));
	}
	
}
