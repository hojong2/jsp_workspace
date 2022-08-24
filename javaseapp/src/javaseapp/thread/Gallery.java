package javaseapp.thread;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javasapp.domain.Movie;

/*개발자가 컴포넌트를 그냥 사용하면, sun에서 이미 정해놓은 그림을 이용하게 된다
 * 하지만, 개발자가 이 그림을 커스터마이징도 할 수 있는데, 이때 오버라이드 해야 할 메서드가 바로
 * paint 메서드이다.
 */
public class Gallery extends JFrame{
	JPanel p_controller;
	JPanel p_content;
	JButton bt_prev, bt_next, bt_auto;
	URL url;
	Image image;  //자바에서 이미지를 표현한 객체이고, 이미지 인스턴스를 얻는 방법은 상당히 다양하다
	
	FileReader fr; //한문자를 읽을수 있는 입력 스트림
	BufferedReader br; //버퍼처리된 문자기반 입력 스트림
	
	List<Movie> movieList;
	BufferedImage img; //패널이 그리게 될 이미지 객체
	
	int index=0;
	
	public Gallery() {
		/*
		try {
			url=new URL("https://images-na.ssl-images-amazon.com/images/I/91qvAndeVYL._RI_.jpg");
			image = ImageIO.read(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		p_controller = new JPanel();
		
		init();
		System.out.println("최종적으로 모여진 영화의 수는 "+movieList.size());
		loadImage(index);
		
		p_content = new JPanel(){
			//아래의 메서드를 재정의 하는 순간부터는 개발자가 그린 그림을 우선시해준다
			//결론: paint 메서드의 호출시점은? 다시 그려져야 할 때
			@Override
			public void paint(Graphics g) {
				g.drawImage(img, 0, 0, 600, 500, p_controller);
			}
		};
		bt_prev = new JButton("이전");
		bt_next = new JButton("다음");
		bt_auto = new JButton("auto");
		
		//스타일 적용
		p_controller.setPreferredSize(new Dimension(600,50));
		p_controller.setBackground(Color.cyan);
		p_content.setPreferredSize(new Dimension(600,500));
		p_content.setBackground(Color.yellow);
		
		//배치관리자 적용
		setLayout(new FlowLayout());
		
		//조립
		p_controller.add(bt_prev);
		p_controller.add(bt_next);
		p_controller.add(bt_auto);
		add(p_controller);
		add(p_content);
		
		//윈도우속성
		setLocationRelativeTo(null); //화면 가운데로 윈도우창을 띄운다
		setSize(600,550);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
		//다음 버튼에 대한 이벤트 구현
		bt_next.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				next();
			}
		});
		bt_prev.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				prev();
			}
		});
		
		bt_auto.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//무한루프가 걸리게 되므로, 절대 메인쓰레드는 루프에 넣어서는 안된다
				//메인쓰레드 대신 업무를 처리할 개발자정의 쓰레드로 처리
				
				Thread thread=new Thread() {
					@Override
					public void run() {
						auto();
					}
				};
				thread.start();
			}
		});
		
	}
	
	//프로그램에서 사용할 데이터 가져오기
	public void init() {
		//지금 JSON 데이터는 파일로 존재한다. 따라서 실행중인 자바 프로그램에서 문서 파일을 읽어야 하므로,
		//지금 필요한 기술은 입력스트림이 필요하다.
		//1) 방향: 모든 스트림은 데이터의 처리 방향에 따라 입력, 출력으로 나뉜다.
		//2) 다루는 데이터 : 바이트 기반, 문자 기반, 버퍼 처리
		
		try {
			fr=new FileReader("D:/jsp_workspace/javaseapp/data/data.json");
			//json 형식을 이해하고 해석할 수 있는 파서를 이용하여, data.json 안에 표기된 데이터를 접근한다
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject= (JSONObject)jsonParser.parse(fr);
			
			JSONArray jsonArray=(JSONArray)jsonObject.get("marvel");

			//곧 닫히게될 스트림과 죽게될 jsonArray변수를 대체할 방법
			movieList = new ArrayList<Movie>();
			for(int i=0; i<jsonArray.size();i++) {
				JSONObject obj=(JSONObject)jsonArray.get(i);
				
				Movie movie = new Movie();
				movie.setUrl((String)obj.get("url"));
				movie.setTitle((String)obj.get("title"));
				
				//리스트에 담기
				movieList.add(movie);
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
		}
		
	}
	
	//다음 사진 넘어가기
	public void next() {
		if(index<movieList.size()-1) {
			index++;
			loadImage(index);
			//Panel의 그림을 다시 그리는 방법(프로그래밍 적으로)
			//repaint() --> update() 화면을 모두 지움 --> paint() 스스로 호출
			p_content.repaint();
		}else {
			JOptionPane.showMessageDialog(this, "마지막 이미지입니다.");
		}
	}
	//이전 사진 넘어가기
	public void prev() {
		if(index>0) {
			index--;
			loadImage(index);
			//Panel의 그림을 다시 그리는 방법(프로그래밍 적으로)
			//repaint() --> update() 화면을 모두 지움 --> paint() 스스로 호출
			p_content.repaint();
		}else {
			JOptionPane.showMessageDialog(this, "처음 이미지입니다.");
		}
	}
	
	public void auto() {
		
		while(true) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			next();
		}
	}
	
	//이 메서드를 호출하는 자는, 원하는 인덱스를 인수로 넘긴다
	public void loadImage(int index) {
		Movie movie= movieList.get(index); //영화 한편을 얻는다(title, url)
		URL url;
		try {
			url = new URL(movie.getUrl());
			img=ImageIO.read(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Gallery();
	}

}
