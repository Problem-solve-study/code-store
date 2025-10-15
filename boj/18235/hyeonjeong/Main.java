// 71656KB 396ms

import java.io.*;
import java.util.*;

/**
 * 50만의 좌표 공간을 2배씩 이동 -> 최대 이동 횟수 log2_500,000
 * 매 이동마다 선택지 = 좌 혹은 우
 * => 모든 이동의 선택지: 2 ^ (log2_500,000)
 * 
 * BFS로 탐색
 */
class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        if (a == b) {
            System.out.println(0);
            return;
        }
        
        Deque<Integer> queueA = new ArrayDeque<>();
        Deque<Integer> queueB = new ArrayDeque<>();
        // 오리의 현재 레벨의 방문지
        // 매 레벨마다, 오리가 방문하는 곳을 저장하고 육리가 Set에 존재하는 좌표에 방문하면 만난 걸로 처리
        Set<Integer> current = new HashSet<>();     
        queueA.add(a);
        queueB.add(b);
        
        int level = 1;
        int delta = 1;
        while (!queueA.isEmpty() && !queueB.isEmpty()) {
            int size = queueA.size();
            for (int s = 0; s < size; s++) {
                int prev = queueA.poll();

                int next = prev + delta;
                if (next <= n) {
                    queueA.add(next);
                    current.add(next);
                }

                next = prev - delta;
                if (next > 0) {
                    queueA.add(next);
                    current.add(next);
                }
            }

            size = queueB.size();
            for (int s = 0; s < size; s++) {
                int prev = queueB.poll();

                int next = prev + delta;
                if (next <= n) {
                    if (current.contains(next)) {
                        System.out.println(level);
                        return;
                    }
                    queueB.add(next);
                }

                next = prev - delta;
                if (next > 0) {
                    if (current.contains(next)) {
                        System.out.println(level);
                        return;
                    }
                    queueB.add(next);
                }
            }

            level++;
            delta *= 2;
            current.clear();
        }

        System.out.println(-1);
    }
}
