
// 11932 KB, 88 ms
/*
 * 크리 문자열은 길이가 짝수이고, 앞 절반의 합과 뒤 절반의 합이 같은 부분 문자열이다.
 * 문자열 S가 주어지면, S의 크리 문자열 중 가장 긴 것의 길이를 출력.
 * 
 * 구간합 사용. 길이가 1,000이니까 길이가 짝수인 모든 구간에 대해 검사해도 1,000,000
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] acc;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String nums = br.readLine();

        int len = nums.length();
        acc = new int[len + 1];
        for (int i = 0; i < len; i++) {
            int t = nums.charAt(i) - '0';
            for (int j = i + 1; j <= len; j++)
                acc[j] += t;
        }

        int res;
        loop: for (res = (len | 1) ^ 1; res > 0; res -= 2) {
            for (int left = 0, right = left + res; right <= len; left++, right++) {
                int mid = (left + right) / 2;
                if (acc[mid] - acc[left] == acc[right] - acc[mid])
                    break loop;
            }
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
