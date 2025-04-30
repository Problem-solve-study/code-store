// 26276 KB, 460 ms
/*
 * 상품 수, 고객 기업 수 N, M
 * 만족도 N 개
 * 비용 M 개
 * 
 * A 만족도 물품을 B 비용 지불하는 기업에 렌탈하면 A - B 만큼의 고객 만족도.
 * 지불한 비용만큼의 만족도 얻기를 원함. 음수면 거래 x
 * 고객 만족도 합의 최댓값.
 * 
 * 만족도 큰 물품과 작은 비용 기업끼리 매칭하면 되는건가?
 * 음수면 안하니까, 혹시 더 많은 매칭을 할 때 만족도 합이 더 커지는 경우가 있나?
 * 
 * 아니다. 큰 물품과 작은 비용끼리 하는 게 제일 커짐.
 * 
 */

import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        int N = readInt(), M = readInt();
        int[] products = new int[N];
        for (int i = 0; i < N; i++)
            products[i] = -readInt();
        Arrays.sort(products);
        int[] budgets = new int[M];
        for (int i = 0; i < M; i++)
            budgets[i] = readInt();
        Arrays.sort(budgets);

        long res = 0;
        int j = Math.min(N, M);
        for (int i = 0; i < j; i++) {
            int t = products[N - i - 1] - budgets[i];
            if (t < 0)
                break;
            res += t;
        }
        System.out.println(res);
    }

    static int readInt() throws IOException {
        int c;
        while ((c = System.in.read()) <= 32)
            ;
        boolean negative = c == 45;
        if (negative)
            c = System.in.read();
        int n = c & 15;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return negative ? -n : n;
    }
}