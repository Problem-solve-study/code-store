// 12376 KB, 84 ms
// int 로 잡았다가 1틀..
/*
 * 하루에 한 캐릭터 당 최대 15분씩, 최대 M개의 캐릭터만 보스를 잡는다.
 * 캐릭터가 보스에게 데미지를 넣으면 보스의 체력이 데미지만큼 감소함.
 * 보스의 체력이 0 이하가 되면 보스를 자는다.
 * 
 * 보스 몬스터의 체력, 보상 메소 정보와 캐릭터의 데미지 정보가 주어질 때
 * 하루에 보스를 잡아 얻을 수 있는 최대 메소를 구해라.
 * 
 * 캐릭터의 개수 N, 하루에 사용할 캐릭터의 개수 M, 보스의 가짓수 K
 * N줄에 걸쳐 캐릭터가 1초에 가하는 데미지 D
 * K줄에 걸쳐 보스의 체력 P와 보스를 처치했을 때 드랍하는 메소 Q
 * P <= 2.66 x 10^11, Q <= 1,596,506
 * 
 * 최대 15분, 60 x 15 초 = 900 초
 * 
 * 데미지가 센 M 개의 캐릭터만 사용
 * 
 * 처치를 해야 골드를 획득함.
 * 캐릭터마다 각 보스는 1회씩만 처치 가능.
 * 
 * 그럼 선택된 M 개의 상위 캐릭터들마다 dp 돌리면 되겠네.
 * dp[i] = i 초에 획득 가능한 최대 골드
 */

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    static int N, M, K;
    static Long[] damages;
    static Boss[] boss;

    public static void main(String[] args) throws IOException {
        N = readInt();
        M = readInt();
        K = readInt();
        damages = new Long[N];

        for (int i = 0; i < N; i++)
            damages[i] = readLong();
        Arrays.sort(damages, Collections.reverseOrder());

        boss = new Boss[K];
        for (int i = 0; i < K; i++)
            boss[i] = new Boss(readLong(), readInt());

        int res = 0;
        for (int i = 0; i < M; i++) {
            int[] dp = new int[901];
            for (int j = 0; j < K; j++) {
                // j번째 보스 처치에 걸리는 시간 boss[j].hp / damages[i]
                int t = (int) Math.ceil((double) boss[j].hp / damages[i]);
                for (int time = 900; time >= t; time--)
                    dp[time] = Math.max(dp[time - t] + boss[j].gold, dp[time]);
            }
            res += dp[900];
        }

        System.out.println(res);
    }

    static class Boss {
        long hp;
        int gold;

        public Boss(long hp, int gold) {
            this.hp = hp;
            this.gold = gold;
        }

    }

    static int readInt() throws IOException {
        int c;
        while ((c = System.in.read()) <= 32)
            ;
        int n = c & 15;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }

    static long readLong() throws IOException {
        int c;
        while ((c = System.in.read()) <= 32)
            ;
        long n = c & 15;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}