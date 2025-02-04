//	13728KB	408ms

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] A;
    static int res;
    static int[] X;
    static int v;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N][9];
        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j<9; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        X = new int[9];
        v = 1;
        solve(0);
        System.out.println(res);
    }
    static void solve(int i) {
        if (i == 9) run();
        if (i == 3) solve(i+1);
        else {
            for (int j=0; j<9; j++) {
                if ((v&(1<<j)) > 0) continue;
                v |= 1<<j;
                X[i] = j;
                solve(i+1);
                v ^= 1<<j;
            }
        }
    }

    static void run() {
        int[] g = new int[3];
        int s = 0, j = 0;
        for (int i=0; i<N; i++) {
            int o = 0;
            g[0] = g[1] = g[2] = 0;
            while (o<3) {
                int a = A[i][X[j]];
                if (a==1) {
                    s += g[2];
                    g[2] = g[1];
                    g[1] = g[0];
                    g[0] = 1;
                }
                else if (a==2) {
                    s += g[2] + g[1];
                    g[2] = g[0];
                    g[1] = 1;
                    g[0] = 0;
                }
                else if (a==3) {
                    s += g[2] + g[1] + g[0];
                    g[2] = 1;
                    g[1] = 0;
                    g[0] = 0;
                }
                else if (a==4) {
                    s += g[2] + g[1] + g[0] + 1;
                    g[2] = 0;
                    g[1] = 0;
                    g[0] = 0;
                }
                else {
                    o++;
                }
                j = (j+1)%9;
            }
        }
        res = Math.max(res, s);
    }
}