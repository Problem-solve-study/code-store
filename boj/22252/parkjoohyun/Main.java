//Memory 161236KB
//Time 768ms

import java.io.*;
import java.util.*;

public class Main {

    static long price = 0;
    static Map<String, PriorityQueue<Integer>> infos = new HashMap<>();

    public static void main(String[] args) throws IOException {
        input();
        System.out.println(price);
    }

    public static void saveQuery(String[] query) {
        String name = query[1];
        int k = Integer.parseInt(query[2]);

        PriorityQueue<Integer> pq = infos.getOrDefault(name, new PriorityQueue<>(Collections.reverseOrder()));
        
        for (int i = 0; i < k; i++) {
            int val = Integer.parseInt(query[3 + i]);
            pq.offer(val);
        }

        infos.put(name, pq);
    }

    public static void buyQuery(String[] query) {
        String name = query[1];
        int b = Integer.parseInt(query[2]);

        PriorityQueue<Integer> pq = infos.get(name);
        if (pq == null) return;

        for (int i = 0; i < b && !pq.isEmpty(); i++) {
            price += pq.poll();
        }
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int q = Integer.parseInt(br.readLine());

        for (int i = 0; i < q; i++) {
            String[] query = br.readLine().split(" ");
            if (query[0].equals("1")) {
                saveQuery(query);
            } else {
                buyQuery(query);
            }
        }
    }
}
