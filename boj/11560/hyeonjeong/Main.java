import java.io.*;
import java.util.*;

/**
 * (어떤 다항식)(1+x+x^1+...x^k)의 결과에서
 * x^i의 계수 = 어떤 다항식에서 x^(i-k)항부터 x^i항까지 계수의 합
 * 
 * k=20으로 N의 최대값이 250이 안 됨
 * 탐색 범위도 20(k 탐색) * 20(i 탐색) * 250(N 탐색) 정도
 */
class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        long[][] digits = calculateDigits();

        int T = nextInt();
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            int k = nextInt();
            int n = nextInt();

            sb.append(digits[k][n]).append('\n');
        }

        System.out.println(sb);
    }

    static long[][] calculateDigits() {
        final int MAX_DEGREE_INPUT = 20;    // 입력으로 들어오는, 곱항 할의 최고차항의 차수
        final int MAX_DEGREE_RESULT = 250;  // 연산 결과의 최고차항의 차수

        long[][] digits = new long[MAX_DEGREE_INPUT + 1][MAX_DEGREE_RESULT];
        digits[1][0] = 1;  // x + 1 세팅
        digits[1][1] = 1;

        // 곱해지는 항의 최고차항의 차수
        for (int mulDegree = 2; mulDegree <= MAX_DEGREE_INPUT; mulDegree++) {
            digits[mulDegree][0] = 1;

            for (int i = 1; i < MAX_DEGREE_RESULT; i++) {
                for (int j = 0; j <= mulDegree; j++) {
                    if (i - j < 0) {
                        break;
                    }
                    digits[mulDegree][i] += digits[mulDegree - 1][i - j];
                }
                if (digits[mulDegree][i] == 0) {
                    break;
                }
            }
        }

        // for (int i = 0; i <= MAX_DEGREE_INPUT; i++) {
        //     System.out.println(Arrays.toString(digits[i]));
        // }

        return digits;
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
