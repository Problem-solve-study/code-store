import java.io.*;

/*
13288KB, 132ms

n^2으로 도달 가능한지 체크
n^2이 시초가 안나기 때문에 러프하게 확인해도 충분
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws Exception {
        int N = nextInt();
        int K = nextInt();
        int[] arr = new int[N];
        boolean[] arr2 = new boolean[N];
        for (int i = 0; i < N; i++) {
            arr[i] = nextInt();
        }

        arr2[0] = true;
        for (int i = 0; i < N; i++) {
            //현재 돌로 건너올 수 없다면 넘어감
            if (!arr2[i]) continue;
            for (int j = i + 1; j < N; j++) {
                int v = (j - i) * (1 + Math.abs(arr[i] - arr[j]));
                if (v <= K) {
                    arr2[j] = true;
                }
            }
        }
        System.out.print(arr2[N - 1] ? "YES" : "NO");
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}