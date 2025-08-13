
// 16536 KB, 128 ms
/*
 * 트리에서 최장 경로 구하기.
 * 
 * 1에서 출발해 dfs 한 번 돌아서 가장 깊은 leaf 를 구함
 * 해당 leaf 에서 dfs 한번 돌아서 최장 거리의 거리 구하기
 * 
 * 트리 표현 이게 최선이었을까?
 * 입력 받으면서 한번에 트리 구성까지 처리하려고 하니까 복잡해졌는데
 * 그냥 입력 쭉 받아놓고 처리 로직 추가했으면 훨씬 간단했을 것 같다.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    static int N, max_dist, max_vertex;
    static HashMap<Integer, ArrayList<int[]>> tree = new HashMap<>();
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inStr;
        int u, v, w;
        while ((inStr = br.readLine()) != null && !inStr.isEmpty()) {
            String[] input = inStr.split(" ");
            u = Integer.parseInt(input[0]);
            v = Integer.parseInt(input[1]);
            w = Integer.parseInt(input[2]);
            N = Math.max(N, u);
            N = Math.max(N, v);

            if (!tree.containsKey(u)) {
                ArrayList<int[]> temp = new ArrayList<>();
                temp.add(new int[] { v, w });
                tree.put(u, temp);
            } else {
                ArrayList<int[]> temp = tree.get(u);
                temp.add(new int[] { v, w });
            }

            if (!tree.containsKey(v)) {
                ArrayList<int[]> temp = new ArrayList<>();
                temp.add(new int[] { u, w });
                tree.put(v, temp);
            } else {
                ArrayList<int[]> temp = tree.get(v);
                temp.add(new int[] { u, w });
            }
        }

        if (N == 0)
            System.out.println(0);
        else {
            visit = new boolean[N + 1];
            visit[1] = true;
            dfs(0, 1);

            visit = new boolean[N + 1];
            visit[max_vertex] = true;
            dfs(0, max_vertex);

            System.out.println(max_dist);
        }
    }

    static void dfs(int dist, int vertex) {
        if (max_dist < dist) {
            max_dist = dist;
            max_vertex = vertex;
        }
        ArrayList<int[]> link = tree.get(vertex);
        for (int[] next : link) {
            if (visit[next[0]])
                continue;
            visit[next[0]] = true;
            dfs(dist + next[1], next[0]);
        }
    }
}