// 27184 KB, 1336 ms
/*
 * 학생 N 명, 주최자는 M명 특별상, 심판은 특별상 받지 않은 작품 중 점수가 높은 K명 본상
 * 상 받는 학생들의 작품에 대해 주최자가 매긴 점수의 합이 최대가 되도록.
 * 
 * 2 <= N <= 200,000
 * 
 * 심판이 매긴 점수 상위 K 명은 무조건 상을 받는다.
 * 제외하고, 주최자의 점수 상위 M명을 더한 게 답이다.
 */

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) throws IOException {
        int N = readInt(), M = readInt(), K = readInt();
        Score[] scores = new Score[N];
        for (int i = 0; i < N; i++) {
            scores[i] = new Score(readInt(), readInt());
        }

        // 심판 기준 내림차순 정렬
        Arrays.sort(scores, new comparatorB());
        long sum = 0;
        for (int i = 0; i < K; i++)
            sum += scores[i].scoreA;

        // K번째부터 주최자 점수 기준 내림차순 정렬
        Arrays.sort(scores, K, N, new comparatorA());
        for (int i = 0; i < M; i++)
            sum += scores[K + i].scoreA;

        System.out.println(sum);
    }

    static class Score {
        int scoreA, scoreB;

        public Score(int scoreA, int scoreB) {
            this.scoreA = scoreA;
            this.scoreB = scoreB;
        }
    }

    static class comparatorA implements Comparator<Score> {
        @Override
        public int compare(Score s1, Score s2) {
            return s2.scoreA - s1.scoreA;
        }
    }

    static class comparatorB implements Comparator<Score> {
        @Override
        public int compare(Score s1, Score s2) {
            return s2.scoreB - s1.scoreB;
        }
    }

    static int readInt() throws IOException {
        int c;
        do {
            c = System.in.read();
        } while (c <= 32);
        int n = c & 15;
        c = System.in.read();
        while (c > 47) {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return n;
    }
}
