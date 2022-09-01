package javaseapp.util;
//key-value의 쌍으로 객체를 관리하는 Map의 유형 중, Properties를 사용해본다.

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropTest {
	FileInputStream fis;
	Properties props;  //key-value로 이루어진 데이터를 이해하는 객체
	public PropTest() {
		try {
			fis=new FileInputStream("D:/jsp_workspace/javaseapp/data/star.txt");
			props=new Properties();
			props.load(fis); //이 시점부터 파일을 대상으로 검색이 가능하다.
			
			//검색 (key 사용)
			String value=props.getProperty("terran");
			System.out.println("결과값은 : "+value );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(fis!=null) {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public static void main(String[] args) {
		new PropTest();
	}
}
