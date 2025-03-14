
/*
 * Degree>=2인 노드를 선택하고, 한 친구를 선택.
 * 둘 중 친구 수가 작은 사람의 친구 목록을 보며 친구 관계인 세사람을 고름.
 * 친구 수의 최솟값 찾기.
 */
import java.io.IOException;
import java.util.HashSet;

public class Main {
    static int N, M;
    static Node[] graph;

    public static void main(String[] args) throws IOException {
        N = readInt();
        M = readInt();
        graph = new Node[N + 1];
        for (int i = 1; i <= N; i++)
            graph[i] = new Node();
        for (int i = 0; i < M; i++) {
            int a = readInt(), b = readInt();
            graph[a].friends.add(b);
            graph[b].friends.add(a);
        }

        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            int A, B, C;
            // 최소 친구가 2명 있어야 함.
            if (graph[i].friends.size() < 2)
                continue;
            A = i;
            for (int t : graph[i].friends) {
                if (graph[t].friends.size() < 2)
                    continue;
                B = t;
                // A와 B 중 친구 수가 작은 놈의 친구 목록을 탐색하며 C가 될 만한 녀석을 선택
                HashSet<Integer> tempSet = graph[A].friends.size() < graph[B].friends.size() ? graph[A].friends
                        : graph[B].friends;
                for (int temp : tempSet) {
                    if (A == temp || B == temp)
                        continue;
                    // A의 친구 중 후보 선택한 경우, B와 친구가 아니면 탈락
                    if (graph[A].friends.size() < graph[B].friends.size()) {
                        if (!graph[B].friends.contains(temp))
                            continue;
                        C = temp;
                        min = Math.min(min,
                                graph[A].friends.size() + graph[B].friends.size() + graph[C].friends.size());
                    } else {
                        if (!graph[A].friends.contains(temp))
                            continue;
                        C = temp;
                        min = Math.min(min,
                                graph[A].friends.size() + graph[B].friends.size() + graph[C].friends.size());
                    }
                }
            }
        }
        System.out.println(min == Integer.MAX_VALUE ? -1 : min - 6);
    }

    static class Node {
        HashSet<Integer> friends = new HashSet<>();
    }

    static int readInt() throws IOException {
        int c;
        do {
            c = System.in.read();
        } while (c <= 32);
        int n = c & 15;
        c = System.in.read();
        while (c > 47) {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return n;
    }
}