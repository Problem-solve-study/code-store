//제출번호: 90069273
//메모리:   13220 KB
//실행시간: 80 ms
import java.io.*;
import java.util.*;

//n이 매우 작기 때문에 최대 2^10가지를 모두 탐색하는 방법을 채택
//각 구역마다 선거구를 정하고, dfs 탐색으로 선거구가 하나로 연결되는 지 확인
//선거구가 하나씩만 존재하면 각각의 인구를 구해 차이값을 업데이트  
public class Main {    

    static int n;
    static List<List<Integer>> graph = new ArrayList<>();
    static int min = Integer.MAX_VALUE;
    static boolean[] voteLoc = new boolean[11];
    static boolean[] visited = new boolean[11];
    static int[] d = new int[11];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            d[i] = Integer.parseInt(st.nextToken());
        }

        graph.add(new ArrayList<>());
        for (int i = 1; i <= n; i++) {
            graph.add(new ArrayList<>());
            
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            for (int j = 0; j < size; j++) {
                graph.get(i).add(Integer.parseInt(st.nextToken()));
            }
        }

        perm(1);

        System.out.print(min == Integer.MAX_VALUE ? -1 : min);
    }

    static void perm(int k) {
        if (k == n + 1) {
            min = Math.min(min, calc());
            return;
        }

        voteLoc[k] = false;
        perm(k + 1);
        voteLoc[k] = true;
        perm(k + 1);
    }
    
    static int dfs(int node, boolean vote) {
        if (visited[node]) {
            return 0;
        }

        visited[node] = true;

        int res = d[node];
        for (int nNode : graph.get(node)) {
            if (voteLoc[nNode] == vote) {
                res += dfs(nNode, vote);
            }
        }

        return res;
    }

    static int calc() {
        int aCount = 0, bCount = 0;
        int aPeople = 0, bPeople = 0;
        resetVisited();

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                int val = dfs(i, voteLoc[i]);

                if (voteLoc[i]) {
                    ++aCount;
                    aPeople += val;
                } else {
                    ++bCount;
                    bPeople += val;
                }
            }
        }

        if (aCount > 1 || bCount > 1) {
            return Integer.MAX_VALUE;
        }

        return Math.abs(aPeople - bPeople);
    }

    static void resetVisited() {
        for (int i = 1; i <= n; i++) {
            visited[i] = false;
        }
    }
}