//Memory : 17656 Time : 144ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Position{
		int x, y;
		Position(int y, int x){
			this.y = y;
			this.x = x;
		}
	}
	
	static int[] dir = {0,0,1,-1};
	
	static int width, height;
	static char[][] soldiers;
	static boolean[][] visited;
	
	
	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		width = Integer.parseInt(st.nextToken());
		height = Integer.parseInt(st.nextToken());
		soldiers = new char[height][width];
		visited = new boolean[height][width];
		
		for(int h =0;h<height;h++) {
			String line = br.readLine();
			
			for(int w = 0;w<width;w++) {
				soldiers[h][w] = line.charAt(w);
				visited[h][w] = false;
			}
		}
	}
	
	static boolean checkPos(int y, int x) {
		if(x >= width || x < 0 || y >=height || y < 0)
			return false;
		return true;
	}
	
	static int bfs(int y, int x) {
		int cnt = 0;
		char team = soldiers[y][x];
		visited[y][x] = true;
		Queue<Position> que = new LinkedList<>();
		que.add(new Position(y,x));
		
		while(!que.isEmpty()) {
			Position p = que.poll();
			cnt++;
			
			for(int i = 0;i<4;i++) {
				int dx= p.x + dir[i];
				int dy = p.y + dir[3-i];
				if(checkPos(dy, dx) && !visited[dy][dx] && soldiers[dy][dx] == team) {
					que.add(new Position(dy, dx));
					visited[dy][dx] = true;
				}
			}
		}
		
		
		return cnt*cnt;
	}
	
	static void solution() {
		int white = 0;
		int blue = 0;
		for(int y= 0;y<height;y++) {
			for(int x= 0; x<width;x++) {
				if(visited[y][x]) continue;
				int power = bfs(y, x);
				if(soldiers[y][x] == 'W')
					white += power;
				else
					blue += power;
			}
		}
		
		System.out.println(white + " " + blue);
	}
	
	
	public static void main(String[] args) throws IOException {
		input();
		solution();
	}
}
