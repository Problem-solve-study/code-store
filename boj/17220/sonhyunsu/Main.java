//제출번호: 90171306
//메모리:   11592 KB
//실행시간: 64 ms
import java.io.*;
import java.util.*;

//처음 문제를 보고 dfs로 풀 수 있을 것이라고 생각함
//마약 원산지를 구분해야 했기 때문에 supplied 배열을 이용해 구분했음
//마약 원산지와 2개 이상 연결된 공급책이 있을 수 있기 때문에
//마지막에 visited를 이용해서 실제로 방문한 공급책만 세는 방법으로 결과 출력
public class Main {    

    static List<List<Integer>> graph = new ArrayList<>();
    static boolean[] caught, visited, supplied;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        caught = new boolean[n];
        visited = new boolean[n];
        supplied = new boolean[n];
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            char s = st.nextToken().charAt(0);
            char c = st.nextToken().charAt(0);
            graph.get(s - 'A').add(c - 'A');
            supplied[c - 'A'] = true;
        }

        st = new StringTokenizer(br.readLine());
        int catchCount = Integer.parseInt(st.nextToken());

        for (int i = 0; i < catchCount; i++) {
            char target = st.nextToken().charAt(0);
            caught[target - 'A'] = true;
        }

        for (int i = 0; i < n; i++) {
            if (!supplied[i]) {
                dfs(i);
            }
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            if (visited[i] && supplied[i]) {
                res++;
            }
        }
        System.out.print(res);
    }

    static void dfs(int node) {
        if (caught[node] || visited[node]) {
            return;
        }

        visited[node] = true;
        for (int nNode : graph.get(node)) {
            dfs(nNode);
        }
    }
}