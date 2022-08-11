/*
 * List는 순서있는 집합이므로, 배열과 상당히 흡사하다. 하지만 차이점도 존재하며 차이점은 다음과 같다
 * 
 * 1)대상
 * 		List : 객체만을 대상으로 함
 * 		배열 : 기본자료형, 객체가 대상이 될 수 있다.
 * 
 * 2)유연성
 * 		List : 생성시 크기를 명시하지 않아도 됨
 * 		배열 : 생성시 크기를 명시 해주어야 함
 */
package javaseapp.collection;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ListTest extends JFrame{  //ListTest는 윈도우 창이다
	//맴버변수는 사실, 부품이 온다. 자바에서는 부품관계를 has a 관계라 한다
	//객체를 멤버변수로 선언한 경우를 has a 관계라 한다.
	JButton bt_create, bt_color;
	JPanel p; //그냥 비어있는 컴포넌트
	
	//JButton arr[]= new JButton[20]; //유저가 동적으로 버튼을 생성하므로 배열로는 해결이 불가능하다
	List<JButton> btList = new ArrayList<JButton>();
	
	public ListTest() {
		bt_create = new JButton("버튼생성");
		bt_color = new JButton("색상적용");
		p=new JPanel();
		
		p.setBackground(Color.yellow);
		p.setPreferredSize(new Dimension(380,450));
		//this란 레퍼런스 변수이다. 객체의 인스턴스가 자기 자신을 가리키는 변수용도임
		setLayout(new FlowLayout());
		add(bt_create);
		add(bt_color);
		add(p);
		
		setSize(400,500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);  //public+static+final
		
		//이벤트 연결코드는 1회성이므로 .java로 만들 필요가 없다. 따라서 내부익명클래스로 생성한다.
		bt_create.addActionListener(new ActionListener() {
			//test
			@Override
			public void actionPerformed(ActionEvent e) {
				//동적으로 버튼 생성하기
				createBtn();
			}
		});
		
		bt_color.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setColor();
			}
		});
	}
	
	public void createBtn() {
		JButton bt = new JButton("버튼1");
		p.add(bt);
		btList.add(bt);
		System.out.println("현재 버튼 생성 개수는: "+ btList.size());
		//화면갱신	
		p.updateUI();
	}
	
	public void setColor() {
		//동적으로 생성된 모든 버튼의 배경색을 파란색으로 변경
		
		for(int i=0; i<btList.size(); i++) {
			JButton bt=btList.get(i);
			bt.setBackground(Color.BLUE);
		}
		
	}
	public static void main(String[] args) {
		ListTest listtest = new ListTest();
	}

}
