//제출번호: 90003354
//메모리:   18836 KB
//실행시간: 208 ms
import java.io.*;
import java.util.*;

public class Main {

    static int n, d[][];
    static boolean visited[][];
    static int dx[] = {1, -1, 0, 0}, dy[] = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        d = new int[n][];
        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            d[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        List<Integer> group = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (d[i][j] == 1 && !visited[i][j]) {
                    group.add(dfs(i, j));
                }
            }
        }
        
        System.out.println(group.size());
        group.stream().sorted().forEach(System.out::println);
    }

    static int dfs(int x, int y) {
        if (!isInRange(x, y) || d[x][y] == 0 || visited[x][y]) {
            return 0;
        }

        visited[x][y] = true;
        int res = 1;
        for (int i = 0; i < 4; i++) {
            res += dfs(x + dx[i], y + dy[i]);
        }

        return res;
    }

    static boolean isInRange(int x, int y) {
        return 0 <= x && x < n && 0<= y && y < n;
    }
}