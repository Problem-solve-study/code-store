
//15572KB, 140ms
import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[][] map;
    static int blue, white;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        blue = 0;
        white = 0;
        divMap(0, 0, N);
        sb.append(white).append('\n').append(blue).append('\n');
        System.out.println(sb.toString());
    }

    public static void divMap(int start_x, int start_y, int size) {
        if (checkedMap(start_x, start_y, size))
            return;

        int half = size / 2;
        divMap(start_x, start_y, half);
        divMap(start_x, start_y + half, half);
        divMap(start_x + half, start_y, half);
        divMap(start_x + half, start_y + half, half);
    }

    public static boolean checkedMap(int start_x, int start_y, int size) {
        int flag = map[start_x][start_y];
        boolean isSame = true;
        for (int i = start_x; i < start_x + size; i++) {
            for (int j = start_y; j < start_y + size; j++) {
                if (flag != map[i][j]) {
                    isSame = false;
                    break;
                }
            }
        }
        if (isSame) {
            if (flag == 1)
                blue++;
            else if (flag == 0)
                white++;
        }
        return isSame;
    }
}
