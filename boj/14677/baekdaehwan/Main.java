/**
 * 91136KB	188ms
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
import java.util.*;

public class Main {
	static int N;
	static int[] A; 
	static int[][][] DP;

	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine())*3;
		A = new int[N];
		char[] c = br.readLine().toCharArray();
		for (int i=0; i<N; ++i) A[i] = c[i]=='B'? 0:(c[i]=='L'? 1:2);
		DP = new int[N][N][3];
		for (int i=0; i<N; ++i) {
			for (int j=0; j<N; ++j) Arrays.fill(DP[i][j], -1);
		}
		
		System.out.println(bt(0, N-1, 0));
    }
	
	static int bt(int l, int r, int c) {
		if (l>r) return 0;
		if (DP[l][r][c] == -1) {
			DP[l][r][c] = 0;
			if (A[l] == c) DP[l][r][c] = Math.max(DP[l][r][c], bt(l+1, r, (c+1)%3)+1);
			if (A[r] == c) DP[l][r][c] = Math.max(DP[l][r][c], bt(l, r-1, (c+1)%3)+1);
		}
		return DP[l][r][c];
	}
}
