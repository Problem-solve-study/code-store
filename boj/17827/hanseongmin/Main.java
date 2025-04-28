import java.io.*;
import java.util.*;

/*
 * 31704KB 372ms
 * 
 * 사이클 이전에는 그냥 바로 구해버리고
 * 사이클에 들어온 이후에는 적절히 사이클의 길이를 이용하여 모듈러로 갈 위치 바로 구하기
 */

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	
	public static void main(String[] args) throws Exception {
		int N = nextInt();
		int m = nextInt();
		int v = nextInt();
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = nextInt();
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m; i++) {
			int q = nextInt();
			//사이클에 진입하기 전이라면 그냥 그 위치로 바로 가면 됨
			if (q <= v) {
				sb.append(arr[q]);
			} else {
				//사이클에 진입한 이후라면 적절히 위치를 계산
				int len = N - v + 1;
				int start = v - 1;
				sb.append(arr[start + (q - start) % len]);
			}
			sb.append('\n');
		}
		System.out.print(sb);
	}
	
	static int nextInt() throws Exception {
		st.nextToken();
		return (int) st.nval;
	}
}