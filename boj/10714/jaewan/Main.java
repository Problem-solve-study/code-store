// 44504 KB, 216 ms
/*
 * 케이크를 N 조각, 각 조각마다 1부터 N까지 반시계 방향으로 번호 매김.
 * 케이크를 나누는 방법
 * 1. JOI 군이 원하는 것 하나 가져감
 * 2. 번갈아 가며 남은 조각을 하나씩 가져감.
 *  > 단, 인접한 조각 중 하나 이상이 선택된 경우만 가져갈 수 있음.
 *  > IOI 양은 가능한 조각 중 가장 큰 조각을 가져감.
 *  > JOI 군은 원하는 조각을 가져갈 수 있음.
 * JOI 군이 가져온 조각 크기의 합의 최대치를 구해라.
 * 
 * 가장 큰 조각을 못 가져가도록 하는 방법도 있겠네.
 * 시작을 어디서 하냐에 따라서도 달라짐.
 * 모든 경우는 O(N*2^N) 15점짜리 서브태스크.
 * 
 * 케이크 조각 선택은 연속적이다. 그리고 연속 구간 1양 끝점만 선택할 수 있음.
 * 어떤 구간이 결정되면 IOI 양이 다음에 가져갈 조각을 알 수 있음.
 * dp[i][j] 는 i~j구간이 선택된 경우에 JOI 군이 가져온 조각 크기의 합의 최대치를 저장
 * 
 * dp[1][1]~dp[N][N]까지 입력받은 값 저장
 * JOI군 선택 이후에는 IOI양이 뭘 선택할 지 정해져 있다.
 * 왼쪽 오른쪽 중 큰 걸 선택함. 왼쪽 요소 선택은 i를 -1, 오른쪽 요소 선택은 j를 +1.
 * 계산해서 구간 크기 2짜리 dp에 저장. dp[1][2]나 dp[5][1]
 * 
 * 이후 구간 크기 3
 * 
 * 구간 크기 5.. N까지 이어서 진행
 * 
 * 홀수면 JOI 군이 선택
 * 짝수면 IOI 양이 선택
 * 
 */

import java.io.IOException;

public class Main {
    static int N;
    static int[] arr;
    static long[][] dp;

    public static void main(String[] args) throws IOException {
        N = readInt();
        arr = new int[N];
        dp = new long[N][N];

        for (int i = 0; i < N; i++)
            arr[i] = readInt();

        for (int size = 1; size < N; size += 2) {
            // JOI 군이 선택해 구간 (i, i + size - 1) 이 되는 경우
            // 1. (i + 1, i + size - 1)에서 왼쪽 요소 선택
            // 2. (i, i + size - 2) 에서 오른쪽 요소 선택
            for (int i = 0; i < N; i++) {
                dp[i][(i + size - 1) % N] = Math.max(dp[(i + 1) % N][(i + size - 1) % N] + arr[i],
                        dp[i][(i + size - 2 + N) % N] + arr[(i + size - 1) % N]);
            }

            // IOI 양이 선택해 구간 (i, i + size)가 되는 경우
            // > dp[i][i + size - 1] 에서 큰 값이 오른쪽 요소일 때 dp 값
            // (arr[i-1] ,arr[i + size] 중 arr[i+size]가 클 때)
            // > dp[i + 1][i + size] 에서 큰 값이 왼쪽 요소일 때 dp 값
            // (arr[i], (arr[i + size + 1] 중 arr[i]가 클 때)
            // 중 최댓값 저장
            for (int i = 0; i < N; i++) {
                if (arr[(i - 1 + N) % N] <= arr[(i + size) % N])
                    dp[i][(i + size) % N] = Math.max(dp[i][(i + size) % N], dp[i][(i + size - 1) % N]);
                if (arr[i] > arr[(i + size + 1) % N])
                    dp[i][(i + size) % N] = Math.max(dp[i][(i + size) % N], dp[(i + 1) % N][(i + size) % N]);
            }
        }

        // N이 홀수면 마지막 요소 선택 진행
        if (N % 2 == 1) {
            int size = N;
            // JOI 군이 선택해 구간 (i, i + size - 1) 이 되는 경우
            // 1. (i + 1, i + size - 1)에서 왼쪽 요소 선택
            // 2. (i, i + size - 2) 에서 오른쪽 요소 선택
            for (int i = 0; i < N; i++) {
                dp[i][(i + size - 1) % N] = Math.max(dp[(i + 1) % N][(i + size - 1) % N] + arr[i],
                        dp[i][(i + size - 2 + N) % N] + arr[(i + size - 1) % N]);
            }
        }

        // 최대값 찾기
        long max = 0;
        for (int i = 0; i < N; i++)
            max = Math.max(max, dp[i][(i + N - 1) % N]);
        System.out.println(max);
    }

    private static int readInt() throws IOException {
        int c;
        do {
            c = System.in.read();
        } while (c <= 32);
        int n = c & 15;
        c = System.in.read();
        while (c > 47) {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return n;
    }
}