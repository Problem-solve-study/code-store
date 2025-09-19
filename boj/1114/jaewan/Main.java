
/*
 * 매개 변수 탐색
 * 
 * 가장 긴 조각의 길이를 매개 변수로
 * 가능한 것이 여러 가지면, 처음 자르는 위치를 작은 것이 먼저임.
 * 출력은 가장 긴 조각의 길이와 그 때 처음 자르는 위치. 수 두 개.
 * 
 * 판정 함수에서 거꾸로, 오른쪽 끝에서부터 매개 변수보다 길이가 길어지지 않게, 최대한 길게 자르기
 */
import java.io.IOException;
import java.util.Arrays;

public class Main {
    static int L, C, K, res2;
    static int[] cut;

    public static void main(String[] args) throws IOException {
        L = readInt();
        K = readInt();
        C = readInt();

        cut = new int[K];

        for (int i = 0; i < K; i++)
            cut[i] = readInt();

        Arrays.sort(cut);

        System.out.printf("%d %d", parametricSearch(), res2);
    }

    // 막대 조각 길이의 최대가 x 로 자르기가 가능한지 판별
    static boolean check(int x) {
        int len = L, cnt = C, idx = K - 1;

        // 제일 오른쪽 지점에서 잘라도 조각 길이 x 넘으면 false
        if (len - cut[idx] > x)
            return false;

        while (cnt > 0 && idx >= 0) {
            // 가능한 경우, 안될때까지 가서, 이전 idx 에서 자르기.
            while (idx >= 0 && len - cut[idx] <= x) {
                idx--;
            }
            idx++;
            cnt--;
            len = cut[idx];
        }

        if (len <= x) {
            res2 = cut[idx];
            return true;
        }
        return false;
    }

    static int parametricSearch() {
        int left = 1, mid, right = L;
        while (left <= right) {
            mid = (left + right) / 2;
            if (check(mid))
                right = mid - 1;
            else
                left = mid + 1;
        }
        return left;
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