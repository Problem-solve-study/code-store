//제출번호: 89061677
//메모리:	40960 KB
//실행시간:	448 ms
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    private static int[] lotto = new int[6];

    public static void main(String[] args) throws IOException{        
        BufferedReader re = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(re.readLine());
            int n = Integer.parseInt(st.nextToken());

            if (n == 0) {
                break;
            }

            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            printLotto(wr, arr, 0, 0);
            wr.newLine();
        }

        wr.flush();
        wr.close();
        re.close();
    }

    public static void printLotto(BufferedWriter wr, int[] arr, int k, int depth) throws IOException {
        if (depth == 6) {
            for (int num : lotto) {
                wr.write(String.format("%d ", num));
            }
            wr.newLine();
            return;
        }

        for (int i = k; i < arr.length; i++) {
            lotto[depth] = arr[i];
            printLotto(wr, arr, i + 1, depth + 1);
        }
    }
}
