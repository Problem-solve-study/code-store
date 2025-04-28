// 34160KB 316ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 정렬한 후 조합 범위 조절
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] files = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            files[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(files);

        long answer = 0L;
        int right = 0;
        for (int fi = 0; fi < n; fi++) {
            right = Math.max(fi + 1, right);
            while (right < n) {
                if (files[fi] * 1.0 / files[right] < 0.9) {
                    break;
                }

                right++;
            }

            answer += right - fi - 1;
        }

        System.out.println(answer);
    }
}
