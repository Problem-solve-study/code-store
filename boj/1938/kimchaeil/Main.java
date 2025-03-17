//문제: BOJ 1938번 통나무 옮기기
//메모리: 11920 KB
//시간: 76 ms

/*
 * BFS인데 구현을 해야하는 BFS로 보았다.
 * 바운더리 체크와 잘려지지 않은 나무 체크를 실수하지 않기위해 집중해서 구현해야했다.
 */

import java.io.*;
import java.util.*;

class Train {
	int mid; //중심점
	boolean isRow; //가로면 true, 세로면 false
	int depth; //bfs depth
	public Train(int mid, boolean isRow, int depth) {
		this.mid = mid;
		this.isRow = isRow;
		this.depth = depth;
	}
}
public class Main {
	static boolean[][] map;
	static int n;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new boolean[n][n];
		boolean[][] visited = new boolean[2][n * n]; //가로일때 세로일때 따로 방문처리 해야하므로 2차원배열
		int[] train = new int[3];
		int[] end = new int[3];
		int tIdx = 0;
		int eIdx = 0;
		
		int[][] d = {{1,0,-1,0},{0,1,0,-1}};
		
		for(int i=0;i<n;i++) {
			String input = br.readLine();
			for(int j=0;j<n;j++) {
				char c = input.charAt(j);
				map[i][j] = (c == '1');
				if(c=='B')train[tIdx++]=i*n+j;
				else if(c=='E')end[eIdx++]=i*n+j;
			}
		}
		
		Train endpos = new Train(end[1],end[1]-end[0]==1,0); //목적지 세팅
		Queue<Train> queue = new ArrayDeque<>();
		queue.add(new Train(train[1],train[1]-train[0]==1,0)); //출발지 queue에 삽입
		visited[queue.peek().isRow?0:1][queue.peek().mid]=true; //출발지 방문 처리
		int result = 0;
		while(!queue.isEmpty()) {
			Train now = queue.poll();
			if(now.mid == endpos.mid && now.isRow==endpos.isRow) { //현재 중심위치와 목적지 중심위치가 동일하며 가로 또는 세로가 동일하면 도작
				result = now.depth;
				break;
			}
			int i = now.mid/n;
			int j = now.mid%n;
			for(int dir=0;dir<4;dir++) { //상하좌우 이동 처리
				int ni = i+d[0][dir];
				int nj = j+d[1][dir];
				int pos = ni*n+nj;
				if(boundCheck(ni,nj,now.isRow)&&!visited[now.isRow?0:1][pos]) { //바운더리체크,장애물체크,방문체크
					queue.add(new Train(pos,now.isRow,now.depth+1));
					visited[now.isRow?0:1][pos]=true;
				}
			}
			if(boundCheck(i,j,true)&&boundCheck(i-1,j,true)&&boundCheck(i+1,j,true)&&!visited[!now.isRow?0:1][now.mid]) {
				//회전 처리, 회전은 중심을 기준으로 8방향이 valid해야한다. 그러므로 위 세칸, 중간 세칸, 아래 세칸에 대해 체크해주면 9칸 모두 체크가 된다.
				queue.add(new Train(now.mid,!now.isRow,now.depth+1));
				visited[!now.isRow?0:1][now.mid]=true;
			}
		}
		System.out.println(result);
	}
	static boolean boundCheck(int i,int j, boolean isRow) {
		if(isRow) {
			if(i<0||i>=n) return false;
			if((j-1)<0||(j+1)>=n)return false;
			if(map[i][j]||map[i][j-1]||map[i][j+1]) return false;
		}else {
			if(j<0||j>=n) return false;
			if((i-1)<0||(i+1)>=n)return false;
			if(map[i][j]||map[i-1][j]||map[i+1][j]) return false;
		}
		return true;
	}
}
