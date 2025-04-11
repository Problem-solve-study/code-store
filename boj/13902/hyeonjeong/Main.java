// 24952KB 904ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;


/**
 * BFS
 * 목표가 10000이면 M=1이면 10000번 탐색 -> 완탐 되겠는데?
 * nC2 해둔 결과에 대해서 BFS 수행
 */
public class Main {
    static int n;
    static int m;
    static int[] numbers;
    static Set<Integer> combinations = new HashSet<>();
    static Queue<Integer> queue = new ArrayDeque<>();
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        numbers = new int[m];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        visited = new boolean[n + 1];

        // numbersC2를 combinations에 추가
        nC2(-1, 0, 0);

        // 만들어진 조합을 큐에 추가
        for (int c : combinations) {
            visited[c] = true;
            queue.add(c);
        }

        // System.out.println(queue);
        // System.out.println(Arrays.toString(visited));

        // BFS
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                int curr = queue.poll();
    
                if (curr == n) {
                    System.out.println(level + 1);
                    return;
                }

                for (int c : combinations) {
                    int next = curr + c;
                    if (next > n) {
                        continue;
                    }

                    if (visited[next]) {
                        continue;
                    }

                    visited[next] = true;
                    queue.add(next);
                }
            }

            level++;
        }

        System.out.println(-1);
    }

    static void nC2(int pi, int depth, int number) {
        if (depth == 2) {
            return;
        }

        for (int i = pi + 1; i < m; i++) {
            if (number + numbers[i] > n || visited[number + numbers[i]]) {
                continue;
            }
            
            combinations.add(number + numbers[i]);
            
            visited[number + numbers[i]] = true;
            nC2(i, depth + 1, number + numbers[i]);
            visited[number + numbers[i]] = false;
        }
    }
}
