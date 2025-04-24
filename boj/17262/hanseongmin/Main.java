import java.io.*;
import java.util.*;

/*
 * 15684KB, 160ms
 * start의 최댓값과 end의 최솟값을 빼면 반드시 모든 사람을 만날 수 있다.
 * 처음엔 정렬로 했다가 700ms 나와서 입력받으며 최소, 최대를 갱신하는 것으로 변경
 */

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	
	public static void main(String[] args) throws Exception {
		int N = nextInt();
		int sMax = 0;
		int eMin = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			sMax = Math.max(sMax, nextInt());
			eMin = Math.min(eMin, nextInt());
		}
		
		System.out.print(Math.max(sMax - eMin, 0));
	}
	
	static int nextInt() throws Exception {
		st.nextToken();
		return (int) st.nval;
	}
}