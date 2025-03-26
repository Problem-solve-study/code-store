// 58440KB 784ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 그리디: 내가 먹을 수 있는 상어 중 가장 큰 상어 먹기
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        long t = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> eatables = new PriorityQueue<>((s1, s2) -> s2 - s1); // MAX HEAP
        PriorityQueue<Integer> biggers = new PriorityQueue<>((s1, s2) -> s1 - s2);  // MIN HEAP

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int shark = Integer.parseInt(st.nextToken());

            if (shark < t) {
                eatables.add(shark);
                continue;
            }

            biggers.add(shark);
        }
        
        for (int i = 0; i < k; i++) {
            if (eatables.isEmpty()) {
                break;
            }

            t += eatables.poll();
            
            while (!biggers.isEmpty() && biggers.peek() < t) {
                eatables.add(biggers.poll());
            }
        }

        System.out.println(t);
    }
}
