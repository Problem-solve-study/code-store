import java.io.*;
import java.util.StringTokenizer;
import java.util.function.IntPredicate;

public class Main {
    static int N;
    static int[] in_arr = new int[13], res = new int[6];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        while (isAvailable()) {
            getLottoNum(0);
            bw.newLine();
        }
        bw.close();
    }

    static boolean isAvailable() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        if (N == 0) return false;
        for (int i = 0; i < N; i++) in_arr[i] = Integer.parseInt(st.nextToken());
        return true;
    }

    static void getLottoNum(int k) throws IOException {
        if (k == 6) {
            for (int i = 0; i < 6; i++) {
                bw.write(in_arr[res[i]] + " ");
            }
            bw.newLine();
            return;
        }
        for (int i = k > 0 ? res[k - 1] + 1 : 0; i < N; i++) {
            res[k] = i;
            getLottoNum(k + 1);
        }
    }
}