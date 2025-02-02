// 	12740KB	112ms

import java.util.*;
import java.io.*;

public class Main {
    static Long n, m;
    static PriorityQueue<Long> pq = new PriorityQueue<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Long.parseLong(st.nextToken());
        m = Long.parseLong(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<n; i++) pq.add(Long.parseLong(st.nextToken()));

        for (int i=0; i<m; i++) {
            Long tmp = pq.poll() + pq.poll();
            pq.add(tmp);
            pq.add(tmp);
        }

        Long res = 0L;
        while (!pq.isEmpty()) res += pq.poll();
        System.out.println(res);
    }
}