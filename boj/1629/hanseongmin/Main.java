import java.io.*;
import java.util.*;

/*
 * 11524KB, 68ms
 * 
 * 단순히 곱하면 나올 수 있는 수가 21억이므로 당연히 시간초과
 * a를 b번 곱한다는 뜻은 a를 b제곱한다는 뜻이니 분할정복을 이용한 거듭제곱 사용
 * 
 * 오버플로우 고려 안했다가 1틀
 * 기저조건 때 모듈러 안했다가 2틀
 */

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	
	public static void main(String[] args) throws Exception {
		long A, B, C;
		A = nextInt(); B = nextInt(); C = nextInt();
		System.out.print(pow(A, B, C));
	}
	
	static long pow(long a, long b, long c) {
		if (b == 1) {
			return a % c;
		}
		
		long temp = pow(a, b / 2, c);
		if (b % 2 == 0) {
			return (temp % c * temp % c) % c;
		} else {
			return (temp % c * temp % c * a % c) % c;
		}
	}
	
	static int nextInt() throws Exception {
		st.nextToken();
		return (int) st.nval;
	}
}