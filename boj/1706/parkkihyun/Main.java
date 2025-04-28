import java.io.*;
import java.util.*;

// 사이클이 주어지고 몇 번 반복되냐를 묻는 문제

class Main {

    static int N, M, V;

    static int[] list;

    public static void main(String[] args) throws Exception {
        N = ni();
        M = ni();
        V = ni();

        list = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            int c = ni();
            list[i] = c;
        }

        // diff는 사이클에 포함된 노드의 개수
        int diff = N - V + 1;

        while (M-- > 0) {
            // ni() + 1이 노드의 번호임
            int k = ni() + 1;
            if (k <= N) {
                // N보다 작거나 같다면 그냥 리스트의 값
                sb.append(list[k]).append('\n');
            } else {
                // N보다 크다면 달팽이 껍질 어디에 있는지 골라야함
                k -= (V);
                k %= diff;
                k += (V);

                sb.append(list[k]).append('\n');
            }
        }

        System.out.println(sb.toString());
    }

    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tks;

    static String ns() throws Exception {
        if (tks == null || !tks.hasMoreTokens())
            tks = new StringTokenizer(br.readLine());
        return tks.nextToken();
    }

    static int ni() throws Exception {
        return Integer.parseInt(ns());
    }
}
