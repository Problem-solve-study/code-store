import java.io.*;
import java.math.BigInteger;
import java.util.*;

/*
 * 59316KB, 184ms
 * 
 * 모든 자리가 1로 이루어져 있으므로 두 수의 최대공약수만큼 1로 이루어져 있는 숫자가 최대 공약수가 된다.
 * GCD는 유클리드 호제법으로 구할 수도 있지만 BigInteger에는 GCD를 구하는 메소드가 있다. 그걸 사용
 */

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BigInteger a = new BigInteger(st.nextToken());
		BigInteger b = new BigInteger(st.nextToken());
		StringBuilder sb = new StringBuilder();
		int len = a.gcd(b).intValue();
		for (int i = 0; i < len; i++) {
			sb.append('1');
		}
		System.out.print(sb);
	}
}