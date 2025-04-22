// 12280KB	160ms
import java.io.*;
import java.util.*;

/**
 * 길이가 k 이상인 모든 구간을 탐색
 */
public class Main {
    static int n, k, num[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        num = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        double answer = Double.MAX_VALUE;
        for (int i=0; i<=n-k; i++) {
            for (int j=i+k; j<=n; j++) {
                answer = Math.min(answer, calculate(i, j));
            }
        }
        System.out.println(answer);
    }

    static double calculate(int start, int end) {
        double avg = 0;
        for (int i=start; i<end; i++) {
            avg += num[i];
        }
        avg /= (end-start);

        double deviation = 0;
        for (int i=start; i<end; i++) {
            deviation += (num[i] - avg) * (num[i] - avg);
        }
        deviation /= (end-start);

        return Math.sqrt(deviation);
    }
}
