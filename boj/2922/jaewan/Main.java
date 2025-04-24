// 13280 KB, 68 ms
/*
 * 즐거운 단어와 즐겁지 않은 단어로 분류
 * 즐겁지 않은 단어에서 보기 싫은 알파벳을 지우고 밑줄
 * 
 * 즐거운 단어는 모음(A,E,I,O,U)이 연속해서 3번, 자음이 연속해서 3번 나오지 않아야 함.
 * L을 반드시 포함해야 함.
 * 
 * 상근이가 보기 싫은 알파벳을 지운 단어가 주어졌을 때, 즐거운 단어를 만들 수 있는 경우의 수를 세기.
 * 
 * 자음(L 제외), 모음, L 3가지 경우를 넣으면서, 경우의 수를 세기
 * 
 */

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    static final int 자음 = 0;
    static final int 모음 = 1;
    static final int 빈칸 = -1;

    static int len, blankCnt;
    static long res;
    static int[] word = new int[100], temp = new int[100];
    static boolean[] isBlank = new boolean[100];
    static ArrayList<Integer> blankList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        int t;
        boolean lFlag = false;

        while ((t = System.in.read()) > 64) {
            switch (t) {
                case '_':
                    blankList.add(len);
                    word[len] = 빈칸;
                    isBlank[len++] = true;
                    break;
                case 'A':
                case 'E':
                case 'I':
                case 'O':
                case 'U':
                    word[len++] = 모음;
                    break;
                case 'L':
                    lFlag = true;
                default:
                    len++;
            }
        }

        blankCnt = blankList.size();
        DFS(1, 0, lFlag);
        System.out.println(res);
    }

    /// cnt 번째 blank 에 문자 넣기
    public static void DFS(long caseCnt, int cnt, boolean lFlag) {
        if (cnt == blankCnt) {
            if (lFlag)
                res += caseCnt;
            return;
        }

        int idx = blankList.get(cnt);
        // cnt 번째 L 넣기. 자음 연속 3개인지 확인.
        if (isValid(idx, 자음)) {
            // L 넣기 가능
            temp[idx] = 자음;
            DFS(caseCnt, cnt + 1, true);
            // L 제외 자음 넣기
            DFS(caseCnt * 20, cnt + 1, lFlag);
        }

        // 모음 넣기
        if (isValid(idx, 모음)) {
            temp[idx] = 모음;
            // 모음 넣기
            DFS(caseCnt * 5, cnt + 1, lFlag);
        }
    }

    public static boolean isValid(int idx, int type) {
        int cnt = 0;
        for (int i = Math.max(idx - 2, 0); i < idx; i++) {
            if (word[i] == type || (word[i] == 빈칸 && temp[i] == type))
                cnt++;
            else
                cnt = 0;
        }
        if (++cnt >= 3)
            return false;
        for (int i = idx + 1; i < Math.min(idx + 3, len); i++) {
            if (word[i] == type)
                cnt++;
            else
                break;
        }
        if (cnt >= 3)
            return false;

        return true;
    }
}