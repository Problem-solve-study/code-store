//제출번호: 89096831
//메모리:   15772 KB
//실행시간: 160 ms
import java.io.*;
import java.util.*;

public class Main {
    static int[][] mul = new int[11][10];
    static int[] arr = new int[10];
    static int[] res = new int[10];
    static boolean[] isUsed = new boolean[11];
    static int n;
    static int f;
    static boolean doStop = false;
    
    public static void main(String[] args) throws IOException {   
        for (int i = 1; i < 11; i++) {
            mul[i][0] = mul[i][i-1] = 1;
            for (int j = 1; j < i; j++) {
                mul[i][j] = mul[i-1][j-1] + mul[i-1][j];
            }
        }

        BufferedReader re = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(re.readLine());
        n = Integer.parseInt(st.nextToken());
        f = Integer.parseInt(st.nextToken());

        permutation(0);

        for (int i = 0; i < n; i++) {
            System.out.printf("%d ", res[i]);
        }
    }

    public static void permutation(int k) {
        if (k == n) {
            int r = 0;
            for (int i = 0; i < n; i++) {
                r += arr[i] * mul[n][i];
            }

            if (r == f) {
                res = Arrays.copyOf(arr, n);
                doStop = true;
            }
        } else {
            for (int i = 0; i < n; i++) {
                if (isUsed[i]) {
                    continue;
                }

                isUsed[i] = true;
                arr[k] = i + 1;
                permutation(k + 1);

                if (doStop) {
                    break;
                }

                isUsed[i] = false;
            }
        }
    }
}
