// 문제: 16919번 봄버맨2
// 메모리: 14696 KB
// 시간: 472 ms

import java.util.*;
import java.io.*;

/*
 * N이 1일때는 입력 그대로 출력
 * N이 짝수일때는 모두 지뢰
 * N이 3,7,11... 일때는 첫 폭발
 * N이 5,9,13,,, 일때는 첫 폭발->설치->두번째 폭발
 * 위처럼 3가지의 상태가 반복되므로 이를 이용한다.
 */

public class Main {
	static int R;
	static int C;
	static boolean[][] map; //지뢰인지 아닌지
	static List<Integer> old_mines_position; //다음 폭발때 터지는 지뢰
	static List<Integer> new_mines_position; //최근에 설치한 지뢰들
	static int[][] d = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } }; //4방 탐색

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] inputs = br.readLine().split(" ");
		R = Integer.parseInt(inputs[0]);
		C = Integer.parseInt(inputs[1]);
		int N = Integer.parseInt(inputs[2]);

		map = new boolean[R][C];
		old_mines_position = new ArrayList<>();
		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				if (line.charAt(j) == 'O') { //지뢰면 해당 위치를 지뢰라고 저장하고 다음에 터트릴 지뢰에 추가
					map[i][j] = true;
					old_mines_position.add(i * C + j);
				}
			}
		}

		N--; //N을 1감소
		if (N > 0) { //N이 1이상일때만 (N이 감소 시키기전에 1이었다면 아무것도 안하기 때문)
			install_mine(); //N이 홀수면 지회를 설치한 시점이므로 모든 위치가 지뢰
			if(N%2==0) { //N이 짝수면 지뢰가 터지는 시점
				explosion(); //홀수번째 폭발
				if((N/2)%2==0) {
					install_mine(); //홀수번째 폭발 이후 설치
					explosion(); //짝수번째 폭발
				}
			}
		}

		for (int i = 0; i < R; i++) { //출력
			for (int j = 0; j < C; j++) {
				if (map[i][j]) {
					sb.append('O');
				} else {
					sb.append('.');
				}
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}

	public static int install_mine() { //지뢰 설치
		new_mines_position = new ArrayList<>(); //설치할 지뢰들의 위치를 저장
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (!map[i][j]) { //지뢰가 설치되어있지 않은 장소라면 설치하고 추가
					map[i][j] = true;
					new_mines_position.add(i * C + j);
				}
			}
		}
		return 0;
	}

	public static int explosion() {
		for (int position : old_mines_position) { //터트릴 지뢰들에 대해
			int i = position / C;
			int j = position % C;
			map[i][j] = false; //지뢰 위치 폭발
			for (int dir = 0; dir < 4; dir++) { //폭발 반경 지뢰 제거
				int ni = i + d[dir][0];
				int nj = j + d[dir][1];
				if (ni >= 0 && nj >= 0 && ni < R && nj < C) {
					map[ni][nj] = false;
					new_mines_position.remove(Integer.valueOf(ni * C + nj)); //최근에 설치한 지뢰에서 폭발에 영향을 받은 지뢰 삭제
				}
			}
		}

		old_mines_position = new_mines_position; //최근에 설치한 지뢰들을 터트릴 지뢰로 만듬
		return 0;
	}

}
