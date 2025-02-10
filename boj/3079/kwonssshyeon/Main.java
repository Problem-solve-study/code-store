// 21416KB	232ms
import java.io.*;
import java.util.*;

public class Main {
    static int n,m;
    static int[] times;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        times = new int[n];
        int max = 0;
        for (int i=0; i<n; i++) {
            times[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, times[i]);
        }

        long answer = binarySearch((long)m*max);
        System.out.print(answer);
    }

    /** 이분탐색 코드 */
    static long binarySearch(long n) {
        long left = 0L, right = n;
        
        while (left < right) {
            long mid = (left + right) / 2;

            long cnt = caculate(mid);
            if (cnt < m) {
                left = mid + 1;
            }
            else {
                right = mid;
            }
        }
        return left;
    }

    /** 전체 소요시간이 num 일때 수용가능한 사람수 cnt 를 계산 */
    static long caculate(long num) {
        long sum = 0;
        for (int time : times) {
            sum += num / time;
            // num 은 최대 10^9 * 10^9 근처이다. time 이 1이고, times길이가 10^19 일 때,
            // 10^(9+9+9) 이므로 long 타입에서 도 오버플로가 발생하기 때문에 조기종료가 필요하다.
            if (sum > m) break;
        }
        return sum;
    }
}

/**
 * 전에 풀었던거라서 이미 이분탐색인줄 알고 풀었어요 ...
 */