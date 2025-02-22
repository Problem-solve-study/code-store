// 11920KB	72ms
import java.io.*;
import java.util.*;

/**
 * 1. 같은 집합은 '서로서로' 좋아해야 하므로 사이클이 생긴다고 판단함.
 * 2. dfs로 그래프 탐색을 하며 사이클이 생기는지를 판단하려고 했음.
 * 2-1. 한 그룹안에서 1가지 경우의 사이클만 생기는게 아님 ! 
 *      => 그래서 dfs 탐색 후 두 노드간 연결이 0으로 되어있는지 모두 확인해야 할 것 같은데 ..? 라고 생각함.
 * 3. 최종 : dfs 와 동시에 사이클을 판단할 필요가 없을 것 같다. 대신, 탐색한 집합의 두 노드가 모두 0 으로 연결되어있는지를 판단하자
 *    (n = 100 이므로 n^2 으로 두 노드를 전부 비교해도 ㄱㅊ)
 */
public class Main {
    static int n;
    static int[][] map;
    static boolean[] visited;
    static ArrayList<Integer> group = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for (int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j<n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[n];
        int count = 0;
        for (int i=0; i<n; i++) {
            if (!visited[i]) {
                group.clear();

                dfs(i);

                if (group.size() >= 2 && isCorrectGroup()) { // 집합의 크기는 2이상이고, 한 집합 안의 원소끼리는 모두 0 연결
                    setString();
                    count++;
                }
                else {
                    count = 0;
                    break;
                }
            }
        }

        if (count == 0) {
            System.out.print(0);
        }
        else {
            System.out.println(count);
            System.out.print(sb);
        }
    }

    static void setString() {
        for (int element : group) {
            sb.append(element+1).append(" ");
        }
        sb.append("\n");
    }

    static boolean isCorrectGroup() {
        for (int i : group) {
            for (int j : group) {
                if (i == j) continue;
                if (map[i][j] != 0) return false;
            }
        }
        return true;
    }

    static void dfs(int now) {
        group.add(now);
        visited[now] = true;

        for (int i=0; i<n; i++) {
            if (map[now][i] == 0 && !visited[i]) {
                dfs(i);
            }
        }
    }
}