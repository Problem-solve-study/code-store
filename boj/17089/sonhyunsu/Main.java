//제출번호: 91356342
//메모리:   29108 KB
//실행시간: 196 ms
import java.io.*;
import java.util.*;

//처음에는 dfs로 구할 수 있나 싶었는데 구현하고 보니 예외 케이스가 존재해서 코드를 한 번 갈아 엎음
//간선의 개수가 4000개 이하로 적길래 간선 하나를 고르고,
//골라진 두 친구 사이에 서로가 친구인 C가 존재하는 지 확인
//시간복잡도는 O(NM)으로 시간 안에 통과할 수 있을 것이라 생각했음
public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static boolean[][] graph;
    static List<int[]> edges = new ArrayList<>();
    static int[] friends;
    static int res = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        int n = nextInt();
        int m = nextInt();

        friends = new int[n + 1];
        for (int i = 0; i < m; i++) {
            int a = nextInt();
            int b = nextInt();

            //친구의 수를 세어둔다.
            friends[a]++;
            friends[b]++;

            //친구 관계를 저장한다.
            edges.add(new int[]{a, b});
        }

        graph = new boolean[n + 1][n + 1];
        for (int[] edge : edges) {
            //친구 관계 하나를 선택하고
            int a = edge[0], b = edge[1];
            for (int i = 1; i <= n; i++) {
                //두 사람 모두와 친구인 사람이 있는 지 찾는다.
                if (graph[a][i] && graph[i][b]) {
                    //찾으면 세 사람이 서로 친구이므로 최솟값 갱신
                    res = Math.min(res, friends[a] + friends[b] + friends[i] - 6);
                }
            }
            //친구임을 등록한다.
            graph[a][b] = graph[b][a] = true;
        }

        //만약에 세 사람이 서로 친구인 경우가 있으면 res는 적어도 한 번 갱신됨
        //없으면 res는 MAX_VALUE를 가짐
        System.out.print(res == Integer.MAX_VALUE ? -1 : res);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}