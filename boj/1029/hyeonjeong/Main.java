// 16808KB 100ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// DFS & 가지치기
class Main {
    static int n;
    static int[][] map;
    static boolean[][][] memo;      // (현재노드, 현재가격, 경로집합)의 방문 여부

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            char[] numbers = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                map[i][j] = numbers[j] - '0';
            }
        }

        memo = new boolean[n][10][(int) Math.pow(2, n)];

        int result = search(0, 1, 0, 1);

        System.out.println(result);
    }
    
    static int search(int pi, int path, int prevPrice, int count) {
        // 이미 방문했으면 종료
        if (memo[pi][prevPrice][path]) {
            return -1;
        }
        memo[pi][prevPrice][path] = true;

        int maxCount = count;
        for (int ni = 0; ni < n; ni++) {
            // 가격이 더 적거나 방문햇으면 무시
            if (map[pi][ni] < prevPrice || ((1 << ni) & path) > 0) {
                continue;
            }

            int result = search(ni, path | (1 << ni), map[pi][ni], count + 1);
            
            if (result > maxCount) {
                maxCount = result;
            }
        }

        return maxCount;
    }
}
