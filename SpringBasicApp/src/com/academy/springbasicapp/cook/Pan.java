package com.academy.springbasicapp.cook;

//프라이팬이건, 인덕션이건 모든 팬 종류를 가리킬 수 있는 최상위 객체
//인터페이스로 정의하여, 자식들에게 구현을 강제시킨다
//메서드명을 통일 시킬 수 있다.
public interface Pan {
	public void boil();
}
