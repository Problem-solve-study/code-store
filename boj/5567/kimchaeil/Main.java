//문제: BOJ 5567번 결혼식
//메모리: 15364 KB
//시간: 112 ms

/*
 * 1번의 친구들을 queue에 넣으면서 카운트 해주고
 * queue에 들어간 친구들의 친구들을 카운트 해준다
 * 친구가 중복될 수 있으므로 visited 배열을 사용한다.
 */

import java.io.*;
import java.util.*;
public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	public static void main(String[] args) throws IOException{
		int n = nextInt();
		int m = nextInt();
		List<List<Integer>> friends = new ArrayList<>();
		for(int i=0;i<=n;i++) {
			friends.add(new ArrayList<>());
		}
		
		while(m-->0) {
			int a = nextInt();
			int b = nextInt();
			friends.get(a).add(b);
			friends.get(b).add(a);
		}
		
		boolean[] visited = new boolean[n+1];
		int cnt = 0;
		Queue<Integer> queue = new ArrayDeque<>();
		visited[1]=true;
		for(int friend : friends.get(1)) {
			queue.add(friend);
			visited[friend]=true;
			cnt++;
		}
		while(!queue.isEmpty()) {
			int now = queue.poll();
			for(int friend : friends.get(now)) {
				if(!visited[friend]) {
					cnt++;
					visited[friend]=true;
				}
			}
		}
		System.out.println(cnt);
	}
	static int nextInt() throws IOException {
		st.nextToken();
		return (int)st.nval;
	}
}
