import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/*
26888KB, 304ms

n의 개수가 매우 적다 -> 브루트포스일 가능성이 높음
1. 2개의 구역으로 나눔
2. 각각의 구역에서 그래프 탐색을 통해 2개의 구역으로 나뉘었는지 판별
3. 2개의 구역으로 나뉘었다면 값 갱신

N이 작아서 제대로 구현만 했다면 맞는 문제로 보임.
구현할거리가 좀 많아서 귀찮은듯
디버깅용 출력 안지우고 냈다고 한 번 틀림.
 */

public class Main {
    static int n;
    static int res = Integer.MAX_VALUE;
    static int[] people;
    static ArrayList<Integer>[] adj;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //입력
        n = Integer.parseInt(br.readLine());

        people = new int[n + 1];
        adj = new ArrayList[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            people[i] = Integer.parseInt(st.nextToken());
            adj[i] = new ArrayList<>();
        }


        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            for (int j = 0; j < size; j++) {
                adj[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        //본격적으로 풀이 시작
        //현재 구역을 탐색했는지 판별할 set
        //HashSet에 HashSet을 넣으면 내부 원소의 동일성이 동일 객체의 판단 기준이므로 visited로 사용 가능
        HashSet<HashSet<Integer>> visited = new HashSet<>();
        //첫 번째 구역
        HashSet<Integer> set1 = new HashSet<>();
        //두 번째 구역
        HashSet<Integer> set2 = new HashSet<>();
        //첫 번째 구역이 비어있는 상태니 두 번째 구역은 모든 선거구를 넣어줌
        IntStream.rangeClosed(1, n).forEach(set2::add);
        divide(1, visited, set1, set2);

        bw.write(String.valueOf(res == Integer.MAX_VALUE ? -1 : res));
        bw.flush();
    }

    static void divide(int idx, HashSet<HashSet<Integer>> visited, HashSet<Integer> set1, HashSet<Integer> set2) {
        //가능한 선거구의 모든 조합을 확인
        //set1과 set2가 모두 문제의 조건을 만족시키면 값 갱신
        if (check(set1) && check(set2)) {
            res = Math.min(res, Math.abs(getSumOfPeople(set1) - getSumOfPeople(set2)));
        }

        for (int i = idx; i <= n; i++) {
            if (!set1.contains(i)) {
                set1.add(i);
                set2.remove(i);
                if (!visited.contains(set1)) {
                    visited.add(new HashSet<>(set1));
                    divide(idx + 1, visited, set1, set2);
                }
                set1.remove(i);
                set2.add(i);
            }
        }
    }

    static boolean check(HashSet<Integer> set) {
        //set1의 한 점에서 그래프 탐색 시작
        //adj에 있으면서 set1에 존재한다면 다음 노드를 큐에 넣으며 cnt++
        //bfs가 끝났을 때 cnt가 set1의 size와 동일하다면 문제의 조건을 만족(해당 선거구가 연결됨)

        for (int idx : set) {
            return bfs(idx, set) == set.size();
        }

        //set이 비어있다면 무조건 불가능한 경우
        return false;
    }

    static int bfs(int idx, HashSet<Integer> set) {
        //군집의 개수를 세는 것 외엔 일반적인 bfs
        int cnt = 0;
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> q = new ArrayDeque<>();

        q.add(idx);
        cnt++;
        visited[idx] = true;
        while (!q.isEmpty()) {
            int cur = q.remove();
            for (int next : adj[cur]) {
                if (!visited[next] && set.contains(next)) {
                    q.add(next);
                    cnt++;
                    visited[next] = true;
                }
            }
        }

        return cnt;
    }

    static int getSumOfPeople(HashSet<Integer> set) {
        //해당 구역의 인구수의 합을 반환
        return set.stream().mapToInt(idx -> people[idx]).sum();
    }
}
