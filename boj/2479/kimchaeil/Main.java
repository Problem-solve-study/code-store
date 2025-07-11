//문제: BOJ 2479번 경로 찾기
//시간: 92 ms
//메모리: 12696 KB

import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		
		int n = nextInt(), k = nextInt();
		Map<Integer, Integer> code2num = new HashMap<Integer, Integer>();
		int[] num2code = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			int code = readBinary();
			code2num.put(code, i);
			num2code[i] = code;
		}
		int a = nextInt(), b = nextInt();

		Queue<Integer> queue = new ArrayDeque<Integer>();
		Map<Integer, Integer> prev = new HashMap<Integer, Integer>();
		boolean[] isVisited = new boolean[n + 1];
		queue.add(b);
		isVisited[b] = true;
		
		while (!queue.isEmpty()) {
			int now = queue.poll();
			if (a == now)
				break;
			int nowCode = num2code[now];
			for (int i = 0, mask = 1; i < k; i++, mask <<= 1) {
				Integer next = code2num.get(nowCode ^ mask);
				if (next != null && !isVisited[next]) {
					isVisited[next] = true;
					prev.put(next, now);
					queue.add(next);
				}
			}
		}
		
		if (prev.get(a) == null) {
			System.out.println(-1);
		} else {
			StringBuilder sb = new StringBuilder();
			Integer now = a;
			while (now != null) {
				sb.append(now).append(' ');
				now = prev.get(now);
			}
			System.out.println(sb);
		}
	}

	static int nextInt() throws Exception {
		int n, c;
		while ((c = System.in.read()) < 48)
			;
		n = c & 15;
		while ((c = System.in.read()) > 47)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}

	static int readBinary() throws Exception {
		int n, c;
		while ((c = System.in.read()) < 48)
			;
		n = c & 15;
		while ((c = System.in.read()) > 47)
			n = (n << 1) + (c & 15);
		return n;
	}
}

