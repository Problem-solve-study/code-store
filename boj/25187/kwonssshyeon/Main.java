// 80820KB	644ms

import java.io.*;
import java.util.*;


public class Main {
    static int n,m,q;
    static int[] tanks;
    static int[] root;
    static int[] count;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        tanks = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for (int i=1; i<=n ; i++) {
            tanks[i] = Integer.parseInt(st.nextToken());
        }

        root = new int[n+1];
        for (int i=1; i<=n; i++) {
            root[i] = i;
        }

        for (int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            merge(a, b);
        }

        count = new int[n+1];

        init();

        for (int i=0; i<q; i++) {
            int input = Integer.parseInt(br.readLine());
            int group = findRoot(input);
            String result = count[group] > 0 ? "1\n" : "0\n";
            bw.append(result);
        }
        bw.flush();
    }


    static void init() {
        for (int i=1; i<=n; i++) {
            int group = findRoot(i);
            if (tanks[i] > 0) count[group]++;
            else count[group]--;
        }
    }


    static void merge(int a, int b) {
        int rA = findRoot(a);
        int rB = findRoot(b);
        if (rA == rB) return;
        if (rA < rB) {
            root[rB] = rA;
        }
        else {
            root[rA] = rB;
        }
    }

    static int findRoot(int node) {
        while (node != root[node]) {
            node = root[node];
        }
        return node;
    }
}