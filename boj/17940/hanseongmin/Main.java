import java.io.*;
import java.util.*;

/*
27652KB, 420ms

환승횟수, 비용 기준으로 다익스트라 돌리면 됨.
환승횟수를 우선으로 보고 환승횟수가 동일하다면 그때 비용을 본 후 힙에 넣을지 말지를 결정
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws Exception {
        int N = nextInt(); int M = nextInt();
        int[] info = new int[N];
        for (int i = 0; i < N; i++) {
            info[i] = nextInt();
        }

        int[][] mat = new int[N][N];
        //0: 환승횟수, 1: 비용
        int[][] cost = new int[N][2];
        for (int i = 0; i < N; i++) {
            cost[i][0] = cost[i][1] = Integer.MAX_VALUE;
            for (int j = 0; j < N; j++) {
                mat[i][j] = nextInt();
            }
        }

        //환승횟수, 비용, 현재 지하철역
        PriorityQueue<int[]> h = new PriorityQueue<>(Comparator.comparingInt((int[] arr) -> arr[0]).thenComparingInt(arr -> arr[1]));
        h.add(new int[] {0, 0, 0});
        cost[0][0] = 0; cost[0][1] = 0;
        while (!h.isEmpty()) {
            int[] cur = h.remove();
            int curCnt = cur[0]; int curCost = cur[1]; int curPos = cur[2];
            if (cost[curPos][0] < curCnt || (cost[curPos][0] == curCnt && cost[curPos][1] < curCost)) continue;

            //현재 역과 연결되어 있는 다른 역을 살펴봄
            for (int nextPos = 0; nextPos < N; nextPos++) {
                //현재역과 다음역이 연결되어 있으면
                if (mat[curPos][nextPos] != 0) {
                    //다음역으로 갔을 때 환승횟수와 비용을 계산한 후
                    int nextCnt = curCnt + (info[curPos] != info[nextPos] ? 1 : 0);
                    int nextCost = curCost + mat[curPos][nextPos];
                    //더 높은 우선순위를 가지면 비용 갱신 후 힙에 넣기
                    if (cost[nextPos][0] > nextCnt || (cost[nextPos][0] == nextCnt && cost[nextPos][1] > nextCost)) {
                        cost[nextPos][0] = nextCnt; cost[nextPos][1] = nextCost;
                        h.add(new int[] {nextCnt, nextCost, nextPos});
                    }
                }
            }
        }
        System.out.print(cost[M][0] + " " + cost[M][1]);
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
