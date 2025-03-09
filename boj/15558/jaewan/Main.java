/*
 * 두 줄 지도에서 게임 진행
 * 각 줄은 N개의 칸, 위험한 칸과 안전한 칸
 * 안전한 칸은 유저가 이동 가능, 위험한 칸은 이동 불가.
 * 
 * 처음 유저는 왼쪽 줄의 1번 칸. 매 초마다 행동
 * > 한 칸 앞으로 이동 i + 1
 * > 한 칸 뒤로 이동 i - 1
 * > 반대편 줄로 점프, 원래 있던 칸보다 k칸 앞 칸으로 이동해야 함. i + k
 * N번 칸보다 큰 칸으로 이동하는 경우, 게임 클리어
 * 
 * 1초에 한 칸씩 각 줄의 첫 칸이 사라지는 기능.
 * 각 칸의 정보가 주어질 때 게임 클리어 여부 구하기.
 * 
 * <input>
 * N k <= 100,000
 * 왼쪽 줄 정보 101010 ... 0은 위험한 칸, 1은 안전한 칸
 * 오른쪽 줄 정보 101010 ... 
 * 
 * 앞으로 쭉 가다가 위험한 칸은 앞으로 갈 수 없으니 반대편 줄로 가는 것이 강제된다.
 * BFS 로 가되, 방문 체크, 사라진 칸으로 이동 안하기로 경우 줄이면 될 것 같다.
 * 점프로 끝낼 수 있으면 클리어가 가능, 점프했을 때 idx + K >= N 이면 클리어 가능
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
    static String[] ladder = new String[2];
    static int N, K;
    static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);
        ladder[0] = br.readLine();
        ladder[1] = br.readLine();

        System.out.println(func());
    }

    private static int func() {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        visit = new boolean[2][N];
        visit[0][0] = true;
        q.offer(new int[] { 0, 0 });
        // 1초 후, 첫번째 칸 사라진다. time 이하인 인덱스는 갈 수 없다. 같은 곳으로 가면, 바로 사라짐.
        for (int time = 0; time < N; time++) {

            int size = q.size(), nextSide, nextIdx;
            while (size-- > 0) {
                int[] cur = q.poll();
                // 앞으로 한 칸
                nextSide = cur[0];
                nextIdx = cur[1] + 1;
                if (canClear(nextIdx))
                    return 1;
                if (isValid(nextSide, nextIdx, time)) {
                    visit[nextSide][nextIdx] = true;
                    q.offer(new int[] { nextSide, nextIdx });
                }
                // 뒤로 한 칸
                nextSide = cur[0];
                nextIdx = cur[1] - 1;
                if (canClear(nextIdx))
                    return 1;
                if (isValid(nextSide, nextIdx, time)) {
                    visit[nextSide][nextIdx] = true;
                    q.offer(new int[] { nextSide, nextIdx });
                }
                // 반대편으로 점프
                nextSide = 1 - cur[0];
                nextIdx = cur[1] + K;
                if (canClear(nextIdx))
                    return 1;
                if (isValid(nextSide, nextIdx, time)) {
                    visit[nextSide][nextIdx] = true;
                    q.offer(new int[] { nextSide, nextIdx });
                }
            }
        }
        return 0;
    }

    private static boolean canClear(int nextIdx) {
        return nextIdx >= N;
    }

    private static boolean isValid(int nextSide, int nextIdx, int time) {
        if (nextIdx <= time)
            return false;
        if (ladder[nextSide].charAt(nextIdx) == '0')
            return false;
        return !visit[nextSide][nextIdx];
    }
}
