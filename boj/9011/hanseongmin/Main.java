//15864KB, 140ms

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCase = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < testCase; t++) {
            br.readLine();
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] res = new int[arr.length];
            ArrayList<Integer> list = new ArrayList<>();
            IntStream.rangeClosed(1, arr.length).forEach(list::add);

            boolean isImpossible = false;
            for (int i = arr.length - 1; i >= 0; i--) {
                if (arr[i] > i) {
                    isImpossible = true;
                    break;
                }

                res[i] = list.get(arr[i]);
                list.remove(arr[i]);
            }

            if (isImpossible) {
                sb.append("IMPOSSIBLE");
            } else {
                Arrays.stream(res).forEach(e -> sb.append(e).append(" "));
            }
            sb.append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
    }
}