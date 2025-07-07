import java.io.*;
import java.util.*;

/**
* 밑장빼기 한 카드가 들어가기 전엔 짝수번째 인덱스의 카드의 합, 밑장빼기 한 카드가 들어간 후엔 홀수번째 카드의 합이 총합이 된다.
*/
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int size = n / 2 + 1;

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] evenSum = new int[size];
        int[] oddSum = new int[size];
        for (int i = 1; i < size; i++) {
            evenSum[i] = Integer.parseInt(st.nextToken()) + evenSum[i - 1];
            oddSum[i] = Integer.parseInt(st.nextToken()) + oddSum[i - 1];
        }

        if (n == 2) {
            System.out.println(evenSum[1]);
            return;
        }

        // i: 밑장빼기 한 카드가 들어오는 위치
        int max = 0;
        int last = oddSum[size - 1] - oddSum[size - 2];
        for (int i = 0; i < n; i++) {
            // 내 카드의 합 = 밑장빼기 전의 짝수번째 카드의 합 + 밑장빼기 후의 홀수번째 카드의 합
            int sum = evenSum[(i + 1) / 2] + oddSum[size - 2] - oddSum[i / 2];

            // 밑장빼기 한 카드가 짝수 위치에 들어오면 내가 먹은 거
            if (i % 2 == 0) {
                sum += last;
            }

            if (sum > max) {
                max = sum;
            }
        }

        System.out.println(max);
    }
}
