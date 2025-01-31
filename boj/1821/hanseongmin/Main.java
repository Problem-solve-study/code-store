// 50044KB, 276ms

import java.io.*;
import java.util.*;

public class Main {
    static int n, f;
    static int[] arr;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        f = Integer.parseInt(st.nextToken());
        arr = new int[n];
        visited = new boolean[n + 1];

        makeSequance(1, 0);
    }

    public static void makeSequance(int idx, int size) throws Exception {
        if (size == n) {
            int[] temp = new int[n];
            for (int i = 0; i < size; i++) {
                temp[i] = arr[i];
            }

            for (int i = 0; i < size - 1; i++) {
                for (int j = 0; j < size - 1 - i; j++) {
                    temp[j] = temp[j] + temp[j + 1];
                }
            }

            if (f == temp[0]) {
                Arrays.stream(arr).forEach(n -> sb.append(n).append(" "));
                bw.write(sb.toString());
                bw.flush();
                System.exit(0);
            }
        }

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                arr[size] = i;
                makeSequance(i + 1, size + 1);
                visited[i] = false;
            }
        }
    }
}