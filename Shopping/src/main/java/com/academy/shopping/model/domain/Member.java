package com.academy.shopping.model.domain;

import lombok.Data;

@Data
public class Member {
	private int member_id;
	private String customer_id;
	private String customer_name;
	private String customer_pass;
	private String customer_email;
}
