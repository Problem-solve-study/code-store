//문제: BOJ 9470번 Strahler 순서
//메모리: 11520 KB
//시간: 64 ms

/*
 *  강의 근원부터 접근 가능하게 위상 정렬하고
 *  그 이후 노드들은 문제에 주어진 방법대로 i를 구한다
 *  그리고 m번 노드가 무조건 바다와 만나는 노드이므로
 *  m번 노드의 i를 출력한다.
 *  
 *  처음에는 위상정렬과 정렬 후 작업을 queue를 사용해서 풀었다.
 *  근데 어짜피 queue에 들어갈 노드의 개수는 m과 같으므로 배열로 수정하였다.
 *  그리고 위상정렬을 위한 queue와 정렬 후 작업을 하기 위한 order에 노드들이 들어가는 순서가 같으니 하나의 배열로 만들었다.
 *  위상 정렬과 정렬후 작업은 한번의 반복문으로 동시에 처리가 가능하여 하나의 반복문으로 합쳤다.
 *  길이가 m+1인 배열들을 보니 모두 노드와 관련된 정보가 저장되는 배열이라 2차원 배열로 합쳤다.
 */


import java.io.*;
import java.util.*;

public class Main {
	static StreamTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StreamTokenizer(br);
		StringBuilder sb = new StringBuilder();

		int T = nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			int k = nextInt(), m = nextInt(), p = nextInt();
			sb.append(k).append(' ');

			List<List<Integer>> to = new ArrayList<>();
			int[][] nodes = new int[4][m + 1];
			/*
			 * node[0] 들어오는 강의 개수 cnt (위상정렬을 위한 데이터)
			 * node[1] 들어오는 강들 중 가장 큰 i값
			 * node[2] 들어오는 강들 중 가장 큰 i값의 개수
			 * node[3] i값
			 */

			for (int i = 0; i <= m; i++) {
				to.add(new ArrayList<>());
			}

			for (int i = 0; i < p; i++) {
				int a = nextInt();
				int b = nextInt();
				to.get(a).add(b); // a->b 간선 저장
				nodes[0][b]++; //b의 들어오는 강의 개수 카운트
			}

			int[] queue = new int[m]; //위상정렬을 진행하면서 i값을 구할 때 사용할 배열
			int idx = 0; //queue에 접근할 때 사용
			
			for (int i = 1; i <= m; i++) {
				if (nodes[0][i] == 0) { //강의 근원이라면
					queue[idx++]=i; //queue에 넣기
				}
			}
			
			for(int now:queue) { //queue를 탐색하면서 (반복문이 진행되면서 queue에 노드들이 저장되므로 모든 노드들을 탐색할 수 있음)
				//현재 노드의 i값 초기화 작업
				nodes[3][now] = nodes[1][now]; //노드의 i값을 들어오는 강들 중 가장 큰 i값으로 초기화
				nodes[3][now] += nodes[2][now] == 1 ? 0 : 1; //들어오는 강들 중 가장 큰 i값이 2개이상이라면 i값 증가
				//강의 근원의 경우 node[1]와 node[2]의 초기값이 0이므로 node[3]이 1로 초기화됨
				for (int next : to.get(now)) { //현재 노드가 흘러들어가는 노드(next)들에 대해
					//추후 next의 i값을 구하기 위한 작업
					if (nodes[1][next] < nodes[3][now]) { //현재 노드의 i값이 next의 node[1]보다 클 때
						nodes[1][next] = nodes[3][now]; // node[1]을 현재 노드의 i값으로 초기화
						nodes[2][next] = 1; //node[2]를 1로 초기화
					} else if (nodes[1][next] == nodes[3][now]) { //현재 노드의 i값이 next의 node[1]과 같을 때
						nodes[2][next]++; //node[2] 증가
					}
					//아래는 위상정렬
					nodes[0][next]--; //next의 node[0] 감소
					if (nodes[0][next] == 0) { //next의 node[0]이 0이라면 queue에 추가
						queue[idx++]=next;
					}
				}
			}
			sb.append(nodes[3][m]).append('\n'); //출력
		}
		System.out.println(sb);
	}

	static int nextInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
}
