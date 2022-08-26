package javaseapp.network.multicasting;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
	ClientThread clientThread;
	
	
	int port=9999;
	Socket socket; //데이터 송수신용 소켓
	
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
	
		bt_connect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				connect();
			}
		});
		
		bt_send.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				clientThread.send(t_input.getText());
				t_input.setText("");
			}
		});
		
		
		//메시지입력 텍스트필드에 리스너 연결
		t_input.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					//자바에서는 상수가 주로, 사람이 이해하기 힘든 데이터의 경우 의미를 부여한, 즉 직관성을 부여한 단어를 이용하여 상수를 정의함
					clientThread.send(t_input.getText());
					t_input.setText("");
				}
			}
		});
		
		
	}
	
	//소켓을 생성하는 시점에, 접속이 시도된다
	public void connect() {
		try {
			socket = new Socket(ch.getSelectedItem(), Integer.parseInt(t_port.getText()));
			//접속이 성공되었다면, 이 시점부터는 대화가 가능해야 하므로 쓰레드로하여금 대화를 나누게 하자
			clientThread = new ClientThread(socket, this);
			clientThread.start();
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
