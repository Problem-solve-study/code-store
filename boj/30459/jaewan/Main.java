
// 14072 KB, 128 ms
/* 
 * N개의 말뚝에 대해 가능한 밑변의 길이를 전부 생성한다.
 * 정렬한다.
 * 
 * M개의 말뚝에 대해 R 을 upper bound로 하여
 * 가능한 현수막의 최대 넓이의 넓이를 구한다.
 * 
 */
import java.io.IOException;
import java.util.Arrays;

public class Main {
    static int R;
    static boolean[] basePossible;

    public static void main(String[] args) throws IOException {
        int N = readInt(), M = readInt();
        R = readInt();
        int[] arr = new int[N];

        for (int i = 0; i < N; i++)
            arr[i] = readInt();
        Arrays.sort(arr);

        int maxdiff = arr[N - 1] - arr[0];
        basePossible = new boolean[maxdiff + 1];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < i; j++)
                basePossible[arr[i] - arr[j]] = true;

        int[] heights = new int[M];
        for (int i = 0; i < M; i++)
            heights[i] = readInt();
        Arrays.sort(heights);

        int best = 0;
        for (int width = 1; width <= maxdiff; width++) {
            if (!basePossible[width])
                continue;
            double upper = 2.0 * R / width;
            // heights는 오름차순 정렬되어 있으므로 이진 탐색
            int left = 0, right = M - 1;
            while (left <= right) {
                int mid = (left + right) >> 1;
                if (heights[mid] <= upper)
                    left = mid + 1;
                else
                    right = mid - 1;
            }
            int idx = right;
            if (idx >= 0) {
                int prod = width * heights[idx];
                if (prod > best)
                    best = prod;
            }
        }
        if (best == 0)
            System.out.println(-1);
        else
            System.out.printf("%.1f", best / 2.0);
    }

    static int pos, len;
    static byte[] buf = new byte[8192];

    static byte read() throws IOException {
        if (pos == len) {
            len = System.in.read(buf);
            pos = 0;
        }
        return buf[pos++];
    }

    static int readInt() throws IOException {
        int c;
        while ((c = read()) <= 32)
            ;
        boolean negative = c == 45;
        if (negative)
            c = read();
        int n = c & 15;
        while ((c = read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return negative ? -n : n;
    }
}