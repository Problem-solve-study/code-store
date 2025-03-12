//문제: BOJ 25391번 특별상
//메모리: 36652 KB
//시간: 1276 ms

/*
 * 주최자가 어떤 학생들을 고르든 심판 점수 기준 상위 K명은 상을 받게 된다.
 * 그렇다면 심판 점수 기준 상위 K명에게 본상을 받게 하고
 * 남은 인원 중에서 주최자 점수 기준 상위 M명에게 특별상을 주면
 * 주최자 점수 합의 최댓값이 나온다.
 */

import java.io.*;
import java.util.Arrays;

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

	public static void main(String[] args) throws IOException {
		int n = nextInt(), m = nextInt(), k = nextInt();
		int[][] arr = new int[n][2];
		long sum = 0;
		for(int i=0;i<n;i++) {
			arr[i][0]=nextInt();
			arr[i][1]=nextInt();
		}
		Arrays.sort(arr,(a,b)->-(a[1]-b[1])); //심판 점수 기준 내림차순 정렬
		for(int i=0;i<k;i++) { //K명 본상 수상, 본상을 수상한 학생들은 특별상을 수상하지 못하게 하기 위해 주최자 점수를 -1로
			sum+=arr[i][0];
			arr[i][0]=-1;
		}
		Arrays.sort(arr,(a,b)->-(a[0]-b[0])); //주최자 점수 기준 내림차순 정렬, 본상 수상자들은 주최자 점수가 -1이 되었으므로 상위 m명에 포함되지 않음
		for(int i=0;i<m;i++) { //M명 특별상 수상
			sum+=arr[i][0];
		}
		System.out.println(sum);
	}

	static int nextInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
}
