// 13032KB 120ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 
 * Knapsack
 * 항상 대미지가 큰 캐릭터부터 쓰는 게 최적
 * 대미지는 10^11이니까 시간 * 보스개수 로 탐색
 */
class Main {
    static int k;
    static long[][] boss;

    static int max;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        final int HP = 0;
        final int MESO = 1;

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        long[] damages = new long[n];
        for (int i = 0; i < n; i++) {
            damages[i] = Long.parseLong(br.readLine());
        }

        boss = new long[2][k];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            boss[HP][i] = Long.parseLong(st.nextToken());
            boss[MESO][i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(damages);

        int money = 0;
        for (int i = n - 1; i > n - m - 1; i--) {
            long damage = damages[i];
            
            int[] kanpsack = new int[901];
            for (int time = 0; time <= 900; time++) {
                if (time * damage >= boss[HP][0]) {
                    kanpsack[time] = (int) boss[MESO][0];
                }
            }

            for (int bi = 1; bi < k; bi++) {
                for (int time = 900; time > 0; time--) {
                    if (time * damage < boss[HP][bi]) {
                        continue;
                    }

                    int killingTime = (int) Math.ceil(boss[HP][bi] * 1.0 / damage);
                    int nextMeso = Math.max(kanpsack[time - 1], kanpsack[time - killingTime] + (int) boss[MESO][bi]);

                    if (nextMeso > kanpsack[time]) {
                        kanpsack[time] = nextMeso;
                    }
                }
            }

            money += kanpsack[900];
        }

        System.out.println(money);
    }
}
