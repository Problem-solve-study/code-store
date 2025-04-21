//문제: BOJ 1081번 합
//메모리: 11424 KB
//시간: 64 ms

/*
 * 자리마다 어떤 숫자가 몇번 나오는지 계산하여 구한다
 */

import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		int l = nextInt(), r = nextInt();
		int frontl = l, nowl = 0, backl = 0;
		int frontr = r, nowr = 0, backr = 0;
		long result = 0, temp = 1, digit = 0;
		while (backr != r) {
			backl = (int) (backl + nowl * digit);
			backr = (int) (backr + nowr * digit);
			nowl = frontl % 10;
			nowr = frontr % 10;
			frontl /= 10;
			frontr /= 10;
			temp *= 10;
			digit = temp / 10;
			if (nowl == nowr && frontl == frontr) {
				result += nowl * (backr - backl + 1);
			} else {
				result += nowl * (digit - backl);
				result += nowr * (backr + 1);
				if (frontl == frontr) {
					for (int i = nowl + 1; i < nowr; i++)
						result += i * digit;
				} else {
					result += (45 - ((nowl * nowl + nowl) >> 1)) * digit;
					result += ((nowr * nowr - nowr) >> 1) * digit;
					result += 45 * digit * (frontr - frontl - 1);
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
