import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        TreeMap<String, Double> map = new TreeMap<>();
        StringBuilder sb = new StringBuilder();

        double totalCnt = 0;
        String word;
        while ((word = br.readLine()) != null && !word.isEmpty()) {
            if (map.containsKey(word)) {
                map.put(word, map.get(word) + 1);
            } else if (!map.containsKey(word)) {
                map.put(word, 1.0);
            }
            totalCnt++;
        }

        for (Map.Entry<String, Double> entry : map.entrySet()) {
            sb.append(entry.getKey() + " ");
            double result = (entry.getValue() / totalCnt) * 100;

            String formatted = String.format("%.4f", result);
            sb.append(formatted).append('\n');
        }
        System.out.println(sb.toString());
    }
}