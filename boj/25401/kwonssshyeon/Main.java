//	11956KB	144ms
import java.io.*;
import java.util.*;

/**
 * 모든 두 점에 대해 등차수열이 될 수 있는 경우의 수를 계산
 */
public class Main {
    static int n, num[];
    static int answer = 500;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        num = new int[n];
        for (int i=0; i<n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        calculate();
        System.out.print(answer);
    }

    static void calculate() {
        for (int i=0; i<n-1; i++) {
            for (int j=i+1; j<n; j++) {
                int d = (num[j] - num[i]) / (j - i);
                int a = num[i] - i * d;
                answer = Math.min(answer, count(a, d));
            }
        }
    }

    static int count(int a, int d) {
        int result = 0;
        int comp = a;
        for (int i=0; i<n; i++) {
            if (num[i] != comp) result++;
            comp += d;
        }
        return result;
    }
}
