// 14828KB	88ms
import java.io.*;
import java.util.*;


public class Main {
	static int n;
	static byte[][] map;
	static final byte B = 2, E = 3;
	static final int HOR = 0, VER = 1;
	static int answer;
	static int sy, sx, ty, tx, sd, td;
	static int[] dx = {0,0,-1,1,0};
	static int[] dy = {-1,1,0,0,0};
	static boolean[][][] visited;
	
	// 가운데 점이랑 방향, 이동횟수
	static class Node {
		int y, x, direct,cnt;
		Node(int y, int x, int direct, int cnt) {
			this.y = y;
			this.x = x;
			this.direct = direct;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new byte[n][n];
		int direct = 2, flag = 0;
		int sy = 0, sx = 0;
		for (int i=0; i<n; i++) {
			String line = br.readLine();
			for (int j=0; j<n; j++) {
				map[i][j] = line.charAt(j) == 'B' ? B : line.charAt(j) == 'E' ? E : (byte)(line.charAt(j) -'0');
			}
		}
		setMapInfo();
		
		visited = new boolean[n][n][2];
		bfs();
		System.out.print(answer);
	}
	
	
	static void setMapInfo() {
		int sflag = 0, tflag = 0;
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				if (map[i][j] == B && sflag == 0) {
					if (j-1 >= 0 && map[i][j-1] == B) {
						sd = HOR; 
						sflag = 1;
						sy = i; sx = j;
					}
					if (i-1 >= 0 && map[i-1][j] == B) {
						sd = VER; 
						sflag = 1;
						sy = i; sx = j;
						
					}
				}
				if (map[i][j] == E && tflag == 0) {
					if (j-1 >= 0 && map[i][j-1] == E) {
						td = HOR; 
						tflag = 1;
						ty = i; tx = j;
					}
					if (i-1 >= 0 && map[i-1][j] == E) {
						td = VER; 
						tflag = 1;
						ty = i; tx = j;
					}
				}
			}
		}
	}
	
	
	static void bfs() {
		Queue<Node> queue = new ArrayDeque<>();
		visited[sy][sx][sd] = true;
		queue.add(new Node(sy, sx, sd, 0));
		
		while (!queue.isEmpty()) {
			Node now = queue.poll();
			if (now.y == ty && now.x == tx && now.direct == td) {
				answer = now.cnt;
				break;
			}
			
			for (int i=0; i<5; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				if (ny>=0 && ny<n && nx>=0 && nx<n) {
					int d = now.direct;
					if (i == 4) {
						d = (now.direct + 1) % 2;
					}
					// 1. 같은 위치로 방문한 적이 없어야 함.
					// 2. 주위에 나무가 없어야 함.
					if (d == HOR) {
						int[][] next = {{ny, nx-1}, {ny, nx}, {ny, nx+1}};
						if (!visited[ny][nx][HOR] && isPossible(next , i)) {
							visited[ny][nx][HOR] = true;
							queue.add(new Node(ny, nx, HOR, now.cnt + 1));
						}
					}
					else {
						int[][] next = {{ny-1, nx}, {ny, nx}, {ny+1, nx}};
						if (!visited[ny][nx][VER] && isPossible(next , i)) {
							visited[ny][nx][VER] = true;
							queue.add(new Node(ny, nx, VER, now.cnt + 1));
						}
					}
				}
			}
		}
	}
	
	
	static boolean isPossible(int[][] node, int op) {
		if (op < 4) {
			for (int i=0; i<3; i++) {
				int ny = node[i][0];
				int nx = node[i][1];
				if (ny<0 || ny>=n || nx<0 || nx>=n) {
					return false;
				}
				if (map[ny][nx] == 1) return false;
			}
			return true;
		}
		else {
			for (int i=node[1][0]-1; i<=node[1][0]+1; i++) {
				for (int j=node[1][1]-1; j<=node[1][1]+1; j++) {
					if (i<0 || i>=n || j<0 || j>=n) {
						return false;
					}
					if (map[i][j] == 1) return false;
				}
			}
			return true;
		}
	}
}