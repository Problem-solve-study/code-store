// 11468 KB, 68 ms
/*
 * n 이 주어질 때, 6번 줄이 몇 번 실행될까.
 * 이중 for문의 바깥 i 는 1부터 n까지.
 * 안쪽의 j는, 1부터 n까지인데, i 씩 더해감.
 * 
 * 즉, 1, 1 + i, 1 + 2i, 1 + 3i, ... 니까 
 * 
 * 0, i, 2i, 3i 이고 n - 1 까지인 경우의 수와 동일하다.
 * 
 * 즉, (n - 1) / i + 1 회.
 * 
 * i를 1부터 n까지 바꿔가며 더하면 됨.
 * n 이 10^9 = 1,000,000,000 니까 O(N) 시간초과 나겠네.
 * 
 * N - 1 / i 는, N - 1 까지 i 의 배수의 개수.
 * 같은 값인 경우를 한꺼번에 계산 가능.
 * (N - 1) / ((N - 1) / i) 까지 같은 값임.
 */

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        int N = readInt(), j = 1;
        long res = N;
        for (int i = 1; i < N; i = j + 1) {
            // (N - 1) / i 가 같은 범위의 최댓값
            j = (N - 1) / ((N - 1) / i);
            int cnt = (N - 1) / i;
            res += (j - i + 1) * cnt;
        }
        System.out.println(res);
    }

    static int readInt() throws IOException {
        int c;
        while ((c = System.in.read()) <= 32)
            ;
        int n = c & 15;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}