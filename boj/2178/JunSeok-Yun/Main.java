//14688KB, 112ms
import java.io.*;
import java.util.*;

class Node{
	int x;
	int y;
	Node(int x, int y){
		this.x = x;
		this.y = y;
	}
}

public class Main {
	static int N, M;
	static int minVal = Integer.MAX_VALUE;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++){
			String str = br.readLine();
			for (int j = 0; j < M; j++){
				map[i][j] = str.charAt(j) - '0';
			}
		}
		bfs();
		System.out.println(map[N - 1][M - 1]);
	}

	public static boolean isValidation(int nx, int ny){
		return (nx >= 0 && ny >= 0 && nx < N && ny < M);
	}

	public static void bfs(){
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(0, 0));
		visited[0][0] = true;

		while (!q.isEmpty()){
			int nx, ny;
			Node node = q.poll();
			for (int i = 0; i < 4; i++){
				nx = node.x + dx[i];
				ny = node.y + dy[i];
				if (isValidation(nx, ny) && !visited[nx][ny] && map[nx][ny] != 0){
					q.offer(new Node(nx, ny));
					visited[nx][ny] = true;
					map[nx][ny] = map[node.x][node.y] + 1;
				}
			}
		}
	}
}
