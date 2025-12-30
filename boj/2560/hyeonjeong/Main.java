import java.io.*;
import java.util.*;

/**
 * DP
 * d*N의 배열은 시간 & 메모리 조건이 부족해 사용하지 못함
 * N은 모두 탐색해야 할 것 같아서 d를 어떻게 줄일 지 고민
 * 
 * 0-d의 숫자들을 네가지 범위로 구분
 * 1. 오늘 태어남
 * 2. 복제를 시작하지 않음
 * 3. 복제하는 중
 * 4. 복제 중단 & 사망 전
 * 2-4번 상태의 개체수를 구할 때 a, b, d일 전의 1번 상태의 개체수를 참고하면 구할 수 있음
 * 
 * 오버플로우를 방지하기 위해 모듈러 연산을 적용할 때
 * 원래 개체수에는 음수가 올 수 없지만, 값마다 모듈러 연산이 들어갔으므로 개체수 계산 결과가 음수가 나올 수 있음
 * -> 이를 방지하기 위해 1000을 더한 후에 나눈다.
 */
public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    
    public static void main(String[] args) throws IOException {
        int a = next();
        int b = next();
        int d = next();
        int n = next();

        final int BORN = 0;
        final int BORN_UNTIL_START = 1;
        final int START_UNTIL_END = 2;
        final int END_UNTIL_DEAD = 3;

        final int MOD = 1000;

        int[][] count = new int[4][n + 1];
        count[BORN][0] = 1;

        for (int i = 1; i <= n; i++) {
            count[BORN_UNTIL_START][i] = count[BORN_UNTIL_START][i - 1];
            count[BORN_UNTIL_START][i] += count[BORN][i - 1];
            if (i >= a) {
                count[BORN_UNTIL_START][i] -= count[BORN][i - a];
            }
            count[BORN_UNTIL_START][i] += MOD;
            count[BORN_UNTIL_START][i] %= MOD;
            
            count[START_UNTIL_END][i] += count[START_UNTIL_END][i - 1];
            if (i >= a) {
                count[START_UNTIL_END][i] += count[BORN][i - a];
                if (i >= b) {
                    count[START_UNTIL_END][i] -= count[BORN][i - b];
                }
            }
            count[START_UNTIL_END][i] += MOD;
            count[START_UNTIL_END][i] %= MOD;

            count[END_UNTIL_DEAD][i] += count[END_UNTIL_DEAD][i - 1];
            if (i >= b) {
                count[END_UNTIL_DEAD][i] += count[BORN][i - b];
                if (i >= d) {
                    count[END_UNTIL_DEAD][i] -= count[BORN][i - d];
                }
            }
            count[END_UNTIL_DEAD][i] += MOD;
            count[END_UNTIL_DEAD][i] %= MOD;

            count[BORN][i] = count[START_UNTIL_END][i];
        }

        System.out.print(
            (count[BORN][n] 
            + count[BORN_UNTIL_START][n]
            + count[START_UNTIL_END][n]
            + count[END_UNTIL_DEAD][n]) % MOD
        );
    }
 
    static int next() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
