//문제: 1490번 자리수로 나누기
//메모리: 11524 KB
//시간: 64 ms

/*
 * 자리수는 10 미만의 수이므로 길이가 10인 boolean 배열을 만들고 확인해야 하는 수를 true로 만든다.
 * 
 * 만약 어떤 자리수의 배수가 확인해야 하는 수라면 굳이 확인할 필요가 없다.
 * 예를 들어 24라는 수가 n으로 들어왔을때, 2의 배수인 4가 있기 때문에 2는 확인할 필요가 없다.
 * +)위 부분을 하지 않는 코드도 제출해보았으나 시간에 차이가 없었다.
 * 
 * 일단 n이 문제의 조건에 맞는지 확인한다.
 * n이 조건에 맞지 않다면 조건에 맞는 수를 찾아내는 과정을 수행한다.
 * 이 과정은 아래 코드에서 주석으로 설명하겠다.
 */

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		int n = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
		long result = n;

		boolean[] check = new boolean[10];
		while (n != 0) { // 확인해야 하는 수 체크
			int k = n % 10;
			if (k != 0)
				check[k] = true;
			n /= 10;
		}

//		for (int i = 1; i < 5; i++) // true인 배수가 있으면 false로
//			for (int j = i * 2; j < 10; j += i)
//				if (check[j])
//					check[i] = false;
//		// 위 부분을 하지 않아도 시간에 차이가 없었으므로 굳이 넣을 필요는 없을 듯하다.

		boolean canDivide = true;
		for (int i = 1; i < 10; i++) { // 입력받은 수가 이미 조건에 만족하는지 확인
			if (check[i] && result % i != 0) { // 조건에 만족 못하면 canDivide를 false로 만들고 break
				canDivide = false;
				break;
			}
		}
		int cnt = 0; // 10 곱한 횟수
		while (!canDivide) { // 조건에 만족할 때까지
			result *= 10; // 10 곱하기
			cnt++; // 10 곱한 횟수 증가
			for (int i = 0; i < Math.pow(10, cnt); i++) { // 0 <= i < 10^cnt 범위 체크
				canDivide = true; // 확인할때 마다 canDive를 true로 만들어 놓기
				long temp = result + i; // result는 10을 곱하는 과정에만 사용하고 조건을 만족하는 수인지 확인할 때는 temp를 사용, 그래야 n뒤에 수를 붙이는 작업을 하기
										// 편하다
				for (int j = 1; j < 10; j++) {
					if (check[j] && temp % j != 0) { // 조건에 만족 못하면 canDivide를 false로 만들고 break;
						canDivide = false;
						break;
					}
				}
				if (canDivide) { // 조건에 만족하면 result에 temp를 넣고 break;
					result = temp;
					break;
				}
			}
		}

		System.out.println(result);
	}
}