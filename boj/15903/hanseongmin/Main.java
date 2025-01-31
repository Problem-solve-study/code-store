import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        PriorityQueue<Long> h = new PriorityQueue<>();
        Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).forEach(h::add);
        for (int i = 0; i < m; i++) {
            long sum = h.remove() + h.remove();
            h.add(sum); h.add(sum);
        }

        long res = h.stream().mapToLong(Long::longValue).sum();
        bw.write(String.valueOf(res));
        bw.flush();
    }
}