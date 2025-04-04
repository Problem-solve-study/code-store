
// 22840KB	616ms
import java.io.*;
import java.util.*;

/**
 * 간선개수가 500이어서 백트래킹해도 되지 않을까 했는데, 방문했던 마을에 다시 방문할 수 있음 -> 시간초과 1틀
 * 백트래킹에서 반복되는 불필요한 연산 부분을 줄이기 위해 메모이제이션을 고려함
 * dp[마을번호][지나온 횟수] = 점수의 최댓값
 */
public class Main {
    static int n, m, k;
    static char[] color;
    static ArrayList<Node>[] map;
    static int answer = 0;
    static int[][] dp;

    static class Node {
        int pos;
        char color;

        Node(int pos, char color) {
            this.pos = pos;
            this.color = color;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        color = new char[n];
        for (int i = 0; i < n; i++) {
            color[i] = st.nextToken().charAt(0);
        }
        st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new ArrayList[k + 1];
        for (int i = 1; i <= k; i++) {
            map[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);
            map[a].add(new Node(b, c));
            map[b].add(new Node(a, c));
        }
        dp = new int[k + 1][n];
        for (int i = 1; i <= k; i++) {
            Arrays.fill(dp[i], -1);
        }
        dfs(1, 0);
        System.out.print(dp[1][0]);
    }

    static int dfs(int now, int cnt) {
        if (cnt == n) {
            return 0;
        }
        if (dp[now][cnt] != -1) {
            return dp[now][cnt];
        }
        int result = 0;
        for (Node next : map[now]) {
            if (next.color == color[cnt]) {
                result = Math.max(result, dfs(next.pos, cnt + 1) + 10);
            } else {
                result = Math.max(result, dfs(next.pos, cnt + 1) + 0);
            }
        }
        dp[now][cnt] = result;
        return result;
    }
}