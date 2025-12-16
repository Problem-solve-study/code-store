// 604508KB 2064ms

import java.util.*;
import java.io.*;

// 11차원 BFS
class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        int m = next();
        int n = next();
        int o = next();
        int p = next();
        int q = next();
        int r = next();
        int s = next();
        int t = next();
        int u = next();
        int v = next();
        int w = next();

        int[][][][][][][][][][][] tomatos = new int[m][n][o][p][q][r][s][t][u][v][w];
        Deque<int[]> queue = new ArrayDeque<>();
        int ripeCount = 0;
        int emptyCount = 0;
        for (int k = 0; k < w; k++) {
        for (int j = 0; j < v; j++) {
        for (int i = 0; i < u; i++) {
        for (int h = 0; h < t; h++) {
        for (int g = 0; g < s; g++) {
        for (int f = 0; f < r; f++) {
        for (int e = 0; e < q; e++) {
        for (int d = 0; d < p; d++) {
        for (int c = 0; c < o; c++) {
        for (int b = 0; b < n; b++) {
        for (int a = 0; a < m; a++) {
            int tomato = next();

            tomatos[a][b][c][d][e][f][g][h][i][j][k] = tomato;
            if (tomato == 1) {
                ripeCount++;
                queue.add(new int[]{a,b,c,d,e,f,g,h,i,j,k});
            }
            if (tomato == -1) {
                emptyCount++;
            }
        }}}}}}}}}}};

        int time = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int sz = 0; sz < size; sz++) {
                int[] curr = queue.poll();
                
                for (int delta = 0; delta < 11; delta++) {
                    int a = curr[0] + (delta == 0 ? 1 : 0);
                    int b = curr[1] + (delta == 1 ? 1 : 0);
                    int c = curr[2] + (delta == 2 ? 1 : 0);
                    int d = curr[3] + (delta == 3 ? 1 : 0);
                    int e = curr[4] + (delta == 4 ? 1 : 0);
                    int f = curr[5] + (delta == 5 ? 1 : 0);
                    int g = curr[6] + (delta == 6 ? 1 : 0);
                    int h = curr[7] + (delta == 7 ? 1 : 0);
                    int i = curr[8] + (delta == 8 ? 1 : 0);
                    int j = curr[9] + (delta == 9 ? 1 : 0);
                    int k = curr[10] + (delta == 10 ? 1 : 0);

                    if (a >= 0 && a < m
                        && b >= 0 && b < n
                        && c >= 0 && c < o
                        && d >= 0 && d < p
                        && e >= 0 && e < q
                        && f >= 0 && f < r
                        && g >= 0 && g < s
                        && h >= 0 && h < t
                        && i >= 0 && i < u
                        && j >= 0 && j < v
                        && k >= 0 && k < w
                        && tomatos[a][b][c][d][e][f][g][h][i][j][k] == 0) {
                        ripeCount++;
                        tomatos[a][b][c][d][e][f][g][h][i][j][k] = 1;
                        queue.add(new int[]{a,b,c,d,e,f,g,h,i,j,k});
                    }

                    a = curr[0] + (delta == 0 ? -1 : 0);
                    b = curr[1] + (delta == 1 ? -1 : 0);
                    c = curr[2] + (delta == 2 ? -1 : 0);
                    d = curr[3] + (delta == 3 ? -1 : 0);
                    e = curr[4] + (delta == 4 ? -1 : 0);
                    f = curr[5] + (delta == 5 ? -1 : 0);
                    g = curr[6] + (delta == 6 ? -1 : 0);
                    h = curr[7] + (delta == 7 ? -1 : 0);
                    i = curr[8] + (delta == 8 ? -1 : 0);
                    j = curr[9] + (delta == 9 ? -1 : 0);
                    k = curr[10] + (delta == 10 ? -1 : 0);

                    if (a >= 0 && a < m
                        && b >= 0 && b < n
                        && c >= 0 && c < o
                        && d >= 0 && d < p
                        && e >= 0 && e < q
                        && f >= 0 && f < r
                        && g >= 0 && g < s
                        && h >= 0 && h < t
                        && i >= 0 && i < u
                        && j >= 0 && j < v
                        && k >= 0 && k < w
                        && tomatos[a][b][c][d][e][f][g][h][i][j][k] == 0) {
                        ripeCount++;
                        tomatos[a][b][c][d][e][f][g][h][i][j][k] = 1;
                        queue.add(new int[]{a,b,c,d,e,f,g,h,i,j,k});
                    }
                }
            }

            time++;
        }

        if (ripeCount + emptyCount != m * n * o * p * q * r * s * t * u * v * w) {
            System.out.println(-1);
            return;
        }

        System.out.println(time - 1);
    }

    static int next() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
