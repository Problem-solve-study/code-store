// 11696KB	72ms
import java.io.*;

public class Main {
    static int n, map[][];
    static int answer = Integer.MAX_VALUE;
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws Exception {
        n = nextInt();
        map = new int[n][n];
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                map[i][j] = nextInt();
            }
        }

        dfs(1,0,0, new int[3][2]);
        System.out.print(answer);
    }

    // 꽃을 심는 조합을 구하는 함수
    // idx: 2차원 map을 한 줄로 폈을때 위치, cnt: 지금까지 심은 개수, sum: 비용 합, path: 꽃을 심은 위치치
    static void dfs(int idx, int cnt, int sum, int[][] path) {
        if (sum > answer) return; // 이미 최소비용을 넘은 경우 가지치기기
        if (cnt == 3) {
            answer = Math.min(answer, sum);
            return;
        }

        for (int i=idx; i<n*(n-1); i++) { // 가장자리는 꽃을 심을 수 없으므로 n-1번째 행 제외: (n*(n-1))
            int ny = i / n;
            int nx = i % n;
            if (isInside(ny, nx) && !isAdjacent(ny, nx, path, cnt)) {
                path[cnt][0] = ny;
                path[cnt][1] = nx;
                dfs(i+1, cnt + 1, sum + calculateCost(ny, nx), path);
            }
        }
    }

    // 꽃을 심을 수 있는 범위 내인지 확인하는 함수수
    static boolean isInside(int y, int x) {
        if (y < 1 || x < 1 || y >= n-1 || x >= n-1) return false;
        return true;
    }

    // 5평의 비용 합을 계산하는 함수
    static int[] dx = {1,0,0,-1};
    static int[] dy = {0,1,-1,0};
    static int calculateCost(int y, int x) {
        int sum = map[y][x];
        for (int i=0; i<4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            sum += map[ny][nx];
        }
        return sum;
    }

    // 현재 선택된 위치와 인접한지 확인하는 함수
    static boolean isAdjacent(int y, int x, int[][] path, int cnt) {
        for (int i=0; i<cnt; i++) {
            if ((Math.abs(y - path[i][0]) + Math.abs(x - path[i][1])) <= 2) return true;
        }
        return false;
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
