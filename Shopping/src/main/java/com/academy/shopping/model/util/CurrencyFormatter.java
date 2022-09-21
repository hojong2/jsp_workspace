package com.academy.shopping.model.util;

import java.text.DecimalFormat;

import org.springframework.stereotype.Component;

//정수를 대상으로 세자리 마다 쉼표로 끊어서, 통화를 표현한다.
public class CurrencyFormatter {
	public static String getCurrency(long num) {
		DecimalFormat df=new DecimalFormat(",###.###");
		return df.format(num);
	}

}
