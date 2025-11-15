
/*
 * N 덩어리의 고기(무게, 가격)
 * 덩어리 사면 덩어리보다 싼 고기들은 덤으로 얻을 수 있음.
 * 가격이 더 싸면 필요한 양보다 더 많은 고기를 살 수도 있음.
 * 원하는 양의 고기를 구매하기 위해 필요한 최소 비용 계산
 * 
 * 1. 현재 덩어리의 가격보다 싼 고기들의 양의 합 계산, + 현재 고기의 양 >= 필요한 고기 양 M 만족하는 최소 가격 탐색
 * 2. 같은 무게의 고깃덩어리 여러 개 있는 경우, 무게가 큰 것부터 구매를 고려해서 이전 + 현재 무게(같은 가격) >= M 인 최소 가격 탐색
 */
import java.io.IOException;
import java.util.Arrays;

public class Main {
    static int N, M;
    static Meet[] meets;

    public static void main(String[] args) throws IOException {
        N = readInt();
        M = readInt();
        meets = new Meet[N];
        for (int i = 0; i < N; i++)
            meets[i] = new Meet(readInt(), readInt());

        Arrays.sort(meets);

        if (M <= meets[0].weight) {
            System.out.println(meets[0].cost);
            System.exit(0);
        }
        int prevWeight = 0, curWeight = meets[0].weight, curCost = meets[0].cost, min = Integer.MAX_VALUE;
        boolean flag = false;
        for (int i = 1; i < N; i++) {
            if (meets[i - 1].cost < meets[i].cost) {
                prevWeight += curWeight;
                curWeight = 0;
                curCost = 0;
            }
            curWeight += meets[i].weight;
            curCost += meets[i].cost;
            if (M <= prevWeight + curWeight) {
                min = Math.min(min, curCost);
                flag = true;
            }
        }
        if (flag)
            System.out.println(min);
        else
            System.out.println(-1);
    }

    static class Meet implements Comparable<Meet> {
        int weight, cost;

        public Meet(int weight, int cost) {
            this.weight = weight;
            this.cost = cost;
        }

        @Override
        public int compareTo(Meet meet) {
            return this.cost == meet.cost ? meet.weight - this.weight : this.cost - meet.cost;
        }
    }

    static int pos, len;
    static byte[] buf = new byte[8192];

    static int readInt() throws IOException {
        int c;
        while ((c = read()) <= 32)
            ;
        int n = c & 15;

        while ((c = read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }

    static byte read() throws IOException {
        if (pos == len) {
            len = System.in.read(buf);
            pos = 0;
        }
        return buf[pos++];
    }
}