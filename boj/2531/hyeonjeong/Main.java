// 17780kb 156ms: 윈도우 초밥 종류 개수를 Set으로
// 16188kb 116ms: 윈도우 초밥 종류 개수를 int로

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]); // 초밥 개수
        int d = Integer.parseInt(input[1]); // 초밥 종류 개수
        int k = Integer.parseInt(input[2]); // 윈도우 크기
        int c = Integer.parseInt(input[3]) - 1; // 쿠폰 번호

        // 회전초밥 초기화
        int[] sushies = new int[n];
        for (int i = 0; i < n; i++) {
            sushies[i] = Integer.parseInt(br.readLine()) - 1;
        }

        // 윈도우 초기화: 쿠폰 스시 넣고 시작
        int[] sushiCount = new int[d];
        sushiCount[c]++;
        int typeCount = 1;
        for (int i = 0; i < k; i++) {
            int sushi = sushies[(i % n)];
            if (sushiCount[sushi]++ == 0) {
                typeCount++;
            }
        }
        int max = typeCount;

        int i = 0;
        int j = k - 1;
        for (int cnt = 0; cnt < n; cnt++) {
            int removeSushi = sushies[i];
            i = (i + 1) % n;
            j = (j + 1) % n;
            int appendSushi = sushies[j];
            if (appendSushi != removeSushi) {
                if (sushiCount[appendSushi]++ == 0) {
                    typeCount++;
                }

                if (--sushiCount[removeSushi] == 0) {
                    typeCount--;
                }
                
                if (typeCount > max) {
                    max = typeCount;
                }
            }
        }

        System.out.println(max);
    }
}
