import java.io.*;
import java.util.*;

/*
13056KB, 76ms

차이의 합이 최소가 되어야하므로 그냥 정렬하고 순차적으로 값을 구하면 되지 않나 싶었음.
애드 혹 풀이(2 * (max - min))도 있던데 거기까진 생각하지 못했다
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws Exception {
        int N = nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = nextInt();
        }
        //일단 정렬 후
        Arrays.sort(arr);

        //차이를 차례대로 합산
        long sum = 0;
        for (int i = 0; i < N; i++) {
            if (i != N - 1) {
                sum += arr[i + 1] - arr[i];
            } else {
                sum += arr[i] - arr[0];
            }
        }
        System.out.print(sum);
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
