// 37460KB	252ms
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int minEnd = 100_000, maxStart = 0;
    
    // 가장 먼저 끝나는 사람 <-> 가장 늦게 입장하는 사람의 차이
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for (int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (minEnd > b) {
                minEnd = b;
            }
            if (maxStart < a) {
                maxStart = a;
            }
        }
        System.out.println(Math.max(maxStart - minEnd, 0));
    }
}