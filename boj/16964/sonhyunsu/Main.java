//제출번호: 91310274
//메모리:   30852 KB
//실행시간: 272	ms
import java.io.*;
import java.util.*;

//올바른 방문 순서인지 검사해야 하는 문제, 그래프는 트리로 주니까 항상 부모는 1명임
//depth와 부모 정점을 계산하고, 이번에 방문한 정점이 방문 가능한 정점인지 검사하는 방법으로 구현
public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static List<List<Integer>> graph = new ArrayList<>();
    static int[] depth;
    static int maxDepth;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        int n = nextInt();
        depth = new int[n + 1];
        parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
            depth[i] = 1000000;
        }

        for (int i = 1; i < n; i++ ){
            int a = nextInt();
            int b = nextInt();

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        //임의의 0번 정점을 1번 정점을 가리키가 만들고, 0번 정점의 높이를 0으로 만듦
        //1번 정점도 계산에 들어가도 문제가 발생하지 않도록 일반화함
        graph.get(0).add(1);
        depth[0] = 0;

        dfs(0); //각 정점마다 depth와 부모 정점을 계산함

        //depth 별 가장 최근에 방문한 정점을 저장할 공간을 만듦
        int[] nodePerDepth = new int[maxDepth + 1];
        boolean isPossible = true;
        for (int i = 0; i < n; i++) {
            int printNode = nextInt();
            int nodeDepth = depth[printNode];

            //만약에 depth 별 가장 최근에 방문한 정점과 현재 정점의 부모가 다르면
            //dfs 순서가 불가능한 경우임 (dfs는 항상 부모에서 자식으로 들어가야 하므로)
            if (nodePerDepth[nodeDepth - 1] != parent[printNode]) {
                isPossible = false;
                break;
            }

            //depth 별 가장 최근에 방문한 정점 갱신
            //갱신이 된다는 것은 갱신되기 전의 정점을 루트로 하는 서브 트리를 모두 방문했다는 뜻
            nodePerDepth[nodeDepth] = printNode;
        }

        System.out.print(isPossible ? 1 : 0);
    }

    static void dfs(int node) {
        for (int nNode : graph.get(node)) {
            //아직 방문하지 않은 점이면 depth와 부모를 갱신함
            if (depth[nNode] == 1000000) {
                maxDepth = Math.max(maxDepth, depth[nNode] = depth[node] + 1);
                parent[nNode] = node;
                dfs(nNode);
            }
        }
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}