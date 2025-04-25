/**
 * 17188KB	808ms
 * 공차의 후보군을 뽑고, 피봇을 정해서 모든 후보군을 넣어서 전수탐색
 */

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] A;
	static Set<Integer> D;
	
	static StreamTokenizer st = new StreamTokenizer(new InputStreamReader(System.in));
	public static void main(String[] args) throws Exception {
		N = ni();
		A = new int[N];
		D = new HashSet<>();
		for (int i=0; i<N; ++i) A[i] = ni();
		for (int i=0; i<N; ++i) {
			for (int j=i+1; j<N; ++j) {
                // 공차의 후보군 뽑기
				if ((A[i]-A[j])%(j-i) == 0) D.add((A[j]-A[i])/(j-i));
			}
		}
		int res = Integer.MAX_VALUE;
		for (int d: D) {
            // 피봇과 공차로 바꿔야 하는 카드 개수 찾기 (피봇은 숫자를 고정)
			for (int p=0; p<N; ++p) res = Math.min(res, solve(p, d));
		}
		System.out.println(res);
	}
	
	static int solve(int p, int d) {
		int cnt = 0;
		for (int i=0; i<N; ++i) {
            // 피봇 기준으로 공차에 따라 예상되는 i번째 수와 실제 수를 비교
			if (A[p]+d*(i-p) != A[i]) ++cnt;
		}
		return cnt;
	}
	
	static int ni() throws Exception {
		st.nextToken();
		return (int) st.nval;
	}
}