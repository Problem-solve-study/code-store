//	11676KB	84ms
import java.io.*;
import java.util.*;

/**
 * 그래프 탐색을 하며 연결된 컴포넌트를 찾는 문제
 * -> DFS
 */

public class Main {
    static int n;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = new int[] {1,0,0,-1};
    static int[] dy = new int[] {0,1,-1,0};
    static int answer;
    static ArrayList<Integer> list = new ArrayList<>();
    

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new char[n][n];
        for (int i=0; i<n; i++) {
            String line = br.readLine();
            for (int j=0; j<n; j++) {
                map[i][j] = line.charAt(j);
            }
        }
        visited = new boolean[n][n];
        int count = 0;
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if (!visited[i][j] && map[i][j] == '1') {
                    count++;
                    dfs(i, j);
                    list.add(answer);
                    answer = 0;
                }
            }
        }
        Collections.sort(list);
        System.out.println(count);
        for (int li : list) {
            System.out.println(li);
        }
    }

    static void dfs(int y, int x) {
        visited[y][x] = true;
        answer++;

        for (int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (ny>=0 && ny<n && nx>=0 && nx<n) {
                if (!visited[ny][nx] && map[ny][nx] == '1') {
                    dfs(ny, nx);
                }
            }
        }

    }
}