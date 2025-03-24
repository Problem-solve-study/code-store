//제출번호: 91903230
//메모리:   13284 KB
//실행시간: 92 ms
import java.io.*;
import java.util.*;

//처음에는 멘헤튼 거리가 1000 이하면 canVisit에 true를 저장해서 도착점을 찾는 방법으로 풀이
// => 시작점과 가장 가까운 편의점이 가장 마지막에 들어올 수 있기 때문에 틀림
//두 번째는 플로이드워셜을 이용해서 두 점을 잇는 중간 경로가 있으면 업데이트 하는 방법으로 풀이
// => 시작점을 거치치 않고 도착점을 가는 경우도 true가 되어서 틀림
//마지막으로 멘헤튼 거리로 갈 수 있는 지점들을 그래프 간선으로 묶고 시작점부터 bfs로 돌림 => 맞음
//시작점부터 출발해서 그래프 탐색으로 도착점에 갈 수 있는 지만 확인하면 됨
public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        int T = nextInt();
        
        //재활용할 공간을 미리 만듦
        int[][] positions = new int[2][102];
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < 102; i++) {
            graph.add(new ArrayList<>());
        }

        Queue<Integer> q = new ArrayDeque<>();
        boolean[] isVisited = new boolean[102];
        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < T; t++) {
            int n = nextInt() + 1;
            for (int i = 0; i <= n; i++) {
                //정보를 초기화 함
                graph.get(i).clear();
                isVisited[i] = false;

                //현재 좌표를 저장
                positions[0][i] = nextInt();
                positions[1][i] = nextInt();

                //0 ~ i - 1까지 멘헤튼 거리가 1000이하인 지점을 그래프 간선으로 연결
                for (int j = 0; j < i; j++) {
                    if (Math.abs(positions[0][i] - positions[0][j]) + Math.abs(positions[1][i] - positions[1][j]) <= 1000) {
                        graph.get(i).add(j);
                        graph.get(j).add(i);
                    }
                }
            }

            //큐에 남아있는 원소를 모두 삭제하고 bfs를 돌림
            q.clear();
            q.add(0);
            isVisited[0] = true;
            while (!q.isEmpty()) {
                int node = q.poll();

                if (node == n) {
                    break;
                }

                //다음에 갈 수 있으면서 아직 방문하지 않았다면 방문하기로 체크
                for (int nNode : graph.get(node)) {
                    if (!isVisited[nNode]) {
                        isVisited[nNode] = true;
                        q.add(nNode);
                    }
                }
            }

            //도착점에 방문한 적이 있다면 happy, 아니면 sad
            sb.append(isVisited[n] ? "happy" : "sad").append('\n');
        }

        System.out.print(sb);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}