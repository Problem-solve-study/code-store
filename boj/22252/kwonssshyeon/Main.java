//	144188KB	1144ms
import java.io.*;
import java.util.*;

/**
 * name을 키로 하고, value 로 PQ를 가지도록 함.
 * PQ에 정수를 내림차순으로 정렬해서 가장 큰 k개를 뽑아올 수 있도록 함.
 */
public class Main {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<String, PriorityQueue<Integer>> map = new HashMap<>();
        int n = Integer.parseInt(br.readLine());
        long answer = 0L;
        for (int t=0; t<n; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            if (op == 1) {
                String name = st.nextToken();
                if (map.get(name) == null) {
                    map.put(name, new PriorityQueue<>(Comparator.reverseOrder()));
                }
                int k = Integer.parseInt(st.nextToken());
                for (int i=0; i<k; i++) {
                    int c = Integer.parseInt(st.nextToken());
                    map.get(name).add(c);
                }
            } 
            else {
                String name = st.nextToken();
                int k = Integer.parseInt(st.nextToken());
                if (!map.containsKey(name)) continue;
                int limit = Math.min(k, map.get(name).size());
                for (int i=0; i<limit; i++) {
                    answer += map.get(name).poll();
                }
            }
        }
        System.out.print(answer);
    }
}