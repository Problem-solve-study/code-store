
//제출번호: 90883871
//메모리:   11716 KB
//실행시간: 64 ms
import java.io.*;
import java.util.*;

//처음 문제를 보고 위상정렬을 이용해서 풀 수 있을 것이라고 생각함
//강의 근원부터 바다까지 내려가면서 strahler를 구해주고,
//최대 strahler 순서가 여러 개인 노드만 잘 체크해주면 될 것
public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int[] in = new int[1001];
    static int[] strahler = new int[1001];
    static boolean[] hasMultiStrahler = new boolean[1001];
    static List<List<Integer>> graph = new ArrayList<>();
    static Queue<Integer> queue = new ArrayDeque<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int T = nextInt();

        for (int i = -1; i < 1000; i++) {
            graph.add(new ArrayList<>());
        }

        for (int t = 0; t < T; t++) {
            int k = nextInt();
            int m = nextInt();
            int p = nextInt();

            // 여러 개의 테스트 케이스가 주어지므로 쓸 때마다 초기화 해줘야 함
            for (int i = 1; i <= m; i++) {
                graph.get(i).clear();
                in[i] = 0;
                strahler[i] = 0;
                hasMultiStrahler[i] = false;
            }

            for (int i = 0; i < p; i++) {
                int a = nextInt();
                int b = nextInt();
                graph.get(a).add(b);
                in[b]++; // b를 기준으로 들어오는 간선의 개수를 세어줌
            }

            for (int i = 1; i <= m; i++) {
                // in[i]가 0이면 강의 근원이므로 큐에 모두 추가
                if (in[i] == 0) {
                    queue.add(i);
                    strahler[i] = 1;
                }
            }

            int maxStrahler = 0;
            while (!queue.isEmpty()) {
                int node = queue.poll();

                // 만약 최대 strahler 값이 여러 개라면 +1함
                if (hasMultiStrahler[node]) {
                    strahler[node]++;
                }

                maxStrahler = Math.max(maxStrahler, strahler[node]);

                for (int nNode : graph.get(node)) {
                    in[nNode]--; // 들어오는 간선을 하나 줄여줌

                    // nNode의 strahler를 갱신함
                    // 이게 가능한 이유는 큐에 들어오는 노드의 순서를 봤을 때
                    // 노드의 strahler는 항상 비내림차순으로 볼 수 있기 때문
                    if (strahler[nNode] < strahler[node]) {
                        strahler[nNode] = strahler[node];
                        hasMultiStrahler[nNode] = false;
                    } else if (strahler[nNode] == strahler[node]) {
                        hasMultiStrahler[nNode] = true;
                    }

                    // 만약 nNode 기준으로 들어오는 간선을 모두 썼다면 nNode를 갱신할 수 있음
                    if (in[nNode] == 0) {
                        queue.add(nNode);
                    }
                }
            }

            sb.append(k).append(' ').append(maxStrahler).append('\n');
        }

        System.out.print(sb);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}