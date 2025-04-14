// 	39196KB	196ms

import java.io.*;
import java.util.*;

/**
 * d로 나눴을때 나머지가 같은 것 끼리 빼면 d로 나눴을때 나누어 떨어지는 것을 이용한다.
 * 만약 현재 나머지가 1이라면, 내 앞에 있는 수 중 나머지가 1인 것과 빼기 연산을 하면 나누어 떨어진다.
 * 따라서 내 앞에 나머지가 1인 수가 몇개인지 기억해야 한다.
 * 
 * 1. 입력 배열의 누적합을 구한다.
 * 2. 누적합을 d로 나눈 나머지를 구한다.
 * 3. 현 위치를 계산하며, 내 앞에 나와 같은 수가 몇 개인지를 answer에 더한다.
 */
public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static void main(String[] args) throws Exception {
        int tc = nextInt();
        StringBuilder sb = new StringBuilder();
        
        while (tc-- > 0) {
            int d = nextInt();
            int n = nextInt();
            int[] count = new int[d];
            count[0] = 1;
            
            long prefixSum = 0;
            int answer = 0;
            for (int i = 0; i < n; i++) {
                prefixSum += nextInt();
                int mod = (int)(prefixSum % d);
                answer += count[mod];
                count[mod]++;
            }
            sb.append(answer).append("\n");
        }
        System.out.print(sb);
    }

    static int nextInt() throws IOException {
        int c;
        while ((c = System.in.read()) <= 32)
            ;
        int n = c & 15;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;

    }
}
