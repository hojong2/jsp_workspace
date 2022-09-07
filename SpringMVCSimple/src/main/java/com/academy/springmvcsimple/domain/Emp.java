package com.academy.springmvcsimple.domain;

import lombok.Data;

@Data
public class Emp {
	private int empno;
	private String ename;
	private String hiredate;
	private int sal;
	private Dept dept;	
}
