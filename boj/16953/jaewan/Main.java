/*
 * 가능한 연산은 두가지다.
 *  - 곱하기 2
 *  - '1'을 수의 가장 오른쪽에 추가.
 *  
 *  연산의 특성상, B가 1이 아닌 홀수라면 만들기 불가.
 *  짝수면, 곱하기 2 연산을 해서 만드므로, 2로 나누었을때, 1의 자리가 '1'의 아니라면 불가.
 *  
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int A = Integer.parseInt(input[0]);
		int B = Integer.parseInt(input[1]);

		int cnt = 0;
		while (A != B) {
			if (B < A) {
				cnt = -2;
				break;
			}
			if (B % 2 == 0) {
				B /= 2;
				cnt++;
				continue;
			} else {
				if (B % 10 != 1) { // 1의 자리가 1이 아닌 홀수면, 만들기 불가
					cnt = -2;
					break;
				} else if (B % 10 == 1) {
					B /= 10;
					cnt++;
				}
			}
		}
		System.out.println(cnt + 1);
	}
}