// 12124 KB, 296 ms
/*
 * 1층부터 t 층 까지 비가 옴.
 * 무너지는 층 중 아무거나 출력하면 되니까 1층 출력하면 됨.
 * 
 * 총 량이 K 초과되었을 때.
 */

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int N = readInt(), M = readInt(), K = readInt();
        int sum = 0;
        for (int i = 1; i <= M; i++) {
            readInt();
            sum += readInt();
            if (sum > K) {
                System.out.println(i + " " + 1);
                break;
            }
        }
        if (sum <= K)
            System.out.println(-1);
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