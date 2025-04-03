import java.io.*;
import java.util.*;

/*
 * 18452KB, 208ms
 * 
 * DFS로 돌리면 최소 횟수를 구할 수 없어 BFS로 접근해야한다.
 * 이때 수의 범위가 커서 방문 체크는 set으로 해야함.
 * 메모리가 넉넉하길래 경로까지 같이 넣어줬는데 그래도 널널하게 통과되는듯
 * long을 써야함에 주의
 */

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	static int s, t;
	static boolean flag;
	static ArrayList<Character> list = new ArrayList<>();
	static HashSet<Long> set = new HashSet<>();
	static StringBuilder sb = new StringBuilder();
	
	//큐에 다른 타입이 다른 데이터를 넣어야 하므로 커스텀 클래스 작성
	static class Data {
		long v;
		ArrayList<Character> list;
		
		Data(long v, ArrayList<Character> list) {
			this.v = v;
			this.list = list;
		}
	}
	
	public static void main(String[] args) throws Exception {
		s = nextInt(); t = nextInt();
		
		//둘이 같으면 0
		if (s == t) {
			System.out.print(0);
		} else {
			//다르면 BFS
			Queue<Data> q = new ArrayDeque<>();
			q.add(new Data(s, new ArrayList<>())); set.add((long) s);
			while (!q.isEmpty()) {
				Data cur = q.remove();
				//수의 범위를 벗어나면 넘어감
				if (!(1 <= cur.v && cur.v <= (int)1e9)) continue;
				if (cur.v == t) {
					cur.list.forEach(e -> sb.append(e));
					break;
				}
				
				//각 연산을 수행해본 적이 없다면 탐색 수행
				if (!set.contains(cur.v * cur.v)) {
					ArrayList<Character> temp = new ArrayList<>(cur.list);
					q.add(new Data(cur.v * cur.v, temp)); temp.add('*'); set.add(cur.v * cur.v);
				}
				
				if (!set.contains(cur.v + cur.v)) {
					ArrayList<Character> temp = new ArrayList<>(cur.list);
					q.add(new Data(cur.v + cur.v, temp)); temp.add('+'); set.add(cur.v + cur.v);
				}
				
				if (!set.contains(cur.v - cur.v)) {
					ArrayList<Character> temp = new ArrayList<>(cur.list);
					q.add(new Data(cur.v - cur.v, temp)); temp.add('-'); set.add(cur.v - cur.v);
				}
				
				if (cur.v != 0 && !set.contains(cur.v / cur.v)) {
					ArrayList<Character> temp = new ArrayList<>(cur.list);
					q.add(new Data(cur.v / cur.v, temp)); temp.add('/'); set.add(cur.v / cur.v);
				}
			}
			
			//탐색이 종료됐는데 sb가 비어있으면 불가능한 경우
			System.out.print(sb.length() == 0 ? -1 : sb);
		}
	}
	
	static int nextInt() throws Exception {
		st.nextToken();
		return (int) st.nval;
	}
}
