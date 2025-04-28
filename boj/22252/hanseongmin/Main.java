import java.io.*;
import java.util.*;

/*
 * 43764KB, 608ms
 * 
 * 이름별로 가장 비싼 정보를 구해야함 -> 이름을 키로 하는 맵을 생성하고 값에는 힙을 넣음
 * 이후 위 자료구조를 적절히 관리하면서 쿼리에 대해 답을 하면 된다.
 */

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	
	public static void main(String[] args) throws Exception {
		TreeMap<String, PriorityQueue<Integer>> map = new TreeMap<>();
		long res = 0;
		int Q = nextInt();
		for (int i = 0; i < Q; i++) {
			int q = nextInt();
			String name = nextToken();
			
			if (q == 1) { //고릴라가 정보를 얻음
				int k = nextInt();
				//맵에 힙을 할당해주고
				map.putIfAbsent(name, new PriorityQueue<>(Collections.reverseOrder()));
				//정보를 삽입
				PriorityQueue<Integer> h = map.get(name);
				while (k --> 0) {
					h.add(nextInt());
				}
			} else { //정보를 구매
				int b = nextInt();
				//힙을 가져오고
				PriorityQueue<Integer> h = map.get(name);
				//힙에서 정보 차례대로 빼면서 구매
				for (int cnt = 0; cnt < b && h != null && !h.isEmpty(); cnt++) {
					res += h.remove();
				}
			}
		}
		System.out.print(res);
	}
	
	static int nextInt() throws Exception {
		st.nextToken();
		return (int) st.nval;
	}
	
	static String nextToken() throws Exception {
		st.nextToken();
		return st.sval;
	}
}