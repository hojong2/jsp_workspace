package javaseapp.thread;
/*
 * 쓰레드란 하나의 프로세스내에서 독립적으로 실행될 수 있는 세부실행단위
 */

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class ProgressApp extends JFrame{
	JProgressBar bar1, bar2, bar3;
	JButton bt;
	MyThread myThread, myThread2, myThread3;
	
	public ProgressApp() {
		
		bar1 = new JProgressBar();
		bar2 = new JProgressBar();
		bar3 = new JProgressBar();
		
		bt = new JButton("download");
		myThread = new MyThread(bar1,500);
		myThread2 = new MyThread(bar2,100);
		myThread3= new MyThread(bar3,50);
		
		
		setSize(400,300);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//스타일
		bar1.setPreferredSize(new Dimension(380,50));
		bar2.setPreferredSize(new Dimension(380,50));
		bar3.setPreferredSize(new Dimension(380,50));
		
		//배치 관리자 적용
		setLayout(new FlowLayout());
		
		//조립
		add(bar1);
		add(bar2);
		add(bar3);
		add(bt);
		
		bt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//생성된 쓰레드를 jvm의 Runnable 영역으로
				//밀어넣어버리자
				myThread.start();
				myThread2.start();
				myThread3.start();
			}
		});
	}

	
	//자바는 쓰레드 기반 언어이다. 우리가 쓰레드를 정의하지 않더라도 기본적으로 일명 '프로그램의 실행부'라 불리는 단위가
	//바로 메인 쓰레드(main())였다. 특히 이 메인 쓰레드는 개발자가 정의할 수 있는 쓰레드가 아닌, 시스템에서 이미 지원하는
	//특별한 쓰레드이다.
	public static void main(String[] args) {
		new ProgressApp();
	}
	
}
