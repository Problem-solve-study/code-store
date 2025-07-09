//문제: BOJ 22869번 징검다리 건너기 (small)
//시간: 108 ms
//메모리: 12608 KB

/*
 * 출발 지점에서부터 도달가능한 지점만이 경유지가 될 수 있다.
 */

public class Main {

	public static void main(String[] args) throws Exception {
		int n = nextInt(), k = nextInt();
		int[] arr = new int[n];
		boolean[] dp = new boolean[n]; //도달 가능한가
		dp[0] = true; //시작점은 항상 도달 가능

		for (int i = 0; i < n; i++) {
			arr[i] = nextInt(); // i번째 지점 입력
			for (int j = i - 1, dis = 1; j >= 0; j--, dis++) { //i 보다 작고 0 이상의 지점 j에 대하여
				if (dp[j] && (dp[i] = (dis * (1 + Math.abs(arr[i] - arr[j])) <= k))) //j가 도달 가능하며 j->i에 요구되는 힘이 k 보다 크지 않다면
					break; //i지점 도달 가능함이 판별
			}
		}

		System.out.println(dp[n - 1] ? "YES" : "NO"); //마지막 지점에 도달 가능한지 출력
	}

	static int nextInt() throws Exception {
		int n, c;
		while ((c = System.in.read()) < 48)
			;
		n = c & 15;
		while ((c = System.in.read()) > 47)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}
}
