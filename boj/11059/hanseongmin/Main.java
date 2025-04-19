import java.io.*;

/*
187016KB, 244ms

최대 문자열의 길이가 1000이니 N^2 브루트포스
 */

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int res = 0;
        for (int i = 0; i < s.length(); i++) { //시작 인덱스
            for (int j = 2; (i + j) <= s.length(); j += 2) { //구간 길이
                String subStr = s.substring(i, i + j);
                //앞 절반의 합과 뒤 절반의 합이 같다면 답 갱신 시도
                if (getSum(subStr, 0, subStr.length() / 2) == getSum(subStr, subStr.length() / 2, subStr.length())) {
                    res = Math.max(res, j);
                }
            }
        }
        System.out.print(res);
    }

    static int getSum(String str, int s, int e) {
        int sum = 0;
        for (int i = s; i < e; i++) {
            sum += str.charAt(i) - '0';
        }
        return sum;
    }
}
