import java.io.*;
import java.util.*;

/*
40404KB, 192ms

웰노운 BFS 응용 문제
문제를 일직선으로 연결된 그래프에서의 탐색으로 바꿀 수 있고,
그래프에서의 최단 거리 탐색은 일반적으로 BFS로 수행하므로 BFS로 해결할 수 있다.
가지치기를 안하면 메모리가 터지는듯
 */

public class Main {
    static Queue<int[]> q = new ArrayDeque<>();
    static HashSet<Integer> visited = new HashSet<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        q.add(new int[] {n, 0});
        visited.add(n);

        int res = -1;
        while (!q.isEmpty()) {
            int[] cur = q.remove();
            if (cur[0] == k) {
                res = cur[1];
                break;
            }

            //음수로는 갈 필요가 없음
            if (cur[0] >= 0)
                addQ(cur[0] - 1, cur[1] + 1);
            //앞으로가는 경우 k보다 클 경우 갈 필요가 없음
            if (cur[0] < k) {
                addQ(cur[0] + 1, cur[1] + 1);
                addQ(2 * cur[0], cur[1] + 1);
            }
        }

        bw.write(String.valueOf(res));
        bw.flush();
    }

    static void addQ(int pos, int time) {
        if (!visited.contains(pos)) {
            q.add(new int[] {pos, time});
            visited.add(pos);
        }
    }
}