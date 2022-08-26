package javaseapp.network.multicasting;

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
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import javaseapp.network.multicasting.ChatThread;

public class GUIMultiServer extends JFrame{
	JTextField t_port;
	JButton bt_start;
	JTextArea area;
	JScrollPane scroll;
	Thread listenThread;  //메인쓰레드가 accept()에 의해 대기상태에 빠지지 않도록 별도의 쓰레드 필요
	ServerSocket server;  //데이터 전송용이 아닌 통신 연결용 소켓
	boolean flag=true;  //서버 가동 여부를 결정짓는 논리값
	Vector<ChatThread> vec=new Vector<ChatThread>();  //ArrayList와 기능은 동일하나, 차이점은 동기화를 지원해준다 (내부적으로 동기화 처리 떄문에 속도는 떨어짐)
	
	public GUIMultiServer() {
		t_port= new JTextField("9999",12);
		bt_start= new  JButton("서버가동");
		area = new JTextArea();
		scroll= new JScrollPane(area);
		listenThread= new Thread() {
			public void run(){
				startServer();
			}
		};
		
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
		
		//가동버튼에 리스너 연결
		bt_start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				listenThread.start();  //쓰레드를 Runnable 영역으로 넣어준다
			}
		});		
	}
	//서버 가동
	public void startServer() {
		try {
			server= new ServerSocket(Integer.parseInt( t_port.getText()));
				area.append("서버 가동 및 접속자 기다리는 중...\n");
			while(flag) {
				Socket socket= server.accept();  //해당 메서드를 통해 접속자가 발생할 때까지 무한대기 상태에 빠짐
				String ip = socket.getInetAddress().getHostAddress();
				area.append("접속자 감지\n");
				area.append(ip+" 주소에서 접속\n");
				
				//대화용 쓰레드를 생성하여, 대화를 나누도록 해준다
				ChatThread chatThread = new ChatThread(socket, this);
				chatThread.start();
				
				//접속 클라이언트마다 일대일 대응하는 객체인 ChatThread의 주소값을 벡터에 보관해놓자
				//이유: 총 접속자 명단을 보유하기 위함
				vec.add(chatThread);
				area.append("현재 접속자 수: "+vec.size()+"\n");
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new GUIMultiServer();
	}
}
