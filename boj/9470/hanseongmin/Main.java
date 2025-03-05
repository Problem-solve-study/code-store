import java.io.*;
import java.util.*;

/*
 * 11572KB, 64ms
 * 
 * 노드의 순서를 갱신할 때 순서가 중요하고 문제에서 인엣지에 관한 얘기를
 * 하고 있으므로 위상정렬을 의심.
 * 
 * 인엣지와 아웃엣지를 기록하고 위상정렬을 하며
 * 순서를 차례대로 갱신한다.
 */

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int T = nextInt();
		while (T --> 0) {
			//테스트케이스 번호
			int K = nextInt();
			//노드의 수
			int M = nextInt();
			//간선의 수
			int P = nextInt();
			
			//노드의 역방향 간선 개수를 기록
			int[] inEdgeCnt = new int[M + 1];
			//노드의 역방향 간선을 기록
			ArrayList<Integer>[] inEdges = new ArrayList[M + 1];
			//노드의 정방향 간선을 기록 
			ArrayList<Integer>[] outEdges = new ArrayList[M + 1];
			for (int i = 0; i <= M; i++) {
				inEdges[i] = new ArrayList<>();
				outEdges[i] = new ArrayList<>();
			}
			
			//간선 입력
			while (P --> 0) {
				int A = nextInt();
				int B = nextInt();
				inEdgeCnt[B]++;
				inEdges[B].add(A);
				outEdges[A].add(B);
			}

			//위상 정렬을 위한 스택 선언 및 초기값 add
			//위상 정렬은 인엣지의 개수가 0인 녀석을 스택에 넣음
			ArrayDeque<Integer> stack = new ArrayDeque<>();
			for (int i = 1; i <= M; i++) {
				if (inEdgeCnt[i] == 0) {
					stack.add(i);
				}
			}

			//노드의 Strahler 순서를 기록
			int[] orders = new int[M + 1];
			while (!stack.isEmpty()) {
				//스택에 있는 노드를 꺼내고
				int cur = stack.removeLast();

				//문제에 주어진대로 순서를 갱신
				if (inEdges[cur].isEmpty()) {
					//강의 근원이면 순서는 1
					orders[cur] = 1;
				} else {
					//나머지 노드의 순서를 갱신하기 위해
					//순서의 최댓값과 개수를 탐색
					int maxOrder = 0;
					int maxCnt = 0;
					for (int prev : inEdges[cur]) {
						maxOrder = Math.max(maxOrder, orders[prev]);
					}
					
					for (int prev : inEdges[cur]) {
						if (orders[prev] == maxOrder) {
							maxCnt++;
						}
					}
					
					if (maxCnt == 1) {
						orders[cur] = maxOrder;
					} else {
						orders[cur] = maxOrder + 1;
					}
				}
				
				//이후 정방향 간선을 타고 내려가서
				//해당 간선의 inEdge 카운트를 감소
				for (int next : outEdges[cur]) {
					inEdgeCnt[next]--;
					//감소시켜서 0이 됐다면 다음 위상정렬의 대상
					if (inEdgeCnt[next] == 0) {
						stack.addLast(next);
					}
				}
			}
			
            //절대 String.format을 쓰지마.
			sb.append(K).append(" ").append(orders[M]).append('\n');
		}
		System.out.println(sb);
	}
	
	static int nextInt() throws Exception {
		st.nextToken();
		return (int) st.nval;
	}
}
