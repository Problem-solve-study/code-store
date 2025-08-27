// 13696KB 92ms

import java.io.*;
import java.util.*;

class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        final int MAX = 99_999;

        int n = nextInt();
        int t = nextInt();
        int g = nextInt();

        int count = 0;
        boolean[] visited = new boolean[MAX + 1];
        Queue<Integer> queue = new ArrayDeque<>();

        queue.add(n);
        visited[n] = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                int curr = queue.poll();

                if (curr == g) {
                    System.out.println(count);
                    return;
                }

                // A 버튼
                int next = curr + 1;
                if (next <= MAX && !visited[next]) {
                    queue.add(next);
                    visited[next] = true;
                }

                // B 버튼
                next = curr * 2;
                if (next == 0 || next > MAX) {
                    continue;
                }

                next -= (int) Math.pow(10, (int) Math.log10(next));
                if (visited[next]) {
                    continue;
                }
                queue.add(next);
                visited[next] = true;
            }

            count++;
            if (count > t) {
                break;
            }
        }

        System.out.println("ANG");
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
