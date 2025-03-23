/**
 * 11808KB	68ms
 * 플5 기여했더니 플래로 올라갔습니다 :)
 * 골드 치고 정말 어려웠어요...
 * 
 * 
 * [사고흐름]
 * 각 경우의 수를 찾기? 중복된 연산이 잦을 것이다 => 이건 분명 DP라고 생각했습니다.
 * 하지만 단순 DP는 아닌 것 같고, 연산 중 자주 호훌되는 부분만 메모이제이션 하는 방향으로 갈피를 잡았습니다.
 * 
 * 
 * [알고리즘 설명]
 * 먼저, 세 케이스로 문제를 분해했습니다.
 * 1. 아무도 M번 자리에 앉지 않는 경우
 * 2. M기준 왼쪽 범위의 사람이 M자리에 앉는 경우
 * 3. M기준 오른쪽 범위의 사람이 M자리에 앉는 경우
 *
 * L : 왼쪽 범위의 사람 수
 * R : 오른쪽 범위의 사람 수
 * 라고 했을 때, 각 케이스의 경우의 수는 다음과 같습니다.
 *
 * 1. 꽉 찬 L개 자리에서 swap이 가능한 경우의 수 * 꽉 찬 R개 자리에서 swap이 가능한 경우의 수
 * 2. L개 자리 중 한 자리가 비었을 때 swap이 가능한 경우의 수 * 꽉 찬 R개 자리에서 swap이 가능한 경우의 수
 * 2. R개 자리 중 한 자리가 비었을 때 swap이 가능한 경우의 수 * 꽉 찬 L개 자리에서 swap이 가능한 경우의 수
 *
 * 위 과정에서 필요한 두 연산을 추출할 수 있습니다.
 * getDefaultCase   : 꽉 찬 n개 자리에서 swap이 가능한 경우의 수
 * getExceptOneCase : n개 자리 중 한 자리가 비었을 때 swap이 가능한 경우의 수
 *
 * 이제 이 두 함수를 구현하기 위해서, 어떤 부분을 메모이제이션 할 지 생각해 봅시다.
 * 가장 먼저 식별한 부분은 n개의 꽉 찬 자리가 존재할 때, i번 swap을 하는 경우의 수입니다.
 * 그래서 다음과 같이 DP를 정의했습니다.
 * 
 * DP[n][i] = 꽉 찬 n개의 자리 중 인접한 좌석을 i번 swap 할 수 있는 경우의 수
 * 
 * 점화식은 다음과 같습니다.
 * DP[n][i] = DP[n-2][i-1] + DP[n-3][i-1] + ... + DP[1][i-1]
 * 
 * 이제, getDefaultCase와 getExceptOneCase는 위 DP를 활용하여 만들 수 있습니다.
 * 아래 코드에 각 case를 구하는 방법을 설명해두었습니다.
 */

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] DP; // DP[n][i] = 꽉 찬 n개의 자리 중 인접한 좌석을 i번 swap 할 수 있는 경우의 수
    static int[] defaultCase;
    static int[] exceptOneCase;

    static StreamTokenizer st = new StreamTokenizer(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        N = ni();
        M = ni();

        DP = new int[N][N];
        defaultCase = new int[N];
        exceptOneCase = new int[N];
        for (int i=1; i<N; ++i) Arrays.fill(DP[i], -1);
        for (int i=0; i<N; ++i) DP[i][1] = i-1;
        Arrays.fill(defaultCase, -1);
        Arrays.fill(exceptOneCase, -1);

        int l = M-1;
        int r = N-M;

        int res = getDefaultCase(l) * getDefaultCase(r); // 아무도 M 자리에 앉지 않는 경우
        res += getExceptOneCase(l) * getDefaultCase(r); // M기준 왼쪽범위의 사람이 M 자리에 앉는 경우
        res += getExceptOneCase(r) * getDefaultCase(l); // M기준 오른쪽범위의 사람이 M 자리에 앉는 경우
        System.out.println(res);
    }

    // n개의 자리 중, 한 자리를 비웠을 때 가능한 경우의 수
    public static int getExceptOneCase(int n) {
        if (exceptOneCase[n] == -1) {
            exceptOneCase[n] = 0;
            // k명 중 사람(i)을 M자리로 옮긴 경우
            for (int i = 1; i <= n; ++i) {
                // i 지점을 기준으로 좌우 사람 수
                int cntL = i - 1;
                int cntR = n - i;

                // i 자리에 아무도 앉지 않은 경우
                exceptOneCase[n] += getDefaultCase(cntL) * getDefaultCase(cntR);

                // i 자리에 왼쪽의 사람들이 앉은 경우
                for (int j = 1; j <= cntL; ++j) exceptOneCase[n] += getDefaultCase(cntR) * getDefaultCase(cntL - j);

                // i 자리에 오른쪽 사람들이 앉은 경우
                for (int j = 1; j <= cntR; ++j) exceptOneCase[n] += getDefaultCase(cntL) * getDefaultCase(cntR - j);
            }
        }
        return exceptOneCase[n];
    }

    // n개의 자리를 꽉 채울 때, 가능한 경우의 수
    public static int getDefaultCase(int n) {
        if (defaultCase[n] == -1) {
            defaultCase[n] = 1;
            int e = n>>1;
            for (int j=1; j<=e; ++j) defaultCase[n] += getDP(n, j);
        }
        return defaultCase[n];
    }

    // n개의 자리가 꽉 차있을 때, 서로 인접한 두 자리를 i번 swap하는 것이 가능한 경우의 수 (단, 이미 swap한 자리는 다시 swap할 수 없음)
    public static int getDP(int n, int i) {
        if (DP[n][i] == -1) {
            DP[n][i] = 0;
            for (int j=2; j<n; ++j) DP[n][i] += getDP(n-j, i-1);
        }
        return DP[n][i];
    }

    private static int ni() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}