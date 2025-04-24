//문제: BOJ 2922번 즐거운 단어
//메모리: 12272 KB
//시간: 64 ms

/*
 *  모든 가능한 경우에 대해 백트래킹
 *  
 *  가지치기가 되는 조건
 *  1. 현재 위치가 알파벳
 *  	1-1. 전전 알파벳, 전 알파벳, 현재 알파벳 모두 자음인 경우
 *  	1-2. 전전 알파벳, 전 알파벳, 현재 알파벳 모두 모음인 경우
 *  2. 현재 위치가 '_'
 *  	2-1. 전전 알파벳, 전 알파벳이 자음이면서 현재 위치에 자음이 들어가는 경우
 *  	2-2. 전전 알파벳, 전 알파벳이 모음이면서 현재 위치에 모음이 들어가는 경우
 *  
 *  끝까지 갔는데 L이 포함되지 않는 경우라면 카운트하지 않음
 */

import java.io.*;

public class Main {
	static char[] input;
	static int len;
	static long ans;

	public static void main(String[] args) throws Exception {
		input = new BufferedReader(new InputStreamReader(System.in)).readLine().toCharArray();
		len = input.length;
		backTracking(0, false, false, false, 1);
		System.out.print(ans);
	}

	public static void backTracking(int depth, boolean ppc, boolean pc, boolean containl, long cnt) {
		if (depth++ == len) { // 아래에서 depth+1의 중복 연산을 제거하기 위해 미리 증가시킴 <최적화 용도>
			if (containl) // 'L'이 들어간 경우라면
				ans += cnt; // 카운트
			return;
		}
		switch (input[depth - 1]) { // 위에서 depth를 증가시켰기 때문에 depth-1번째 인덱스를 확인
		case 'A': case 'E': case 'I': case 'O': case 'U': // 모음이라면
			if (depth < 3 || ppc || pc) // depth가 2 이하(증가 전 depth가 1 이하)거나 전전 알파벳, 전 알파벳 둘 중 하나라도 자음이라면
				backTracking(depth, pc, false, containl, cnt); // 다음 단계
			break;
		case 'L': // 'L'이라면
			if (depth < 3 || !ppc || !pc) // depth가 2 이하(증가 전 depth가 1 이하)거나 전전 알파벳, 전 알파벳 둘 중 하나라도 모음이라면
				backTracking(depth, pc, true, true, cnt); // 'L'포함 체크 후 다음단계
			break;
		case '_': // '_'라면
			long temp = (cnt << 2) + cnt; // cnt*5 미리 계산 <최적화 용도>
			if (depth < 3 || ppc || pc) { // depth가 2 이하(증가 전 depth가 1 이하)거나 전전 알파벳, 전 알파벳 둘 중 하나라도 자음이라면
				backTracking(depth, pc, false, containl, temp); // 현 위치에 모음이 들어가는 경우(cnt*5)
			}
			if (depth < 3 || !ppc || !pc) { // depth가 2 이하(증가 전 depth가 1 이하)거나 전전 알파벳, 전 알파벳 둘 중 하나라도 모음이라면
				backTracking(depth, pc, true, containl, temp << 2); // 현 위치에 'L'을 제외한 자음이 들어가는 경우(cnt*20)
				backTracking(depth, pc, true, true, cnt); // 현 위치에 'L'이 들어가는 경우(cnt는 그대로, 'L'포함 체크)
			}
			break;
		default: //자음이라면
			if (depth < 3 || !ppc || !pc) // depth가 2 이하(증가 전 depth가 1 이하)거나 전전 알파벳, 전 알파벳 둘 중 하나라도 모음이라면
				backTracking(depth, pc, true, containl, cnt); // 다음 단계
		}
	}
}
