//	11488KB	64ms

import java.io.*;

public class Main {
    static long answer = 0L;
    static boolean include;
    static String word;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        word = br.readLine();
        dfs(0, 1L, 1L, 0, 0);
        System.out.print(answer);
    }


    /**
     * cnt: 현재 검사 위치
     * num: L을 포함한 문자열을 만드는 경우의 수
     * withoutL: L을 포함하지 않은 문자열을 만드는 경우의 수
     * a: 연속된 모음의 개수
     * b: 연속된 자음의 개수
     * 
     * 입력 문자열에 L이 있으면 L을 포함한 모든 경우의 수를 answer 에 더한다.
     * 입력 문자열에 L이 없으면 [(전체 - L을 포함하지 않은 경우) = L이 한번이라도 포함됨] 을 더한다.
     */
    static void dfs(int cnt, long num, long withoutL, int a, int b) {
        if (a >= 3 || b >= 3) return;
        if (cnt == word.length()) {
            answer = (include) ? answer + num : answer + (num - withoutL);
            return;
        }
        switch (word.charAt(cnt)) {
            case 'A': case 'E' : case 'I' : case 'O' : case 'U':
                dfs(cnt + 1, num, withoutL, a + 1, 0);
                break;
            case '_':
                dfs(cnt + 1, (num << 2) + num, (withoutL << 2) + withoutL, a + 1, 0);
                dfs(cnt + 1, (num << 4) + (num << 2) + num, (withoutL << 4) + (withoutL << 2), 0, b + 1);
                break;
            case 'L':
                include = true;
            default:
                dfs(cnt + 1, num, withoutL, 0, b + 1);
                break;
        }
    }
}