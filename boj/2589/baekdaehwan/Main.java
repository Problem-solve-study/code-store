/**
 * 	219480KB	496ms
 * 
 * [사고흐름]
 * 기본적인 BFS 브루트포스 문제로 보였음. 복잡도는 N^3이지만 N<=50이므로 추가적인 고민 없이 풀이를 시작했음
 * 
 * [알고리즘 설명]
 * (r,c)가 섬인 모든 지점을 대상으로 BFS를 수행함, BFS 중에는 최초 위치에서부터 떨어진 거리를 추가로 관리함.
 * 위 과정 중 거리의 최대가 답이 됨.
 */

import java.io.*;
import java.util.*;

public class Main {
	static int R, C;
	static boolean[][] M;
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = new boolean[R][C];
	
		for (int r=0; r<R; ++r) {
			String str = br.readLine();
			for (int c=0; c<C; ++c) {
				M[r][c] = str.charAt(c) == 'L';
			}
		}
		
		Queue<int[]> q = new ArrayDeque<int[]>();
		int res = 0;
		for (int r=0; r<R; ++r) {
			for (int c=0; c<C; ++c) {
				if (M[r][c]) {					
					boolean[][] visited = new boolean[R][C];
					visited[r][c] = true;
					
					q.add(new int[] {r,c,0});
					while (!q.isEmpty()) {
						int[] cur = q.poll();
						res = Math.max(res, cur[2]);
						
						for (int d=0; d<4; ++d) {
							int nr = cur[0]+dr[d];
							int nc = cur[1]+dc[d];
							if (
								0<=nr&&nr<R && 0<=nc&&nc<C && 
								!visited[nr][nc] && M[nr][nc]
							) {
								visited[nr][nc] = true;
								q.add(new int[] {nr,nc,cur[2]+1});
							}
						}
					}
				}
			}
		}
		System.out.println(res);
	}
}
