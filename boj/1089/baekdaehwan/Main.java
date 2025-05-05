/**
 * 11612KB	68ms
 * 
 * [사고 흐름]
 * 구현문제긴 한데, 비트마스킹으로 구현하면 짧게 나올것 같아서 시도했습니다.
 * 
 * [알고리즘 설명]
 * 비트마스킹 부분은 간단하니 설명을 생략합니다.
 * 각 자리별로 가능한 수의 합을, 가능한 수의 개수만큼 나눈 값을 기존 자릿수에 맞게 결괏값에 더하는 것이 전부인 문제입니다.
 * 이게 가능한 이유는 더 작은 자릿수가 얼마나 많더라도, 더 큰 자릿수의 등장 비율은 동일하기 때문입니다.
 */

import java.io.*;

public class Main {
    static int N;
    static int[] num = {
            0b111101101101111,
            0b001001001001001,
            0b111001111100111,
            0b111001111001111,
            0b101101111001001,
            0b111100111001111,
            0b111100111101111,
            0b111001001001001,
            0b111101111101111,
            0b111101111001111
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int len = 4*N - 1;
        int[] val = new int[N];
        for (int i=0; i<5; ++i) {
            String line = br.readLine();
            for (int j=0; j<len; ++j) {
                if ((j+1)%4 == 0) continue;
                int c = (j+1)/4;
                val[c] = (val[c]<<1) + (line.charAt(j)=='#'? 1:0);
            }
        }
        double res = 0;
        boolean impossible = false;
        for (int i=0; i<N; ++i) {
            int sum = 0;
            int cnt = 0;
            boolean found = false;
            for (int j=0; j<10; ++j) {
                if ((val[i]^(val[i]&num[j])) == 0) {
                    found = true;
                    sum += j;
                    ++cnt;
                }
            }
            if (!found) impossible = true;
            res += sum*Math.pow(10.0, N-i-1)/cnt;
        }
        if (impossible) System.out.println(-1);
        else System.out.println(res);
    }
}