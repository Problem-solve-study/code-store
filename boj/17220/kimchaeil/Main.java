//문제: 17220번 마약 수사대
//메모리: 11588 KB
//시간: 68 ms

import java.util.*;
import java.io.*;

/*
 *  처음에는 무조건 A만 원산지라는 말이 없어서
 *  일단 무조건 A만 원산지인 경우로 풀었으나 오답이었습니다.
 *  그래서 어떤 공급책이든지 원산지가 될 수 있다는 조건으로 코드를 수정하니 정답이 나왔습니다.
 *  
 *  일단 입력을 통해 단방향 그래프를 구성합니다.
 *  입력할 때 공급을 받는 공급책은 원산지가 아니라고 저장해줍니다.
 *  그 후 경찰이 소재를 파악하고 있는 공급책들의 공급을 모두 null로 만들어 연결을 끊어 줍니다.
 *  원산지들에 대해 BFS를 돌리며 여전히 공급받는 공급책들을 세어주고 결과를 출력합니다.
 */

public class Main {
	static StreamTokenizer st;
	static List<List<Integer>> link = new ArrayList<>(); //단방향 그래프의 간선
	static boolean[] supplied; //공급을 받으면(원산지가 아니면) true
	static boolean[] visited; //방문한 공급책인지
	static int result = 0; //결과

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StreamTokenizer(br);

		int n = nextInt();
		int m = nextInt();

		supplied = new boolean[n];
		visited = new boolean[n];

		for (int i = 0; i < n; i++) {
			link.add(new ArrayList<Integer>());
		}

		for (int i = 0; i < m; i++) {
			int a = nextNode();
			int b = nextNode();
			link.get(a).add(b); //a의 공급에 b를 추가
			supplied[b] = true; //공급받는 b를 true로 표현하여 원산지가 아님을 저장
		}

		int k = nextInt();
		for (int i = 0; i < k; i++) {
			link.set(nextNode(), null); //소재를 파악하고 있는 공급책들의 공급을 null로
		}

		for (int i = 0; i < n; i++) {
			if (!supplied[i]) { //원산지라면
				bfs(i); //BFS
			}
		}
		System.out.println(result); //결과 출력

	}

	static int nextInt() throws IOException { //int형 입력
		st.nextToken();
		return (int) st.nval;
	}

	static int nextNode() throws IOException { //A를 0으로 Z를 25로 입력
		st.nextToken();
		return st.sval.charAt(0) - 'A';
	}

	static int bfs(int start) {
		if (link.get(start) == null) { //원산지의 소재가 파악되고 있다면 바로 리턴
			return 0;
		}
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(start); //원산지부터 시작
		while (!queue.isEmpty()) {
			int now = queue.poll();
			for (int next : link.get(now)) { //현재 확인하는 공급책으로 부터 공급받는 곳들에 대해
				if (!visited[next] && link.get(next) != null) { //방문하지 않았고 next의 소재가 파악되고 있지 않다면
					queue.add(next); //next를 queue에 추가
					visited[next] = true; //next를 방문하였음을 저장
					result++; //결과 증가
				}
			}
		}
		return 0;
	}
}
