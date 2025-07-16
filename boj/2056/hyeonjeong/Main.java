// 34264KB 376ms

import java.io.*;
import java.util.*;

/**
 * 위상정렬, DP
 * 
 * 선행을 부모, 후행을 자식이라 할 때
 * 위상정렬을 하며 시간 업데이트
 * - accTimes: i번 노드와 i번 노드의 모든 자식을 처리한 시간
 * - accTimes[i] = Max(accTimes of child) + i번 노드 소요 시간
 * 
 * 부모가 없는 경우를 처리하기 위해 0번 노드를 추가하고, 부모가 없는 노드의 부모를 0번 노드로 설정
 */
class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static List<List<Integer>> adjList; // 부모 -> 자식 방향의 인접리스트
    static int[] times;     // i번 노드의 소요 시간
    static int[] accTimes;  // i번 노드와 i번 노드의 모든 자식을 처리한 시간

    public static void main(String[] args) throws IOException {
        int n = nextInt();

        times = new int[n + 1];
        adjList = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 1; i < n + 1; i++) {
            int time = nextInt();
            times[i] = time;

            int parentCount = nextInt();
            for (int j = 0; j < parentCount; j++) {
                int parent = nextInt();
                adjList.get(parent).add(i);
            }

            if (parentCount == 0) {
                adjList.get(0).add(i);
            }
        }

        accTimes = new int[n + 1];
        search(0);

        System.out.println(accTimes[0]);
    }

    // 위상정렬 순으로 탐색
    static void search(int current) {
        int maxChild = 0;

        for (int child : adjList.get(current)) {
            if (accTimes[child] == 0) {
                search(child);
            }

            if (accTimes[child] > maxChild) {
                maxChild = accTimes[child];
            }
        }

        accTimes[current] = maxChild + times[current];
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
