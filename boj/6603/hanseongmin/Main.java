//15208KB, 128ms

import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int k;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            var st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            if (k == 0) break;

            arr = new int[k];
            for (int i = 0; i < k; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            getLottery(0, new LinkedList<Integer>());
            sb.append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
    }

    public static void getLottery(int idx, LinkedList<Integer> list) {
        if (list.size() == 6) {
            list.stream().forEach(n -> sb.append(n).append(" "));
            sb.append('\n');
            return;
        }

        for (int i = idx; i < arr.length; i++) {
            list.addLast(arr[i]);
            getLottery(i + 1, list);
            list.removeLast();
        }
    }
}
