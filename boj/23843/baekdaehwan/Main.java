//	12980KB	104ms

/**
 * [사고 흐름]
 * 오래 걸리는 순서대로 우선순위 할당하고, 
 * 콘센트가 비는 순간 다음 전자기기 할당을 하면 될 것 같다.
 * => 그리디네? => pq를 쓰자
 * 
 * 
 * [코드 설명]
 * pq에 들어가는 값은 각 콘센트가 점유 해제되는 시간를 뜻한다.
 * 
 * 1. 전자기기를 충전 시간 기준으로 정렬한다.
 * 2. pq에 콘센트의 개수만큼 0을 추가한다. (최초에는 모든 콘센트가 비어있으므로)
 * 3. pq에서 가장 작은 값을 뽑아서, 충전이 안된 전자기기 중 충전이 가장 오래걸리는 전자기기의 충전시간을 더하고, 다시 pq에 집어넣는다.
 * 4. (3)을 모든 전자기기가 충전될때까지 반복한다.
 * 5. pq의 가장 큰 값을 출력한다.
 */

import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] t = new int[N];
        int res = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; ++i) t[i] = Integer.parseInt(st.nextToken());
        for (int i=0; i<M; ++i) pq.add(0);
        Arrays.sort(t);

        for (int i=N-1; i>=0; --i) pq.add(pq.poll()+t[i]);
        while (!pq.isEmpty()) res = pq.poll();
        System.out.println(res);
    }
}