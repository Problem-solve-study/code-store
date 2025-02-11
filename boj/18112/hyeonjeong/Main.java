// 65016KB 312ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int start = Integer.parseInt(br.readLine(), 2);
        int target = Integer.parseInt(br.readLine(), 2);

        System.out.println(bfs(start, target));
    }


    static int bfs(int start, int target) {
        Queue<Integer> queue = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        queue.add(start);
        visited.add(start);

        int level = 0;
        while (queue.size() > 0) {
            int currLevelSize = queue.size();
            for (int i = 0; i < currLevelSize; i++) {
                int current = queue.poll();
                System.out.println("curr: " + Integer.toBinaryString(current));
                if (current == target) {
                    return level;
                }

                int next = current + 1;
                if (!visited.contains(next)) {
                    System.out.println("+1: next " + Integer.toBinaryString(next) + " appended");
                    queue.add(next);
                    visited.add(next);
                }

                if (current != 0) {
                    next = current - 1;
                    if (!visited.contains(next)) {
                        System.out.println("-1: next " + Integer.toBinaryString(next) + " appended");
                        queue.add(next);
                        visited.add(next);
                    }
                }

                // 자리수마다 보수 스와핑
                int bound = current != 0 ? (int) (Math.log(current) / Math.log(2)) : 1;
                for (int digit = 0; digit < bound; digit++) {
                    // 나머지 잘라내고 1의 자리에서 스와핑 한 후 나머지 붙이기
                    int rest = current % (1 << digit);
                    int swap = current >> digit;
                    swap = swap % 2 == 0 ? swap + 1 : swap - 1;
                    swap <<= digit;
                    
                    next = swap + rest;
                    System.out.println("swap: (bound: %d, digit: %d) %s".formatted(bound, digit, Integer.toBinaryString(next)));
                    if (!visited.contains(next)) {
                        System.out.println("swap: next " + Integer.toBinaryString(next) + " appended");
                        queue.add(next);
                        visited.add(next);
                    }
                }

                System.out.println(" ");
            }

            System.out.println("== new level ==");
            level++;
        }

        return 0;
    }
}
