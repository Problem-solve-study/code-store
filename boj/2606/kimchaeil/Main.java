//메모리: 11764 KB
//시간: 68 ms

/*
 * 1번 컴퓨터와 네트워크 상 연결된 컴퓨터의 개수를 세는 문제이므로
 * BFS, DFS 중 하나를 사용하면 된다. 나느 BFS를 사용
 */

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int numComputer = Integer.parseInt(br.readLine()); // 컴퓨터개수
		int numConnect = Integer.parseInt(br.readLine()); // 연결개수

		List<List<Integer>> connects = new ArrayList<List<Integer>>(); // 각 컴퓨터에 대해 연결된 컴퓨터의 번호를 저장할 곳
		for (int i = 0; i < numComputer; i++) {
			connects.add(new ArrayList<Integer>());
		}

		boolean[] visited = new boolean[numComputer]; // 방문한 컴퓨터인지 확인하기 위한 배열
		StringTokenizer st;
		for (int i = 0; i < numConnect; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1; // 컴퓨터의 번호를 0 부터 시작하도록 -1
			int b = Integer.parseInt(st.nextToken()) - 1;
			connects.get(a).add(b); // a에 b가 연결되었다고 저장
			connects.get(b).add(a); // b에 a가 연결되었다고 저장
		}
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(0); // 0번 컴퓨터에서 시작 (문제에서는 1번부터 시작이지만 위에서 컴퓨터의 번호를 0부터 시작하도록 만들었다.)
		visited[0] = true; // 0번 컴퓨터에 대한 방문을 기록
		int result = 0;
		while (!queue.isEmpty()) {
			int now = queue.poll();
			result++;
			for (int connect : connects.get(now)) { // now와 연결된 컴퓨터들을 가져와 탐색
				if (!visited[connect]) { // 방문한 컴퓨터가 아니라면
					queue.add(connect); // 큐에 넣기
					visited[connect] = true; // 방문을 기록하기
				}
			}
		}
		System.out.println(result - 1); // 위 BFS를 끝내면 result에는 0번 컴퓨터가 같이 카운트되었기 때문에 -1
	}

}
