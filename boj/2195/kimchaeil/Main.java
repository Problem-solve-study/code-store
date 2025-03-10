//문제: BOJ 2195번 문자열 복사
//메모리: 11708 KB
//시간: 88 ms

/*
 * P를 순차탐색하면서 S와 최대한 길게 일치하는 부분을 찾는 것이 관건
 * 두 문자열의 길이가 1000이어서 단순하게 생각해도 시간초과가 나지 않을 것이라고 생각
 */

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		String P = br.readLine();
		int result = 0;
		for (int i = 0; i < P.length();) { //P 순차탐색
			int max = 0; 
			for (int j = 0; j < S.length(); j++) { //S 순차탐색 -> P의 i번째부터 S와 최대한 길게 일치하는 부분의 길이를 찾는다
				int cnt = 0; 
				while (i + cnt < P.length() && j + cnt < S.length() && P.charAt(i + cnt) == S.charAt(j + cnt)) //범위를 벗어나지 않고 P의 i+cnt번째 문자와 S의 j+cnt번째 문자가 같으면 반복
					cnt++; //cnt 증가
				max = max < cnt ? cnt : max; //최대값 초기화
			}
			i += max; //찾은 최대 길이만큼 i증가
			result++; //result 증가
		}
		System.out.println(result);
	}
}
