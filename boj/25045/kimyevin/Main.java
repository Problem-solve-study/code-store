import java.io.*;
import java.util.*;

/**
 * 80188KB	636ms
 * 만족도는 내림차순, 가격은 오름차순으로 정렬 후
 * 물건과 회사를 매칭한다
 * 만약, 남아있는 물건 중 가장 높은 만족도가 가격보다 작다면 멈추기
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        long[] sat = new long[N];
        long[] cost = new long[M];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            sat[i] = - Integer.parseInt(st.nextToken()); // 내림차순 정렬 위해 음수로 저장
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++){
            cost[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(sat);
        Arrays.sort(cost);
        long sum = 0;
        int j = 0;
        for(int i = 0; i < N && j < M; i++){
            if(sat[i] + cost[j] > 0){ // 물건 매칭 X
                break;
            }
            else{
                sum -= (sat[i] + cost[j]); // 물건 + 회사 매칭
                j++;
            }
        }
        System.out.println(sum);
    }
}
