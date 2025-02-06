package BJ25187;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M, Q, clean;
	static int[] go, mool;
	static int[][] map;
    static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        
        mool = new int[N];
        StringTokenizer st3 = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            mool[i] = Integer.parseInt(st3.nextToken());
        }
        for(int i=0; i<M; i++){
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st2.nextToken());
            int b = Integer.parseInt(st2.nextToken());
            map[a-1][b-1] = map[b-1][a-1] = 1;
        }
        
        int go;
        for(int i=0; i<Q; i++){
            visited = new boolean[N];
            clean = 0;
            go = Integer.parseInt(br.readLine());
            dfs(go-1);
            for(int j=0; j<N; j++) {
            	if(visited[j]) {
            		if(mool[j] == 1)	clean++;
            		else	clean--;
            	}
            }
            if(clean>0)	System.out.println(1);
            else	System.out.println(0);
        }     
	}	
	
    static void dfs(int go){
        visited[go] = true;
        for(int i=0; i<N; i++){
            if(!visited[i] && map[go][i]==1){
                dfs(i);
            }
        }
    }
}
