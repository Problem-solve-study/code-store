
//제출번호: 91146311
//메모리:   18304 KB
//실행시간: 188 ms
import java.io.*;
import java.util.*;

//2차원 dp를 이용해서 구현할 때, [start, end]에 있는 점들로 만들 수 있는 최솟값을 저장하고,
//최솟값일 때의 높이를 따로 저장해 이용했음
//점들을 구할 때는 전체 최솟값부터 해서, 특정 점을 골랐을 때 최소가 되는, 역추적을 통해서 구했음
public class Main {

    static int n;
    static int[][] dp, maxHeight;
    static boolean[][] canMake;
    static List<int[]> points = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        boolean d[] = new boolean[n];
        String line = br.readLine();
        for (int i = 0; i < line.length(); i++) {
            d[i] = line.charAt(i) == '1';
        }

        maxHeight = new int[n][n];
        dp = new int[n][n];
        canMake = new boolean[n][n];
        //항상 짝수개의 점들을 가진 구간에서만 답이 나올 가능성이 있음
        for (int i = 1; i < n; i += 2) {
            //구간이 i인 모든 구간을 탐색해봄
            for (int start = 0, end = i; end < n; start++, end++) {
                //만약에 양 끝을 이을 수 있다면 이은 결과를 dp에 저장함
                if (d[start] != d[end]) {
                    //구간이 1이라면 답이 하나 밖에 없으므로 상수를 저장
                    if (i == 1) {
                        dp[start][end] = 3;
                        maxHeight[start][end] = 1;
                        canMake[start][end] = true; //이 구간에서 모든 점들을 이을 수 있다고 저장
                    } else if (canMake[start + 1][end - 1]) {
                        //만약에 양 두 끝점을 제외하고도 내부에 있는 점들만으로 모든 점들을 이을 수 있다면
                        //두 점을 포함해서도 모든 점들을 이을 수 있음
                        maxHeight[start][end] = maxHeight[start + 1][end - 1] + 1;
                        dp[start][end] = dp[start + 1][end - 1] + 2 * maxHeight[start][end] + end - start;
                        canMake[start][end] = true; //이 구간에서 모든 점들을 이을 수 있다고 저장
                    }
                }

                //양 끝을 잇는 방법이 아니라 구간을 두개로 나눠서 서브 구간의 합이 답이 될 수도 있음
                for (int mid = start + 1; mid + 1 < end; mid += 2) {
                    //만약에 두 구간으로 나눴을 때 두 구간 모두 내부에서 모든 점들을 이을 수 있을 때,
                    //그리고 두 구간의 합이 지금 dp[start][end] 보다 작다면 최솟값 갱신임
                    if (canMake[start][mid] && canMake[mid + 1][end]
                            && (!canMake[start][end] || dp[start][end] > dp[start][mid] + dp[mid + 1][end])) {
                        maxHeight[start][end] = Math.max(maxHeight[start][mid], maxHeight[mid + 1][end]);
                        dp[start][end] = dp[start][mid] + dp[mid + 1][end];
                        canMake[start][end] = true;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder().append(dp[0][n - 1]).append('\n');

        backTracking(dp[0][n - 1], 0, n - 1);
        points.sort((i1, i2) -> Integer.compare(i1[0], i2[0]));
        for (int[] point : points) {
            sb.append(point[0] + 1).append(' ').append(point[1] + 1).append('\n');
        }

        System.out.print(sb);
    }

    static void backTracking(int res, int start, int end) {
        //인접한 두 구간이 점을 이을 수 있다면, 선택되는 두 점임
        if (end - start == 1) {
            if (canMake[start][end] && res == dp[start][end]) {
                points.add(new int[] { start, end });
            }

            return;
        }

        //만약에 양 끝에 두 점을 제외하고도 모든 점을 이을 수 있고
        if (canMake[start + 1][end - 1]) {
            int dist = 2 * maxHeight[start][end] + end - start;

            //그게 최솟값으로 만드는 방법이었다면 양 끝의 두 점을 추가함
            if (dp[start][end] == dp[start + 1][end - 1] + dist) {
                points.add(new int[] { start, end });
                backTracking(res - dist, start + 1, end - 1);
                return;
            }
        }

        for (int mid = start + 1; mid + 1 < end; mid += 2) {
            //만약 두 구간으로 나눴을 때 두 구간 모두 모든 점을 이을 수 있고
            //그게 최솟값으로 만드는 방법이었다면 두 구간의 점을 찾는 함수를 호출함
            if (canMake[start][mid] && canMake[mid + 1][end] && dp[start][end] == dp[start][mid] + dp[mid + 1][end]) {
                backTracking(dp[start][mid], start, mid);
                backTracking(dp[mid + 1][end], mid + 1, end);
                break;
            }
        }
    }
}