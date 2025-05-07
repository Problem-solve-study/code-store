import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 완탐
 * 그대로 시뮬레이션 하면 nlog_2(n)
 */
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            queue.add(new int[]{i, i});
        }

        int count = 1;
        while (true) {
            int size = queue.size();

            for (int s = 0; s < size; s++) {
                if (size - s > 1) {
                    s++;

                    int start = queue.poll()[0];
                    int end = queue.poll()[1];

                    if (a >= start && a <= end && b >= start && b <= end) {
                        System.out.println(count);
                        return;
                    }

                    queue.add(new int[]{start, end});
                    continue;
                }

                queue.add(queue.poll());
            }

            count++;
        }
    }
}
