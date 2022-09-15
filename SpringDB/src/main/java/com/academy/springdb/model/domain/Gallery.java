package com.academy.springdb.model.domain;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

//VO(Value Object) - readOnly Object setter (x)
//DTO(Data Transfer Object)
@Data
public class Gallery {
	private String title;
	private String writer;
	private MultipartFile photo;  //html의 파라미터 이름과 일치할 경우, 파일 업로드 처리를 내부적으로 자동 처리함
}
