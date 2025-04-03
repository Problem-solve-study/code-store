// 11676KB 64ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * 뺄셈 연산은 항상 0이 되는데, start나 target이 0이 아니기 때문에 뺄셈 연산을 하는 경우가 없다.
 * 나눗셈 연산은 항상 1이 되므로 최초 1회에만 해 본다.
 * => *랑 + 로만 BFS
 * => 수가 항상 증가하므로 target보다 큰 수로 가는 경우는 더 탐색하지 않는다.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());

        if (start == target) {
            System.out.println(0);
            return;
        }

        // BFS
        final int ROOT_TARGET = (int) Math.sqrt(target);

        Set<Integer> visited = new HashSet<>();
        Queue<int[]> queue = new ArrayDeque<>();    // int[]: 0 - 값, 1~n - 연산자 캐릭터 배열

        // *를 먼저 넣어서 사전순으로 탐색
        if(start <= ROOT_TARGET){
            visited.add(start * start);
            queue.add(new int[]{start * start, '*'});
        }
        if(start + start <= target){
            visited.add(start + start);
            queue.add(new int[]{start + start, '+'});
        }
        visited.add(1);
        queue.add(new int[]{1, '/'});

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                int[] curr = queue.poll();

                int number = curr[0];
                if (number == target) {
                    display(curr);
                    return;
                }

                // *는 오버플로우를 방지하기 위해 연산 전에 검사
                if (number <= ROOT_TARGET) {
                    int multipleNumber = number * number;
                    if (!visited.contains(multipleNumber)) {
                        visited.add(multipleNumber);
    
                        int[] next = Arrays.copyOf(curr, curr.length + 1);
                        next[0] = multipleNumber;
                        next[curr.length] = '*';
    
                        queue.add(next);
                    }
                }

                int plusNumber = number + number;
                if (plusNumber <= target && !visited.contains(plusNumber)) {
                    visited.add(plusNumber);

                    int[] next = Arrays.copyOf(curr, curr.length + 1);
                    next[0] = plusNumber;
                    next[curr.length] = '+';

                    queue.add(next);
                }
            }
        }

        System.out.print(-1);
    }

    static void display(int[] path) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < path.length; i++) {
            sb.append((char) path[i]);
        }

        System.out.print(sb);
    }
}
