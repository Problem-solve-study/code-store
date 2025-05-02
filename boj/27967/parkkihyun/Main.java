//22656 KB	176 ms
import java.io.*;
import java.util.*;

// G는 최대 20개 -> 20개의 문자가 전부 G라도 2^20 => 1백만 정도.
// 그래서 괄호 검사만 stack으로 진행

class Main {

    static int N;
    static char[] line;

    public static void main(String[] args) throws Exception {
        N = ni();
        line = ns().toCharArray();

        solve(0);

        System.out.println(sb.toString());
    }

    static boolean solve(int cur) {
        if (cur == line.length) {
            Stack<Boolean> stack = new Stack<>();

            for (char c : line) {
                if (c == '(')
                    stack.push(true);
                else {
                    if (stack.empty()) {
                        return false;
                    }
                    stack.pop();
                }
            }
            if (stack.empty()) {
                for (char c : line) {
                    sb.append(c);
                }
                return true;
            }
            return false;
        }

        if (line[cur] == 'G') {
            line[cur] = '(';
            if (solve(cur + 1))
                return true;

            line[cur] = ')';
            if (solve(cur + 1))
                return true;

            // 초기화 필수
            line[cur] = 'G';
        }

        return solve(cur + 1);
    }

    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tks;

    static String ns() throws Exception {
        if (tks == null || !tks.hasMoreTokens())
            tks = new StringTokenizer(br.readLine());
        return tks.nextToken();
    }

    static int ni() throws Exception {
        return Integer.parseInt(ns());
    }
}
