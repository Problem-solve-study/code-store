import java.io.*;
import java.util.*;

/*
12608KB, 68ms

매우매우 쉬운 그리디.
단순히 장신구를 많이 만들기만 하면 되므로 피로도가 낮은 장신구를 최대한 많이 만든다.
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws Exception {
        int P = nextInt();
        int N = nextInt();

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = nextInt();
        }
        Arrays.sort(arr);

        int ans = 0;
        for (int i = 0; i < N && P < 200; i++) {
            ans++;
            P += arr[i];
        }
        System.out.print(ans);
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
