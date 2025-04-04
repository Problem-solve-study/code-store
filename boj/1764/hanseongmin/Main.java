import java.io.*;
import java.util.*;

/*
 * 32008KB, 468ms
 * 
 * 교집합 구하기. 사전순이여야하니까 TreeSet 사용
 */

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); int M = Integer.parseInt(st.nextToken());
		TreeSet<String> a = new TreeSet<>();
		TreeSet<String> b = new TreeSet<>();
		
		for (int i = 0; i < N; i++) {
			a.add(br.readLine());
		}
		for (int i = 0; i < M; i++) {
			b.add(br.readLine());
		}
		
		a.retainAll(b); //교집합 구하는 메소드
		StringBuilder sb = new StringBuilder();
		sb.append(a.size()).append('\n');
		a.forEach(e -> sb.append(e).append('\n'));
		System.out.print(sb);
	}
}
