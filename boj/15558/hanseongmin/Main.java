import java.io.*;
import java.util.*;

/*
21536KB, 136ms

문제를 읽고 BFS 혹은 DP로도 되나..? 싶은 생각이 들었고 BFS는 확실하게 정답이 나올 것 같아서 BFS로 구현
문제 지문에 나온대로 적절히 조건을 걸어주면서 BFS를 돌리면 풀 수 있는듯 하다.
 */

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        String[] lines = new String[2];
        lines[0] = br.readLine();
        lines[1] = br.readLine();

        //lines 인덱스, String 인덱스, 시간
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] v = new boolean[2][N + k];
        q.add(new int[] {0, 0, 0});
        v[0][0] = true;

        int res = 0;
        while (!q.isEmpty()) {
            int[] cur = q.remove();
            int lineIdx = cur[0];
            int strIdx = cur[1];
            int time = cur[2];
            //승리 조건
            if (strIdx >= N - 1) {
                res = 1;
                break;
            }

            //한 칸 앞으로 이동
            if (!v[lineIdx][strIdx + 1] && lines[lineIdx].charAt(strIdx + 1) == '1') {
                q.add(new int[] {lineIdx, strIdx + 1, time + 1});
                v[lineIdx][strIdx + 1] = true;
            }

            //한 칸 뒤로 이동
            //뒤로 이동할 때는 시간을 함께 고려
            if (strIdx - 1 > time && !v[lineIdx][strIdx - 1] && lines[lineIdx].charAt(strIdx - 1) == '1') {
                q.add(new int[] {lineIdx, strIdx - 1, time + 1});
                v[lineIdx][strIdx - 1] = true;
            }

            //반대편 줄로 점프
            //반대편 줄로 점프할 때는 k만큼 점프하므로 IndexOutOfBound를 회피하기 위한 단축 평가 조건식 설정
            if (strIdx + k >= N || !v[lineIdx ^ 1][strIdx + k] && lines[lineIdx ^ 1].charAt(strIdx + k) == '1') {
                q.add(new int[] {lineIdx ^ 1, strIdx + k, time + 1});
                v[lineIdx ^ 1][strIdx + k] = true;
            }
        }
        System.out.println(res);
    }
}
