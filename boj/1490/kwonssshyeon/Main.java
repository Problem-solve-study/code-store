// 11528KB	68ms
import java.io.*;

/**
 * 완전탐색 문제.
 * divisor에 각 자리수를 저장하고, 가능한 작은 수부터 divisor의 모든 원소로 나누어 떨어지는지를 확인한다.
 * - 주어진 n 이 몇자리수인지를 알아내기 위해 log10을 이용 (k자리수일때 Math.log10의 시간복잡도는 k)
 * - n 으로 시작하는 수를 만들기 위해 n에 10을 곱하고, 아래 자리의 수를 1씩 늘려감.
 * 
 * ! divisor 배열에 중복된 수가 담길 수 있어 약간 비효율적이긴 함. (이미 나눴던 수로 또 나눠봄...)
 */
public class Main {
    static int n;
    static int[] divisor;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int length = (int) Math.log10(n) + 1;
        divisor = new int[length];
        int copy = n;
        for (int i=0; i<length; i++) {
            divisor[i] = copy % 10;
            copy /= 10;
        }
        long step = 1;
        while(true) {
            for (int i=0; i<step; i++) {
                if (isPossible(n * step + i)) {
                    System.out.print(n * step + i);
                    return;
                }
            }
            step *= 10;
        }
    }

    static boolean isPossible(long num) {
        for (int div : divisor) {
            if (div == 0) continue;
            if (num % div != 0) return false; 
        }
        return true;
    }
}