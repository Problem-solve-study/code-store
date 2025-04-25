// 18124 KB, 148 ms
/*
 * 모든 항들의 쌍을 돌면서 (초항, 공차) 값을 찾는다.
 * 가장 많이 나온 (초항, 공차) 로 수열을 만들기 위해서 바꿔야 할 카드 수의 값을 구하기.
 * 
 * 사실 나온 쌍의 수로 바꿔야 할 카드 수도 구할 수 있다.
 * 한 쌍만 나왔다 == 해당 초항, 공차에 맞는 수가 2개뿐.
 * 만약 3개면? 3쌍이 나옴
 * 4개면? 6쌍
 * 5개면? 10쌍. 즉, N(N-1)/2
 * x쌍이 나왔으면, 맞는 카드는 (1 + Math.sqrt(1 + 8 * x)) / 2 장.
 * 바꿔야 할 카드는, 전체 카드 - (위 계산식)
 */

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {
        int N = readInt();
        int[] cards = new int[N];
        for (int i = 0; i < N; i++)
            cards[i] = readInt();

        HashMap<Data, Integer> pair = new HashMap<>();
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                // cards[i] = a + d * i, cards[j] = a + d * j
                // cards[j] - cards[i] = d * (j - i), d = (cards[j] - cards[i]) / (j - i)
                // d는 정수여야 함.
                // a = cards[i] - d * i
                if ((cards[j] - cards[i]) % (j - i) != 0)
                    continue;
                int d = (cards[j] - cards[i]) / (j - i);
                int a = cards[i] - d * i;
                Data newData = new Data(a, d);
                pair.put(newData, pair.getOrDefault(newData, 0) + 1);
            }
        }

        System.out.println(N - (int) (1 + Math.sqrt(1 + 8 * Collections.max(pair.values()))) / 2);
    }

    static class Data {
        int a, d;

        public Data(int a, int d) {
            this.a = a;
            this.d = d;
        }

        @Override
        public int hashCode() {
            return Integer.hashCode(a + d);
        }

        @Override
        public boolean equals(Object obj) {
            Data d = (Data) obj;
            return (a == d.a) && (this.d == d.d);
        }
    }

    static int readInt() throws IOException {
        int c;
        while ((c = System.in.read()) <= 32)
            ;
        boolean negative = c == 45;
        if (negative)
            c = System.in.read();
        int n = c & 15;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return negative ? -n : n;
    }
}