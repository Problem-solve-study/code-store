import java.io.*;
import java.util.*;

/*
11636KB, 68ms

모든 팀이 소수가 아닌 수로 득점하는 경우를 계산하여 모두 더하고 1에서 빼면 답이 나온다.
만약에 모든 골을 넣는다고 가정하면 각 팀당 최대 18골이고
18 이하의 소수는 2 3 5 7 11 13 17이므로 이를 제외한 18이하의 합성수만큼 골을 넣을 확률을 모두 더하면 된다.

만일 A가 4골을 넣는 확률을 생각해본다면
먼저 18 간격 중 4골을 고르는 경우의 수는 18C4이다. 조합은 계산 전에 미리 구해두자
18 간격 중 4번은 골을 넣고 14번은 골을 넣으면 안되니까

경우의 수 * (골 넣을 확률 * 4) * (골을 넣지 못할 확률 * 14)를 하면 A가 전체 경우의 수에서 4골을 넣을 확률이 나온다.

이를 모든 합성수에 대해 시행하여 A가 전체 경우의 수에서 합성수만큼의 골을 넣을 확률을 구한다.
B도 동일하게 구한 후 A와 B가 동시에 합성수로 득점하는 경우의 수를 1에서 빼면 되므로
1 - (A 확률 * B 확률)이 답이 된다.

진짜 바보같이 입력받을 때 실수해서 1틀함..
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    //18 이하의 합성수 목록
    static int[] target = {0, 1, 4, 6, 8, 9, 10, 12, 14, 15, 16, 18};
    static int[][] combination = new int[19][19];

    public static void main(String[] args) throws Exception {
        double A = input(nextInt());
        double B = input(nextInt());
        //조합을 구함
        getCombination();

        //특정 확률일 때 합성수로 골을 넣을 확률
        double a = getAns(A);
        double b = getAns(B);
        System.out.print(1 - (a * b));
    }

    static double input(int n) {
        if (n == 100) return 1;
        else if (10 <= n && n < 100) return Double.parseDouble("0." + n);
        else return Double.parseDouble("0.0" + n);
    }

    static double getAns(double goalP) {
        double res = 0;
        for (int t : target) {
            //전체 18번의 간격 중 합성수만큼 뽑는 경우의 수
            int cnt = combination[18][t];
            //득점을 t번 하고
            double goal = Math.pow(goalP, t);
            //미득점을 18 - t번만큼 하게 된다.
            double noGoal = Math.pow(1 - goalP, 18 - t);
            //이러한 확률이 경우의 수만큼 존재하므로 경우의 수만큼 곱해줌
            res += (cnt * goal * noGoal);
        }
        return res;
    }

    static void getCombination() {
        for (int n = 0; n <= 18; n++) {
            combination[n][0] = 1;
        }

        for (int n = 1; n <= 18; n++) {
            for (int r = 1; r <= 18 && r <= n; r++) {
                combination[n][r] = combination[n - 1][r - 1] + combination[n - 1][r];
            }
        }
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
