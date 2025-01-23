import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseLimit = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int testCase = 1; testCase <= testCaseLimit; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            int ans = IntStream.rangeClosed(0, n).reduce((a, b) -> a + b == p ? a + b - 1 : a + b).orElse(0);
            sb.append(ans).append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
    }
}
