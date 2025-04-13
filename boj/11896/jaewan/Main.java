// 11416 KB, 64 ms
/*
 * 다각형의 모든 변이 한 직선으로 반갈죽 돼야 함.
 * 예제 경우처럼 N 이 짝수일 때만, 닭발처럼 그리면 가능한 듯.
 * 
 * 결국 a, b 구간의 짝수 합을 구해야 하는데, 1,000,000,000 까지니까 O(N) 이면 시간초과.
 * 
 * 2는 제외. 도형이 안됨.
 * N/2 x (N/2 + 1)
 */

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println(-func(readInt() - 1) + func(readInt()));
    }

    static long func(int n) {
        if (n < 4)
            return 0;
        return (long) n / 2 * (n / 2 + 1) - 2;
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