//문제: BOJ 1081번 합
//메모리: 11424 KB
//시간: 64 ms

/*
 * 자리마다 어떤 숫자가 몇번 나오는지 계산하여 구한다
 */

import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		int frontl = nextInt(), nowl = 0, backl = 0;
		// frontl: L의 현재 보고있는 자리 앞부분, nowl: L의 현재 보고있는 자리수, backl: L의 현재 보고있는 자리 뒷부분
		int frontr = nextInt(), nowr = 0, backr = 0;
		// frontr: R의 현재 보고있는 자리 앞부분, nowr: R의 현재 보고있는 자리수, backr: R의 현재 보고있는 자리 뒷부분
		long result = 0, temp = 1, digit = 0;
		// digit: 현재 보고 있는 자리
		while (frontr + nowr != 0) {
			// L과 R의 보고 있는 자리를 앞으로
			backl = (int) (backl + digit * nowl);
			backr = (int) (backr + digit * nowr);
			nowl = frontl % 10;
			nowr = frontr % 10;
			frontl /= 10;
			frontr /= 10;
			temp = (digit = temp) * 10;

			if (nowl == nowr && frontl == frontr) { // 앞부분과 현재 자리가 모두 같다면
				result += (backr - backl + 1) * nowl; // 현재 자리가 나오는 횟수 = R 뒷부분 - L 뒷부분 + 1
			} else { // 앞부분과 현재자리 중 하나라도 다르다면
				result += (digit - backl) * nowl; // L의 현재 자리수가 front가 바뀌지 않는 동안은 (digit - L의 뒷부분)만큼 나옴
				result += (backr + 1) * nowr; // R의 현재 자리수가 front가 바뀌지 않는 동안은 (R의 뒷부분 + 1)만큼 나옴
				if (frontl == frontr) { // 만약 앞부분만 같다면
					for (int i = nowl + 1; i < nowr; i++) // L의 현재 자리수보다 크고, R의 현재 자리수보다 작은 수들이 digit만큼 나옴
						result += digit * i;
				} else { // 앞부분이 다르다면(현재 자리는 같아도 됨)
					result += digit * (45 - ((nowl * nowl + nowl) >> 1)); // (L의 현재 자리수 + 1) ~ 9가 digit만큼 나옴
					result += digit * ((nowr * nowr - nowr) >> 1); // 1 ~ (R의 현재 자리수 - 1)가 digit만큼 나옴
					result += digit * 45 * (frontr - frontl - 1); // 1 ~ 9가 (R의 앞부분 - L의 앞부분 - 1) * digit만큼 나옴
				}
			}
		}
		System.out.println(result);
	}

	static int nextInt() throws IOException {
		int c, n;
		while ((c = System.in.read()) < 48)
			;
		n = c & 15;
		while ((c = System.in.read()) > 47)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}
}
