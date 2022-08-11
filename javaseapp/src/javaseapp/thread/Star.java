package javaseapp.thread;

public class Star extends Thread {
	String mark;
	public Star(String mark) {
		this.mark=mark;
	}
	public void run() {
		while(true) {
			System.out.println(mark);
			//while문은 속도가 너무 빠르므로, jvm에게
			//너무 빠른 run을 맞는다. 따라서 지정한 시간동안
			//non-runnable 영역으로 빼놓을수 있는 기능이 지원된다
			//sleep(시간)
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
