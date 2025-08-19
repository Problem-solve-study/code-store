
// 12720 KB, 84 ms
/*
 * N이 3인 경우. 가장 큰 수 x 2가 답
 * N이 4인 경우, 벌통이 중간에 있을 수 있음. 따로 계산
 * 나머지 경우
 * 벌통은 좌, 우 끝에 배치하는 게 가장 큰 결과
 * 첫번째 벌은 무조건 반대쪽 끝에.
 * 두번째 벌은 N 돌면서 최댓값을 계산
 */
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        int N = readInt();
        int[] arr = new int[N], acc = new int[N];
        arr[0] = acc[0] = readInt();
        for (int i = 1; i < N; i++) {
            arr[i] = readInt();
            acc[i] = acc[i - 1] + arr[i];
        }

        int max = 0;
        if (N == 3) {
            max = Math.max(Math.max(arr[0], arr[1]), arr[2]);
            max <<= 1;
        } else {
            // 벌통 좌측 배치한 경우
            // 첫번째 벌은 우측 끝
            int bee1 = acc[N - 2];
            // 두번째 벌은 위치 찾기
            int bee2 = 0;
            for (int i = 1; i < N - 1; i++)
                bee2 = Math.max(bee2, acc[i - 1] - arr[i]);
            max = bee1 + bee2;

            // 벌통 우측 배치
            // 첫번째 벌은 좌측 끝
            bee1 = acc[N - 1] - arr[0];
            bee2 = 0;
            for (int i = 1; i < N - 1; i++)
                bee2 = Math.max(bee2, acc[N - 1] - acc[i] - arr[i]);
            max = Math.max(max, bee1 + bee2);

            if (N == 4)
                max = Math.max(max, arr[1] + arr[2] + Math.max(arr[1], arr[2]));
        }
        System.out.println(max);
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