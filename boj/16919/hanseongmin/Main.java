import java.io.*;
import java.util.*;

/*
14800KB, 124ms

시뮬레이션의 탈을 쓴 애드 혹 문제.
언뜻보면 시뮬레이션 문제처럼 생겼는데 N의 제한이 너무나도 커서 시뮬레이션으로 돌린다면
무조건 터진다고 생각했다.

그래서 규칙을 찾을 수 있을 것이라고 생각했고 아래와 같은 규칙을 발견할 수 있었다.

N이 짝수 -> 전체 지뢰
N이 1 -> 맨 처음 입력받은 그대로
N이 3 -> 상태1
N이 5 -> 상태2
N이 7 -> 상태1
N이 9 -> 상태2

출력할 배열을 미리 전처리하여 만들어두고 N의 값에 따라 위의 규칙에 맞게 출력하면 된다.
*/

public class Main {
	static int r, c, n;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static char[][] init;
	static char[][] all;
	static char[][] change;
	static char[][] change2;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
        //출력할 배열 4개 할당 및 초기화
		init = new char[r][c];
		all = new char[r][c];
		change = new char[r][c];
		change2 = new char[r][c];
		
		for (int i = 0; i < r; i++) {
			String line = br.readLine();
			for (int j = 0; j < c; j++) {
				init[i][j] = line.charAt(j);
			}
		}
		
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				all[i][j] = 'O';
			}
		}
		
		simulation(init, change);
		simulation(change, change2);
		
        //규칙에 맞게 출력
		if (n % 2 == 0) {
			printMap(all);
		} else if (n == 1) {
			printMap(init);
		} else if (n % 4 == 3) {
			printMap(change);
		} else {
			printMap(change2);
		}
	}
	
	static void simulation(char[][] src, char[][] dist) {
        //시뮬레이션으로 배열 초기화
		ArrayList<int[]> list = new ArrayList<>();
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (src[i][j] == 'O') {
					list.add(new int[] {i, j});
				}
				dist[i][j] = 'O';
			}
		}
		
		for (int[] mine : list) {
			dist[mine[0]][mine[1]] = '.';
			for (int i = 0; i < dr.length; i++) {
				int nr = mine[0] + dr[i];
				int nc = mine[1] + dc[i];
				if (boundaryCheck(nr, nc)) {
					dist[nr][nc] = '.';
				}
			}
		}
	}
	
	static void printMap(char[][] map) throws Exception {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				sb.append(map[i][j]);
			}
			sb.append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
	}
	
	static boolean boundaryCheck(int i, int j) {
		return (0 <= i && i < r) && (0 <= j && j < c);
	}
}