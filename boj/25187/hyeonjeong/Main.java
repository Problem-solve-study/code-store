// 91488KB, 1728ms
// 하하하 같은 파이프가 여러 번 오기도 하는구나!!

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] ids;
    static int[] count;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N, M, T;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken(" "));
        M = Integer.parseInt(st.nextToken(" "));
        T = Integer.parseInt(st.nextToken(" "));

        ids = new int[N + 1];
        count = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            ids[i] = i;
            count[i] = Integer.parseInt(st.nextToken()) == 1 ? 1 : -1;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int id1 = Integer.parseInt(st.nextToken(" "));
            int id2 = Integer.parseInt(st.nextToken(" "));

            union(id1, id2);
        }

        for (int i = 0; i < T; i++) {
            int target = Integer.parseInt(br.readLine());
            int root = find(target);
            if (count[root] > 0) {
                System.out.println("1");
                continue;
            }
            System.out.println("0");
        }
    }

    static int find(int x) {
        int prev;
        while (ids[x] != x) {
            prev = x;
            x = ids[x];
            ids[prev] = ids[x];
        }

        return x;
    }

    // x를 y에 붙임
    static void union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);

        if (xRoot == yRoot) {
            return;
        }

        count[yRoot] += count[xRoot];
        ids[xRoot] = yRoot;
    }
}