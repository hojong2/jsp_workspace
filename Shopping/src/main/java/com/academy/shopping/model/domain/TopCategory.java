package com.academy.shopping.model.domain;

import java.util.List;

import lombok.Data;

@Data
public class TopCategory {
	private int topcategory_id;
	private String category_name;
	
	//다수의 자식을 담을 수 있는 자료형
	List<SubCategory> subList;
}
