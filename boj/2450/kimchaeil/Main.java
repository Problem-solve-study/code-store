//문제: BOJ 2450번 모양 정돈
//메모리: 12332 KB
//시간: 92 ms

/* 정렬 후 모양의 순서는
 * { 1, 2, 3 }, { 1, 3, 2 }, { 2, 1, 3 }, { 2, 3, 1 }, { 3, 1, 2 }, { 3, 2, 1 }	
 * 6가지이다
 * 
 * 6가지 순서에 대해 모두 정렬을 했을 때, 최소값을 구하면 된다
 * 
 * 처음 코드는 실제로 정렬을 하여 1500 ms 이상의 시간이 걸렸다
 * 그래서 실제로 정렬을 하지 않고 구하는 방법을 생각했다
 * 
 * 첫 번째 구간에서 (첫 번째 구간의 수가 아닌 수)가 나오면 해당 숫자는 무조건 스왑된다
 * 두 번째 구간에서 (첫 번째 구간의 수)가 나왔다면
 * 앞 과정에서 스왑되었으면 넘어가고
 * 앞 과정에서 스왑되지 않았다면 스왑
 * 두 번째 구간에서 (세 번째 구간의 수)가 나왔다면 스왑
 */

import java.io.*;

public class Main {
	public static void main(String args[]) throws Exception {

		int result = Integer.MAX_VALUE;
		int[][] perms = { { 1, 2, 3 }, { 1, 3, 2 }, { 2, 1, 3 }, { 2, 3, 1 }, { 3, 1, 2 }, { 3, 2, 1 } }; //순서 하드 코딩
		int[] shapeCnt = new int[4];

		int n = nextInt();
		int[] arr = new int[n];

		for (int i = 0; i < n; i++)
			shapeCnt[arr[i] = nextInt()]++; //수열 입력 받으면서 모양 카운트

		int end1, end2;
		for (int[] perm : perms) { // 각 순서에 대해
			int[] changeCnt = new int[4];
			end2 = (end1 = shapeCnt[perm[0]]) + shapeCnt[perm[1]]; //end1은 첫 구간의 끝, end2는 두번째 구간의 끝

			int cnt = 0;
			for (int i = 0; i < end1; i++) { //첫 번째 구간에서
				if (arr[i] != perm[0]) { //첫 번째 구간의 값이 아닌 수가 나오면
					changeCnt[arr[i]]++; //그 수를 스왑했다고 카운트
					cnt++; //스왑 카운트
				}
			}
			for (int i = end1; i < end2; i++) //두 번째 구간에서
				if ((arr[i] == perm[0] && changeCnt[perm[1]]-- <= 0) || arr[i] == perm[2]) //첫 번째 구간의 수가 나왔는데 첫 번째 구간에서 스왑되지 않았거나 세 번째 구간의 수가 나왔다면
					cnt++; //스왑 카운트

			result = result > cnt ? cnt : result;
		}
		System.out.println(result);
	}

	static int nextInt() throws IOException {
		int c, n;
		while ((c = System.in.read()) < 48);
		n = c & 15;
		while ((c = System.in.read()) > 47)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}
}
