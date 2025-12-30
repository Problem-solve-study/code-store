// 53308KB 332ms

import java.io.*;
import java.util.*;

/**
 * 완탐 + DP
 * 본 게임의 최선의 전략 = 상대가 이기지 못하는 경로를 찾는다
 * 
 * 현재 숫자 n에 대해서, n에 약수를 더했을 때 상대가 이기지 못하는 경우가 하나라도 있으면 이긴 것으로 판단. 모두 상대가 이기면 진 것으로 판단.
 * 위 판단 로직을 재귀 및 메모이제이션으로 구현하고, 숫자 1에 대한 결과를 출력
 * 
 * 이때 각 수의 약수를 구하는 로직을 k * k^1/2로 구해 시간 조건을 맞춘다
 */
public class Main {
    static int k;
    static Boolean[] winnable;
    static List<List<Integer>> divisors = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        k = Integer.parseInt(br.readLine());
        winnable = new Boolean[k + 1];

        getDivisors();

        String result = checkWinnable(1) ? "Kali" : "Ringo";

        // System.out.println(Arrays.toString(winnable));
        
        System.out.print(result);
    }

    static void getDivisors() {
        for (int n = 0; n <= k; n++) {
            divisors.add(new ArrayList<>());
            for (int i = 1; i * i <= n; i++) {
                if (n % i != 0) {
                    continue;
                }
    
                divisors.get(n).add(i);
                divisors.get(n).add(n / i);
            }
        }
    }

    static boolean checkWinnable(int n) {
        if (winnable[n] != null) {
            return winnable[n];
        }

        for (int i : divisors.get(n)) {
            if (n + i > k) {
                continue;
            }

            boolean nextWinnable = checkWinnable(n + i);

            // 다음 차례인 상대방이 이길 수 없는 경로가 하나라도 있으면 내가 이길 수 있음
            if (!nextWinnable) {
                winnable[n] = true;
                return true;
            }
        }
        
        // 다음 차례인 상대방이 이기는 경로만 있으면 내가 이길 수 없음
        winnable[n] = false;
        return false;
    }
}
