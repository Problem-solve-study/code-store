//	18724KB	192ms
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int l = Integer.parseInt(st.nextToken());
        int u = Integer.parseInt(st.nextToken());

        System.out.println(digitSumTotal(u) - digitSumTotal(l - 1));
    }

    /*
    * 자릿수 DP를 위한 재귀 함수
    *
    * pos   : 현재 보고 있는 자릿수 위치 (0부터 시작, 왼쪽에서 오른쪽으로)
    * sum   : 지금까지 선택한 숫자들의 자릿수 합
    * tight : 현재 자릿수 선택이 원래 숫자의 자릿수 범위 내인지 여부 (true면 제한 있음)
    *           예) [4,5,2] -> 0번째자리가 4면 tight = true, 1번째자리도 5까지만 가능 tight = true ...
    *                       -> 0번째자리가 3면 tight = false, 1번째자리는 0~9까지 모두 가능 tight = false ...
    * digits: 목표 숫자를 자릿수 배열로 만든 것 (예: 123 → [1,2,3])
    * memo  : 메모이제이션을 위한 3차원 배열 [pos][sum][tight이 false일 때만 저장]
    */
    static long dfs(int pos, int sum, boolean tight, int[] digits, long[][][] memo) {
        

        // 모든 자릿수를 다 선택했다면, 지금까지의 자릿수 합을 반환
        if (pos == digits.length) return sum;

        // tight가 false일 때 이미 계산한 적 있다면 메모이제이션 값 반환
        if (!tight && memo[pos][sum][0] != -1) return memo[pos][sum][0];

        // 현재 자릿수에서 선택할 수 있는 최대 숫자 결정
        // tight == true이면 원래 숫자의 해당 자릿수까지만 가능, 아니면 0~9 자유롭게 가능
        int limit = tight ? digits[pos] : 9;

        long res = 0; // 결과 누적 변수

        // 현재 자릿수에 대해 0부터 limit까지 가능한 모든 숫자를 선택
        for (int d = 0; d <= limit; d++) {
            // 다음 자릿수로 이동하면서 자릿수 합과 tight 조건 갱신
            res += dfs(
                pos + 1,
                sum + d,
                tight && (d == limit),  // tight는 현재도 tight이고, d가 최대값일 때만 유지
                digits, memo
            );
        }

        // tight가 false일 때만 메모이제이션 저장 (tight가 true면 경우의 수 제한되므로 저장 안 함)
        if (!tight) memo[pos][sum][0] = res;

        return res;
    }

    // 0부터 n까지의 모든 숫자들의 자릿수 합을 구하는 함수
    static long digitSumTotal(int n) {
        // 음수일 경우 0 반환 (l이 0인 경우 예외 처리)
        if (n < 0) return 0;

        // 숫자 n을 각 자릿수로 나눈 배열로 변환 (123 → [1,2,3])
        int[] digits = Integer.toString(n).chars().map(c -> c - '0').toArray();

        /*
         * memo[자릿수 길이 + 1][최대 가능한 자릿수 합 + 1][tight 상태 개수]
         * 최대 자릿수 합은 자릿수 개수 * 9 (각 자리에 9가 들어가는 최대 경우)
         *      예) 자리수 3 -> 999 -> 9+9+9 가 가능한 가장 큰 자리수의 합
         */
        long[][][] memo = new long[digits.length + 1][9 * digits.length + 1][1];

        for (long[][] m1 : memo)
            for (long[] m2 : m1)
                Arrays.fill(m2, -1);

        // 0부터 n까지의 자릿수 합 계산
        return dfs(0, 0, true, digits, memo);
    }
}
