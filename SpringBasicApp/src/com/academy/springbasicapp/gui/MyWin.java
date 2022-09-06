package com.academy.springbasicapp.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import lombok.Data;

//지금까지 작성했던 GUI 프로그래밍을 Spring DI를 적용하여 개발해본다.
@Data
public class MyWin extends JFrame{
	
	//상위 자료형으로 선언한 이유? 결합도를 약화시키기 위해
	private JComponent area; //스프링으로부터 주입 받기위해
	private JComponent t_input;
	private JComponent bt;
	
	public void init() {
		//객체의 생성은 고민할 필요가 없다. 왜? 스프링 컨테이너가 알아서 주입시켜준다
		area.setPreferredSize(new Dimension(295,340));
		
		//조립
		setLayout(new FlowLayout());
		add(area);
		add(t_input);
		add(bt);
		
		//윈도우 보이기
		setVisible(true);
		setSize(300,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
}
