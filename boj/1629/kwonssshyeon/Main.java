// 11504KB	68ms
import java.io.*;
import java.util.*;

/**
 * 이젠 익숙하죠
 * 분할정복을 이용한 거듭제곱
 */
public class Main {
    static long a, n, c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Long.parseLong(st.nextToken());
        n = Long.parseLong(st.nextToken());
        c = Long.parseLong(st.nextToken());
        System.out.print(mult());
    }

    static long mult() {
        long result = 1;
        while (n > 0) {
            if (n % 2 == 1) {
                result = (result * a) % c;
            }
            a = (a * a) % c;
            n /= 2;
        }
        return result;
    }
}
