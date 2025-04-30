// 13508 KB, 100 ms
/*
 * 정수 카드 한 장 씩
 * a, b, c
 * 
 * 3장 최댓값과 최솟값의 차이가 벌점.
 * 
 * 숫자 카드가 주어졌을 때 만들 수 있는 가장 작은 벌점
 * 
 * A B C 첫번째, 두번째, 세번째 플레이어가 받은 카드의 개수
 * 
 * 
 * 1A 2C 3C 4A 5A 6B 6C 6C 8B 9C 10B 15C
 * 
 * 같은 플레이어의 중복 카드 제거 후, 전체 정렬 후 Deque에 넣는다.
 * Deque에 들어있는 플레이어별 카드 장수를 counting 한다.
 */

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) throws IOException {
        TreeSet<Data> cardSet = new TreeSet<>();
        int aSize = readInt(), bSize = readInt(), cSize = readInt();
        for (int i = 0; i < aSize; i++)
            cardSet.add(new Data(0, readInt()));
        for (int i = 0; i < bSize; i++)
            cardSet.add(new Data(1, readInt()));
        for (int i = 0; i < cSize; i++)
            cardSet.add(new Data(2, readInt()));

        int min = Integer.MAX_VALUE;
        int[] cardCnt = new int[3];
        ArrayDeque<Data> deque = new ArrayDeque<>();
        Iterator<Data> iter = cardSet.iterator();
        while (iter.hasNext()) {
            Data d = iter.next();
            cardCnt[d.player]++;
            deque.addLast(d);
            while (cardCnt[0] > 0 && cardCnt[1] > 0 && cardCnt[2] > 0) {
                min = Math.min(min, deque.peekLast().num - deque.peekFirst().num);
                cardCnt[deque.removeFirst().player]--;
            }
        }
        System.out.println(min);
    }

    static class Data implements Comparable<Data> {
        int player, num;

        public Data(int player, int num) {
            this.player = player;
            this.num = num;
        }

        @Override
        public int compareTo(Main.Data o) {
            return num != o.num ? num - o.num : player - o.player;
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