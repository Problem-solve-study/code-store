package Java;

//제출번호: 89841208
//메모리:   12844 KB
//실행시간: 92 ms
import java.io.*;
import java.util.*;

//단순히 n을 가지고 주어진 점화식을 해결하면 됨
//메모이제이션을 이용해서 t(k)를 중복으로 계산하지 않도록 함
public class Main {

    static long[] t = new long[36];

    public static void main(String[] args) throws IOException {
        int n = new Scanner(System.in).nextInt();

        t[0] = 1;
        for (int i = 1; i <= n; i++) {
            t[i] = calc(i);
        }

        System.out.print(t[n]);
    }
    
    static long calc(int k) {
        long res = 0;
        for (int i = 0; i < k; i++) {
            res += t[i] * t[k-1 - i];
        }

        return res;
    }
}