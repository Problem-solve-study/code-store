
// 11564KB	68ms
import java.io.*;

public class Main {
    static int n;
    static long[][] comb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        comb = new long[49][49];
        makeComb();
        calculate();
    }

    // 이항계수를 이용해서 n=48 까지 모든 조합의 경우를 계산한다.
    static void makeComb() {
        comb[0][0] = 1;
        for (int i = 1; i <= 48; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0)
                    comb[i][j] = 1;
                else
                    comb[i][j] = comb[i - 1][j - 1] + comb[i - 1][j];
            }
        }
    }

    /**
     * 1. 13개의 숫자중 1개가 포카드인 경우 -> 나머지 (52-4)개 중 더 뽑을 만큼 뽑는다.
     * 2. 위의 경우에서 13개의 숫자중 2개가 포카드인 경우가 있을 수 있다. -> 2개가 포카드면 (1,2)가 포카드이든, (2,1)이
     * 포카드이든 같은 경우이다.
     * 따라서 1개가 포카드인 경우에서 3개가 포카드인 경우를 빼준다.
     * 3. 다시 3개가 포카드일 수 있다. 이 경우 2개가 포카드인 경우에 포함되어 빼졌을 것이다. -> 다시 더해준다.
     * 
     * => 규칙이 있는 것을 확인할 수 있다.
     */
    static void calculate() {
        int num = n / 4;
        long result = 0;
        for (int i = 1; i <= num; i++) {
            if (i % 2 == 1)
                result += (comb[13][i] * comb[52 - (4 * i)][n - (4 * i)]);
            else
                result -= (comb[13][i] * comb[52 - (4 * i)][n - (4 * i)]);
        }
        System.out.println(result % 10007);
    }
}
