package javaseapp.collection;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* 컬렉션 프레임웍중 Set을 학습한다.
 * Set : 순서없는 객체 집합
 */
public class SetTest {

	public static void main(String[] args) {
		Set<String> set=new HashSet<String>();
		
		set.add("파인애플");
		set.add("오렌지");
		set.add("키위");
		set.add("사과");
		set.add("바나나");
		
		System.out.println("담겨진 과일의 수: " + set.size());
		
		//담겨진 과일 출력하기(반복문으로는 불가)
		//순서없는 집합이므로, 별도의 도구를 이용해야 한다. 이때 지원되는 도구들은 객체들을
		//일렬로 늘어뜨리는 기능을 가진 Enumeration, Iterator 가 있다
		Iterator<String> it=set.iterator();
		
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}

}
