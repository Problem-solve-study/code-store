//제출번호: 96201012
//메모리:   14248 KB
//실행시간: 124 ms
import java.io.*;
import java.util.*;

//가장 짧은 거리를 구하기 위해서는 BFS를 사용하면 됨
//하지만 해밍거리가 1인 관계를 모두 찾아야 하고 해밍경로의 역추적도 필요함

//해밍거리가 1인 두 숫자를 찾는 방법은 다음과 같음
//먼저 두 수를 xor 한 다음 그 수가 2^k 꼴인지 확인하면 됨
//2^k인지는 가장 끝 비트를 가져오는 i & -i 연산 결과와 i 가 동일한지 비교하면 알 수 있음

//해밍거리가 1인 관계를 담은 그래프를 만들고 BFS를 수행할 때
//부모 노드가 누구인지 미리 기록해두면 역추적이 쉬워짐
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken());
        
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine(), 2);
        }

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = -1; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            int num1 = arr[i];
            for (int j = 0; j < i; j++) {
                int and = num1 ^ arr[j];

                if (and != 0 && (and & -and) == and) {
                    graph.get(i + 1).add(j + 1);
                    graph.get(j + 1).add(i + 1);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken()), e = Integer.parseInt(st.nextToken());
        int[] visited = new int[n + 1], parent = new int[n + 1];

        Queue<Integer> q = new ArrayDeque<>();
        q.add(s);
        visited[s] = 1;

        while (!q.isEmpty()) {
            int node = q.poll();

            for (int nNode : graph.get(node)) {
                if (visited[nNode] == 0) {
                    parent[nNode] = node;
                    visited[nNode] = visited[node] + 1;
                    q.add(nNode);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        if (visited[e] == 0) {
            sb.append(-1);
        } else {
            int p = e;
            Deque<Integer> dq = new ArrayDeque<>();
            while (p != 0) {
                dq.addLast(p);
                p = parent[p];
            }
            
            while (!dq.isEmpty()) {
                sb.append(dq.pollLast()).append(' ');
            }
        }

        System.out.print(sb);
    }
}