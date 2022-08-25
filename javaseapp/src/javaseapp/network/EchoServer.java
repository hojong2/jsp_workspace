package javaseapp.network;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;

//메아리 서버를 구축한다(채팅서버의 가장 기초적인 단계)
public class EchoServer {
	int port=9999;  //1~1024 이미 os 및 다른 시스템 차원에서 점유되는 포트라서 사용을 피해야 한다.
	ServerSocket server;  //네트워크를 통해 데이터를 주고 받기 전에, 먼저 클라이언트와 서버와의 연결이 선행되어야 하는데, 이 연결을 처리해 주는 전담 객체를 가리켜 서버소켓이라 한다.
								//주의) 우리가 흔히 알고 있는 대화용 소켓이 아니다
	public EchoServer() {
		try {
			server= new ServerSocket(port); //서버 생성
			
			//Socket을 통해 데이터를 클라이언트와 주고 받을 수 있다. 이때 개발자는 네트워크에 대한 지식이
			//필요없고, 오직 데이터 IO에 집중(결국 스트림 제어에 집중)
			//가능한 이유? 소켓이 네트워크 하부 구조에 대한 추상화 담당
			
			
			String data=null;
			while(true) {
				Socket socket= server.accept();  //클라이언트가 접속하기를 기다린다(즉 청취함)
				String ip=socket.getInetAddress().getHostAddress();
				
				System.out.println(ip+" 주소에서 접속");
				InputStream is =  socket.getInputStream();
				InputStreamReader isr= new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				data=br.readLine(); //1line 읽기
				System.out.println(data);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new EchoServer();
	}
}
