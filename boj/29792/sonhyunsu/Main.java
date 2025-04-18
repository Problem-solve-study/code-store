//제출번호:	93276996
//메모리:	12504 KB
//실행시간:	92 ms
import java.io.*;
import java.util.*;

//처음에는 2차원 냅색으로 dp[m][l]으로 잡고
//현재 선택된 i번째 캐릭터를 m번째로 사용했고, l초까지 얻을 수 있는 최대 메소를 저장하도록 구현해봤음
//하지만 dp[m][l]은 원래 하나의 캐릭터에 대한 값만 가지고 있어야 하는데 위 식을 사용해버리면
//여러 개의 캐릭터의 값이 섞여버리는 문제가 발생해서 값이 제대로 나오지 않음

//그래서 냅색을 3차원으로 잡고 dp[i][m][l]로 해봄
//i번째 캐릭터라는 정보를 dp에 추가로 저장하고 m,l 차원을 업데이트 한 후
//dp[i][m][l] = Math.max(dp[i-1][m][l])로 업데이트 해보려고 했음
//하지만 이렇게 만들어도 2차원 냅색에서 발생했던 문제를 해결하지 못 했고 코드를 갈아 엎었음

//모든 캐릭터가 독립적으로 보스를 잡으니까 각 캐릭터마다 얻을 수 있는 메소의 최댓값들을 구한 다음
//그리디하게 가장 많이 벌은 메소부터 m개를 선택하면 되지 않을까? 생각했음 => 통과
//메소의 최댓값은 1차원 냅색을 이용해서 구현함
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        long[] damages = new long[n];
        for (int i = 0; i < n; i++) {
            damages[i] = Long.parseLong(br.readLine());
        }

        long[] bossHps = new long[k];
        long[] mesos = new long[k];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            bossHps[i] = Long.parseLong(st.nextToken());
            mesos[i] = Long.parseLong(st.nextToken());
        }

        Long[] getMeso = new Long[n];
        long[] dp = new long[901];
        for (int i = 0; i < n; i++) {
			//냅색 초기화
            for (int l = 0; l < 901; l++) {
                dp[l] = 0;
            }

			//각 보스에 대해서 
            for (int j = 0; j < k; j++) {
				//클리어에 필요한 시간을 계산함
                long needClearTime = bossHps[j] / damages[i] + (bossHps[j] % damages[i] != 0 ? 1 : 0);

				//만약 15분이 넘는다면 못 잡는 보스임
                if (needClearTime > 900) {
                    continue;
                }

				//냅색 업데이트
                for (int l = 900; l >= needClearTime; l--) {
                    dp[l] = Math.max(dp[l], dp[l-(int)needClearTime] + mesos[j]);
                }
            }

			//15분에 벌 수 있는 메소의 최댓값을 getMeso에 저장
            getMeso[i] = dp[900];
        }

		//getMeso를 내림차순으로 정렬하고
        Arrays.sort(getMeso, Comparator.reverseOrder());

		//m개 만큼 res에 저장함
        long res = 0;
        while (--m >= 0) {
            res += getMeso[m];
        }

        System.out.print(res);
    }
}