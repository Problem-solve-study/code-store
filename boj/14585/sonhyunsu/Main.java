//제출번호: 94026033
//메모리:   12968 KB
//실행시간: 76 ms
import java.io.*;

//좌표 범위가 300 밖에 안 되기 때문에 그냥 300*300 만큼 순회하게 했음
//처음에는 dp로 풀어봤는데 2틀해서 코드를 갈아 엎었음 -- 나중에 풀어서 아래 주석으로 달아둠
public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        int n = nextInt();
        int m = nextInt();

        int[][] candies = new int[302][302];
        for (int i = 0; i < n; i++) {
            int x = nextInt();
            int y = nextInt();
            candies[x][y] = Math.max(m - x - y, 0); //x, y까지 갔을 때 남아 있는 사탕을 계산해서 저장
        }
        
        //300, 300 부터 0, 0까지 거꾸로 계산함
        for (int x = 300; x >= 0; x--) {
            for (int y = 300; y >= 0; y--) {
                //현재 x, y에서 다음으로 갈 수 있는 경로에 대해서, 사탕을 먹을 수 있는 최댓값을 현재 x, y에 더함
                candies[x][y] += Math.max(candies[x+1][y], candies[x][y+1]);
            }
        }

        //0, 0에 저장된 값 출력
        System.out.println(candies[0][0]);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}

/* 다른 방법으로 푼 문제
//제출번호: 94027240
//메모리:   18316 KB
//실행시간: 192 ms
import java.io.*;
import java.util.*;

//시간과 관련된 코드가 틀려서 2틀했음.
//시간은 항상 x + y 만큼 들기 때문에 그렇게 바꾸니까 통과함
public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        int n = nextInt();
        int m = nextInt();

        int[] dp = new int[n + 1];
        int[][] pos = new int[n + 1][];

        pos[0] = new int[]{0, 0};
        for (int i = 1; i <= n; i++) {
            pos[i] = new int[]{nextInt(), nextInt()};
        }
        
        //멘헤튼 거리가 짧은 순으로 업데이트하기 위해서 정렬
        Arrays.sort(pos, (i1, i2) -> Integer.compare(i1[0] + i1[1], i2[0] + i2[1]));
        
        for (int i = 1; i <= n; i++) {
            int x = pos[i][0];
            int y = pos[i][1];
            int remainCandy = Math.max(m - (x + y), 0); //현재 x, y에 남아 있는 사탕의 수를 계산

            for (int j = 0; j < i; j++) {
                //만약 j번째 바구니에서 i번째 바구니로 울 수 있으면서
                if (pos[j][0] <= x && pos[j][1] <= y) {
                    //현재 먹을 수 있는 사탕의 수보다 j를 거쳐서 먹을 수 있는 사탕의 수가 더 많다면 업데이트
                    if (dp[i] < remainCandy + dp[j]) {
                        dp[i] = remainCandy + dp[j];
                    }
                }
            }
        }

        //각각의 바구니를 가장 마지막으로 방문했을 때, 먹을 수 있는 사탕들 중 최댓값을 출력
        int res = 0;
        for (int i = 1; i <= n; i++) {
            res = Math.max(res, dp[i]);
        }

        System.out.print(res);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
 */