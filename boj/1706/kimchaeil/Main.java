//문제: BOJ 1706 크로스워드
//메모리: 11556 KB
//시간: 64 ms

/*
 * 가로와 세로로 읽으면서 #을 기준으로 자르고
 * 길이가 2 이상이면 리스트에 넣는다
 * 리스트를 정렬하고 0번 인덱스에 있는 문자열을 출력
 */

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		StringBuilder sb = new StringBuilder();
		char[][] inputs = new char[R][];
		List<String> words = new ArrayList<>();
		String word;
		for (int i = 0; i < R; i++) {
			inputs[i] = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				if (inputs[i][j] == '#') {
					word = sb.toString();
					if (word.length() > 1)
						words.add(word);
					sb.setLength(0);
					continue;
				}
				sb.append(inputs[i][j]);
			}
			word = sb.toString();
			if (word.length() > 1)
				words.add(word);
			sb.setLength(0);
		}
		for (int j = 0; j < C; j++) {
			for (int i = 0; i < R; i++) {
				if (inputs[i][j] == '#') {
					word = sb.toString();
					if (word.length() > 1)
						words.add(word);
					sb.setLength(0);
					continue;
				}
				sb.append(inputs[i][j]);
			}
			word = sb.toString();
			if (word.length() > 1)
				words.add(word);
			sb.setLength(0);
		}
		words.sort(null);
		System.out.print(words.get(0));
	}
}
