/* 
 * 연결된 도시 입력 받고
 * 각 도시 돌며 인접한 도시까지 파괴된 도시에 포함되면 폭탄이 떨어진 도시 목록에 추가 
 * 하고 표시
 * 
 * 끝까지 하고 파괴된 도시가 전부 표시됐으면 출력
 * 아니면 -1 출력
 */

import java.io.IOException;
import java.util.ArrayList;

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
            int u = readInt(), v = readInt();
            graph[u].link.add(v);
            graph[v].link.add(u);
        }

        int K = readInt();
        int[] list = new int[K];
        boolean[] destroyed = new boolean[2001], check = new boolean[2001];
        for (int i = 0; i < K; i++) {
            list[i] = readInt();
            destroyed[list[i]] = true;
        }

        ArrayList<Integer> result = new ArrayList<>();
        // 폭탄 떨굴 도시 찾기
        for (int i = 1; i <= N; i++) {
            if (!destroyed[i])
                continue;
            boolean flag = true;
            for (Integer next : graph[i].link) {
                if (!destroyed[next]) {
                    flag = false;
                    break;
                }
            }
            if (!flag)
                continue;

            result.add(i);
            check[i] = true;
            for (Integer next : graph[i].link)
                check[next] = true;
        }

        boolean flag = true;
        for (int i = 0; i < K; i++) {
            if (!check[list[i]]) {
                flag = false;
                break;
            }
        }

        StringBuilder sb = new StringBuilder();
        if (flag) {
            sb.append(result.size()).append('\n');
            for (Integer integer : result) {
                sb.append(integer).append(' ');
            }
        } else
            sb.append(-1);
        System.out.println(sb);
    }

    static class Node {
        ArrayList<Integer> link = new ArrayList<>();
    }

    static int pos, len;
    static byte[] buf = new byte[8192];

    static byte read() throws IOException {
        if (pos == len) {
            len = System.in.read(buf);
            pos = 0;
        }
        return buf[pos++];
    }

    static int readInt() throws IOException {
        int c;
        while ((c = read()) <= 32)
            ;
        int n = c & 15;
        while ((c = read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}