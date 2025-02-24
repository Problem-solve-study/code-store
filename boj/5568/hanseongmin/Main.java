import java.io.*;
import java.util.*;

/*
14480KB, 76ms

카드를 주고 뽑으라네? 조합인가보다
->
아 근데 뽑은 수를 나열해야하구나 순열이네
->
정수의 개수를 구해야하는거면 순열을 뽑고 set에 넣은 다음에 크기를 출력하면 되겠다.
 */

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StreamTokenizer st = new StreamTokenizer(br);
    static HashSet<Integer> set = new HashSet<>();
    static int n, k;
    static boolean[] v;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        n = nextInt();
        k = nextInt();
        v = new boolean[n];
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = nextInt();
        }

        dfs(0, 0);
        bw.write(String.valueOf(set.size()));
        bw.flush();
    }

    static void dfs(int cnt, int value) {
        if (cnt == k) {
            set.add(value);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!v[i]) {
                int nextV;
                if (value == 0) nextV = arr[i];
                else if (arr[i] < 10) nextV = value * 10 + arr[i];
                else nextV = value * 100 + arr[i];

                v[i] = true;
                dfs(cnt + 1, nextV);
                v[i] = false;
            }
        }
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
