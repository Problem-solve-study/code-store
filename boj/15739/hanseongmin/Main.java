import java.io.*;
import java.util.HashSet;

/*
13900KB, 92ms

그냥 문제에 주어진대로 구현하면 됨 딱히 어렵지는 않은듯
중복된 원소가 존재하면 안된다는 것에 주의
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws Exception {
        int N = nextInt();
        //원소의 중복 여부를 판별할 SET
        HashSet<Integer> set = new HashSet<>();
        int[][] mat = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                mat[i][j] = nextInt();
                set.add(mat[i][j]);
            }
        }

        boolean isFalse = set.size() != N * N;
        int targetSum = N * (N * N + 1) / 2;
        //행과 열의 합을 체크
        for (int i = 0; i < N && !isFalse; i++) {
            int temp = 0;
            int temp2 = 0;
            for (int j = 0; j < N; j++) {
                temp += mat[i][j];
                temp2 += mat[j][i];
            }

            if (temp != targetSum || temp2 != targetSum) {
                isFalse = true;
                break;
            }
        }

        //대각선의 합을 체크
        int temp = 0;
        int temp2 = 0;
        for (int i = 0; i < N && !isFalse; i++) {
            temp += mat[i][i];
            temp2 += mat[N - 1 - i][i];
        }

        if (temp != targetSum || temp2 != targetSum) {
            isFalse = true;
        }

        System.out.print(isFalse ? "FALSE" : "TRUE");
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
