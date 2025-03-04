
//제출번호: 90839451
//메모리:   13080 KB
//실행시간: 104 ms
import java.util.Scanner;

//3개만 고르기 때문에 재귀말고 반복문을 쓰고 싶었음
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] d = new int[n];

        for (int i = 0; i < n; i++) {
            d[i] = sc.nextInt();
        }

        int sum = 0;
        int res = 0;
        // 고르는 개수가 작기 때문에(3개를 고름) 반복문 3개를 이용하는 것도 괜찮아 보였음
        for (int i = 0; i < n; i++) {
            // 만약 목표 숫자를 넘어간다면 다음 것을 고른다.
            if (d[i] > m) {
                continue;
            }

            sum += d[i];

            for (int j = i + 1; j < n; j++) {
                // 만약 목표 숫자를 넘어간다면 다음 것을 고른다.
                if (sum + d[j] > m) {
                    continue;
                }

                sum += d[j];

                for (int k = j + 1; k < n; k++) {
                    // 만약 목표 숫자를 넘어간다면 다음 것을 고른다.
                    if (sum + d[k] > m) {
                        continue;
                    }

                    // 목표 숫자를 넘지 않는 것들 중에서 목표 숫자에 최대한 가까운 값은
                    // 가능한 값들 중 최댓값이다.
                    res = Math.max(res, sum + d[k]);
                }

                sum -= d[j];
            }

            sum -= d[i];
        }

        System.out.print(res);

        sc.close();
    }

}
