//문제: BOJ 3613번 Java VS C++
//메모리: 11516 KB
//시간: 64ms

/*
 * error인 경우
 * 1. 첫 글자가 소문자가 아님
 * 2. 마지막 글자가 언더바
 * 3. 언더바 뒤에 소문자가 아님
 * 4. 언더바와 대문자 모두 포함
 */

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		char[] input = new BufferedReader(new InputStreamReader(System.in)).readLine().toCharArray();
		StringBuilder sb = new StringBuilder();
		boolean isJava = false, isCpp = false;
		if (input[0] < 97 || input[input.length - 1] == '_') { // 첫 글자가 소문자가 아니거나 마지막 글자가 언더바면 error
			sb.append("Error!");
		} else {
			for (int i = 0; i < input.length; i++) {
				if (input[i] > 96) { //소문자면 넘어가기
					sb.append(input[i]);
					continue;
				}
				if (input[i] == '_') { //언더바면
					if (isJava || input[++i] < 97) { //대문자가 나왔었거나 다음 글자가 소문자가 아니면 error 
						sb.setLength(0);
						sb.append("Error!");
						break;
					}
					sb.append((char) (input[i] - 32)); //다음 글자 소문자로
					isCpp = true;
				} else {
					if (isCpp) { //언더바가 나왔었다면 error
						sb.setLength(0);
						sb.append("Error!");
						break;
					}
					sb.append('_').append((char) (input[i] + 32)); //언더바 + 소문자
					isJava = true;
				}
			}
		}
		System.out.println(sb);
	}
}
