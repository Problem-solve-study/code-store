// 17628KB 276ms

import java.util.*;
import java.io.*;

/**
 * DP
 * 
 * cost[i][j]: i번째 파일부터 j번째 파일을 합치는 데 든 최소 비용
 * cost[i][j] = (cost[i][k] + cost[k + 1][j]의 최소인 k 탐색) + i번째 파일부터 j번째 파일까지의 파일 크기 합
 */
class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    
    public static void main(String[] args) throws IOException {
        int t = nextInt();

        int[] files = new int[500];         // i-j번째 파일들의 크기 합을 위한 누적합
        int[][] cost = new int[500][500];   // dp 테이블
        final int MAX_FILE = Integer.MAX_VALUE;

        StringBuilder sb = new StringBuilder();
        for (; t > 0; t--) {
            int n = nextInt();

            // 모든 파일의 복사 비용 초기화
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    cost[i][j] = MAX_FILE;
                }
            }

            // 누적합 배열 초기화 & 파일 1개는 복사 비용 = 0
            files[0] = nextInt();
            cost[0][0] = 0;
            for (int i = 1; i < n; i++) {
                int file = nextInt();
                files[i] = files[i - 1] + file;
                cost[i][i] = 0;
            }

            // cost[i][j] 구하기
            // j - i을 1부터 n-1까지 늘려가며 탐색
            for (int step = 1; step < n; step++) {
                for (int i = 0; i + step < n; i++) {
                    int j = i + step;

                    // cost[i][j]를 최소로 만들 k 탐색
                    for (int k = i; k < j; k++) {
                        cost[i][j] = Math.min(
                            cost[i][j],
                            cost[i][k] + cost[k + 1][j]
                        );
                    }

                    // 이번 합치기 비용 추가
                    cost[i][j] += files[j] - (i == 0 ? 0 : files[i - 1]);
                }
            }
            
            sb.append(cost[0][n - 1]).append('\n');
        }

        System.out.println(sb);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
