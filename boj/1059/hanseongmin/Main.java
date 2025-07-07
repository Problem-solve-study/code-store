import java.io.*;
import java.util.*;

/*
11576KB, 76ms

뭔가 빠르게 구할 수 있어 보였는데 L이랑 수의 범위가 너무 작길래 그냥 브루트포스로 구함
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int L, n;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        L = nextInt();
        arr = new int[L];
        for (int i = 0; i < L; i++) {
            arr[i] = nextInt();
        }
        n = nextInt();
        Arrays.sort(arr);

        int ans = 0;
        //n이 첫 번째 원소보다 작을 수 있음 이 경우도 고려해줘야 함
        if (n < arr[0]) {
            for (int a = 1; a < arr[0]; a++) {
                for (int b = a + 1; b < arr[0]; b++) {
                    if (a <= n && n <= b) ans++;
                }
            }
        } else {
            for (int i = 0; i < L - 1; i++) {
                int j = i + 1;
                for (int a = arr[i] + 1; a < arr[j]; a++) {
                    for (int b = a + 1; b < arr[j]; b++) {
                        if (a <= n && n <= b) ans++;
                    }
                }
            }
        }

        System.out.print(ans);
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
