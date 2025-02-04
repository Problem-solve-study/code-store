//메모리: 13848KB
//시간: 460ms

import java.util.*;
import java.io.*;
public class Main {
	static int[][] players;
	static int[] order = new int[9];
	static boolean[] visited = new boolean[8];
	static int ans = 0;
	static int n;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		players = new int[n][9];
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<9;j++)
				players[i][j]=Integer.parseInt(st.nextToken());
		}
		order[3]=0;
		dfs(0);
		bw.write(ans+"\n");
		bw.flush();
		bw.close();
	}
	public static void dfs(int depth) {
		if(depth==9) { game(); return; }
		if(depth==3) { dfs(depth+1); return; }
		for(int i=1;i<9;i++) {
			if(!visited[i-1]) {
				visited[i-1]=true;
				order[depth]=i;
				dfs(depth+1);
				visited[i-1]=false;
			}
		}
	}
	public static void game() {
		int out, bases=0, cnt=0, score=0;
		for(int i=0;i<n;i++) {
			bases=0; out=0;
			while(out<3) {
				if(players[i][order[cnt]]==0) {out++;}
				else {
					bases<<=1; score+=bases/8; bases%=8;
					bases+=1;
					for(int j=1;j<players[i][order[cnt]];j++) {
						bases<<=1; score+=bases/8; bases%=8;
					}
				}
				cnt=(cnt+1)%9;
			}
		}
		ans=Math.max(ans, score);
	}
}
