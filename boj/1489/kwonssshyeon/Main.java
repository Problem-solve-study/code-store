// 11632KB	68ms
import java.io.*;
import java.util.*;

public class Main {
    static int[] a, b;
    static boolean[] usedA, usedB;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        a = new int[n];
        b = new int[n];
        usedA = new boolean[n];
        usedB = new boolean[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<n; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(a);
        Arrays.sort(b);
        
        int score = 0;

        for (int i=0; i<n; i++) {
            for (int j=n-1; j>=0; j--) {
                if (usedB[j]) continue;
                if (a[i] > b[j]) {
                    score += 2;
                    usedA[i] = true;
                    usedB[j] = true;
                    break;
                }
            }
        }

        for (int i=0; i<n; i++) {
            if (usedA[i]) continue;
            for (int j=n-1; j>=0; j--) {
                if (usedB[j]) continue;
                if (a[i] == b[j]) {
                    score += 1;
                    usedA[i] = true;
                    usedB[j] = true;
                    break;
                }
            }
        }

        System.out.print(score);
    }
}
