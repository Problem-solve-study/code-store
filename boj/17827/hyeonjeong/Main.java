// 31916KB 348ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.StringTokenizer;

// 인덱스 계산
class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        int n = nextInt();
        int m = nextInt();
        int v = nextInt() - 1;

        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = nextInt();
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int k = nextInt();

            if (k < n) {
                sb.append(numbers[k]).append('\n');
                continue;
            }

            k -= n;
            k %= n - v;
            int idx = v + k;
            sb.append(numbers[idx]).append('\n');
        }

        System.out.println(sb);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
