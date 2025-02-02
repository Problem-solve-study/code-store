// 15792KB	116ms

import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int[] a;
    static int v;
    static int s;
    static int[][] t;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        a = new int[n+1];
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
        p(0);
    }

    static void p(int i) {
        if (i == n) solve();
        else {
            for (int n = 1; n<= Main.n; n++) {
                if ((v & 1<<n) != 0) continue;
                v |= 1<<n;
                a[i+1] = n;
                p(i+1);
                v ^= 1<<n;
            }
        }
    }

    static void solve() {
        int res = 0;
        for (int i=0; i<n; i++) res += a[i+1] * t[s][i];
        if (res == m) {
            for (int i=0; i<n; i++) System.out.print(a[i+1]+" ");
            System.exit(0);
        }
    }
}