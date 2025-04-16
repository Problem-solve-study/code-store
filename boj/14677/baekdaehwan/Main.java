/**
 * 13880KB	68ms
 * 실행시간, 숏코딩 둘 다 1등 가져갑니다
 * 
 * [사고흐름]
 * 뭐야, 단순 DP네?
 * -> 입력 잘못받아서 2틀
 * 
 * [알고리즘 설명]
 * DP[left][right][curTime] = 남은 알약이 left~right 구간일 때, curTime의 알약을 먹어야 한다면 지금부터 몇 번 더 먹을 수 있는가.
 * 탑다운은 대부분의 상황에서 옳습니다.
 */


import java.io.*;

public class Main {
	static int N;
	static int[] A;
	static boolean[][] visited;
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine())*3;
		A = new int[N];
		visited = new boolean[N][N];
		char[] c = br.readLine().toCharArray();
		for (int i=0; i<N; ++i) A[i] = c[i]=='B'? 0:(c[i]=='L'? 1:2);
		System.out.println(bt(0, N-1, 0));
    }
	static int bt(int l, int r, int c) {
		if (l>r || visited[l][r]) return 0;
		visited[l][r] = true;
		int max = 0;
		if (A[l] == c) max = Math.max(max, bt(l+1, r, (c+1)%3)+1);
		if (A[r] == c) max = Math.max(max, bt(l, r-1, (c+1)%3)+1);
		return max;
	}
}
