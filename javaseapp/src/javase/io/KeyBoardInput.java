/*
 * 지금까지 사용해왔던 대부분의 스트림은 개발자가 원하는 시점에 생성하여 사용할 수 있었지만
 * 키보드나, 모니터와 관련된 스트림은 개발자가 생성할 수 있는 것이 아니라, 현재 사용중인 OS가 생성한다
 * 따라서 키보드나 모니터에 연결된 스트림을 사용하기 위해서는 시스템으로부터 얻어와야 한다.
 */
package javase.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class KeyBoardInput {
	public static void main(String[] args) {
		InputStream is=System.in;  //이미 존재하는 스트림을 얻은것, 주의: 여기서 얻은 입력스트림은 반드시 키보드라고 한정지으면 안된다.
											//만일 usb로 스캐너를 꽃고나서 데이터를 읽으면, 이 코드에서 해당 데이터를 읽을수가 있다. 그 이유는 키보드, 스케너 등등이 표준을 지켰기 때문이다
		InputStreamReader isr=new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String data=null;
		
		try {
			data=br.readLine();
			
			//모니터에 출력
			System.out.print(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
