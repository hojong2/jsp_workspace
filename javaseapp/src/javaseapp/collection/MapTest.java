package javaseapp.collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/*
 * 컬렉션프레임웤 중 Map을 학습한다
 * 모여있는 데이터의 구분을 key값으로 제어할 수 있으며, 특히 key값을 알면 데이터를 접근할 수 있다.
 * 데이터 교환에 사용되는 json도 사실 map유형이다.
 */
public class MapTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("a", "apple");
		map.put("b", "banana");
		map.put("c", "cocoa");
		map.put("m", "mango");
		map.put("p", "pineapple");
		
		System.out.println("총 과일의 수는: " +map.size());
		Set<String> set = map.keySet();
		Iterator<String> it = set.iterator();
		while(it.hasNext()) {
			String key = it.next();
			
			//얻어진, key를 이용하여 드디어, map 안에서 꺼내자
			System.out.println(map.get(key));
		}
	}

}
