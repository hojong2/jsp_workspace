package javaseapp.thread;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
		loadImage();
		p_content = new JPanel(){
			//아래의 메서드를 재정의 하는 순간부터는 개발자가 그린 그림을 우선시해준다
			//결론: paint 메서드의 호출시점은? 다시 그려져야 할 때
			@Override
			public void paint(Graphics g) {
				g.drawImage(image, 0, 0, 600, 500, p_controller);
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
		
		loadImage();
		
		//다음 버튼에 대한 이벤트 구현
		bt_next.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				next();
			}
		});
	}
	
	//프로그램에서 사용할 데이터 가져오기
	public void init() {
		//지금 JSON 데이터는 파일로 존재한다. 따라서 실행중인 자바 프로그램에서 문서 파일을 읽어야 하므로,
		//지금 필요한 기술은 입력스트림이 필요하다.
		//1) 방향: 모든 스트림은 데이터의 처리 방향에 따라 입력, 출력으로 나뉜다.
		//2) 다루는 데이터 : 바이트 기반, 문자 기반, 버퍼 처리
	}
	
	//다음 사진 넘어가기
	public void next() {
		
	}
	public void loadImage() {
		//json 로컬 파일로부터 이미지 정보를 얻어와 image 객체 생성하기
		//data.json이 패키지경로에 있을 때 파일을 접근하는 방법을 먼저 알아보자
		/*
		URL url=this.getClass().getResource("/javaseapp/res/data.json");
		System.out.println(url);
		*/
		File file = new File("D:/html_workspace/app0808/data.json");
		FileReader reader = null;	
		try {
			reader = new FileReader(file);
			JSONParser jsonParser = new JSONParser();
			
			//string 문서로 존재하던 json 파일을 읽어들여, json객체화 시킨것
			//"{}" --> JSON.parse()와 동일
			JSONObject jsonObject= (JSONObject)jsonParser.parse(reader);
			JSONArray jsonArray=(JSONArray)jsonObject.get("marvel");
			
			JSONObject movieJson = (JSONObject)jsonArray.get(1); //배열의 0번째 요소 접근
			System.out.println(movieJson.get("url"));
			
			URL url = new URL((String)movieJson.get("url"));
			image = ImageIO.read(url);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(reader!=null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		/*
		try {
			url=new URL("https://images-na.ssl-images-amazon.com/images/I/91qvAndeVYL._RI_.jpg");
			image = ImageIO.read(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Gallery();
	}

}
