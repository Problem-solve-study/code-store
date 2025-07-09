//제출번호: 96116773
//메모리:   11692 KB
//실행시간: 68 ms
import java.io.*;

//필요한 초콜릿 개수를 포함하는 2^k를 찾고
//반씩 나눠보면서 비트 기준으로 포함되면 뺌
public class Main {
    public static void main(String[] args) throws IOException {
        int k = nextInt();

        if ((k & -k) == k) {
            System.out.printf("%d 0", k);
        } else {
            int cut = 0, size = 1;
            while (size < k) {
                size <<= 1;
            }

            for (int i = size >> 1; i > 0 && k != 0; i >>= 1) {
                if ((k & i) != 0) {
                    k -= i;
                }
                cut++;
            }

            System.out.printf("%d %d", size, cut);
        }
    }

    static int nextInt() throws IOException {
        int n = System.in.read() & 15, c;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}