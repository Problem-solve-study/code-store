// 11972KB	68ms
import java.io.*;
import java.util.*;

public class Main {
    static int k,m,p;
    static int[] step;
    static ArrayList<Integer>[] map;
    static ArrayList<Integer>[] rmap;
    static boolean[] visited;
    static int[] strahler;
    static Queue<Integer> queue = new ArrayDeque<>();
    static int[] indegree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t=0; t<T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            p = Integer.parseInt(st.nextToken());
            map = new ArrayList[m+1];
            rmap = new ArrayList[m+1];
            for (int i=1; i<=m; i++) {
                map[i] = new ArrayList<>();
                rmap[i] = new ArrayList<>();
            }
            indegree = new int[m+1];
            for (int i=0; i<p; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                map[a].add(b);
                rmap[b].add(a);
                indegree[b] += 1;
            }
            visited = new boolean[m+1];
            strahler = new int[m+1];

            bfs();

            sb.append(String.format("%d %d\n", k, strahler[m]));
        }
        System.out.print(sb);
    }


    static void bfs() {
        for (int i=1; i<=m; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int now = queue.poll();
            strahler[now] = calcStrahler(now);
            for (int next : map[now]) {
                indegree[next] -= 1;
                if (indegree[next] == 0) {
                    queue.add(next);
                }
            }
        }
    }

    static int calcStrahler(int now) {
        if (rmap[now].size() == 0) return 1;
        int result = 0, count = 0;
        for (int node : rmap[now]) {
            if (result < strahler[node]) {
                count = 1;
                result = strahler[node];
            }
            else if (result == strahler[node]) {
                count++;
            }
        }
        return (count > 1) ? result + 1 : result;
    }
}