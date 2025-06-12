import java.io.*;

/*
11588KB, 68ms

문제 이해하는게 제일 어려웠음;; 문제 이해하는데만 5번은 넘게 읽은듯

문제에서는 작은 사각형에서 큰 사각형으로 확장되는 형태라고 말했는데
결국 우리는 완성된 프렉탈 평면에서 주어진 구간인 경우에만 관심있으므로
일반적인 분할 정복 문제처럼 큰 정사각형 -> 작은 정사각형으로 쪼개진다고 봐도 문제가 없다.

따라서 분할 정복으로 검정색이 칠해야 할 구간을 탐색하되, 모든 구간을 구하는 것이 아니므로
우리가 볼 영역이 아니라면 바로 나가준다. 우리가 볼 영역이 존재한다면 해당 영역을 칠해준다.

칠해야 할 영역이 짝수일 때와 홀수일 때를 조심
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int s, N, K, R1, R2, C1, C2;
    static int[][] mat;

    public static void main(String[] args) throws Exception {
        s = nextInt();
        N = nextInt();
        K = nextInt();
        R1 = nextInt();
        R2 = nextInt();
        C1 = nextInt();
        C2 = nextInt();
        mat = new int[R2 - R1 + 1][C2 - C1 + 1];

        //분할 정복으로 정답 행렬에 색 칠하기
        dq(s, 0, (int) Math.pow(N, s) - 1, 0, (int) Math.pow(N, s) - 1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                sb.append(mat[i][j]);
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }

    static void dq(int t, int sr, int er, int sc, int ec) {
        //칠해야할 사이즈가 1이면 종료
        if (t == 0) return;
        //현재 보고 있는 구간이 행렬의 범위 바깥이면 종료
        if ((er < R1 || R2 < sr) || (ec < C1 || C2 < sc)) return;

        //중심 좌표를 계산
        int centerR = sr + (er - sr) / 2; int centerC = sc + (ec - sc) / 2;
        //검정색으로 칠해야 할 한 변의 길이를 계산
        int targetL = K;
        for (int i = 0; i < t - 1; i++) {
            targetL *= N;
        }
        //칠해야 할 사각형의 구간을 계산
        int r1 = centerR - targetL / 2; int r2 = centerR + targetL / 2;
        int c1 = centerC - targetL / 2; int c2 = centerC + targetL / 2;
        //한 변의 길이가 짝수라면 중심 좌표가 완벽히 중앙이 아니므로 이를 보정
        if (targetL % 2 == 0) {
            r1++; c1++;
        }

        //칠해야 할 넓이와 보고 있는 행렬의 구간의 넓이가 겹치는 곳을 칠해줌
        for (int i = Math.max(r1, R1); i <= Math.min(r2, R2); i++) {
            for (int j = Math.max(c1, C1); j <= Math.min(c2, C2); j++) {
                mat[i - R1][j - C1] = 1;
            }
        }

        //다음 재귀 준비, 다음 재귀에서 볼 영역의 길이를 구함
        int nextL = (er - sr + 1) / N;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                //다음 분할 정복 수행
                dq(t - 1,
                        sr + nextL * j,
                        sr + nextL * (j + 1) - 1,
                        sc + nextL * i,
                        sc + nextL * (i + 1) - 1
                );
            }
        }
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
