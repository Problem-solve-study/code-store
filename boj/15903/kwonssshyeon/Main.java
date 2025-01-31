import java.util.*;
import java.io.*;

public class Main {
    static int n,m;
    static int[] card;
    static PriorityQueue<Long> queue;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        queue = new PriorityQueue<>();
        for (int i=0; i<n; i++) {
            queue.add(Long.parseLong(st.nextToken()));
        }

        for (int i=0; i<m; i++) {
            long a = queue.poll();
            long b = queue.poll();
            long next = a + b;
            queue.add(next);
            queue.add(next);
        }

        long answer = 0;
        for (long num : queue) {
            answer += num;
        }
        System.out.print(answer);
    }
}