package com.academy.shopping.model.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import lombok.Getter;
import lombok.Setter;

public class MailFormReader {

	FileReader reader;  //문자기반의 파일을 대상으로 한 입력스트림
	BufferedReader br;
	@Setter
	@Getter
	private String path;
	public String getStringFromMailForm(String content) {
		StringBuffer sb = new StringBuffer();
		try {
			reader= new FileReader(path);
			br=new BufferedReader(reader);
			//파일 한 줄씩 읽기
			
			
			while(true) {
				String msg = br.readLine();
				if(msg==null)break;
				sb.append(msg);
			}
			System.out.println("읽은 결과: "+sb.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(br!=null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(reader!=null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}
}
