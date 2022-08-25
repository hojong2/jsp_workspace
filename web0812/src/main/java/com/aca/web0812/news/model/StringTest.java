package com.aca.web0812.news.model;
/*
 * Java 언어의 String의 특징
 * 1) immutable: 불변
 */
public class StringTest {

	public static void main(String[] args) {
		String a= "tiger";
		String b= "tiger";
		b="tiger king";
		System.out.println(a==b);
		
		String x=new String("korea");
		String y=new String("korea");
		System.out.println(x==y);
	}
}
