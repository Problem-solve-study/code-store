// 37396 KB, 224 ms
/*
 * 힘 D 만큼 전단지를 전달할 수 있다. 실제로 안 가도 됨.
 * 최소한만 이동해서 모든 노드에 전단지를 돌리기.
 * 
 * 깊이 우선 탐색으로 이동한 경로 합인데, 리프 노드에서 D 만큼 덜 간 거리.
 * 
 * 현재 위치의 깊이를 저장
 * 이동한 노드의 깊이와 현재 위치의 차이가 D 초과면 이동.
 * 
 * 마지막에 다시 루트 노드까지 이동 거리 더하기.
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static int N, S, D;
    static int curDepth = -1, hyunminDepth, movingDist;
    static int[] depth;
    static Node[] tree;

    public static void main(String[] args) throws IOException {
        N = readInt();
        S = readInt();
        D = readInt();
        tree = new Node[N + 1];
        for (int i = 1; i <= N; i++)
            tree[i] = new Node();
        for (int i = 1; i < N; i++) {
            int x = readInt(), y = readInt();
            tree[x].link.add(y);
            tree[y].link.add(x);
        }

        depth = new int[N + 1];
        Arrays.fill(depth, -1);

        DFS(S);
        System.out.println(movingDist);
    }

    static void DFS(int x) {
        curDepth++;

        // 초기값이면 현재 깊이 기록
        if (depth[x] == -1)
            depth[x] = curDepth;

        // 만약 현민이의 힘으로 전단지 뿌릴 수 없는 위치면, 엉덩이가 무거운 현민이가 이동을 한다.
        if (curDepth - hyunminDepth > D) {
            hyunminDepth++;
            movingDist++;
        }

        for (int child : tree[x].link) {
            if (depth[child] != -1)
                continue;
            DFS(child);
            curDepth = depth[x];

            // 돌아가는 거리 계산
            if (hyunminDepth > curDepth) {
                movingDist += hyunminDepth - curDepth;
                hyunminDepth = curDepth;
            }
        }
    }

    static class Node {
        ArrayList<Integer> link = new ArrayList<>();
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