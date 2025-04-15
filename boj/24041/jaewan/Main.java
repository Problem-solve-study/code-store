// 246280 KB, 1224 ms
/*
 * 밀키트의 유통기한은 모든 재료의 유통기한 중 가장 이른 것으로 결정됨
 * 
 * 밀키트에 N 개의 재료가 들어 있다. i 번째 재료의 유통기한은 밀키트를 구매한 후 L 일 까지, 부패 속도는 S
 * 구매 후 x일에 i번째 재료에 있는 세균수는 S x max(1, x - L)
 * 
 * 모든 재료의 세균수의 합이 G 마리 이하일 경우 먹을 수 있음.
 * 중요하지 않은 재료를 최대 K 개 빼서 세균수가 G 마리 이하면 그냥 먹음.
 * 
 * 밀키트를 산 날부터 며칠 후까지 먹을 수 있을까?
 * 이진 탐색.
 * 모든 재료 유통기한 최대면, 일수만큼 세균수 증식함. 최대 일은 10^9
 * 
 * 아. 최대 일은 재료 하나일 때, S = 1, L = 1e9 면, 2e9 가 최대네.
 * 
 * 판정 함수 시간복잡도는 O(N), 전체 시간복잡도는 (N log G)
 */

import java.io.IOException;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    static int N, G, K;
    static Ingredient[] ingreidents;

    public static void main(String[] args) throws IOException {
        N = readInt();
        G = readInt();
        K = readInt();
        ingreidents = new Ingredient[N];
        for (int i = 0; i < N; i++)
            ingreidents[i] = new Ingredient(readInt(), readInt(), readInt() == 0);

        System.out.println(ParametricSearch(0, 2_000_000_000));
    }

    static long ParametricSearch(long left, long right) {
        long mid;
        while (left <= right) {
            mid = (left + right) / 2;

            if (check(mid))
                left = mid + 1;
            else
                right = mid - 1;
        }
        return right;
    }

    static boolean check(long day) {
        PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());

        long sum = 0, t;
        for (int i = 0; i < N; i++) {
            t = ingreidents[i].S * Math.max(1, day - ingreidents[i].L);
            sum += t;
            if (!ingreidents[i].isValuable)
                pq.offer(t);
        }

        int k = K;
        while (!pq.isEmpty() && k-- > 0)
            sum -= pq.poll();

        return sum <= G;
    }

    static class Ingredient {
        int S, L;
        boolean isValuable;

        public Ingredient(int s, int l, boolean isValuable) {
            S = s;
            L = l;
            this.isValuable = isValuable;
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
}