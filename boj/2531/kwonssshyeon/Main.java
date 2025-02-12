import java.io.*;
import java.util.*;

/**
 * 무조건 행사에 참여함 -> 즉, k개를 연속으로 먹을때 겹치지 않는 개수가 가장 클 때를 구하는 문제
 * 겹치는지를 확인하기 위해 set 자료구조와 count 정수 배열을 사용했다.
 * (count 배열만 있어도 되긴 한다.)
 * 
 * next는 그다음 먹을 부분, prev는 빼야할 부분
 * 윈도우를 한칸씩 밀어가면서 count 배열을 갱신하고,
 * count 배열의 원소의 수가 0이 되면 set 에서 해당 초밥을 삭제한다.
 * 
 * 위 과정을 k부터 한바퀴 돌아서 k-1 에 도착할 때까지 반복하며 answer 최댓값 갱신신
 */
public class Main {
    static int n,d,k,c;
    static HashSet<Integer> set = new HashSet<>();
    static int[] map;
    static int[] count;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new int[n];
        for (int i=0; i<n; i++) {
            map[i] = Integer.parseInt(br.readLine());
        }
        count = new int[d+1];
        
        initK();
        int answer = 0;
        int i = k;
        while (true) {
            answer = set.contains(c) 
                ? Math.max(answer, set.size()) 
                : Math.max(answer, set.size()+1);
            
            int next = map[i];
            int prev = map[((i-k)+n)%n]; // 배열을 한바퀴 돌고 다시 돌아오는 경우를 구현하기 위해 모듈러 연산 이용 (음수 방지를 위해 +n 을 먼저하고 모듈러 시행)
            set.add(next);
            if (count[prev] == 1) set.remove(prev);
            count[next]++;
            count[prev]--;
            i = (i+1) % n;
            if (i == k) break;
        }
        System.out.print(answer);
    }


    /** 가장 처음 k개를 연속으로 먹고 시작하기 위한 함수수 */
    static void initK() {
        for (int i=0; i<k; i++) {
            set.add(map[i]);
            count[map[i]]++;
        }
    }
}