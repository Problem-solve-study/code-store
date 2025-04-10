import java.io.*;
import java.util.*;

/*
 * 85632KB, 3172ms
 * 
 * 0행, 0열이 아닌 애벌레는 자신과 인접한 최댓값으로 퍼지듯이 갱신될텐데
 * 그렇다면 값에 영향을 주는 요소는 0행 0열의 애벌레밖에 없을 것이고 따라서 N일동안 0행 0열만 갱신해주고
 * 나머지 애벌레는 마지막에 갱신시키면 되겠다고 생각했다.
 * 
 * 1의 개수와 2의 개수가 둘 다 0일 때 잘못 작동하도록 짜서 2번 틀림
 */

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	
	public static void main(String[] args) throws Exception {
		int M = nextInt(); int N = nextInt();
		int[][] map = new int[M][M];
		//먼저 1로 갱신시킴 (지금 생각해보니 그냥 0행 0열만 1로 초기화시켰어도 무방했을듯) 
		for (int i = 0; i < M; i++) {
			Arrays.fill(map[i], 1);
		}
		
		//0행 0열 애벌레 크기 증가
		ArrayDeque<Integer> s = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			s.addLast(nextInt());
			s.addLast(nextInt());
			s.addLast(nextInt());
			
			int cnt = s.removeLast();
			int v = 2;
			for (int j = M - 1; j >= 0; j--) {
				if (v == 0) break;
				while (cnt == 0) {
					if (!s.isEmpty()) {
						cnt = s.removeLast();
						v--;
					}
				}
				
				map[0][j] += v;
				cnt--;
			}
			
			for (int j = 1; j < M; j++) {
				if (v == 0) break;
				while (cnt == 0) {
					if (!s.isEmpty()) {
						cnt = s.removeLast();
						v--;
					}
				}
				
				map[j][0] += v;
				cnt--;
			}
		}
		
		//0행 0열이 아닌 애벌레 값 갱신
		for (int i = 1; i < M; i++) {
			for (int j = 1; j < M; j++) {
				map[i][j] = Math.max(map[i - 1][j - 1], Math.max(map[i - 1][j], map[i][j - 1]));
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(map[i][j]).append(' ');
			}
			sb.append('\n');
		}
		System.out.print(sb);
	}

	static int nextInt() throws Exception {
		st.nextToken();
		return (int) st.nval;
	}
}
