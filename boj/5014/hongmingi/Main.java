//110016KB	220ms
import java.io.*;
import java.util.*;

/*
 * BFS 연습문제.
 */
public class Main {
	static int F, S, G, U, D;
    static boolean[] visited;
    static int winCount, loseCount;
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        F = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        
        Queue<int[]> queue = new LinkedList<>();
        int cnt = 0;
        int[] node = {S, cnt};
        visited = new boolean[F+1];
        queue.add(node);


        while(!queue.isEmpty()){
            node = queue.poll();
            S = node[0];
            cnt = node[1];
            
            if((S<G && U==0)||(S>G && D==0))    break;

            if(S == G){
                System.out.println(cnt);
                return;
            }
            
            if(!visited[S]){
                visited[S] = true;
                if(S+U<=F){
                    node = new int[]{S+U, cnt+1};
                    queue.add(node);
                }
                if(S-D>=1){
                    node = new int[]{S-D, cnt+1};
                    queue.add(node);
                }
            }
        }
        System.out.println("use the stairs");
	}
}
