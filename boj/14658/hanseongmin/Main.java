import java.io.*;

/*
11804KB, 1532ms

1. 별을 차례로 순회하며 별이 트램펄린의 좌하단에 위치하도록 하기
-> 생각해보니까 모든 경우의 수를 체크할 수 없음

2. 별이 트램펄린의 좌하단 뿐만 아니라 각 꼭짓점에 위치하도록 하기
-> 다이아몬드 꼴로 별이 배치되는 경우 답을 구할 수 없음

3. 별이 트램펄린의 모든 변에 위치하도록 하기
-> 모든 경우의 수를 고려할 수 있지만 반드시 시초남

4. 3에서 다시 생각해보니 y축을 옮길 필요는 없겠다고 생각함
어차피 모든 별을 보기 때문에 x축만을 옮기는 것으로 모든 경우를
커버할 수 있기 때문
-> 살짝 느리지만 AC
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int N, M, L, K;
    static Point[] points;

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        N = nextInt();
        M = nextInt();
        L = nextInt();
        K = nextInt();

        points = new Point[K];
        for (int i = 0; i < K; i++) {
            points[i] = new Point(nextInt(), nextInt());
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < K; i++) {
            ans = Math.min(ans, check(i));
        }
        System.out.print(ans);
    }

    //지구에 부딪히는 별똥별 개수 카운팅
    static int check(int idx) {
        int res = Integer.MAX_VALUE;
        Point standard = points[idx];

        for (int xl = standard.x - L; xl <= standard.x; xl++) {
            int cur = 0;
            int xr = xl + L;

            for (int i = 0; i < K; i++) {
                if (!((xl <= points[i].x && points[i].x <= xr) &&
                        (standard.y <= points[i].y && points[i].y <= standard.y + L))) {
                    cur++;
                }
            }
            res = Math.min(res, cur);
        }

        return res;
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}