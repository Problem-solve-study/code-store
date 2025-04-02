// 63360 KB, 492 ms
/*
 * 
 * 두 명을 가리키고, ㅈ연수 K를 정하고 한 명 지목.
 * 지목된 사람은 자신이 가리키고 있는 사람 중 한 사람 지목.
 * 같은 방식으로 지목해 나가며 K번째로 지목된 사람이 걸리는 게임
 * 
 * N, K 각 사람이 지목한 두 사람이 정해졌을 때, a번 사람이 시작했을 때 b번 사람이 걸리는 경우가 있는지 없는지.
 * a와 b의 쌍은 M개 주어진다.
 * 
 * 2 <= N <= 200, 1 <= K <= 1,000,000, 1 <= M <= 1,000,000
 * 
 * 행렬 곱으로 풀이
 */

import java.io.IOException;

public class Main {
    static int N, K, M;
    static boolean[][] matrix;

    public static void main(String[] args) throws IOException {
        N = readInt();
        K = readInt();
        M = readInt();

        // 인접 행렬
        matrix = new boolean[N + 1][N + 1];

        // 인접 행렬 입력
        for (int i = 1; i <= N; i++)
            for (int j = 0; j < 2; j++)
                matrix[i][readInt()] = true;

        // 행렬 곱, K번째의 인접 행렬 구하기
        boolean[][] res = new boolean[N + 1][N + 1];
        for (int i = 1; i < N + 1; i++)
            res[i][i] = true;

        while (K > 0) {
            if ((K & 1) == 1)
                matrixMul(res, matrix);
            matrixMul(matrix, matrix);
            K >>= 1;
        }

        // M 만큼 입력해 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int a = readInt(), b = readInt();
            sb.append(res[a][b] ? "death" : "life").append('\n');
        }
        System.out.println(sb.toString());

    }

    // 행렬 곱 수행하는 함수
    static void matrixMul(boolean[][] matrix1, boolean[][] matrix2) {
        boolean[][] temp = new boolean[N + 1][N + 1];
        for (int i = 1; i <= N; i++)
            for (int j = 1; j <= N; j++)
                for (int k = 1; k <= N; k++)
                    if (matrix1[i][k] & matrix2[k][j]) {
                        temp[i][j] = true;
                        break;
                    }

        for (int i = 1; i <= N; i++)
            for (int j = 1; j <= N; j++)
                matrix1[i][j] = temp[i][j];
    }

    static int readInt() throws IOException {
        int c;
        while ((c = System.in.read()) <= 32)
            ;
        int n = c & 15;
        while ((c = System.in.read()) > 47) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}