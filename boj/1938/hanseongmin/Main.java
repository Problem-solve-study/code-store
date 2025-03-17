import java.io.*;
import java.util.*;

/*
 * 12828KB, 96ms
 * 
 * 보자마자 BFS 문제임은 눈치챘는데 BFS로 구현이 좀 귀찮을 것 같아 DP로 가능할까 생각했었다.
 * DP로 생각하다보니 그냥 BFS가 맞겠다 싶어서 BFS로 노선을 틀고
 * 큐에 넣어주는 노드마다 visited를 다르게 줘야하나.. 싶은 찰나 어차피 중심점이 정해져 있으니 중심점 기준으로
 * BFS를 하면 될 것 같다는 생각이 스쳤고 그대로 구현
 * 근데 회전할 때 중심점 기준 8방향에 나무가 있으면 안된다는 조건을 못보고 틀렸다.
 * 그것만 수정하니 AC
 */

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, res;
	static char[][] arr;
	static int sr, sc;
	static int er, ec;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		arr = new char[N][N];
		boolean find = false;
		boolean find2 = false;
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				arr[i][j] = line.charAt(j);
                //B와 E의 중심점 찾기
				if (arr[i][j] == 'B') {
					if (!find) {
						find = true;
					} else {
						sr = i;
						sc = j;
						find = false;
					}
				}
				
				if (arr[i][j] == 'E') {
					if (!find2) {
						find2 = true;
					} else {
						er = i;
						ec = j;
						find2 = false;
					}
				}
			}
		}

        //0인 경우 가로로 있는 경우, 1인 경우 세로로 있는 경우
		boolean[][][] v = new boolean[N][N][2];
		boolean isZero = deter(sr, sc);
		Queue<Integer> q = new ArrayDeque<>();
		q.add(sr); q.add(sc); 
		if (isZero) {
			v[sr][sc][0] = true;
			q.add(0);
		} else {
			v[sr][sc][1] = true;
			q.add(1);
		}
		q.add(0);
		while (!q.isEmpty()) {
			int r = q.remove();
			int c = q.remove();
			int status = q.remove();
			int cnt = q.remove();
			if (r == er && c == ec) {
				res = cnt;
				break;
			}
			
			if (status == 0) {	//옆으로 누워있는 상태면
				int r0 = r; int c0 = c - 1;
				int r2 = r; int c2 = c + 1;
				for (int i = 0; i < 4; i++) {
					int nextR0 = r0 + dr[i]; int nextC0 = c0 + dc[i];
					int nextR = r + dr[i]; int nextC = c + dc[i];
					int nextR2 = r2 + dr[i]; int nextC2 = c2 + dc[i];
                    //4방향으로 이동해주고
					if (deployable(nextR0, nextC0, nextR, nextC, nextR2, nextC2) && !v[nextR][nextC][0]) {
						v[nextR][nextC][0] = true;
						q.add(nextR); q.add(nextC); q.add(0); q.add(cnt + 1);
					}
					
                    //그 다음 회전을 고려
					if (canT(r, c, 0) && !v[r][c][1]) {
						v[r][c][1] = true;
						q.add(r); q.add(c); q.add(1); q.add(cnt + 1);
					}
				}
			} else {	//세로인 상태면
				int r0 = r - 1; int c0 = c;
				int r2 = r + 1; int c2 = c;
				for (int i = 0; i < 4; i++) {
					int nextR0 = r0 + dr[i]; int nextC0 = c0 + dc[i];
					int nextR = r + dr[i]; int nextC = c + dc[i];
					int nextR2 = r2 + dr[i]; int nextC2 = c2 + dc[i];
					if (deployable(nextR0, nextC0, nextR, nextC, nextR2, nextC2) && !v[nextR][nextC][1]) {
						v[nextR][nextC][1] = true;
						q.add(nextR); q.add(nextC); q.add(1); q.add(cnt + 1);
					}
					
					if (canT(r, c, 1) && !v[r][c][0]) {
						v[r][c][0] = true;
						q.add(r); q.add(c); q.add(0); q.add(cnt + 1);
					}
				}
			}
		}
		
		System.out.println(res);
	}
	
	static boolean canT(int r, int c, int s) {
		if (!deployable(r - 1, c - 1, r, c, r - 1, c + 1)) return false;
		if (!deployable(r + 1, c - 1, r, c, r + 1, c + 1)) return false;
		
		if (s == 0) {
			int r0 = r; int c0 = c - 1;
			int r2 = r; int c2 = c + 1;
			int nextR0 = r0 - 1; int nextC0 = c0 + 1;
			int nextR2 = r2 + 1; int nextC2 = c2 - 1;
			return deployable(nextR0, nextC0, r, c, nextR2, nextC2);
		} else {
			int r0 = r - 1; int c0 = c;
			int r2 = r + 1; int c2 = c;
			int nextR0 = r0 + 1; int nextC0 = c0 - 1;
			int nextR2 = r2 - 1; int nextC2 = c2 + 1;
			return deployable(nextR0, nextC0, r, c, nextR2, nextC2);
		}
	}
	
	
	static boolean deter(int r, int c) {
		//옆으로 있으면 true
		if (bc(r, c - 1) && bc(r, c + 1)) {
			if (arr[r][c - 1] == 'B') {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	static boolean deployable(int r1, int c1, int r2, int c2, int r3, int c3) {
		if (!bc(r1, c1) || !bc(r2, c2) || !bc(r3, c3)) return false;
		if (arr[r1][c1] == '1' || arr[r2][c2] == '1' || arr[r3][c3] == '1') return false;
		return true;
	}
	
	static boolean bc(int r, int c) {
		return (0 <= r && r < N) && (0 <= c && c < N);
	}
}