package com.aca.web0810.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.aca.web0810.model.BoardManager;

/*웹기반이 아닌 독립실행형 기반의 GUI모드로 등록폼을 정의하자*/
public class FromWin extends JFrame{
	JTextField t_title, t_writer;
	JTextArea area;
	JButton bt; //has a 관계 : 객체가 다른 객체를 멤버로 보유한 관계
	BoardManager boardmanager;
	
	public FromWin(){
		t_title = new JTextField(17);
		t_writer = new JTextField(17);
		area = new JTextArea(10,23);
		area.setBackground(Color.CYAN);
		bt=new JButton("등록");
		boardmanager=new BoardManager();
		
		//레이아웃 스타일 명시
		this.setLayout(new FlowLayout()); //일렬로 배치되는 레이아웃
		add(t_title);
		add(t_writer);
		add(area);
		add(bt);
		setSize(300,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);//윈도우창을 닫을때 프로그램도 같이 종료
		
		//내부 익명 클래스 사용, 어느때 쓰나? 일반적으로 .java를 개발자가 파일로 만들었다는 것은 재사용 가능성을
		//염두해 두고 정의하는 것이다. 하지만 개발을 하다보면, 궅이 .java까지 만들필요없는 1회성 코드들이
		//있을수 있다. 주로 이벤트 연결의 재사용가능성이 없다. 이때 개발자는 이름없는 내부 클래스로 간단히 
		//처리할 수 있다.
		bt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				regist();
			}
		}); //버튼과 리스너 연결
	}
	//중립적으로 정의된 BoardManager를 이용하여 오라클에 글 넣기
	public void regist() {
		String title=t_title.getText();
		String writer=t_writer.getText();
		String content=area.getText();
		boardmanager.insert(title, writer, content);
		int result=boardmanager.insert(title, writer, content);
		if (result==0){
			JOptionPane.showMessageDialog(this, "등록실패");
		}else {
			JOptionPane.showMessageDialog(this, "등록성공");
		}
	}
	public static void main(String[] args) {
		new FromWin();
	}
}
