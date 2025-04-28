// 11872KB 128ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1 2 3 4 5 (m = 3) -> 4 x 1 2 4 (m = 1) -> 2 x 3 x 1 (m = 3) 을 반복
 * 
 * m의 변화
 * 1. m이 k보다 컸으면 m -= k (항상 k칸만큼 줄어든다)
 * 2. m이 k보다 작으면 m = m - k + left * {식의 결과가 양수가 되게 하는 최소값} (k만큼 줄어든 후, 남은 애들의 개수만큼 더해진다)
 *
 * 성민님 채일님 감사합니다 ...
 */
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int left = n;       // 남은 애들 개수
        while (true) {
            m -= k;
            if (m < 0) {
                // m + left * x >= 0 -> x >= -m / left
                m -= Math.floor(m * 1.0 / left) * left;
            }

            if (m == 0) {
                System.out.println(n - left + 1);
                return;
            }

            left--;
        }
    }
}
