package javaseapp.network;

import java.awt.Choice;
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
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/*
 * telnet으로는 영문의 명령어만 지원하므로,  대화를 나누기엔 한계가 있다
 * 따라서 대화용 프로그램을 자바기반으로 만든다
 */
public class GUIClient extends JFrame{
	Choice ch; //html의 select 박스와 동일
	JTextField t_port;
	JButton bt_connect;
	JTextArea area;
	JScrollPane scroll;
	JTextField t_input;
	JButton bt_send;
	Socket socket;
	BufferedWriter bw;
	BufferedReader br;
	
	int port=9999;
	public GUIClient() {
		ch = new Choice();
		t_port= new JTextField(Integer.toString(port),6);
		bt_connect = new JButton("접속");
		area= new JTextArea();
		scroll = new JScrollPane(area);
		t_input= new JTextField(15);
		bt_send = new JButton("전송");
		
		for(int i =13; i<=136;i++) {
			ch.add("192.168.25."+i);
			if(i==80) { //내 아이피인 경우
				ch.select("192.168.25."+i);
			}
		}
		
		//스타일
		scroll.setPreferredSize(new Dimension(280, 270));
		area.setBackground(Color.yellow);
		
		//조립
		setLayout(new FlowLayout());
		add(ch);
		add(t_port);
		add(bt_connect);
		add(scroll);
		add(t_input);
		add(bt_send);
		
		//윈도우 설정
		setVisible(true);
		setBounds(200,100,300,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//접속버튼에 리스너 연결
		bt_connect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				connect();
			}
		});
		
		//전송버튼에 리스너 연결
		bt_send.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				send();
			}
		});
	}
	
	public void connect() {
		//원하는 서버의 ip, port 번호를 이용하여 접속 즉 연결을 시도하다
		try {
			socket=new Socket(ch.getSelectedItem(), port);
			if(socket!=null) {
				System.out.println("접속 성공");
			}
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			br= new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //대화용 소켓
	}
	//입력한 데이터를 서버에 보내자(출력-실행중인 프로그램 기준 데이터가 나가는 것이므로)
	public void send() {
		try {
			bw.write(t_input.getText()+"\n");  //버퍼로 모은 문장의 끝을 알려주기 위해 반드시 개행 문자 포함
			//출력 스트림은 입력스트림과는 다르게 버퍼를 처리할 경우 데이터 전송시 반드시 버퍼를 비워줘야 한다
			bw.flush();
			t_input.setText(""); //텍스트칸 비우기
			
			//서버가 보낸 메시지 받기
			
			area.append(br.readLine()+"\n");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new GUIClient();
	}
}
