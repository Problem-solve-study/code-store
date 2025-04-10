// 46320 KB, 1492 ms
/*
 * 크기가 M x M 인 격자 형태의 벌집
 * 
 * 애벌레들이 매일 에너지를 모아서 정오에 한번 자람..
 * 
 * 각 애벌레가 크기가 커지는 정도는 하루에 +0, +1, +2 세 가지 중 하나다.
 * 
 * 자라는 정도를 정하는 규칙
 * 1. 제일 왼쪽 열과, 제일 위쪽 행의 애벌레들은 자신이 자라는 정도를 스스로 결정. 
 * 감소하지 않는 형태?
 * 2. 나머지 애벌레들은 자신의 왼쪽, 왼쪽 위, 위쪽의 애벌레들이 다 자란 다음, 그 날 가장 많이 자란 애벌레가 자란 만큼 자람.
 * 
 * O(M^2 * N) = 1,000,000 * 490,000 = 500,000,000,000 ??
 * ??
 * 
 * 실제로 날짜별로 다 더하지 않고 제일 왼쪽, 위쪽의 증가량을 다 더하고
 * 나중에 한 번에 더함.
 * 
 * 
 */

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        int N = readInt(), M = readInt();
        int SIZE = 2 * N - 1;
        int[] add = new int[SIZE];
        for (int i = 0; i < M; i++) {
            int num0 = readInt(), num1 = readInt(), num2 = readInt(), idx = 0;
            for (; num0 > 0; num0--, idx++)
                ;
            while (num0-- > 0)
                idx++;
            while (num1-- > 0)
                add[idx++] += 1;
            while (num2-- > 0)
                add[idx++] += 2;
        }

        int[][] map = new int[N][N];
        int idx = 0;
        for (int i = N - 1; i >= 0; i--)
            map[i][0] = 1 + add[idx++];
        for (int j = 1; j < N; j++)
            map[0][j] = 1 + add[idx++];

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < N; j++) {
                map[i][j] = Math.max(map[i - 1][j], map[i][j - 1]);
                map[i][j] = Math.max(map[i - 1][j - 1], map[i][j]);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                sb.append(map[i][j]).append(' ');
            sb.append('\n');
        }
        System.out.println(sb.toString());
    }

    static int readInt() throws IOException {
        int c;
        while ((c = System.in.read()) <= 32)
            ;
        int n = c & 15;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}