// 12172 KB, 92 ms
/*
 * N^2 에 대해 앞 뒤 k 글자가 일치하는지 판단
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] strs = new String[N];
        for (int i = 0; i < N; i++)
            strs[i] = br.readLine();

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (isPossible(strs[i], strs[j]))
                    cnt++;
            }
        }
        System.out.println(cnt);
    }

    static boolean isPossible(String a, String b) {
        int minLen = Math.min(a.length(), b.length());

        for (int k = 1; k <= minLen; k++) {
            boolean flag = true;
            // a의 처음부터, b의 마지막부터 같은지 확인
            for (int idxA = 0, idxB = b.length() - k; idxA < k; idxA++, idxB++) {
                if (a.charAt(idxA) != b.charAt(idxB)) {
                    flag = false;
                    break;
                }
            }
            if (flag)
                return true;
            flag = true;
            // b의 처음부터, a의 마지막부터 같은지 확인
            for (int idxB = 0, idxA = a.length() - k; idxB < k; idxA++, idxB++) {
                if (a.charAt(idxA) != b.charAt(idxB)) {
                    flag = false;
                    break;
                }
            }
            if (flag)
                return true;
        }
        return false;
    }
}