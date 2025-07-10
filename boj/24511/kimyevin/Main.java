import java.io.*;
import java.util.*;

/**
 * 73396KB	424ms
 * 스택의 경우 최종 결과에 영향X
 * 큐(0) 인것만 자료형에 나란히 넣고 선입선출 방식으로 계산
 */

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer stTypes = new StringTokenizer(br.readLine()); // 스택의 경우 그대로 두고, 큐의 경우만 고려
        StringTokenizer stValues = new StringTokenizer(br.readLine());
        Deque<Integer> q = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            int type = Integer.parseInt(stTypes.nextToken()); // 큐 or 스택
            int value = Integer.parseInt(stValues.nextToken()); // data
            if (type == 0) {
                q.addLast(value); // 큐의 순서대로 Deque의 뒤쪽에 추가
            }
        }

        int M = Integer.parseInt(br.readLine());
        StringTokenizer stNewValues = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            int newValue = Integer.parseInt(stNewValues.nextToken());
            q.addFirst(newValue); // 기다란 큐
            sb.append(q.pollLast()).append(' ');
        }

        System.out.println(sb.toString().trim());
    }
}