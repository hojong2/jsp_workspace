package javaseapp.network.multicasting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

//클라이언트가 메시지를 보낼때 청취하는 것이 아니라, 언제나 서버로부터 전송되어오는 메시지를
//무한루프로 입력받아야 하므로, 별도의 쓰레드가 필요하다
//만일 메인 실행부로 무한루프를 걸어버리면, 클라이언트측 GUI프로그램이 열린다

public class ClientThread extends Thread{
	BufferedReader br;
	BufferedWriter bw;
	Socket socket;
	GUIClient guiClient;
	boolean flag=true;
	
	public ClientThread(Socket socket, GUIClient guiClient) {
		this.socket=socket;
		this.guiClient=guiClient;
		
		//접속이 성공 되었다면 대화가 가능해야 하므로, 입출력 스트림을 Socket으로부터 뽑아낸다
		try {
			br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			bw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//클라이언트에게 말하기
		public void send(String msg) {
			try {
				bw.write(msg+"\n");
				bw.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//클라이언트의 말 듣기
		public void listen() {

			try {
				String msg=br.readLine();
				guiClient.area.append(msg+"\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	public void run() {
		while(flag) {
			listen();
		}
	}
	
}
