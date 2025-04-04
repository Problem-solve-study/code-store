import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// DP: i번째 도착한 j의 최대값을 i-1번째를 참고해 갱신
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        int[] colors = new int[n];
        char[] line = br.readLine().toCharArray();
        for (int i = 0; i < n; i++) {
            colors[i] = line[i * 2];
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] edges = new int[k][3];
        
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            char color = st.nextToken().charAt(0);

            edges[i][0] = u;
            edges[i][1] = v;
            edges[i][2] = color;
        }

        // scores[i][j]: i번 노드에 j번째로 도착했을 때 최대값
        // scores[i][j] == -1 이면 j번째에 도달할 수 없는 경로
        int[][] scores = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                scores[i][j] = -1;
            }
        }

        // 0 -> a인 a들 초기화
        for (int[] edge : edges) {
            int next = -1;
            if (edge[0] == 0) {
                next = edge[1];
            }
            if (edge[1] == 0) {
                next = edge[0];
            }

            if (next == -1) {
                continue;
            }

            int score = edge[2] == colors[0] ? 1 : 0;
            if (score > scores[next][0]) {
                scores[next][0] = score;
            }
        }

        // 두 번째부터 i-1번째 방문을 확인하면서 갱신
        for (int seq = 1; seq < n; seq++) {
            for (int[] edge : edges) {
                // edge[0] -> edge[1]로 이동
                if (scores[edge[0]][seq - 1] != -1) {
                    int score = scores[edge[0]][seq - 1] + (edge[2] == colors[seq] ? 1 : 0);
                    if (score > scores[edge[1]][seq]) {
                        scores[edge[1]][seq] = score;
                    }
                }

                // edge[1] -> edge[0]으로 이동
                if (scores[edge[1]][seq - 1] != -1) {
                    int score = scores[edge[1]][seq - 1] + (edge[2] == colors[seq] ? 1 : 0);
                    if (score > scores[edge[0]][seq]) {
                        scores[edge[0]][seq] = score;
                    }
                }
                
            }
        }
        
        int max = 0;
        for (int i = 0; i < m; i++) {
            if (scores[i][n - 1] > max) {
                max = scores[i][n - 1];
            }
        }

        System.out.println(max * 10);
    }
}
