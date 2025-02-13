// 13308kb 100ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] devices = new int[n];
        for (int i = 0; i < n; i++) {
            devices[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(devices);

        int[] concents = new int[m];
        for (int i = n-1; i >= 0; i--) {
            int min = Integer.MAX_VALUE;
            int idx = -1;
            for (int j = 0; j < m; j++) {
                if (concents[j] < min) {
                    min = concents[j];
                    idx = j;
                }
            }
            concents[idx] += devices[i];
        }

        int max = -1;
        for (int j = 0; j < m; j++) {
            if (concents[j] > max) {
                max = concents[j];
            }
        }

        System.out.println(max);
    }
}
