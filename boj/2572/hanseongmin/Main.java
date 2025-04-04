import java.io.*;
import java.util.*;

/*
 * 36420KB, 600ms
 * 
 * 처음에는 방문 배열에 int를 담아 최댓값을 갱신하는 그래프 탐색 문제인줄 알았다.
 * 그래프 탐색으로 접근하니까 터지는걸 보고 아 이거 DP인가 싶어 DP로 선회
 * dp[i][j] = i장의 카드를 사용하고 j번째 정점으로 이동했을 때의 최댓값
 * 으로 테이블을 구성하고 DP를 돌렸다.
 * 
 * 현재 방문하지 않은 정점이면 테이블을 갱신하면 안됨
 */

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	static ArrayList<Edge>[] adj;
	static class Edge {
		int n;
		String color;
		
		public Edge(int n, String color) {
			this.n = n;
			this.color = color;
		}
	}
	
	public static void main(String[] args) throws Exception {
		int N = nextInt();
		adj = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}
		String[] cards = new String[N + 1];
		for (int i = 1; i <= N; i++) {
			cards[i] = nextToken();
		}
		
		int M, K;
		M = nextInt(); K = nextInt();
		for (int i = 0; i < K; i++) {
			int a = nextInt(); int b = nextInt(); String color = nextToken();
			adj[a].add(new Edge(b, color));
			adj[b].add(new Edge(a, color));
		}

		int res = 0;
		boolean[] v = new boolean[M + 1]; v[1] = true;
		int[][] dp = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			String card = cards[i]; //일단 i번째 카드를 꺼낸다.
			ArrayList<Integer> list = new ArrayList<>(); //i번째 카드를 사용했을 때 처음 방문하게 되는 인덱스를 저장 
			for (int cur = 1; cur <= M; cur++) {
				if (!v[cur]) continue; //현재 정점이 방문하지 않은 정점이라면 넘어간다
				for (Edge e : adj[cur]) { //인접한 간선들을 보고
					int next = e.n; String color = e.color; int score = card.equals(color) ? 10 : 0;
					//원래 존재하는 값과 현재 정점에서 i번째 카드를 사용하여 next로 넘어오는 값을 비교하여 갱신
					dp[i][next] = Math.max(dp[i][next], dp[i - 1][cur] + score); 
					if (!v[next]) { //next가 만일 방문하지 않은 정점이었다면 방문 체크를 해주기 위해 list에 넣음
						list.add(next);
					}
					res = Math.max(res, dp[i][next]);
				}
			}
			
			//처음 방문하는 정점들 방문 체크
			for (int idx : list) {
				v[idx] = true;
			}
		}
		
		System.out.print(res);
	}
	
	static int nextInt() throws Exception {
		st.nextToken();
		return (int) st.nval;
	}
	
	static String nextToken() throws Exception {
		st.nextToken();
		return st.sval;
	}
}
