//문제: BOJ 28064 이민희진
//메모리: 12104 KB
//시간: 76 ms

/*
 * 브루트포스
 * i번째 문자열의 접미사와 j번째 문자열의 접두사를 비교하고
 * 이미 비교해서 쌍으로 카운트 된 i-j에 대해 중복 카운트를 방지하기 위해 visited 이용
 */

import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		char[][] inputs = new char[n][];
		for (int i = 0; i < n; i++)
			inputs[i] = br.readLine().toCharArray();

		boolean[][] visited = new boolean[n][n];
		int cnt = 0;

		for (int i = 0; i < n; i++) {
			int ilength = inputs[i].length;
			loop: for (int j = 0; j < n; j++) {
				if (i == j || visited[i][j])
					continue;
				int limit = Math.min(ilength, inputs[j].length);
				subloop: for (int k = 1; k <= limit; k++) {
					int index = ilength - k;
					for (int l = 0; l < k; l++) {
						if (inputs[i][index + l] != inputs[j][l])
							continue subloop;
					}
					visited[i][j] = visited[j][i] = true;
					cnt++;
					continue loop;
				}
			}
		}
		
		System.out.println(cnt);
	}

}
