package com.academy.springbasicapp.cook;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.academy.springbasicapp.gui.MyWin;
import com.academy.springbasicapp.school.Student;

public class AppMain {
	
	public static void main(String[] args) {
		/*
		Cheif cheif = new Cheif();
		cheif.cook();
		*/
		
		//요리사가 쓰고자 하는 객체를, 스프링이 관여하여 직접 객체를 주입시켜주자(Injection)
		//왜 주입하나? 요리사 클래스에서 new 하는 걸 피하기 위해 new 하는 순간 정확한 자료형 명시와 함께
		//결합도(의존성)가 강해지므로
		
		//어플리케이션에서 사용할 객체들을 스프링이 관리해 줄 수 있는데, 이때 사용되는 스프링의 객체를 가리켜
		//스프링 빈 컨테이너라 하며, 자료형은 ApplicationContext 이다.
		
		//앞으로 개발자가 프로그램에서 사용할 객체들은 자바 클래스에서 생성하지 말고, xml에 명시해 놓으면
		//스프링 컨테이너가 알아서 인스턴스를 생성하여 관리해준다.
		
		//아래의 ApplicationContext 객체를 메모리에 올릴때, 지정한 xml을 파싱하고, 그 xml에 명시된 모든 bean들은
		//객체의 인스턴스가 만들어져서 컨테이너에 의해 관리된다.
		ClassPathXmlApplicationContext context= new ClassPathXmlApplicationContext("app/config/Context.xml");
		//Cheif cheif=(Cheif)context.getBean("cheif"); //새롭게 인스턴스를 생성하지 않고 이미 컨테이너가 생성된 빈을 얻어오기
		//MyWin mywin = (MyWin)context.getBean("myWin");
		//cheif.cook();
		//mywin.init();
		Student student=(Student)context.getBean("student");
		student.studyTime1();
	}
}
