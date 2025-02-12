// 64540KB	884ms
import java.io.*;
import java.util.*;

/**
 * 빡센 구현 문제
 * k = 5 ~ 8 일때는 1 ~ 4 의 함수를 조합하여 구현할 수 있음
 * 다시는 못풀겠다 ...
 */
public class Main {
    static int n, r;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        n = 1 << n;
        map = new int[n][n];
        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i=0; i<r; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            l = 1 << l;
            if (k == 1) k1(l);
            else if (k == 2) k2(l);
            else if (k == 3) k3(l);
            else if (k == 4) k4(l);
            else if (k == 5) k5(l);
            else if (k == 6) k6(l);
            else if (k == 7) k7(l);
            else if (k == 8) k8(l);
        }
        print();
    }

    static void print() {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                sb.append(map[i][j]+" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static void k1(int l) {
        if (l == 1) return;
        for (int i=0; i<=n-l; i+=l) {
            for (int j=0; j<l/2; j++) {
                for (int x=0; x<n; x++) {
                    int temp = map[i+j][x];
                    map[i+j][x] = map[(i+l)-j-1][x];
                    map[(i+l)-j-1][x] = temp;
                }
            }
        }
    }
    
    static void k2(int l) {
        if (l == 1) return;
        for (int i=0; i<=n-l; i+=l) {
            for (int j=0; j<l/2; j++) {
                for (int y=0; y<n; y++) {
                    int temp = map[y][i+j];
                    map[y][i+j] = map[y][(i+l)-j-1];
                    map[y][(i+l)-j-1] = temp;
                }                
            }
        }
    }

    static void k3(int l) {
        if (l == 1) return;
        int[][] copy = new int[n][n];
        for (int sy=0; sy<=n-l; sy+=l) {
            for (int sx=0; sx<=n-l; sx+=l) {
                for (int i = sy; i < sy+l; i++) {
                    for (int j = sx; j < sx+l; j++) {
                        copy[j-sx+sy][l-1-i+sy+sx] = map[i][j];
                    }
                }
            }
        }
        map = copy;
    }

    static void k4(int l) {
        if (l == 1) return;
        int[][] copy = new int[n][n];
        for (int sy=0; sy<=n-l; sy+=l) {
            for (int sx=0; sx<=n-l; sx+=l) {
                for (int i = sy; i < sy+l; i++) {
                    for (int j = sx; j < sx+l; j++) {
                        copy[l-1-j+sy+sx][i-sy+sx] = map[i][j];
                    }
                }
            }
        }
        map = copy;
    }

    static void k5(int l) {
        k1(n);
        k1(l);
    }

    static void k6(int l) {
        k2(n);
        k2(l);
    }

    static void k7(int l) {
        int[][] copy = new int[n][n];
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                copy[j][n-1-i] = map[i][j];
            }
        }
        for (int sy=0; sy<=n-l; sy+=l) {
            for (int sx=0; sx<=n-l; sx+=l) {
                for (int i = sy; i < sy+l; i++) {
                    for (int j = sx; j < sx+l; j++) {
                        map[l-1-j+sy+sx][i-sy+sx] = copy[i][j];
                    }
                }
            }
        }
    }

    static void k8(int l) {
        int[][] copy = new int[n][n];
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                copy[n-1-j][i] = map[i][j];
            }
        }
        for (int sy=0; sy<=n-l; sy+=l) {
            for (int sx=0; sx<=n-l; sx+=l) {
                for (int i = sy; i < sy+l; i++) {
                    for (int j = sx; j < sx+l; j++) {
                        map[j-sx+sy][l-1-i+sy+sx] = copy[i][j];
                    }
                }
            }
        }
    }
}