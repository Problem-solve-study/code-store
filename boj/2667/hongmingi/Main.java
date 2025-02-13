// 14308KB 112ms
import java.io.*;
import java.util.*;

/*
 * 간단한 dfs문제. ArrayList 말고 다른거 쓰면 시간 더 줄어드려나?
 */
public class Main {
    static int n, cnt;
    static char[][] map;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean[][] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> arr = new ArrayList<>();
        n = Integer.parseInt(br.readLine());
        map = new char[n][n];

        StringTokenizer st;
        for(int i=0; i<n; i++){
            map[i] = br.readLine().toCharArray();
        }

        visited = new boolean[n][n];

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(map[i][j]=='1'&&!visited[i][j]){
                    cnt=0;
                    dfs(i, j);
                    arr.add(cnt);
                }
            }
        }
        System.out.println(arr.size());
        Collections.sort(arr);
        for(int a : arr)    System.out.println(a);
    }    

    static void dfs(int i, int j){
        visited[i][j] = true;
        cnt++;
        for(int k=0; k<4; k++){
            if(i+dx[k]>=0 && i+dx[k]<n && j+dy[k]>=0 && j+dy[k]<n &&!visited[i+dx[k]][j+dy[k]] && map[i+dx[k]][j+dy[k]]=='1') dfs(i+dx[k], j+dy[k]);
        }
    }
}
