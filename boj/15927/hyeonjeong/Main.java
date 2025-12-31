// 18612KB 156ms

import java.util.*;
import java.io.*;

/**
 * 입력 문자열이 회문이 아니라면 -> 답: 입력 문자열의 길이
 * 입력 문자열이 회문이라면 -> 입력 문자열에서 첫번째 혹은 마지막 문자를 빼면 회문이 아니게 됨 -> 답: 입력 문자열의 길이 - 1
 * 
 * 이때 입력 문자열이 문자 하나로 이루어진 회문이라면 회문이 아닌 부분문자열을 만들 수 없음
 */
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] line = br.readLine().toCharArray();

        boolean isPalindrom = true;
        Set<Character> chars = new HashSet<>();

        int s = 0;
        int r = line.length - 1;
        while (s < r) {
            if (line[s] != line[r]) {
                isPalindrom = false;
                break;
            }

            chars.add(line[s]);
            chars.add(line[r]);

            s++;
            r--;
        }

        chars.add(line[s]);

        if (!isPalindrom) {
            System.out.println(line.length);
            return;
        }

        if (chars.size() != 1) {
            System.out.println(line.length - 1);
            return;
        }

        System.out.println(-1);
    }
}
