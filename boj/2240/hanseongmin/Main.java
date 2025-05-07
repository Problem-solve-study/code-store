import java.io.*;
import java.util.*;

/*
 * 12600KB, 72ms
 * 
 * 처음엔 1의 구간과 2의 구간을 적절히 입력받아서 처리하는 그리디 문제로 생각했다.
 * 근데 이렇게 생각하니까 고려해야할 이런저런 케이스가 꽤나 있어보였고 결국 완탐스럽게 봐야하는거 같아 DP로 선회. 
 * dp[w][t]: w만큼 움직이고 t초일 때 최대 자두의 개수라고 테이블을 설계하고 적절히 테이블을 갱신했다.
 * 
 * 본래의 풀이는 3차원인것 같은데 그냥 2차원이 먼저 생각나서 2차원으로 풀었음.
 */

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	
	public static void main(String[] args) throws Exception {
		int T = nextInt(); int W = nextInt();
		int[] arr = new int[T + 1];
		for (int i = 1; i <= T; i++) {
			arr[i] = nextInt();
		}
		
		int max = -1;
		//테이블 갱신을 편하게 하기 위해 w는 + 2로 잡았다. (w - 1)이 움직인 횟수로 정의
		int[][] dp = new int[W + 2][T + 1];
		for (int w = 1; w <= W + 1; w++) {
			for (int t = 1; t <= T; t++) {
				//w의 나머지가 1이면 현재 위치가 1인 것, w의 나머지가 0이면 현재 위치가 2인 것
				//현재 위치에 해당하는 자두 나무의 자두가 떨어지면 받을 수 있는 자두의 개수가 +1
				//dp[w][t]에서의 최댓값은 현재 위치에서 대기하다가 떨어지는 자두를 받는 경우와
				//현재 위치의 자두가 떨어지기 직전까지 반대쪽 나무에 있다가 현재 자두나무로 이동해서 받는 경우 중 최댓값으로 생각할 수 있음
				if ((w % 2 == 1 && arr[t] == 1) || (w % 2 == 0 && arr[t] == 2)) {
					dp[w][t] = Math.max(dp[w][t - 1], dp[w - 1][t]) + 1;
					max = Math.max(max, dp[w][t]);
				} else {
					//현재 자두를 못받는 경우이라면 이전 테이블의 값을 그대로 들고옴
					dp[w][t] = Math.max(dp[w][t - 1], dp[w - 1][t]);
				}
			}
		}
		System.out.print(max);
	}
	
	static int nextInt() throws Exception {
		st.nextToken();
		return (int) st.nval;
	}
}