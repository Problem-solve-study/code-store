// 	33604KB	348ms

import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static Stack<Integer> a;
    static int v;
    static int s;
    static int[][] t;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        a = new Stack<>();
        t = new int[2][n];
        t[0][0] = 1;
        for (int i=0; i<n-1; i++) {
            for (int j=0; j<=i; j++) {
                t[(i+1)&1][j] += t[i&1][j];
                t[(i+1)&1][j+1] += t[i&1][j];
                t[i&1][j] = 0;
            }
            s = (i+1)&1;
        }
        v = 0;
        p(n);
    }

    static void p(int r) {
        if (r == 0) solve();
        else {
            for (int i=1; i<=n; i++) {
                if ((v & 1<<i) != 0) continue;
                a.push(i);
                v |= 1<<i;
                p(r-1);
                a.pop();
                v ^= 1<<i;
            }
        }
    }

    static void solve() {
        int res = 0;
        int i = 0;
        for (int x: a) res += x * t[s][i++];
        if (res == m) {
            for (int x: a) System.out.print(x+" ");
            System.exit(0);
        }
    }
}