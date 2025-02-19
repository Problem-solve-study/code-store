//문제: 16953번 A->B
//메모리: 11480 KB
//시간: 68 ms

/*
 * 1. 두 연산 모두 값이 커지는 연산이다. 무한 재귀에 빠지는 경우가 없다.
 * 2. 연산 결과가 B보다 큰 경우는 더 이상 확인할 필요가 없다..
 * 3. 두 연산의 순서가 바뀌면 결과가 항상 다르기 때문에 visited 배열 또는 seen 집합이 필요가 없다.
 * 4. 백트래킹으로 해결 가능하다.
 * +) 연산 결과를 int로 하니 틀렸다고 나와서 B가 클때 연산결과가 오버플로우가 나는 것 같아 long으로 바꾸니 정답 처리되었다.
 */

import java.util.*;
import java.io.*;

public class Main {
	static StreamTokenizer st;
	static int result = Integer.MAX_VALUE;
	static int A, B;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StreamTokenizer(br);
		A = nextInt();
		B = nextInt();

		if (backTracking(A, 1)) { //최소 연산 횟수 +1이 출력이므로 depth를 1부터 시작 / 백트래킹의 반환값이 true라면 result 출력, false라면 -1 출력
			System.out.println(result);
		} else {
			System.out.println(-1);
		}
	}

	static boolean backTracking(long res, int depth) {
		if (res > B) //연산결과가 B보다 크다면 더 이상 B로 만들 수 없으므로 false 반환
			return false;
		if (res == B) { //연산결과가 B라면 result를 result와 depth 중 작은 값으로 하고 true 반환
			result = Math.min(result, depth);
			return true;
		}
		boolean ret = false;
		ret = backTracking(res * 2L, depth + 1) || backTracking(res * 10L + 1, depth + 1); //두 경우에 대한 백트래킹 중 true 반환이 있다면 ret는 true
		return ret;
	}

	static int nextInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
}
