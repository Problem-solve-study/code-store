//문제: BOJ 5397번 키로거
//메모리: 111656 KB
//시간: 356 ms

/*
 * 처음에는 양방향 연결리스트를 이용하여 해결하였으나
 * 스택으로도 해결이 가능하다고 생각해서 스택으로 다시 해결하였다.
 * 커서의 좌우이동이 있기 때문에 커서의 앞부분의 삽입,삭제가 뒷부분에 영향을 주지 않게 하기 위해 2개의 스택을 사용하였다.
 * 스택의 길이는 입력으로 들어오는 문자열의 길이보다 길어지지 않으므로 입력으로 들어온 문자열의 길이로 선언한다.
 * 커서의 앞부분 스택을 prev, 커서의 뒷부분 스택을 next로 하자
 * '<'의 경우 prev가 비어있지 않다면 prev에서 pop한 값을 next에 push
 * '>'의 경우 next가 비어있지 않다면 next에서 pop한 값을 prev에 push 
 * '-'의 경우 prev에서 pop;
 * 나머지 문자에 대해서는 prev에 push
 */

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int testcase = Integer.parseInt(br.readLine());
		while (testcase-- > 0) {
			char[] input = br.readLine().toCharArray();
			int length = input.length;
			char[] prev = new char[length]; // 커서 앞부분 스택
			char[] next = new char[length]; // 커서 뒷부분 스택
			int pTop = 0, nTop = 0; // 각 스택의 top
			for (int i = 0; i < length; i++) {
				char c = input[i];
				switch (c) {
				case '<': // 왼쪽 화살표
					if (pTop != 0) // prev가 비어있지 않다면
						next[nTop++] = prev[--pTop]; // prev에서 pop한 값을 next에 push
					break;
				case '>': // 오른쪽 화살표
					if (nTop != 0) // next가 비어있지 않다면
						prev[pTop++] = next[--nTop]; // next에서 pop한 값을 prev에 push
					break;
				case '-': // 백스페이스
					if (pTop != 0) // prev가 비어있지 않다면
						pTop--; // prev에서 pop
					break;
				default: // 나머지 문자(알파벳 대문자, 소문자, 숫자)
					prev[pTop++] = c; // prev에 push
					break;
				}
			}

			for (int i = 0; i < pTop; i++) // prev에 먼저 들어온 문자부터
				sb.append(prev[i]);
			for (int i = nTop - 1; i >= 0; i--) // next에 나중에 들어온 문자부터
				sb.append(next[i]);
			sb.append('\n');
		}
		System.out.print(sb);
	}
}