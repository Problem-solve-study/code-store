import java.io.*;
import java.util.*;

/*
34344KB, 964ms

선호도와 도수 레벨 2가지를 주길래 배낭 문제인가 싶었다가 수 범위보고 아닌 것 같았다.
수의 범위가 2^31씩이나 주는데다가 특정 값의 최솟값을 구하라는 것을 보고 매개변수 탐색으로 접근(왜 문제 태그에는 없는지 모르겠음)
문제를 잘못 읽고 마신 맥주의 도수 레벨의 합이 간 레벨의 합보다 낮아야한다는 건줄 알고 약간 헤맸음

※ StreamTokenizer 쓸 때 반드시 BufferedReader를 같이 쓰십쇼... 메모리 141812 -> 34344로 엄청 차이크게 나네요
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int N, M, K;
    static int[][] beer;

    public static void main(String[] args) throws Exception {
        N = nextInt(); M = nextInt(); K = nextInt();
        beer = new int[K][2];
        for (int i = 0; i < K; i++) {
            beer[i][0] = nextInt();
            beer[i][1] = nextInt();
        }
        //선호도 기준으로 역정렬
        Arrays.sort(beer, Comparator.comparingInt(arr -> -arr[0]));

        //매개변수 탐색
        long l = 0;
        long r = Integer.MAX_VALUE;
        while (l <= r) {
            long mid = l + (r - l) / 2;
            if (search(mid)) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        //도수 레벨의 범위는 int형 범위 안쪽이므로 이 범위를 벗어나면 불가능한 경우
        System.out.print(l > Integer.MAX_VALUE ? -1 : l);
    }

    static boolean search(long mid) {
        int cnt = 0;
        long v = 0;
        for (int i = 0; i < K; i++) {
            //맥주병이 나면 안되니 도수가 너무 높은 맥주는 패스
            if (beer[i][1] > mid) continue;
            //N개의 맥주를 모두 마셨다면 종료
            if (cnt == N) break;
            v += beer[i][0];
            cnt++;
        }

        //N개의 맥주를 모두 마셨으면서 선호도까지 채웠다면 ok
        return cnt == N && v >= M;
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}