// 	11540KB 84ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int n;
    static int target;
    static int[] numbers;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());
        numbers = new int[n];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        dfs(-1, 0);

        System.out.println(count);
    }

    static void dfs(int prev, int sum) {
        // target이 0일 때 공집합이 포함되지 않도록
        if (sum == target && prev != -1) {
            count++;
        }

        if (prev == n - 1) {
            return;
        }

        for (int i = prev + 1; i < n; i++) {
            dfs(i, sum + numbers[i]);
        }
    }

}
