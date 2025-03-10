/*
 * S의 부분 문자열을 복사해 P 문자열을 만들기.
 * P 의 각 문자에 대해, S 처음부터 매칭되는 패턴을 찾음.
 * 가장 긴 패턴을 복사해 가는 경우가 최소 횟수임.
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static String S, P;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        P = br.readLine();

        int i = 0, lenS = S.length(), lenP = P.length(), res = 0;
        // P 문자열 한글자씩 비교
        while (i < lenP) {
            // S 문자열 첫글자부터 일치하는 최대 길이의 부분 문자열 패턴 찾기
            int max = 0, cnt = 0;
            for (int j = 0; j < lenS; j++) {
                if (i + cnt >= lenP) {
                    max = Math.max(max, cnt);
                    break;
                }
                if (S.charAt(j) == P.charAt(i + cnt)) {
                    cnt++;
                } else {
                    max = Math.max(cnt, max);
                    // 처음으로 돌아가기
                    cnt = S.charAt(j) == P.charAt(i) ? 1 : 0;
                }
            }
            max = Math.max(cnt, max);
            i += max;
            res++;
        }
        System.out.println(res);
    }
}
