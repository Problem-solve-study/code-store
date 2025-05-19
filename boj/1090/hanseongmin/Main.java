import java.io.*;
import java.util.*;

/*
1666332KB, 872ms

이리저리 최소 위치를 구해보니 문제에서 주어진 특정 좌표 위에 있으면 답이 항상되는 걸로 보였음

그래서 먼저 전체 점들에 대해 가장 짧은 거리를 가진 점을 구해두고
해당 점과 맨해튼 거리가 가장 작은 녀석들부터 뽑아서 최솟값을 구했음
이 경우 N개에 대한 쿼리는 제대로 답이 나오지만 2개 3개와 같이 작은 수에 대한 쿼리는 제대로 된 답을 보장할 수 없었음
그래서 1틀.

작은 수에 대한 쿼리를 올바르게 내기 위해서 고민하다가
주어진 점들 중 한 점을 고정시키고 해당 점과 맨해튼 거리상으로 가까운 점들을 뽑은 뒤
해당 점들 사이에서 가장 짧은 거리를 가진 점을 뽑아 최솟값을 구했음
그랬는데 또 틀림. 자세히는 모르겠지만 다른 반례가 있는 것 같음

마지막으로 아예 주어진 모든 좌표에 대해 완전 탐색하기로 함
x 좌표들 중에서 하나 고르고 y 좌표들 중에서 하나를 고름
그리고 해당 좌표로부터 다른 점들과의 거리를 구해서 가장 작은 거리를 N개 뽑아서 최솟값을 만들었음
복잡도가 좀 높긴한데 N이 50밖에 안되니까 될 거 같았고 이렇게 해서 AC 받음
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws Exception {
        int N = nextInt();
        int[][] checkers = new int[N][2];
        for (int i = 0; i < N; i++) {
            checkers[i][0] = nextInt();
            checkers[i][1] = nextInt();
        }

        StringBuilder sb = new StringBuilder();
        //1번째 원소의 답은 항상 0이니 하드 코딩
        sb.append('0').append(' ');
        //2번째 원소부터 답을 구함
        for (int i = 2; i <= N; i++) {
            int min = Integer.MAX_VALUE;

            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    //가능한 x, y 좌표들 중 하나를 뽑고
                    int x = checkers[j][0];
                    int y = checkers[k][1];

                    //이 좌표와 다른 모든 점들간의 거리를 계산
                    PriorityQueue<Integer> h = new PriorityQueue<>();
                    for (int a = 0; a < N; a++) {
                        h.add(Math.abs(x - checkers[a][0]) + Math.abs(y - checkers[a][1]));
                    }

                    //현재 원소 개수만큼 최솟값을 뽑고
                    int v = 0;
                    for (int a = 0; a < i; a++) {
                        v += h.remove();
                    }
                    //답 갱신 시도
                    min = Math.min(min, v);
                }
            }
            //여기까지 왔으면 min에는 최솟값이 담겨있을 것이므로 이를 출력
            sb.append(min).append(' ');
        }
        System.out.print(sb);
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}