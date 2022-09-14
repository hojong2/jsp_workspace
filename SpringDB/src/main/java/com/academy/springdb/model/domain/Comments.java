package com.academy.springdb.model.domain;

import lombok.Data;

@Data
public class Comments {
	private int comments_id;
	private String detail;
	private String writedate;
	private String author;
	private int news_id;
}
