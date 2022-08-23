//파일 복사 구현
package javase.io;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.plaf.DimensionUIResource;

public class FileCopy2 extends JFrame{
	//재료준비
	JButton bt;
	JTextField t_ori, t_dest;
	JProgressBar bar;
	FileReader fr;
	FileWriter fw;
	
	public FileCopy2() {
		bt=new JButton("복사실행");
		t_ori= new JTextField(30);
		t_dest=new JTextField(30);
		bar = new JProgressBar();
		
		//스타일
		bar.setPreferredSize(new DimensionUIResource(380, 55));
		bar.setBackground(Color.cyan);
		//조립
		setLayout(new FlowLayout());
		add(bt);
		add(t_ori);
		add(t_dest);
		add(bar);
		
		//윈도우창 보이기
		setSize(400,200);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//이벤트 구현하기
		bt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				copy();
			}
		});
	}
	
	public void copy() {
		//JOptionPane.showMessageDialog(this, "누름");   버튼 동작 확인
		try {
			String oriPath=t_ori.getText();
			String destPath=t_dest.getText();
			
			fr = new FileReader(oriPath);
			fw = new FileWriter(destPath);
			
			
			int data=-1;
			while(true) {
				data=fr.read();//입력 스트림으로부터 1byte 읽기
				if(data==-1) break;
				fw.write(data);//출력 스트림으로 1byte 쓰기
			}
			JOptionPane.showMessageDialog(this, "복사완료");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(fr!=null) {
				try {
					fr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(fw!=null) {
				try {
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new FileCopy2();
	}
}
