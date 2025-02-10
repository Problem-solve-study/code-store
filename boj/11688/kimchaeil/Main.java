//메모리: 12344 KB
//시간: 92 ms
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		long L = Long.parseLong(st.nextToken());


		long lcm_a_b = a / gcd(a, b) * b; //a와 b의 최소공배수

		long result = 0;
		if (L % lcm_a_b == 0) {
			ArrayList<Long> divisors = new ArrayList<>();
			get_divisors(L, divisors); //L의 약수들 구하기
			long k = L/lcm_a_b; //L을 a와 b의 최소공배수로 나눈 몫
			for (long divisor : divisors) { 
				if (divisor == k * gcd(divisor, lcm_a_b)) { //divisor과 lcm_a_b의 최소공배수가 divisor과 같다면 현재 보고있는 약수가 c
					result = divisor;
					break;
				}
			}
		} else { //L을 a와 b의 최소공배수로 나누어 떨어지지 않으면 가능한 c가 없음
			result = -1;
		}
		System.out.println(result);

	}

	public static long gcd(long x, long y) { // 유클리드 호제법 (x와 y의 최대공약수 구하는 알고리즘)
		if (y == 0)
			return x;
		return gcd(y, x % y);
	}

	public static void get_divisors(long n, ArrayList<Long> divisors) { // n의 약수를 구해 divisors에 추가하고 오름차순으로 정렬
		for (long i = 1; i < Math.sqrt(n) + 1; i++) {
			if (n % i == 0) {
				divisors.add(i);
				divisors.add(n / i);
			}
		}
		divisors.sort(null);
	}
}