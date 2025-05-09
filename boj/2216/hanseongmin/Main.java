import java.io.*;
import java.util.*;

/*
85236KB, 424ms

처음에 그리디로 될 줄알고 그리디로 열심히 풀다가 3틀하고 반례가 있겠다는걸 깨달은 이후 버리고 DP로 풀이

dp[i][j]: X.substring(i)와 Y.substring(j)일 때의 최대 점수라고 생각하자.
그러면 현재 보고 있는 문자를 기준으로 생각할 수 있는 경우의 수는 세 가지가 된다.

1) 같은 위치에 두기
a | bc
d | c
와 같이 같은 위치에 두는 것
이 경우 X와 Y 모두 하나의 문자를 사용하므로 i와 j를 모두 늘려야 한다.

2) j를 공백으로 두기
a | bc
  | dc
와 같이 두는 것
X의 문자만 사용하였으므로 i만 늘린다.

3) i를 공백으로 두기
  | abc
d | c
와 같이 두는 것
Y의 문자만 사용하였으므로 j만 늘린다.

이후엔 적절히 상황에 맞게 점수를 계산하여 dp를 채우면 된다.
바텀업으로는 잘 모르겠어서 탑다운으로 풀었다.
 */

public class Main {
    static int A, B, C;
    static String X, Y;
    static long[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        X = br.readLine();
        Y = br.readLine();
        dp = new long[X.length() + 1][Y.length() + 1];
        for (int i = 0; i < X.length(); i++) {
            Arrays.fill(dp[i], Integer.MIN_VALUE);
        }

        System.out.print(findAns(0, 0));
    }

    static long findAns(int i, int j) {
        //한 쪽이 끝에 도달한 경우 나머지 문자들은 B점을 얻게 됨
        if (i >= X.length()) {
            return dp[i][j] = (long) (Y.length() - j) * B;
        } else if (j >= Y.length()) {
            return dp[i][j] = (long) (X.length() - i) * B;
        }
        //이미 계산한 적이 있다면 재사용
        if (dp[i][j] != Integer.MIN_VALUE) return dp[i][j];

        long max = Integer.MIN_VALUE;
        //현재 문자를 같은 위치로 뒀을 경우
        //현재 문자가 같다면 A를 획득, 그게 아니라면 C
        max = Math.max(max, findAns(i + 1, j + 1) + (X.charAt(i) == Y.charAt(j) ? A : C));
        //j를 공백으로 두는 경우
        max = Math.max(max, findAns(i + 1, j) + B);
        //i를 공백으로 두는 경우
        max = Math.max(max, findAns(i, j + 1) + B);

        return dp[i][j] = max;
    }
}
