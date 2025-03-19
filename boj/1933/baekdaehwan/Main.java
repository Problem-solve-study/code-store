/**
 * 137320KB	1036ms
 * 이제 코테 준비하듯이 풀어야 할 필요성을 느껴, 가능한 논리가 간단한 풀이법으로 접근하고자 하였습니다.
 * 
 * [사고흐름]
 * 처음에는 그림만 보고 누적합+좌표압축 문제인가 싶었으나, 문제를 자세히 읽어보니 스위핑 문제같아서 스위핑+우선순위큐로 시도했습니다.
 * 기존 존재하던 건물보다 높이가 높아지거나 작아지는 경우를 찾는 것도 가능해보였으나, 그냥 스위핑 두 번 하는게 논리적으로 더 간단하다고 판단했습니다.
 * 
 * [알고리즘 설명]
 * PQ              - peek가 항상 현재 건물과 겹치는 건물 중 가장 높은 건물
 * 알고리즘 개념    - PQ의 peek보다 현재 건물의 높이가 높다면, 해당 x값과 높이를 resList에 담아둔다. (만약 PQ가 비어있다면 peek의 높이는 0으로 판단합니다)
 * 
 * - 정방향 스위핑
 * PQ의 peek보다 현재 건물의 높이가 높다면, 현재 건물의 왼쪽 좌표와 높이를 resList에 저장.
 * - 역방향 스위핑
 * PQ의 peek보다 현재 건물의 높이가 높다면, 현재 건물의 우측 좌표와 peek의 높이를 resList에 저장.
 * 
 * 
 * 위 개념을 코드로 구현하면 아래와 같습니다.
 */

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] A;
    static PriorityQueue<int[]> pq;
    static List<int[]> resList;

    static StreamTokenizer st = new StreamTokenizer(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        N = ni();
        A = new int[N][3];
        pq = new PriorityQueue<>(Comparator.comparing(e->-e[0]));
        resList = new ArrayList<>();
        
        for (int i=0; i<N; ++i) {
            A[i][0] = ni();
            A[i][1] = ni();
            A[i][2] = ni();
        }

        // 건물의 좌측 지점을 기준으로 오름차순 정렬합니다. (위치가 동일하다면 건물 높이 내림차순)
        Arrays.sort(A, (a, b) -> {
            int lc = Integer.compare(a[0], b[0]);
            return lc!=0? lc:-Integer.compare(a[1], b[1]);
        });
        for (int i=0; i<N; i++) {
            int l = A[i][0];
            int h = A[i][1];
            int r = A[i][2];

            // peek가 현재 건물과 겹치는 건물 중 가장 높은 건물이 되도록 만듭니다. (pq.peek()[1] => 이미 확인한 건물의 우측 지점)
            while (!pq.isEmpty() && pq.peek()[1] < l) pq.poll();
            
            // 높이가 높아진 경우이기 때문에 현재 높이가 lastMaxHeight보다 높다면 현재 건물의 좌측 지점과 현재 높이를 기록합니다.
            int lastMaxH = pq.isEmpty()? 0:pq.peek()[0];
            if (lastMaxH < h) resList.add(new int[] {l, h});
            pq.add(new int[] {h, r});
        }

        // pq를 비우고 건물의 우측 지점을 기준으로 내림차순 정렬합니다. (위치가 동일하다면 건물 높이 내림차순)
        pq.clear();
        Arrays.sort(A,  (a, b) -> {
            int lc = -Integer.compare(a[2], b[2]);
            return lc!=0? lc:-Integer.compare(a[1], b[1]);
        });
        for (int i=0; i<N; i++) {
            int l = A[i][0];
            int h = A[i][1];
            int r = A[i][2];

            // peek가 현재 건물과 겹치는 건물 중 가장 높은 건물이 되도록 만듭니다. (pq.peek()[1] => 이미 확인한 건물의 좌측 지점)
            while (!pq.isEmpty() && r < pq.peek()[1]) pq.poll();

            // 높이가 낮아진 경우이기 때문에 현재 높이가 lastMaxHeight보다 높다면 현재 건물의 우측 지점과 마지막 높이를 기록합니다.
            int lastMaxH = pq.isEmpty()? 0:pq.peek()[0];
            if (h>lastMaxH) resList.add(new int[] {r, lastMaxH});
            pq.add(new int[] {h, l});
        }

        // 정렬 후 답을 출력합니다.
        StringBuilder res = new StringBuilder();
        Collections.sort(resList, Comparator.comparing(e->e[0]));
        for (int[] e: resList) res.append(e[0]).append(' ').append(e[1]).append(' ');
        System.out.println(res);
    }

    public static int ni() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
