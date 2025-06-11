import java.io.*;
import java.util.*;

/*
36480KB, 172ms

TSP랑 느낌이 비슷. BFS로 접근하되 방문체크 배열에 비트마스킹이 필요.
 */

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[][] mat = new char[N][N];
        for (int i = 0; i < N; i++) {
            mat[i] = br.readLine().toCharArray();
        }

        int ans = 0;
        boolean[][][] v = new boolean[N][(1 << N)][10];
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {0, 1, 0});
        while (!q.isEmpty()) {
            int[] cur = q.remove();
            int owner = cur[0];
            int status = cur[1];
            int price = cur[2];
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                if ((status & (1 << i)) != 0) {
                    cnt++;
                } else {
                    int newStatus = (status | (1 << i));
                    int newPrice = mat[owner][i] - '0';
                    if (!v[i][newStatus][newPrice] && newPrice >= price) {
                        q.add(new int[] {i, newStatus, newPrice});
                        v[i][newStatus][newPrice] = true;
                    }
                }
            }
            ans = Math.max(ans, cnt);
        }
        System.out.print(ans);
    }
}