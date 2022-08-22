/*
 * 스트림의 유형
 * 1) 방향 - 입력, 출력
 * 2) 데이터 처리방법 - 바이트기반 : 1byte씩 처리하므로 영문이 아닌 한글의 경우 2byte씩 구성되어 있기 때문에 문자는 깨져보임
 * 							문자기반: 2byte를 묶어서 문자로 인식하는 스트림(한글 등 비영어권 뿐만 아니라 전세계 모든 문자가 깨지지 않음)
 * 										~~Reader(문자 기반 입력스트림), ~~~Writer(문자 기만 출력스트림)
 * 							버퍼기반: 데이터를 효율적으로 입출력하기 위한 스트림
 */
package javase.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ImageReader {
	FileInputStream fis; //파일을 대상으로 데이터를 읽어오는 바이트기반 스트림
	FileReader reader; //파일을 대상으로 데이터를 읽어오되, 2byte를 하나의 문자로 읽을수 있는 스트림

	public ImageReader() {
		try {
			//대상 자원에 빨대 꽃기
			fis=new FileInputStream("D:/jsp_workspace/javaseapp/data/memo.txt");
			reader=new FileReader("D:/jsp_workspace/javaseapp/data/memo.txt");
			System.out.println("스트림 생성 성공");
			
			int data=-1;
			while(true) {
				data=reader.read(); //1바이트 읽기
				if(data==-1)break;
				System.out.print((char)data);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
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
		new ImageReader();
	}

}
