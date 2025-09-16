
/*
 * 매개 변수 탐색
 */
import java.io.IOException;

public class Main {
    static int N, M;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        N = readInt();
        M = readInt();
        arr = new int[N];
        for (int i = 0; i < N; i++)
            arr[i] = readInt();
        System.out.println(parametricSearch(0, 10000));
    }

    static int parametricSearch(int left, int right) {
        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (check(mid))
                right = mid - 1;
            else
                left = mid + 1;
        }
        return left;
    }

    // 구간 점수의 최댓값이 x 이하로 가능한지 판별
    static boolean check(int x) {
        int cnt = 1, min = 10000, max = 0;
        for (int i = 0; i < N; i++) {
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);
            if (max - min > x) {
                cnt++;
                min = arr[i];
                max = arr[i];
            }
        }
        return cnt <= M;
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
        int n = c & 15;
        while ((c = read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}