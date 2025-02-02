//94840KB, 916ms

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        TreeMap<String, Integer> map = new TreeMap<>();
        int cnt = 0;
        String name = "";
        while ((name = br.readLine()) != null) {
            cnt++;
            map.put(name, map.getOrDefault(name, 0) + 1);
        }

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> e : map.entrySet()) {
            sb.append(String.format("%s %.4f\n", e.getKey(), (double) e.getValue() / cnt * 100));
        }

        bw.write(sb.toString());
        bw.flush();
    }
}