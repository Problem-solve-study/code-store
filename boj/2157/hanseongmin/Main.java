import java.io.*;
import java.util.*;

/*
20448KB, 352ms

1차원 형태로 정점이 배치되어 있음

다음의 조건을 만족하는 경로 탐색하고 그때의 값의 합이 최대
- 1번 정점과 N번 정점을 반드시 포함
- M개 이하의 정점을 지나야 함
- 경로는 반드시 오른쪽으로만 이동

먹게되는 기내식이 양수이므로 정점을 갈 수 있으면 최대한 많이 가는 것이 유리할 듯
근데 간선이 모두 생성되어있는 것이 아니므로 탐색이 필요할 것 같은데 n이 좀 크니까 완탐은 아닐 것 같고 dp겠네
단순히 이동하는 것뿐만 아니라 m개 이하의 정점을 지나야하므로 현재 내가 어떤 도시에 있는지
그리고 몇개의 정점을 지났는지를 저장해야할듯. 2차원으로 가자

dp[i][m]: i번째 도시에 있으면서 m개의 정점을 지났을 때의 최댓값
dp[i][m] = max(dp[i - 1][m - 1] + edge[i - 1][i], dp[i - 2][m - 1] + edge[i - 2][i]... dp[1][m - 1] + edge[i - 2][i])
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws Exception {
        int n = nextInt();
        int m = nextInt();
        int k = nextInt();

        int[][] edges = new int[n + 1][n + 1];
        for (int i = 0; i < k; i++) {
            int a = nextInt();
            int b = nextInt();
            int c = nextInt();
            //서쪽 -> 동쪽으로 향하는 간선은 입력할 필요가 없음
            if (a < b) {
                edges[a][b] = Math.max(edges[a][b], c);
            }
        }

        int[][] dp = new int[n + 1][m + 1];
        //일단 1번에서 출발해야하므로 dp 초기화
        //1번에서 i번으로 가면 일단 정점을 2개 지난 상태
        for (int i = 2; i <= n; i++) {
            dp[i][2] = Math.max(dp[i][2], edges[1][i]);
        }

        //현재 거친 정점의 수. 초기화할 때 2개의 정점을 지난 상태이므로 3부터 탐색
        for (int i = 3; i <= m; i++) {
            //갱신하고자 하는 도시의 값. 3개의 정점을 지난 상태부터 탐색할 것이므로 도시는 3번부터 탐색
            for (int j = 3; j <= n; j++) {
                //이전 도시
                for (int l = j - 1; l >= 0; l--) {
                    if (dp[l][i - 1] == 0 || edges[l][j] == 0) continue;
                    dp[j][i] = Math.max(dp[j][i], dp[l][i - 1] + edges[l][j]);
                }
            }
        }

        System.out.println(Arrays.stream(dp[n]).max().orElse(0));
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
