//문제: BOJ 15811 복면산?!
//메모리: 13868 KB
//시간: 432 ms

/*
 * 각 문자에 숫자들을 대입해보며 가능한지 확인
 */

import java.io.*;
import java.util.*;

public class Main {
	static int[][] inputs;
	static int cnt;
	static int[] alphas;
	static int[] result = new int[26];
	static boolean[] visited = new boolean[10];
	static long[] values = new long[3];

	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(new BufferedReader(new InputStreamReader(System.in)).readLine());
		inputs = new int[3][]; //입력 문자열을 'A'를 뺀 값으로 파싱한 배열
		boolean[] seenAlpha = new boolean[26];
		for (int i = 0; i < 3; i++) {
			char[] input = st.nextToken().toCharArray();
			inputs[i] = new int[input.length];
			for (int j = 0; j < input.length; j++) //입력 문자열의 문자들 체크
				seenAlpha[(inputs[i][j] = input[j] - 'A')] = true;
		}

		for (int i = 0; i < 26; i++) //입력 문자열들에 포함된 알파벳 개수 세기
			if (seenAlpha[i])
				cnt++;
		
		if (cnt > 10) {
			//입력 문자열들에 포함된 알파벳의 개수가 10개보다 크다면 숫자를 모두 다르게 매칭 불가능
			System.out.print("NO");
		} else {
			alphas = new int[cnt]; //입력 문자열들에 포함된 알파벳들
			for (int i = 0, idx = 0; i < 26; i++)
				if (seenAlpha[i])
					alphas[idx++] = i;
			System.out.println(permutation(0) ? "YES" : "NO");
		}
	}

	static boolean permutation(int depth) { //순열 만들기
		if (depth == cnt) { //순열이 완성되면 대입 후 계산
			for (int i = 0; i < 3; i++) {
				values[i] = 0;
				for (int k : inputs[i])
					values[i] = (values[i] << 3) + (values[i] << 1) + result[k];
			}

			return values[0] + values[1] == values[2];
		}
		int target = alphas[depth];
		for (int i = 0; i < 10; i++) {
			if (!visited[i]) {
				result[target] = i;
				visited[i] = true;
				if (permutation(depth + 1))
					return true;
				visited[i] = false;
			}
		}
		return false;
	}
}
