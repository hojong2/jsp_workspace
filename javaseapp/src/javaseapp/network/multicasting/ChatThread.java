package javaseapp.network.multicasting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.JTextArea;

//대화를 담당할 객체, 이 객체는 독립수행되어야 하므로, 쓰레드로 정의한다
public class ChatThread extends Thread{
	BufferedReader br;
	BufferedWriter bw;
	GUIMultiServer guiMultiServer;
	boolean flag=true;
	
	public ChatThread(Socket socket, GUIMultiServer guiMultiServer) {
		this.guiMultiServer=guiMultiServer;
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
		String msg=null;
		try {
			msg=br.readLine();
			guiMultiServer.area.append(msg+"\n");
			//나와 연결된 클라이언트에게만 보내지 말고, 현재 서버에 접속한 모든 클라이언트의 ChatThread의
			//출력스트림을 이용해보자
			for(int i=0;i<guiMultiServer.vec.size(); i++) {
				ChatThread chatThread = guiMultiServer.vec.get(i);
				chatThread.send(msg);
			}
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
