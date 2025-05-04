import java.io.*;
import java.util.*;

/*
11792KB, 68ms

피보나치스러운데 N이 좀 크다? -> 행렬 제곱 + 분할 정복을 이용한 거듭제곱

F_n+1       x  y        F_n
        =           *
F_n         1   0       F_n-1

이므로

F_n+1       x  y            a1
        = (       )^n   *
F_n         1   0           a0
이다.

따라서
x y
1 0 행렬을 n번 제곱한 뒤 a1, a0 행렬과 다시 곱한 후 1행의 값을 뽑아오면 된다.
마지막 두 자리의 위치를 벗어난 수는 마지막 두 자리를 결정짓는데 영향을 끼치지 못하므로
MOD 100으로 마지막 두 자리로만 연산한다.
 */

public class Main {
    static int[][] base;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int a0 = Integer.parseInt(st.nextToken());
        int a1 = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        //n이 0이나 1이면 문제에서 주어지므로 그대로 출력하면 됨
        //1일때 01의 꼴로 문제에서 주어지므로 출력할 때도 0으로 패딩
        if (n == 0) {
            System.out.printf("%02d", a0);
        } else if (n == 1) {
            System.out.printf("%02d", a1);
        } else {
            //2항부터는 직접 연산하여 값을 가져옴
            base = new int[][]{{x, y}, {1, 0}};
            int[][] mat = pow(base, n);
            int[][] res = mul(mat, new int[][]{{a1}, {a0}});
            System.out.printf("%02d", res[1][0]);
        }
    }

    static int[][] pow(int[][] mat, int n) {
        if (n == 1) {
            return mat;
        }

        int[][] temp = pow(mat, n / 2);
        if (n % 2 == 0) {
            return mul(temp, temp);
        } else {
            return mul(mul(temp, temp), base);
        }
    }

    static int[][] mul(int[][] mat1, int[][] mat2) {
        int[][] newMat = new int[mat1.length][mat2[0].length];
        for (int i = 0; i < mat1.length; i++) {
            for (int j = 0; j < mat2[0].length; j++) {
                for (int k = 0; k < mat1[0].length; k++) {
                    newMat[i][j] += mat1[i][k] * mat2[k][j];
                }
                newMat[i][j] %= 100;
            }
        }
        return newMat;
    }
}
