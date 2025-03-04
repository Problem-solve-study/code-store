//문제: BOJ 2798번 블랙잭
//메모리: 11528 KB
//시간: 68 ms

/*
 * 카드들 중 3개를 뽑는 문제이므로 길이가 3으로 정해진 조합 문제
 * 2개 이하를 골랐는데 이미 m을 넘어버리면 지금까지 고른 카드들은 고를 수 없음(백트래킹)
 * 즉, 이 문제는 조합+백트래킹
 */

import java.io.*;

public class Main {
	static StreamTokenizer st;
	static int n, m;
	static int[] cards;
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StreamTokenizer(br);

		n = nextInt();
		m = nextInt();
		cards = new int[n];
		for (int i = 0; i < n; i++) {
			cards[i] = nextInt();
		}
		backTracking(0, 0, 0);
		System.out.println(result);
	}

	static void backTracking(int depth, int start, int sum) {
		if (result == m || sum > m) //result가 m인 경우 다른 경우를 확인할 필요 X, sum이 m보다 크면 문제 조건에 벗어남
			return;
		if (depth == 3) { //3개를 고르면 최대값 비교
			result = result < sum ? sum : result;
			return;
		}
		for (int i = start; i < n; i++) { //조합
			backTracking(depth + 1, i + 1, sum + cards[i]);
		}
		return;
	}

	static int nextInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
}
