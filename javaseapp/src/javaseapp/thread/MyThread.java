package javaseapp.thread;

import javax.swing.JProgressBar;

//프로그래스바를 증가시킬 쓰레드 정의
//메인쓰레드는 무한루프나, 대기상태에 빠지게 하면 안된다
//왜나하면 메인 쓰레드는 GUI 프로그램을 운영하는 업무를 해야 하기 때문(이벤트감지, 그래픽처리...)
public class MyThread extends Thread{
	JProgressBar bar;
	int n=0;
	int k=0;
	
	public MyThread(JProgressBar bar, int k) {
		this.bar=bar;
		this.k=k;
	}
	//개발자는 쓰레드 정의시, 원하는 로직을 run메서드에 작성한다(run 오버라이딩)
	//추후, run 메서드는 JVM에게 선택되어지며 이 순간을 가리켜 running 상태라 한다
	public void run() {
		while(true) {
			n++;
			bar.setValue(n);
			try {
				Thread.sleep(k);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
