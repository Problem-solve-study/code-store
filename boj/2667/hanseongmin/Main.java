import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/*
18560KB, 184ms

그래프 탐색을 수행하며 군집 개수 + 크기 측정하여 출력하기
 */

public class Main {
    static int n;
    //값이 0 1 두 개이므로 boolean로 선언
    static boolean[][] map, visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        map = new boolean[n][n];
        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = line.charAt(j) == '1';
            }
        }

        int cnt = 0;
        //단지 개수는 넉넉하게 1000개로 할당
        int[] arr = new int[1000];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //방문 안한 집이면 그래프 탐색 수행
                if (!visited[i][j] && map[i][j]) {
                    arr[cnt++] = bfs(i, j);
                }
            }
        }
        
        //[0 ~ cnt) 범위 내에서 정렬
        Arrays.sort(arr, 0, cnt);
        StringBuilder sb = new StringBuilder();
        sb.append(cnt).append('\n');
        IntStream.range(0, cnt).forEach(e -> sb.append(arr[e]).append('\n'));
        bw.write(sb.toString());
        bw.flush();
    }

    static int bfs(int r, int c) {
        int res = 0;
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {r, c});
        visited[r][c] = true;

        while (!q.isEmpty()) {
            int[] cur = q.remove();
            res++;
            for (int i = 0; i < dr.length; i++) {
                int nextR = cur[0] + dr[i];
                int nextC = cur[1] + dc[i];
                if (boundaryCheck(nextR, nextC) && !visited[nextR][nextC] && map[nextR][nextC]) {
                    q.add(new int[]{nextR, nextC});
                    visited[nextR][nextC] = true;
                }
            }
        }

        return res;
    }

    static boolean boundaryCheck(int r, int c) {
        return (0 <= r && r < n) && (0 <= c && c < n);
    }
}