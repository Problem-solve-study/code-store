//제출번호: 89591498
//메모리:   217000 KB
//실행시간: 2892 ms
import java.io.*;
import java.util.*;

public class Main {

    static boolean[] waterTank = new boolean[100001];
    static List<List<Integer>> pipes = new ArrayList<>(100001);
    static boolean[] visited = new boolean[100001];
    static boolean[] canCleanWater = new boolean[100001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            waterTank[i] = st.nextToken().equals("1");
            pipes.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            pipes.get(x).add(y);
            pipes.get(y).add(x);
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                bfs(i);
            }
        }

        for (int i = 0; i < q; i++) {
            bw.write(String.format("%d%n", canCleanWater[Integer.parseInt(br.readLine()) - 1] ? 1 : 0));
        }

        bw.flush();
    }

    static void bfs(int k) {
        Queue<Integer> queue = new ArrayDeque<>();
        List<Integer> visitedNodes = new ArrayList<>();

        int clean = 0, dirty = 0;

        queue.add(k);
        visited[k] = true;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            visitedNodes.add(node);
            
            if (waterTank[node]) {
                ++clean;
            } else {
                ++dirty;
            }

            for (int nextNode : pipes.get(node)) {
                if (!visited[nextNode]) {
                    visited[nextNode] = true;
                    queue.add(nextNode);
                }
            }
        }

        for (int node : visitedNodes) {
            canCleanWater[node] = clean > dirty;
        }
    }
}