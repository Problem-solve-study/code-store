//제출번호: 92559114
//메모리:   20208 KB
//실행시간: 448 ms
import java.io.*;
import java.util.*;

//한 번 갔던 마을을 다시 갈 수 있기 때문에 visited를 활용한 그래프 탐색은 아니라고 생각함
//카드의 수만큼 길을 지나가야 하기 때문에 그냥 완탐해보기로 함
//이 때 (지금까지 쓴 카드의 개수, 현재 방문한 마을) 기준으로는 항상 같은 값이 나올 것 같아서
//메모이제이션을 이용하기로 함
//DFS + 메모이제이션을 이용하면 N * K의 시간복잡도가 나올 듯 
public class Main {

	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	static int n, m, k;
	static int[] cards;
	static List<List<int[]>> graph;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		n = nextInt();

		cards = new int[n];
		for (int i = 0; i < n; i++) {
            //RGB 문자열을 숫자로 바꿔서 저장
			cards[i] = getRGB(nextString().charAt(0));
		}

		m = nextInt();
		k = nextInt();

		graph = new ArrayList<>();
		for (int i = -1; i < m; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < k; i++) {
			int a = nextInt();
			int b = nextInt();
			int rgb = getRGB(nextString().charAt(0));

			graph.get(a).add(new int[]{b, rgb});
			graph.get(b).add(new int[]{a, rgb});
		}

        //계산한 값이 0일 수 있기 때문에 구별하기 위해서 -1로 모두 저장함
		dp = new int[n + 1][m + 1];
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= m; j++) {
				dp[i][j] = -1;
			}
		}

        //사용한 카드의 개수 0개, 1번 마을부터 탐색한 결과 출력
		System.out.print(dfs(0, 1));
	}

	static int getRGB(char rgb) {
		switch (rgb) {
			case 'R': return 1;
			case 'G': return 2;
			case 'B': return 3;
			default: return 0;
		}
	}

	static int dfs(int idx, int node) {
        //n장을 다 썼으면 더 이상 갈 수 없음
		if (idx == n) {
			return 0;
		}

        //이미 계산했던 값이면 그대로 반환
		if (dp[idx][node] != -1) {
			return dp[idx][node];
		}

		int res = 0;
        //다음에 갈 수 있는 마을에 대해서
		for (int[] nItem : graph.get(node)) {
            //지금 가지고 있는 카드와 길의 RGB가 같다면 10점, 아니면 0점
			int adder = nItem[1] == cards[idx] ? 10 : 0;

            //다음 마을로 넘어가서 계산한 값을 res에 업데이트
			res = Math.max(res, dfs(idx + 1, nItem[0]) + adder);
		}

        //계산이 끝났으므로 저장하고 반환 
		return dp[idx][node] = res;
	}

	static int nextInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}

	static String nextString() throws IOException {
		st.nextToken();
		return st.sval;
	}
}

/* DP로 푸는 방법 - DFS와는 다르게 결과에 필요하지 않은 계산들이 들어가서 시간이 더 오래 걸림
//제출번호: 92559510
//메모리:   35688 KB
//실행시간: 448 ms
import java.io.*;
import java.util.*;

public class Main {

	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

	public static void main(String[] args) throws IOException {
		int n = nextInt();

		int[] cards = new int[n];
		for (int i = 0; i < n; i++) {
			cards[i] = getRGB(nextString().charAt(0));
		}

		int m = nextInt();
		int k = nextInt();

		List<List<int[]>> graph = new ArrayList<>();
		for (int i = -1; i < m; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < k; i++) {
			int a = nextInt();
			int b = nextInt();
			int rgb = getRGB(nextString().charAt(0));

			graph.get(a).add(new int[]{b, rgb});
			graph.get(b).add(new int[]{a, rgb});
		}

		int[][] dp = new int[n + 1][m + 1];

        //DFS에서 했던 일을 그대로 계산함
        //이 때 DFS는 들어갔다가 계산하면서 나오는데
        //DP는 들어가는 로직을 버리고 계산하면서 나오는 로직만 살아있다고 보면 편함 
		for (int idx = n - 1; idx >= 0; idx--) {
			for (int node = m; node > 0; node--) {
				for (int[] item : graph.get(node)) {
					int adder = item[1] == cards[idx] ? 10 : 0;
					int nNode = item[0];

					dp[idx][nNode] = Math.max(dp[idx][nNode], dp[idx + 1][node] + adder);
				}
			}
		}

		System.out.print(dp[0][1]);
	}

	static int getRGB(char rgb) {
		switch (rgb) {
			case 'R': return 1;
			case 'G': return 2;
			case 'B': return 3;
			default: return 0;
		}
	}

	static int nextInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}

	static String nextString() throws IOException {
		st.nextToken();
		return st.sval;
	}
}
 */

