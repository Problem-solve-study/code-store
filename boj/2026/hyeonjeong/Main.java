// 18288KB 280ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// DFS
// 내 친구가 아닌 애들은 후보에서 제외하며 탐색
public class Main {
    static int n;
    static int k;
    static boolean[][] friends;
    static boolean[] visitable;
    static boolean[] visited;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        k = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        int f = Integer.parseInt(st.nextToken());

        friends = new boolean[n][n];
        for (int i = 0; i < f; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            
            friends[u][v] = true;
            friends[v][u] = true;
        }

        visited = new boolean[n];
        visitable = new boolean[n];
        for (int i = 0; i < n; i++) {
            visitable[i] = true;
        }

        boolean succeed = dfs(-1, 0);

        if (!succeed) {
            System.out.println(-1);
        }
    }

    static boolean dfs(int pi, int depth) {
        if (depth == k) {
            display();
            return true;
        }

        boolean result = false;
        for (int nextFriend = pi + 1; nextFriend < n; nextFriend++) {
            if (!visitable[nextFriend]) {
                continue;
            }

            visitable[nextFriend] = false;
            visited[nextFriend] = true;
            // 다음 친구와 친구가 아닌 사람들은 후보에서 제외
            List<Integer> memo = new ArrayList<>();
            for (int notOur = 0; notOur < n; notOur++) {
                if (visitable[notOur] && !friends[nextFriend][notOur]) {
                    visitable[notOur] = false;
                    memo.add(notOur);
                }
            }
            boolean succeed = dfs(nextFriend, depth + 1);
            for (int i : memo) {
                visitable[i] = true;
            }
            visited[nextFriend] = false;
            visitable[nextFriend] = true;

            if (succeed) {
                result = succeed;
                break;
            }
        }

        return result;
    }

    static void display() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                sb.append(i + 1).append('\n');
            }
        }
        System.out.println(sb);
    }
}
