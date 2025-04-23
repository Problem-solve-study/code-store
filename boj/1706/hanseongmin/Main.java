import java.io.*;
import java.util.*;

/*
 * 11484KB, 64ms
 * 
 * 가로로 한 번 스캔하며 단어를 뽑아내고 정답 문자열과 비교하는 과정을 반복
 * 이후 세로로도 한 번 스캔하며 단어를 뽑아내고 정답 문자열과 비교한 후
 * 최종적인 정답 문자열을 출력
 * 
 * if문 조건 실수로 단어 길이가 1개일 때 출력했다가 1틀 
 */

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		char[][] map = new char[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		String res = null;
		//가로로 한 번 스캔
		for (int r = 0; r < R; r++) {
			StringBuilder sb = new StringBuilder();
			for (int c = 0; c < C; c++) {
				if (map[r][c] != '#') {
					//금지된 칸이 아니라면 단어를 만듦
					sb.append(map[r][c]);
				} else {
					//금지된 칸이라면 갱신 시도
					//일단 단어의 길이가 1 이상이여야 함
					if (sb.length() > 1) {
						//정답 문자열이 null이거나 사전순으로 앞서고 있다면 정답 갱신
						if (res == null || res.compareTo(sb.toString()) > 0) {
							res = sb.toString();
						}
					}
					sb = new StringBuilder();
				}
				
				if (c == C - 1) {
					if (sb.length() > 1) {
						if (res == null || res.compareTo(sb.toString()) > 0) {
							res = sb.toString();
						}
					}
				}
			}
		}
		
		//세로로 한 번 스캔
		for (int c = 0; c < C; c++) {
			StringBuilder sb = new StringBuilder();
			for (int r = 0; r < R; r++) {
				if (map[r][c] != '#') {
					sb.append(map[r][c]);
				} else {
					if (sb.length() > 1) {
						if (res == null || res.compareTo(sb.toString()) > 0) {
							res = sb.toString();
						}
					}
					sb = new StringBuilder();
				}
				
				if (r == R - 1) {
					if (sb.length() > 1) {
						if (res == null || res.compareTo(sb.toString()) > 0) {
							res = sb.toString();
						}
					}
				}
			}
		}
		
		System.out.print(res == null ? "" : res);
	}
}
