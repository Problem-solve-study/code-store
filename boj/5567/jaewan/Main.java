/*
 * 자신의 친구와 친구의 친구를 초대
 * 동기 N명
 * 학번 1부터 N까지, 김싸피는 1번
 * 
 * 친구거나, 친구의 친구까지만.
 * 인접 리스트로 구현
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
        boolean[] visit = new boolean[N + 1];
        HashSet<Integer> friendOfKimSsafy = new HashSet<>();
        for (int i = 1; i <= N; i++)
            graph[i] = new Node();
        for (int i = 0; i < M; i++) {
            int a = readInt(), b = readInt();
            graph[a].friends.add(b);
            graph[b].friends.add(a);
            if (a == 1 && !visit[b]) {
                visit[b] = true;
                friendOfKimSsafy.add(b);
            }
        }
        visit[1] = true;
        int res = friendOfKimSsafy.size();
        for (int t : friendOfKimSsafy) {
            for (int next : graph[t].friends) {
                if (visit[next])
                    continue;
                visit[next] = true;
                res++;
            }
        }
        System.out.println(res);
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
