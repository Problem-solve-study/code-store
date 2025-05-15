// 11572 KB, 68 ms
/*
 * 좌표 -> 숫자 변환
 * 
 * (x, y) 좌표 중 절댓값 큰 값을 n 이라고 할 때
 * n 번째 띠에 있는 숫자임
 * 1. y == n 일 때, (2n - 1) ^ 2 + 7n + x
 * 2. y == -n 일 때, (2n - 1) ^ 2 + 3n - x
 * 3. x == n 일 때, (2n - 1) ^ 2 + n - y
 * 4. x == -n 일 때, (2n - 1) ^ 2 + 5n + y
 * 
 * arr[r2 - r1][c2 - c1] 배열에 숫자를 모두 저장하고,
 * 길이가 가장 긴 수의 길이를 구한다.
 * 
 * 그리고 출력.하면 되는데 범위를 한번 생각해 보자.
 * (5000, 5000)이면? 5001^2 = 25,000,000 무조건 int 범위 안이다 !
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        int r1 = Integer.parseInt(in[0]), c1 = Integer.parseInt(in[1]),
                r2 = Integer.parseInt(in[2]), c2 = Integer.parseInt(in[3]);

        int maxLen = 0, row = r2 - r1 + 1, col = c2 - c1 + 1;
        int[][] arr = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                arr[i][j] = posToNum(r1 + i, c1 + j);
                maxLen = Math.max(maxLen, Integer.toString(arr[i][j]).length());
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                for (int k = 0; k < maxLen - Integer.toString(arr[i][j]).length(); k++) {
                    sb.append(' ');
                }
                sb.append(arr[i][j]).append(' ');
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }

    static int posToNum(int y, int x) {
        int n = Math.max(Math.abs(y), Math.abs(x));
        int t = (2 * n - 1) * (2 * n - 1);
        if (y == n)
            return t + 7 * n + x;
        if (y == -n)
            return t + 3 * n - x;
        if (x == n)
            return t + n - y;
        return t + 5 * n + y;
    }
}