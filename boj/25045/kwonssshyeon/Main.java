// 	79460KB	672ms
import java.io.*;
import java.util.*;

/**
 * 가장 큰 수 - 가장 작은 수의 합
 * 어떤 가중치가 없기 때문에 가장 큰 수의 합에서 가장 작은 수의 합과 같을 수 있다.
 * 그래서 그냥 정렬 후 양 끝의 두 수를 연산하도록 구현
 */
public class Main {
    static int n,m;
    static int[] num, cost;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        num = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        cost = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<m; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(num);
        Arrays.sort(cost);

        long answer = 0L;
        for (int i=0; i<Math.min(n,m); i++) {
            answer += Math.max(num[n-i-1] - cost[i], 0);
        }

        System.out.print(answer);
    }
}