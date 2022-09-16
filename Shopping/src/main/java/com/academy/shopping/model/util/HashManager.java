package com.academy.shopping.model.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

//회원정보 중, 비밀번호를 평문으로 넣지 말자
//암호화 시켜넣을 건데, 16진수값의 해쉬값으로 변환하자. 암호화 로직은 직접 짜지 않는다.
@Component
public class HashManager {
	
	public static String getConvertedPassword(String password){
		StringBuffer sb= null;
		//위의 문자열을 사람이 알아볼수 없는 해쉬값을 변환해보자
		try {
			MessageDigest digest=MessageDigest.getInstance("SHA-256");
			byte[] hash=digest.digest(password.getBytes("UTF-8"));//바이트 단위로 쪼개기
			
			//쪼개진 데이터 크기만큼 반복문 돌리면서 하나씩 개별적으로 16진수 해쉬값으로 변환한후,
			//하나의 문자열에 모아놓자
			sb = new StringBuffer();
			
			for(int i=0; i<hash.length;i++) {
				String hex=Integer.toHexString(0xff&hash[i]);
				if(hex.length()==1) {
					sb.append("0");  //hex가 한자리 수 일경우
				}
				sb.append(hex);
			}

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}
}
