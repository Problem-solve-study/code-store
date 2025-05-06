// 20772 KB, 168 ms
/*
 * 친구 N 명 (1 <= N <= 100,000)
 * 성냥의 개수 K(1 <= K <= N)
 * 친구의 도착 시간 T[i](1 <= T[i] <= 1,000,000,000) - 동시에 두 명 방문 x
 * 모든 i 에 대해 T[i] < T[i + 1] 만족.
 * 
 * 친구는 T[i] 시간에 도착, T[i] + 1 시간에 나감.
 * 
 * 친구가 와야 난로를 킨다.
 * 난로가 켜져 있는 시간을 최소로.
 * 
 * 한 번에 한 명만 방문.
 * 
 * 성냥의 개수만큼 불을 켤 수 있다.
 * 전체 시간 (마지막 나가는 시간 - 첫번째 도착 시간) 에서, 성냥 개수 - 1 만큼 중간 텀을 줄일 수 있다.
 * 정렬해서 계산.할까? PQ를 쓸까? 정렬이 나을듯. PQ 에서 들어가고 뽑는 개수가 K 만큼 있으니까.
 * 
 * 10만개니까 이것도 버퍼 써볼까?
 */

import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        int N = readInt(), K = readInt(), prev = readInt(), res = 1 - prev;
        int[] gap = new int[N - 1];

        int t;
        for (int i = 0; i < N - 1; i++) {
            t = readInt();
            gap[i] = prev + 1 - t;
            prev = t;
        }

        res += prev;
        Arrays.sort(gap);

        for (int i = 0; i < K - 1; i++)
            res += gap[i];
        System.out.println(res);
    }

    static int readInt() throws IOException {
        int c = read();
        int n = c & 15;
        while ((c = read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }

    static int pos, len;
    static byte[] buf = new byte[8192];

    static int read() throws IOException {
        if (pos == len) {
            len = System.in.read(buf);
            pos = 0;
        }
        return buf[pos++];
    }
}