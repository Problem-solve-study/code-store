//13056KB	96ms
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] map;
    static final int WHITE=0, BLUE=1;
    static int white, blue;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for (int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j<n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        divideNconquer(0,0,n);
        System.out.println(white);
        System.out.print(blue);
    }


    static void divideNconquer(int y, int x, int size) {
        int color = map[y][x];

        // 탐색 범위 내에 색깔이 다른게 있으면 재귀 호출
        for (int i=y; i<y+size; i++) {
            for (int j=x; j<x+size; j++) {
                if (color != map[i][j]) {
                    divideNconquer(y, x, size / 2);
                    divideNconquer(y, x + size / 2, size / 2);
                    divideNconquer(y + size / 2, x, size / 2);
                    divideNconquer(y + size / 2, x + size / 2, size / 2);
                    return;
                }
            }
        }
        if (color == BLUE) blue++;
        else white++;
    }
}