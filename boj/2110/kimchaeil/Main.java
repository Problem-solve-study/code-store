//문제: BOJ 2110번 공유기 설치
//메모리: 16356 KB
//시간: 200 ms

/*
 * 거리를 이용해 매개 변수 탐색을 하였다.
 * 거리를 넘겨주면 해당 거리로 최대로 설치 할 수 있는 공유기의 개수를 반환하는 함수를 사용
 */

import java.io.*;
import java.util.Arrays;

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	static int n,c;
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		n = nextInt();
		c = nextInt();
		arr = new int[n];
		for(int i=0;i<n;i++) 
			arr[i]=nextInt();
		Arrays.sort(arr);
		
		int left = 1;
		int right = arr[n-1]-arr[0];
		while(left<=right) {
			int mid = (right+left)/2;
			if(calMax(mid)>=c) {
				left = mid+1;
			}else {
				right = mid-1;
			}
		}
		System.out.println(right);
	}
	static int calMax(int distance) {
		int cnt = 1;
		int prev = arr[0];
		for(int i=1;i<n;i++) {
			if(arr[i]-prev>=distance) {
				prev = arr[i];
				cnt++;
			}
		}
		return cnt;
	}
	static int nextInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
}
