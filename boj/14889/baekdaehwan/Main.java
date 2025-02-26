/**
 * 36092KB	208ms
 * 
 * SWEA 요리사와 동일한 문제
 * 
 * [사고 흐름]
 * 웰노운에 속하는 문제이기 때문에 처음부터 브루트포스로 접근했음
 * 
 * [알고리즘 설명]
 * N/2 명을 뽑는 것을 목표로 하여, 백트래킹을 사용함
 */

import java.io.*;
import java.util.*;


public class Main {
	static int N, M;
	static int[] A;
	static int[][] B;
	static boolean[] V;
	static int min;
	static ArrayDeque<Integer> ad = new ArrayDeque<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		A = new int[N];
		B = new int[N][N];
		V = new boolean[N];
		for (int i=0; i<N; ++i) A[i] = i;
		for (int i=0; i<N; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; ++j) {
				B[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		min = Integer.MAX_VALUE;
		M = N>>1;
		bt(0, M);
		
		System.out.println(min);
	}

	public static void bt(int idx, int r) {
		if (idx == N) {
			if (r == 0) {
				int a = 0;
				int b = 0;
				int[] x = new int[M];
				int[] y = new int[M];
				
				int xi = 0;
				int yi = 0;
				for (int i=0; i<N; ++i) {
					if (V[i]) {
						x[xi++] = A[i];
					}
					else {
						y[yi++] = A[i];
					}
				}
				for (int i=0; i<M; ++i) {
					for (int j=0; j<M; ++j) {
						a += B[x[i]][x[j]];
						b += B[y[i]][y[j]];
					}
				}
				min = Math.min(min, Math.abs(a-b));
			}
			return;
		}

		V[idx] = true;
		bt(idx+1, r-1);
		V[idx] = false;
		bt(idx+1, r);
	}
}
