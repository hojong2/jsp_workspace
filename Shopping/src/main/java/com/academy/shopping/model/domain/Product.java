package com.academy.shopping.model.domain;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Product {
	private int product_id;
	private String product_name;
	private String brand;
	private int price;
	private int discount;
	private String memo;
	private String detail;
	private String Product_img;
	private MultipartFile photo;  //파일 내부 자동 처리 객체
	private SubCategory subcategory;
}
