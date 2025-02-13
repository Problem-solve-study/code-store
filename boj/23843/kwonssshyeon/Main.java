//18932KB	224ms
import java.io.*;
import java.util.*;

/**
 * 얼마전에 풀었던 입국심사랑 비슷한듯 ..? 해서 이분탐색을 먼저 생각했었는데
 * 충전 순서가 중요하다는 것을 발견함
 * 가장 오래 걸리는 전자기기를, 시간이 가장 짧게 남은 콘센트에 충전해야함
 * concent 에 충전할 때까지 기다려야하는 시간을 저장했는데 동적으로 변하기 때문에 우선순위 큐를 이용
 */
public class Main {
    static int n,m;
    static int time[];
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        time = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<n; i++) {
            time[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(time);
        PriorityQueue<Integer> concent = new PriorityQueue<>((c1, c2) -> (c1 - c2));

        for (int i=0; i<m; i++) {
            concent.add(0);
        }

        int answer = 0;
        for (int i=n-1; i>=0; i--) {
            answer = Math.max(answer, concent.peek() + time[i]);
            concent.add(concent.poll() + time[i]);
        }
        System.out.print(answer);
    }
}