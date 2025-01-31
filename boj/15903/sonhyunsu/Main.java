//제출번호: 89350147
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {        
        BufferedReader re = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Long> pq = new PriorityQueue<>();

        
        StringTokenizer st = new StringTokenizer(re.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(re.readLine());

        for (int i = 0; i < n; i++) {
            pq.add(Long.parseLong(st.nextToken()));
        }

        for (int i = 0; i < m; i++) {
            long a = pq.poll();
            long b = pq.poll();
            pq.add(a + b);
            pq.add(a + b);
        }

        long res = 0;
        while (!pq.isEmpty()) {
            res += pq.poll();
        }

        System.out.print(res);
    }
}