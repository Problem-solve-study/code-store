// 29184KB	192ms
import java.io.*;
import java.util.*;

public class Main {
    static int n, m, answer = Integer.MAX_VALUE;
    static boolean[][] friend;
    static int[] count;	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        friend = new boolean[n+1][n+1];
        count = new int[n+1];
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            friend[a][b] = true;
            friend[b][a] = true;
            count[a]++;
            count[b]++;
        }
        dfs();
        if(answer == Integer.MAX_VALUE)
            System.out.print(-1);
        else
            System.out.println(answer);
    }

    static void dfs(){
        for(int i=1; i<=n; i++){
            for(int j=i+1; j<=n;j ++) {
                if(friend[i][j]) {
                    for(int k =j+1; k<=n; k++){
                        if(friend[i][k] && friend[j][k]) {
                            answer = Math.min(answer, count[i] + count[j] + count[k] - 6);
                        }
                    }
                }
            }
        }
    }
}