//제출번호: 94362736
//메모리:   21064 KB
//실행시간: 228 ms
import java.io.*;
import java.util.*;

//항상 오른쪽으로만 이동하니까 인접한 2개의 상태만 관리해도 구할 수 있음
//max(col-1 값, col 값, col+1 값) + 당근 여부 로 dp점화식을 세울 수 있고, 쪽문일 때 정답 업데이트를 하면 됨
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
        
        int rabbitCol = -1;
        char[][] map = new char[n][];
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();

            if (rabbitCol == -1) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == 'R') {
                        rabbitCol = j;
                        break;
                    }
                }
            }
        }

        int[] cur = new int[n], next = new int[n], tmp;
        for (int i = 0; i < n; i++) {
            cur[i] = map[i][rabbitCol] == 'R' ? 0 : Integer.MIN_VALUE;
        }

        int res = -1;
        for (int col = rabbitCol + 1; col < m; col++) {
            for (int row = 0; row < n; row++) {
                if (map[row][col] == '#') {
                    next[row] = Integer.MIN_VALUE;
                    continue;
                }

                next[row] = cur[row];
                if (row > 0) {
                    next[row] = Math.max(next[row], cur[row-1]);
                }
                if (row < n-1) {
                    next[row] = Math.max(next[row], cur[row+1]);
                }

                next[row] += map[row][col] == 'C' ? 1 : 0;
                if (map[row][col] == 'O') {
                    res = Math.max(res, next[row]);
                }
            }

            tmp = next;
            next = cur;
            cur = tmp;
        }

        System.out.print(res);
    }
}