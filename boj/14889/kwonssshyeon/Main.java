// 14396KB	272ms
import java.io.*;
import java.util.*;

/**
 * 두 팀의 차를 최소로 하는 경우를 구하는 문제.
 * visited 배열을 이용해 팀을 구분 (true or false)
 * 2중 for문을 이용해 모든 쌍을 탐색하며 점수를 갱신한다.
 */
public class Main {
    static int n, map[][];
    static boolean[] visited;
    static int answer = Integer.MAX_VALUE;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for (int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j<n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        visited = new boolean[n];
        dfs(0,0);
        System.out.print(answer);
    }

    static void dfs(int depth, int idx) {
        if (depth == n / 2) {
            calculate();
            return;
        }

        for (int i = idx; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(depth + 1, i + 1);
                visited[i] = false;
            }
        }
    }

    static void calculate() {
        int start = 0, link = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i] && visited[j]) start += map[i][j];
                if (!visited[i] && !visited[j]) link += map[i][j];
            }
        }
        answer = Math.min(answer, Math.abs(start - link));
    }
}