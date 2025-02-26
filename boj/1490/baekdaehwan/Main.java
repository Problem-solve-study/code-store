/**
 * 13140KB	76ms
 * 
 * LCM 구하는 방법 계속 실수해서 5트만에 성공했습니다. 나가 죽겠습니다 ㅜㅜ...
 * 
 * [사고 흐름]
 * 수학으로 풀려고 했으나 경우의 수가 크지 않다는 힌트를 주셔서 브루트포스로 접근함.
 * 
 * [알고리즘 설명]
 * 전체 자릿수의 LCM을 구하고 그 값으로 나누어 떨어지는 값을 전수탐색
 */

import java.io.*;
import java.util.*;


public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long N = Long.parseLong(br.readLine());
		
		long Z = N;
		long LCM = 1;
		while (Z>0) {
			long a = Z%10;
			if (a!=0) LCM = lcm(LCM, a); 
			Z/=10;
		}
		LCM = Math.max(LCM, 1);
		
		for (int i=0; i<6; ++i) {
			long K = (long) Math.pow(10, i);
			long X = N * K;
			for (int j=0; j<K; ++j) {
				long target = X+j;
				if (target % LCM == 0) {
					System.out.println(target);
					System.exit(0);
				}				
			}
		}
			
	}
	
	public static long lcm(long a, long b) {
		return a*b/gcd(a, b);
	}
	
	public static long gcd(long a, long b) {
		if (b>a) {
			long tmp=a; a=b; b=tmp;
		}
		while (b!=0) {
			long tmp=b; b=a%b; a=tmp;
		}
		return a;
	}
}
