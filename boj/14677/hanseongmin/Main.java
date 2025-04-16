import java.io.*;
import java.util.*;

/*
 * 90452KB, 392ms
 * 
 * 태그보면 DP도 달려있는데 DP는 잘 모르겠고 BFS가 먼저 떠올랐음.
 * visited 체크 안해도 되지 않을까? 싶어서 안하고 했다가 시초로 1틀
 */

public class Main {
	//다음 약을 바로 구할 수 있게 해주는 맵
	static HashMap<Character, Character> nextMap = new HashMap<>();
	//자바에서는 배열 타입을 set에 넣고 싶으면 list를 넣어야 의도된대로 동작함
	static HashSet<ArrayList<Character>> v = new HashSet<>();
	//BFS에 넣을 커스텀 클래스
	static class Data {
		int cnt;
		char next;
		ArrayDeque<Character> d;
		
		Data(int c, char n, ArrayDeque<Character> d) {
			cnt = c;
			next = n;
			this.d = d;
		}
		
		ArrayList<Character> getList() {
			ArrayList<Character> list = new ArrayList<>();
			d.forEach(c -> list.add(c));
			return list;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[] str = br.readLine().toCharArray();
		ArrayDeque<Character> d = new ArrayDeque<>();
		for (int i = 0; i < str.length; i++) {
			d.addLast(str[i]);
		}
		
		nextMap.put('B', 'L'); nextMap.put('L', 'D'); nextMap.put('D', 'B');
		Queue<Data> q = new ArrayDeque<>();
		Data init = new Data(0, 'B', new ArrayDeque<>(d));
		q.add(init); v.add(init.getList()); 
		int res = 0;
		while (!q.isEmpty()) {
			Data cur = q.remove();
			res = Math.max(res, cur.cnt);
			
			//맨 앞을 먹을 수 있으면 먹고 큐에 넣음
			if (!cur.d.isEmpty() && cur.d.peekFirst() == cur.next) {
				ArrayDeque<Character> nextD = new ArrayDeque<>(cur.d);
				nextD.removeFirst();
				Data nextData = new Data(cur.cnt + 1, nextMap.get(cur.next), nextD);
				//visited에 없을 때만 큐에 넣기
				if (!v.contains(nextData.getList())) {
					q.add(nextData); v.add(nextData.getList());
				}
			}
			
			//맨 뒤를 먹을 수 있으면 먹고 큐에 넣음
			if (!cur.d.isEmpty() && cur.d.peekLast() == cur.next) {
				ArrayDeque<Character> nextD = new ArrayDeque<>(cur.d);
				nextD.removeLast();
				Data nextData = new Data(cur.cnt + 1, nextMap.get(cur.next), nextD);
				if (!v.contains(nextData.getList())) {
					q.add(nextData); v.add(nextData.getList());
				}
			}
		}
		System.out.print(res);
	}
}