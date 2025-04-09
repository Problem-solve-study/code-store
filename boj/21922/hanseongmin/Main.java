import java.io.*;
import java.util.*;

/*
 * 34664KB, 464ms
 * 
 * 삼성에서 좋아할거같은 시뮬레이션 문제
 * 도를 닦는 느낌으로 천천히 구현..
 */


public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	static int N, M;
	static ArrayList<int[]> starts = new ArrayList<>();
	static final int DOWN = 0;
	static final int UP = 1;
	static final int RIGHT = 2;
	static final int LEFT = 3;
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	static int[][] dd = {{DOWN, UP, RIGHT, LEFT}, {DOWN, UP, RIGHT, LEFT}, {DOWN, UP, RIGHT, LEFT}, {LEFT, RIGHT, UP, DOWN}, {RIGHT, LEFT, DOWN, UP}};
	static int[][] map;
	static boolean[][] check;
	
	public static void main(String[] args) throws Exception {
		N = nextInt(); M = nextInt();
		map = new int[N][M];
		check = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = nextInt();
				//에어컨들의 위치를 담음
				if (map[i][j] == 9) {
					starts.add(new int[] {i, j});
				}
			}
		}
		
		for (int[] start : starts) {
			int sr = start[0];
			int sc = start[1];
			check[sr][sc] = true;
			//현재 에어컨으로부터 상하좌우 4방향으로 시뮬레이션
			for (int i = 0; i < 4; i++) {
				simulation(sr, sc, i);
			}
		}
		
		//바람이 지나가는 위치를 셈
		int res = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (check[i][j]) {
					res++;
				}
			}
		}
		System.out.print(res);
	}
	
	//시뮬레이션, 적절히 방향을 잘 바꿔주고 종료 조건 잘 설정하면 됨
	static void simulation(int r, int c, int d) {
		while (true) {
			r += dr[d];
			c += dc[d];
			if (!bc(r, c)) break;
			check[r][c] = true;
			//도착한 위치가 에어컨이면 더 탐색할 필요없음. (어차피 모든 에어컨의 모든 방향을 보므로)
			if (map[r][c] == 9 || (map[r][c] == 1 && (d == LEFT || d == RIGHT)) || (map[r][c] == 2 && (d == UP || d == DOWN))) {
				break;
			} 
			d = dd[map[r][c]][d];
		}
	}
	
	static boolean bc(int r, int c) {
		return (0 <= r && r < N) && (0 <= c && c < M);
	}
	
	static int nextInt() throws Exception {
		st.nextToken();
		return (int) st.nval;
	}
}