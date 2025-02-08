import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/*
180716KB, 984ms

맨 처음에는 그래프 탐색을 통한 완전탐색인가 싶었는데 바로 반례가 떠올랐다.
문제 조건을 째려보다보니 모든 정점을 이어야하는 문제이니(이것이 보장된다고 문제에도 서술되어 있고)
최소 스패닝 트리를  정렬 기준을 바꾸면서 2번 구하면 되는거 아닌가? 하는 생각이 들었다.
아무리 생각해도 맞는 풀이인거 같아서 그대로 구현해서 AC.
MST 구현은 정석이라고 할 수 있는 크루스칼로 구현
 */

public class Main {
    static int n, m;
    static int[] disjointSet;
    static ArrayList<int[]> adj = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int road = Integer.parseInt(st.nextToken());
            adj.add(new int[] {v1, v2, road});
        }

        int max = makeMst(Comparator.comparingInt(arr -> arr[2]));
        int min = makeMst(Comparator.comparingInt(arr -> arr[2] * -1));

        bw.write(String.valueOf(max - min));
        bw.flush();
    }

    public static int makeMst(Comparator<int[]> comparator) {
        disjointSet = new int[n + 1];
        IntStream.rangeClosed(0, n).forEach(idx -> disjointSet[idx] = idx);
        PriorityQueue<int[]> h = new PriorityQueue<>(comparator);
        h.addAll(adj);

        int res = 0;
        while (!h.isEmpty()) {
            int[] cur = h.remove();
            if (find(cur[0]) != find(cur[1])) {
                union(cur[0], cur[1]);
                res += (cur[2] + 1) % 2;
            }
        }

        return (int)Math.pow(res, 2);
    }

    public static int find(int i) {
        if (i == disjointSet[i]) {
            return i;
        }

        return disjointSet[i] = find(disjointSet[i]);
    }

    public static void union(int a, int b) {
        int aParent = find(a);
        int bParent = find(b);

        disjointSet[aParent] = bParent;
    }
}