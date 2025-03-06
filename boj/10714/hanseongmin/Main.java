import java.io.*;

/*
46716KB, 220ms

이거 저만 어려웠나요.. 진짜 고전했던 문제. 이런 식의 원형 DP를 처음 풀어봐서 더 어려웠던 것 같다.

처음엔 바텀업으로 점화식을 찾아보려고 했는데 진짜 도저히 모르겠어서 일단 서브태스크 1을 맞추는 완탐 코드를 먼저 작성하고
해당 부분에서 메모이제이션을 어떻게 사용할 수 있는지 위주로 보았음.

JOI가 처음에 아무 조각을 가져갈 수 있으므로 해당 부분은 모두 탐색해봐야 한다고 생각했다.
l, r 범위에서의 최댓값을 찾는 getAns() 함수를 재귀적으로 구현해두고 한 번 계산한 구간은 메모이제이션을 이용하여
재사용한다. 완탐 코드를 짜는 것은 크게 어렵지 않으니 탑다운으로 푸는게 훨씬 쉬운 풀이인 것 같음
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int N;
    static int[] arr;
    static long[][] dp;

    public static void main(String[] args) throws Exception {
        N = nextInt();
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = nextInt();
        }

        long res = 0;
        dp = new long[N][N];
        //JOI가 처음으로 가져갈 조각을 선택
        for (int i = 0; i < N; i++) {
            res = Math.max(res, arr[i] + getAns(getNextLeft(i), getNextRight(i), 1));
        }

        System.out.println(res);
    }

    //i의 왼쪽 인덱스를 구함
    static int getNextLeft(int i) {
        return (i - 1 + N) % N;
    }

    //i의 오른쪽 인덱스를 구함
    static int getNextRight(int i) {
        return (i + 1) % N;
    }

    //먹을 수 있는 케이크 조각이 l, r일 때 JOI가 가져간 조각들의 합의 최대치를 구하는 함수
    //cnt는 현재 턴이 JOI인지 IOI인지 판별하기 위해 사용
    static long getAns(int l, int r, int cnt) {
        //먹을 수 있는 케이크 조각이 오직 하나라면
        if (l == r) {
            //현재 턴이 IOI라면 자신은 해당 조각을 가져갈 수 없다.
            if (cnt % 2 == 1) return 0;
            //현재 턴이 JOI라면 해당 조각을 가져간다.
            else return arr[l];
        }

        //이미 계산된 구간이 존재한다면 해당 값을 재사용
        if (dp[l][r] != 0) {
            return dp[l][r];
        }

        if (cnt % 2 == 1) {
            //IOI의 턴이라면
            //IOI는 반드시 그리디하게 선택하므로 먹을 수 있는 두 케이크 조각의 값을 비교
            if (arr[l] > arr[r]) {
                return dp[l][r] = Math.max(dp[l][r], getAns(getNextLeft(l), r, cnt + 1));
            } else {
                return dp[l][r] = Math.max(dp[l][r], getAns(l, getNextRight(r), cnt + 1));
            }
        } else {
            //JOI의 턴이라면 항상 가져갈 수 있는 경우의 수는 왼쪽 혹은 오른쪽이므로
            //둘 모두 재귀적으로 탐색하여 최댓값을 탐색
            long max = Math.max(
                    getAns(getNextLeft(l), r, cnt + 1) + arr[l],
                    getAns(l, getNextRight(r), cnt + 1) + arr[r]
            );

            return dp[l][r] = Math.max(dp[l][r], max);
        }
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
