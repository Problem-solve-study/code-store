//문제: BOJ 22252 정보 상인 호석
//메모리: 42044 KB
//시간: 632 ms

/*
 * 고릴라마다 우선순위큐 배정
 * 고릴라의 우선순위큐에 접근하기 위해 해시맵 사용
 */

import java.io.*;
import java.util.*;

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

	public static void main(String[] args) throws Exception {
		int q = nextInt();
		Map<String, Queue<Integer>> hs = new HashMap<>();
		long result = 0;
		while (q-- > 0) {
			int op = nextInt();
			String name = next();
			int k = nextInt();
			if (!hs.containsKey(name))
				hs.put(name, new PriorityQueue<>(Collections.reverseOrder()));
			Queue<Integer> pq = hs.get(name);
			if (op == 1) {
				while (k-- > 0)
					pq.add(nextInt());
			} else {
				while (k-- > 0 && !pq.isEmpty())
					result += pq.poll();
			}
		}
		System.out.println(result);
	}

	static int nextInt() throws Exception {
		st.nextToken();
		return (int) st.nval;
	}

	static String next() throws Exception {
		st.nextToken();
		return st.sval;
	}
}
