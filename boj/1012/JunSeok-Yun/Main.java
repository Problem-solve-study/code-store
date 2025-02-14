//16100KB, 140ms
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
    static int T, N, M, cnt;
    static int bugCnt;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for (int test_case = 0; test_case < T; test_case++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            cnt = Integer.parseInt(st.nextToken());
            map = new int[M][N];
            visited = new boolean[M][N];
    
            for (int i = 0; i < cnt; i++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                map[x][y] = 1;
            }
    
            bugCnt = 0;
            for (int i = 0; i < M; i++){
                for (int j = 0; j < N; j++){
                    if (map[i][j] != 0 && !visited[i][j]){
                        bugCnt++;
                        bfs(i, j);
                    }
                }
            }
            sb.append(bugCnt).append('\n');
        }
        System.out.print(sb.toString());
    }

    public static boolean isValidation(int nx, int ny){
        return (nx >= 0 && ny >= 0 && nx < M && ny < N);
    }

    public static void bfs(int i, int j){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(i, j));
        visited[i][j] = true;

        while (!q.isEmpty()){
            Node node = q.poll();
            for (int k = 0; k < 4; k++){
                int nx = node.x + dx[k];
                int ny = node.y + dy[k];
                if (isValidation(nx, ny) && map[nx][ny] != 0 && !visited[nx][ny]){
                    q.offer(new Node(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }
    }
}