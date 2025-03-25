// 11884KB	76ms
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static HashMap<String, Integer> base = new HashMap<>();
    static int[] order;
    /**
     * 가장 긴 증가하는 부분 수열 문제로 품.
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for (int i=0; i<n; i++) {
            base.put(br.readLine(), i);
        }
        order = new int[n];
        for (int i=0; i<n; i++) {
            order[i] = base.get(br.readLine());
        }

        int count = 0;
        for (int i=0; i<n-1; i++) {
            for (int j=i+1; j<n; j++) {
                if (order[i] > order[j]) {
                    count++;
                    break;
                }
            }
        }
        System.out.println(count);
    }
}