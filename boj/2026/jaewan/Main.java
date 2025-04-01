// 12652 KB, 76 ms
/*
 * 1부터 N명의 학생 중 K명을 소풍 보낸다. (K <= N <= 900)
 * 소풍 갈 학생들 모두 서로 친구 사이인 K명을 선발해라. K <= 63
 * 
 * 
 * K, N, F
 * 
 * 친구는 양방향 관계, 
 * 
 * 차수가 K - 1 이상인 노드들을 선별.
 * 해당 노드들에 대해 백트래킹을 적용해 Fully Connected 한 K 개의 노드를 선택.
 * 
 * F <= 5,600인 희소 생렬이므로 많은 경우가 가지치기 될 것.
 * 정확히 계산? 최악의 경우.. N = 900, F = 5,600, K = 63.
 * C(90, 63) = ??? 안될것같은데..
 * 
 * 인접 행렬에 저장, 차수가 K - 1 이상인 노드들을 고름.
 * 
 * 그리고 하나씩 선택하며, 모두 연결된 노드들만 남기고 제외.
 * 만약, 남은 노드 부족해지면 가지치기.
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static int K, N, F, size;
    static int[] degree;
    static boolean[][] adjMatrix;
    static ArrayList<Integer> list = new ArrayList<>();
    static boolean[] select;

    public static void main(String[] args) throws IOException {
        K = readInt();
        N = readInt();
        F = readInt();

        degree = new int[N + 1];
        adjMatrix = new boolean[N + 1][N + 1];
        for (int i = 0; i < F; i++) {
            int u = readInt(), v = readInt();
            if (adjMatrix[u][v])
                continue;
            adjMatrix[u][v] = adjMatrix[v][u] = true;
            degree[u]++;
            degree[v]++;
        }

        for (int i = 1; i <= N; i++)
            if (degree[i] >= K - 1)
                list.add(i);
        size = list.size();
        select = new boolean[size];
        Arrays.fill(select, true);

        if (!func(new int[K], 0, 0, new boolean[size]))
            System.out.println(-1);
    }

    static boolean func(int[] res, int cnt, int idx, boolean[] isPossible) {
        if (cnt == K) {
            for (int i = 0; i < K; i++)
                System.out.println(res[i]);
            return true;
        }

        for (int i = idx; i < size; i++) {
            if (!isPossible[i]) // 선택 불가능하면 패스
                continue;

            res[cnt] = list.get(i); // 후보 list의 i 번째를 선택.

            boolean[] temp = new boolean[size]; // 후보 리스트 갱신, 선택된 요소와 연결되어 있는지 검사.
            int t1 = list.get(i), huboCnt = 0;
            for (int j = i + 1; j < size; j++) {
                if (!isPossible[j])
                    continue;

                int t2 = list.get(j);
                if (adjMatrix[t1][t2]) { // 선택한 i 와 j 가 연결되어 있는지
                    temp[j] = true;
                    huboCnt++;
                }
            }

            if (huboCnt + cnt < K) // 가능한 후보를 다 선택해도 K 개 안되면 가지치기
                continue;

            if (func(res, cnt + 1, i + 1, temp))
                return true;
        }
        return false;
    }

    static int readInt() throws IOException {
        int c;
        while ((c = System.in.read()) <= 32)
            ;
        int n = c & 15;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}