// 141876KB 1148ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 해시 + 정렬
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int q = Integer.parseInt(br.readLine());
        
        Map<String, PriorityQueue<Integer>> infos = new HashMap<>();
        long answer = 0L;

        for (; q > 0; q--) {
            st = new StringTokenizer(br.readLine());

            int command = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            int k = Integer.parseInt(st.nextToken());

            // 고릴라의 정보 수집
            if (command == 1) {
                PriorityQueue<Integer> heap = infos.get(name);
                
                if (heap == null) {
                    heap = new PriorityQueue<>((n1, n2) -> n2 - n1);
                    infos.put(name, heap);
                }

                for (int i = 0; i < k; i++) {
                    heap.add(Integer.parseInt(st.nextToken()));
                }

                continue;
            }

            // 고릴라에게 정보 구매
            // 없는 고릴라는 무시
            if (infos.get(name) == null) {
                continue;
            }

            // 구매할 개수와 고릴라가 가지고 있는 정보의 개수 중 적은 개수를 구매
            int min = Math.min(k, infos.get(name).size());
            for (int i = 0; i < min; i++) {
                answer += infos.get(name).poll();
            }
        }

        System.out.println(answer);
    }
}
