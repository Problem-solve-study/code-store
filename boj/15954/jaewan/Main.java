/*
 * N개의 인형 리스트 중 K개 이상을 선택
 * 표준편차가 최소가 되는 값을 출력. V = (nums[1]-m)^2 + (nums[2]-m)^2 + ... +
 * 
 * 
 * 
 */

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        int N = readInt(), K = readInt();
        long[][] nums = new long[3][N + 1];
        for (int i = 1; i <= N; i++) {
            nums[0][i] = readInt();
            nums[1][i] = nums[1][i - 1] + nums[0][i];
            nums[2][i] = nums[2][i - 1] + nums[0][i] * nums[0][i];
        }

        double minStd = Double.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            for (int j = i + K; j <= N + 1; j++) {
                int size = j - i;
                long sum = nums[1][j - 1] - nums[1][i - 1];
                double m = (double) sum / size;
                double V = ((nums[2][j - 1] - nums[2][i - 1]) - 2 * m * sum) / size + m * m;
                minStd = Math.min(minStd, Math.sqrt(V));
            }
        }
        System.out.println(minStd);
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