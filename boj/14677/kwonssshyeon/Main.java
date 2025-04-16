
// 	13892KB	68ms
import java.io.*;

/**
 * 양끝에서 포인터를 이동시키며 가능한 모든 경우를 탐색
 * DFS + 투포인터
 */
public class Main {
    static int n, answer;
    static char[] base = new char[] { 'B', 'L', 'D' };
    static char[] data;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine()) * 3;
        data = br.readLine().toCharArray();

        visited = new boolean[data.length][data.length];
        dfs(0, data.length - 1, 0, base[0]);
        System.out.print(answer);
    }

    static void dfs(int left, int right, int cnt, char target) {
        if (left >= n || right < 0 || visited[left][right])
            return;

        answer = Math.max(answer, cnt);
        if (cnt == n)
            return;
        visited[left][right] = true;
        if (data[left] == target) {
            dfs(left + 1, right, cnt + 1, base[(cnt + 1) % 3]);
        }
        if (data[right] == target) {
            dfs(left, right - 1, cnt + 1, base[(cnt + 1) % 3]);
        }
    }
}
