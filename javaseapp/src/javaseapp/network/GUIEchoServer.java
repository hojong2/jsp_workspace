package javaseapp.network;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUIEchoServer extends JFrame{
	JTextField t_port;
	JButton bt_start;
	JTextArea area;
	JScrollPane scroll;
	
	ServerSocket server;  //접속자 감지용 소켓(데이터 용이 아니다)
	Thread serverThread; //서버를 가동하고, 접속자를 감지하는 용도의 쓰레드
								  //필요이유? accept() 메서드는 접속자가 발생할때까지 대기상태에 빠지므로
	
	public GUIEchoServer() {
		t_port= new JTextField("9999",12);
		bt_start= new  JButton("서버가동");
		area = new JTextArea();
		scroll= new JScrollPane(area);
		
		//스타일
		scroll.setPreferredSize(new Dimension(280,320));
		area.setBackground(Color.cyan);
		//조립
		setLayout(new FlowLayout());
		add(t_port);
		add(bt_start);
		add(scroll);
		//윈도우설정
		setVisible(true);
		setBounds(500, 100,300,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//쓰레드를 별도의 .java로 만들어도 되지만, 귀찮으니 현재 클래스 내에서 처리하자
		//즉 내부익명으ㅏ로 재정의한다
		serverThread=new Thread() {
			//개발자가 쓰레드를 통해 구현하고 싶은 로직은 run() 메서드에 작성해놓으면, JVM이 선택해준다
			public void run() {
				startServer();
			};
		};
		//가동버튼에 리스너 연결
		bt_start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				serverThread.start();  //개발자 주도가 아닌 jvm에 실행을 맡긴다
			}
		});
	}
	//서버가동하기
	public void startServer() {
		try {
			server=new ServerSocket(Integer.parseInt(t_port.getText()));  //문자열을 정수로
			area.append("서버가동 및 접속자 청취중...\n");
			Socket socket= server.accept();
			String ip=socket.getInetAddress().getHostAddress();
			area.append(ip+" 주소에서 접속\n");
			
			//소켓으로부터 스트림을 뽑아서, 데이터를 주고받아 보자
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));  //입력 스트림
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));  //출력 스트림
			
			String data=null;
			while(true) {
				data=br.readLine();  //한 줄 읽기
				area.append(data+"\n");
				
				//클라이언트가 보낸 메시지를 다시 돌려보내자
				bw.write(data+"\n");
				bw.flush();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new GUIEchoServer();
	}
}
