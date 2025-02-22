// 56556KB	452ms
import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] num;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        num = new int[n+1];
        for (int i=1; i<=n; i++) {
            num[i] = Integer.parseInt(st.nextToken()) + num[i-1];
        }
        int from, to;
        for (int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            sb.append(num[to] - num[from-1]).append("\n");
        }
        System.out.print(sb);
    }
}