/*
 * 문자기반 스트림을 이용하여, 메모장 편집기를 제작한다.
 */
package javase.io;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MemoApp extends JFrame{
	JMenu m_file, m_edit, m_style, m_view, m_help;  //자바에서는 메뉴라 불린다.
	JMenuItem item_open, item_save, item_exit;  //메뉴의 하위 메뉴를 가리켜 메뉴 아이템이라 한다.
	JMenuBar bar; //눈에 보이지는 않지만, 메뉴들을 감싸안는 컨테이너
	JTextArea content; //편집기 영역
	JScrollPane scroll; //편집기에 적용할 스크롤
	JFileChooser chooser; //파일탐색기
	FileReader fr; // 파일을 대상으로 한 문자기반 스트림, 문자기반을 선택한 이유는 복사가 목적이 아니여서, 메모리에 올라온 데이터가 한글이 깨지지 않도록 하기위함
	BufferedReader br; //
	
	public MemoApp() {
		super("메모장");
		m_file= new JMenu("파일");
		m_edit= new JMenu("편집");
		m_style= new JMenu("서식");
		m_view= new JMenu("보기");
		m_help= new JMenu("도움말");
		
		item_open=new JMenuItem("파일열기");
		item_save=new JMenuItem("저장하기");
		m_file.addSeparator(); //분리선
		item_exit=new JMenuItem("종료");
		
		bar=new JMenuBar();
		content= new JTextArea();
		scroll= new JScrollPane(content);
		chooser=new JFileChooser("D:/jsp_workspace/javaseapp/data");
		
		m_file.add(item_open);
		m_file.add(item_save);
		m_file.add(item_exit);
		
		bar.add(m_file);
		bar.add(m_edit);
		bar.add(m_style);
		bar.add(m_view);
		bar.add(m_help);
		
		//bar는 우리가 원하는 곳에 붙일수있는 것이 아니라, 언제나 윈도우창 상단에 고정
		this.setJMenuBar(bar);
		add(scroll);
		
		setSize(800,700);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//파일열기 이벤트 구현
		item_open.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				openFile();
			}
		});
		
	}
	
	public void openFile() {
		int result = chooser.showOpenDialog(this);
		
		//상수는 직관성이 있는 문자를 이용하므로, 개발시 이해하기 좋음. 숫자에 별명을 붙이는 꼴
		if(result==JFileChooser.APPROVE_OPTION) {
			//유저가 선택한 파일에 대한 정보를 얻어야한다.
			File file = chooser.getSelectedFile();
			try {
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				//한 문자읽기
				String data=null;
				while(true) {
				data=br.readLine();
				if(data==null) break;
				content.append(data+"\n");
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if(fr!=null) {
					try {
						fr.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(br!=null) {
					try {
						br.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
	public static void main(String[] args) {
		new MemoApp();
	}
}
