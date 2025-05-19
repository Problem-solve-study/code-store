// 11596KB	68ms
import java.io.*;
import java.util.*;

/**
 * 문제가 뭔소리지 ...
 * 
 * 가능한 최대 득점 18 (90 / 5)
 * 18이하의 수 중 소수인 수 2,3,5,7,11,13,17
 * 
 * 득점할 확률 = (득점확률^횟수) * (안득점확률^(18-횟수)) * 18C(횟수)
 * 
 * 근데 구하는게 두 팀중 하나라도 소수인 확률이니까 전체에서 둘다 소수가 아닌 확률을 빼면 됨
 */
public class Main {
    static double a, b;
    static int[][] comb = new int[18+1][18+1];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        a = Integer.parseInt(br.readLine()) / 100.0;
        b = Integer.parseInt(br.readLine()) / 100.0;

        calculateComb();
        double result = simulate();
        System.out.println(1-result);
    }

    // 둘다 소수가 아닐 확률
    static double simulate() {
        int[] nums = new int[] {0, 1, 4, 6, 8, 9, 10, 12, 14, 15, 16, 18};
        double teamA = 0, teamB = 0;
        for (int i : nums) {
            int count = comb[18][i];
            teamA += Math.pow(a , i) * Math.pow((1-a) , (18-i)) * count;
            teamB += Math.pow(b , i) * Math.pow((1-b) , (18-i)) * count;
        }
        return teamA * teamB;
    }

    static void calculateComb() {
        for (int i = 0; i <= 18; i++) {
            comb[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                comb[i][j] = comb[i - 1][j - 1] + comb[i - 1][j];
            }
        }
    }
}
