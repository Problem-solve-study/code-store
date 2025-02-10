//메모리: 11664 KB
//시간: 68 ms

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		long L = Long.parseLong(st.nextToken());

		long lcm_a_b = a / gcd(a, b) * b; // a와 b의 최소공배수
		long result;

		long temp = L;
		if (temp % lcm_a_b == 0) { // temp가 lcm_a_b로 나누어떨어지면
			Map<Long, Integer> L_PF = new HashMap<>(); // L의 소인수분해 결과를 저장하기위한 해쉬맵 (key는 밑, value는 지수)
			for (long i = 2; temp != 1;) { // L 소인수분해
				if (!isPrime(i) || temp % i != 0) { // i가 소수가 아니거나 temp가 i로 나누어떨어지지 않으면
					if (i > 2) {
						i += 2;
					} // 2 이상이면 2씩 증가(홀수만 하면 되니까)
					else {
						i++;
					} // 2라면 1 증가
				} else { // i가 소수이며 temp가 i로 나누어떨어지면
					L_PF.put(i, L_PF.getOrDefault(i, 0) + 1); // i의 지수를 1 증가
					temp /= i; // temp를 i로 나눈 몫으로 초기화
				}
			}

			long k = L / lcm_a_b; // k는 L을 lcm_a_b로 나눈 몫
			Set<Long> k_PF = new HashSet<>(); // k의 소인수분해 결과를 저장하기위한 해쉬세트 (밑만 필요하므로 Set으로)
			for (long i = 2; k != 1;) { // 밑만 저장하는 것을 제외하면 위와 거의 동일
				if (!isPrime(i) || k % i != 0) {
					if (i > 2) {
						i += 2;
					} else {
						i++;
					}
				} else {
					k_PF.add(i);
					while (k % i == 0) { // 밑만 필요하기에 i로 나누어떨어지지 않을 때까지 i로 나눈 몫으로 초기화
						k /= i;
					}
				}
			}

			result = 1; //아래에서 곱을 저장할 것이므로 1로 초기화
			for (long p : k_PF) { // k의 소인수분해 결과의 밑들에 대해
				result *= Math.pow(p, L_PF.get(p)); // L의 소인수분해 결과에서 지수를 가져와 제곱후 result에 곱하기
			}
		} else { // temp가 lcm_a_b로 나누어떨어지지 않으면 c 없음
			result = -1;
		}
		System.out.println(result);
	}

	public static long gcd(long x, long y) { // 유클리드 호제법 (x와 y의 최대공약수 구하는 알고리즘)
		if (y == 0)
			return x;
		return gcd(y, x % y);
	}

	public static boolean isPrime(long n) { // 소수 판별
		if (n == 2) { // 2면 true
			return true;
		}
		if (n % 2 == 0) { // 2의 배수면 false
			return false;
		}
		for (int i = 3; i < Math.sqrt(n) + 1; i += 2) { // n의 제곱근 이하의 홀수만 확인
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
}