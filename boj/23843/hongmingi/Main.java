//15648KB	160ms

/*
 * 우선순위 큐 사용하는 연습을 할 수 있었던 문제.
 * 처음에는 array만 사용해서 진행했는데 어떻게 계산을 할지는 머리속에 정리가 됐었으나 
 * while문 조건 두는 부분에서 많이 헤맸음. 그래서 pq로 바꿔서 진행. 익숙해지려면 공부 더 많이 해야 할듯.
 */
import java.util.*;
import java.io.*;
public class Main {
    static int n, m;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> pq1 = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> consent = new PriorityQueue<>();
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        
        for(int i=0; i<n; i++){
            pq1.add(Integer.parseInt(st2.nextToken()));
        }

        int cnt = 0;
        List<Integer> tempList;
        for(int i=0; i<m && !pq1.isEmpty(); i++){
            consent.add(pq1.poll());
        }

        while(!consent.isEmpty()){
            int minTime = consent.poll();
            cnt+=minTime;
            tempList = new ArrayList<>();
            while(!consent.isEmpty()){
                tempList.add(consent.poll()-minTime);
            }
            for(int t:tempList){
                if(t>0) consent.add(t);
            }
            while(!pq1.isEmpty() && consent.size()<m)   consent.add(pq1.poll());
        }

        System.out.println(cnt);

    }
}
