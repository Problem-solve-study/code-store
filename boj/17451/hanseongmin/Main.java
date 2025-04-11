import java.io.*;
import java.util.*;

/*
 * 18168KB, 232ms
 * 
 * 맨 마지막 행성은 항상 주어진 속도까지 줄일 수 있을 것이므로
 * 마지막 행성의 속도를 주어진 속도로 맞춰버린 다음 역순으로 행성을 탐사하며
 * 현재 속도보다 크면서 다음 행성의 최소 배수값으로 속도를 늘려버린다.
 * 
 * 1번째 행성까지 도달하면 결국 지구에서 출발해야하는 속도의 최솟값이 나옴
 * 
 * 오버플로우로 1틀
 * 조건 검사할 때 등호 잘못 붙여서 2틀
 */

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	
	public static void main(String[] args) throws Exception {
		int n = nextInt();
		long[] v = new long[n];
		for (int i = 0; i < n; i++) {
			v[i] = nextInt();
		}
		
		//처음 속도는 맨 마지막 행성의 속도로 고정
		long value = v[n - 1];
		for (int i = n - 2; i >= 0; i--) {
			//이후 다음 속도는 현재 속도보다 크면서 다음 행성의 요구 속도의 최소의 배수여야 함
			value = findValue(value, v[i]);
		}
		System.out.print(value);
	}
	
	static long findValue(long n, long base) {
		long res = (n / base) * base;
		return res >= n ? res : res + base;
	}

	static int nextInt() throws Exception {
		st.nextToken();
		return (int) st.nval;
	}
}
