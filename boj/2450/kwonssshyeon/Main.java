// 	23604KB	204ms
import java.io.*;
import java.util.*;

/**
 * 백트래킹을 이용하여 목표 배열을 만들고
 * 입력 배열과 목표 배열의 최소 이동 횟수를 계산
 * 2개끼리 바꿀 수 있는 경우를 모두 바꾸고,
 * 남은 수는 3개끼리 순환하는 형태로 바꿔야 함.
 * 이때 3개를 바꾸려면 1->2, 2->3와 같이 2번의 swap이 필요
 */
public class Main {
    static int n, map[];
    static int[] count = new int[4];
    static boolean[] visited = new boolean[4];
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            map[i] = Integer.parseInt(st.nextToken());
            count[map[i]]++;
        }

        backtracking(new int[3], 0);
        System.out.print(answer);
    }

    // 1,2,3 순열 구하는 함수
    static void backtracking(int[] order, int cnt) {
        if (cnt == 3) {
            int[] result = makeArr(order);
            int temp = calculate(result);
            answer = Math.min(answer, temp);
            return;
        }

        for (int i = 1; i <= 3; i++) {
            if (!visited[i]) {
                visited[i] = true;
                order[cnt] = i;
                backtracking(order, cnt + 1);
                visited[i] = false;
            }
        }
    }

    // 1,2,3 과 각각의 횟수를 고려한 target 배열 만드는 함수
    static int[] makeArr(int[] order) {
        int[] result = new int[n];
        int idx = 0;
        for (int o : order) {
            for (int i = 0; i < count[o]; i++) {
                result[idx + i] = o;
            }
            idx += count[o];
        }
        return result;
    }

    // 최소 이동 횟수 계산 함수
    static int calculate(int[] target) {
        int[][] table = new int[4][4];
        int result = 0;

        for (int i = 0; i < n; i++) {
            if (map[i] != target[i]) {
                table[target[i]][map[i]]++;
            }
        }

        // 2개끼리만 바꾸면 되는 경우
        for (int i = 1; i <= 3; i++) {
            for (int j = i + 1; j <= 3; j++) {
                int swap = Math.min(table[i][j], table[j][i]);
                result += swap;
                table[i][j] -= swap;
                table[j][i] -= swap;
            }
        }

        // 3개가 얽혀 있는 경우
        int remaining = 0;
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                if (i != j) {
                    remaining += table[i][j];
                }
            }
        }

        // 3개가 얽힌 경우, 2번의 swap으로 해결
        result += (remaining / 3) * 2;

        return result;
    }
}