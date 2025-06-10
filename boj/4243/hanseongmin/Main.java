import java.io.*;

/*
19204KB, 112ms

점화식 세우기가 굉장히 빡셈 몇 시간동안 계속 봤는데 점화식 감이 1도 안잡혀서 정해 참고하여 풀었음

dp[s][e][d]: [s, e] 구간을 탐색했을 때 나머지 모든 구간을 탐색하기 위해 추가로 소요되는 최소 시간
d가 0이면 s에 위치, d가 1이면 e에 위치.

로 잡아야하는데 점화식 자체가 굉장히 특이함. 저런 식으로 세우는 걸 처음봤음
보통 이런 구간 DP는 0, N - 1로 잡고 최종 결과값을 구하는데 이 문제는 점화식이 저렇게 구성되기 때문에
min(dp[s][s][0], dp[s][s][1])로 잡아야 최종 결과값이 나옴

점화식을 세우고 난 뒤에도 살짝 난해한게 그냥 구간의 길이를 반환해야하는게 아니라 그 이전의 값들이 점차 누적되어야 함
가장 간단하게 생각할 수 있는건 이전 값 자체를 함수의 인자로 넘겨주고 해당 값을 바탕으로 테이블을 갱신하는 것인데,
이 경우 dp의 상태값(=차원)에 없는 정보가 값에 영향을 같이 줘버리기 때문에 메모이제이션이 잘못되어버림

따라서 dp 테이블 내부에는 이전의 값과 상관없는 독립적인 값을 넣어두고
누적값은 반환값에다가 더해주는 형태로 구현해줘야 함
이는 다음 구간으로 가는데 걸리는 시간에 방문해야할 다른 구간의 개수를 곱해주는 것으로 구현 가능

답이 int 범위를 넘어갈 수 있는데 이걸 고려못했다가 1틀함
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int N, a;
    static int[] arr;
    static int[] sum;
    static long[][][] dp;

    public static void main(String[] args) throws Exception {
        int T = nextInt();
        StringBuilder sb = new StringBuilder();
        while (T --> 0) {
            N = nextInt();
            a = nextInt() - 1;
            arr = new int[N - 1];
            sum = new int[N]; //누적합의 경우 int 범위를 벗어나지 않기 때문에 int로 해도 됨
            dp = new long[N][N][2];
            //초기값 세팅
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    dp[i][j][0] = dp[i][j][1] = Long.MAX_VALUE;
                }
            }

            for (int i = 0; i < N - 1; i++) {
                arr[i] = nextInt();
                sum[i + 1] = sum[i] + arr[i];
            }

            sb.append(Math.min(getAns(a, a, 0), getAns(a, a, 1))).append('\n');
        }
        System.out.print(sb);
    }

    static long getAns(int s, int e, int d) {
        //메모이제이션된 값이 존재한다면 이를 그대로 사용
        if (dp[s][e][d] != Long.MAX_VALUE) return dp[s][e][d];

        long left = Long.MAX_VALUE;
        long right = Long.MAX_VALUE;
        if (d == 0) {
            //현재 방향이 왼쪽이라면
            //왼쪽으로 갈 때는 그냥 한칸만 왼쪽으로 가면 됨
            if (s != 0) left = getAns(s - 1, e, d) + (getSegmentLength(s - 1, s) * getLeftSegment(s, e));
            //오른쪽으로 갈 때는 이전의 구간을 모두 지나서 오른쪽으로 가야함
            if (e != N - 1) right = getAns(s, e + 1, d ^ 1) + (getSegmentLength(s, e + 1) * getLeftSegment(s, e));
        } else {
            //현재 방향이 오른쪽이라면
            //왼쪽으로 갈 때는 이전의 구간을 모두 지나서 왼쪽으로 가야함
            if (s != 0) left = getAns(s - 1, e, d ^ 1) + (getSegmentLength(s - 1, e) * getLeftSegment(s, e));
            //오른쪽으로 갈 때는 그냥 한칸만 오른쪽으로 가면 됨
            if (e != N - 1) right = getAns(s, e + 1, d) + (getSegmentLength(e, e + 1) * getLeftSegment(s, e));
        }

        //현재 구간에서 왼쪽으로 가는 경우와 오른쪽으로 가는 경우 중 최소 값을 택함
        long ans = Math.min(left, right);
        if (ans == Long.MAX_VALUE) ans = 0;
        return dp[s][e][d] = ans;
    }

    static long getSegmentLength(int s, int e) {
        return sum[e] - sum[s];
    }

    static int getLeftSegment(int s, int e) {
        return N - 1 - (e - s);
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
