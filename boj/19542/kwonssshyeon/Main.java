// 65940KB	428ms (입출력 최적화로 224ms 까지 단축가능)
import java.io.*;
import java.util.*;

/**
 * 리프노드부터 현재노드까지의 거리를 구해야한다.
 * 자식 노드의 높이중 최댓값에 + 1 을 하는 식으로 전체 트리의 높이를 재귀적으로 계산
 * 높이가 d 보다 작거나 같은 경우 방문할 필요가 없다.
 */
public class Main {
    static int n, s, d;
    static ArrayList<Integer>[] tree;
    static boolean[] visited;
    static int count;
    static int[] height;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        tree = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree[a].add(b);
            tree[b].add(a);
        }
        visited = new boolean[n + 1];
        height = new int[n + 1];

        visited[s] = true;
        dfs(s, 0);

        System.out.print(count * 2);
    }

    static int dfs(int now, int cnt) {
        int maxHeight = 0;
        for (int next : tree[now]) {
            if (!visited[next]) {
                visited[next] = true;
                int childHeight = dfs(next, cnt + 1); // 자식 노드의 높이를 구한 후
                maxHeight = Math.max(maxHeight, childHeight); // 그 중 가장 큰 것을 부모의 높이로 정함
                visited[next] = false;
            }
        }
        height[now] = maxHeight + 1; // 자식 + 1로 갱신

        if (now != s && height[now] > d) { // 시작점이거나, 높이가 d 이하이면 이동횟수에 포함하지 않는다.
            count++;
        }
        return height[now];
    }
}
