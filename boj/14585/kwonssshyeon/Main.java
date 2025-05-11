// 	13092KB	80ms
import java.io.*;
import java.util.*;

/**
 * 사탕의 개수는 모두 같음
 * 그래프 탐색 ...? 길이 M내의 가능한 많은 점 방문 -> 불가능, 사탕이 최대한 녹기 전에 방문해야함.
 * 최대한 가까운 점부터 탐색 ...? 불가능, 현재 가까운점에서 다음 점까지의 거리는 멀 수 있다.
 * 완탐 ............? x, y 좌표가 커지는 방향밖에 불가능하므로 왼쪽, 위쪽의 최댓값을 따라가면서 현재 위치에 사탕이 있으면 먹는 식으로 갱신
 * 
 * 사탕이 음수가 될 수 있도록 설계해버려서 1틀
 */
public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int n, m;
    static boolean[][] candy = new boolean[301][301];
    static int[][] count = new int[301][301];

    public static void main(String[] args) throws IOException {
        n = nextInt();
        m = nextInt();
        for (int i=0; i<n; i++) {
            int a = nextInt();
            int b = nextInt();
            candy[a][b] = true;
        }

        for (int i=0; i<=300; i++) {
            for (int j=0; j<=300; j++) {
                int time = i + j;
                if (i != 0) count[i][j] = Math.max(count[i][j], count[i-1][j]);
                if (j != 0) count[i][j] = Math.max(count[i][j], count[i][j-1]);
                
                if (candy[i][j]) {
                    count[i][j] += Math.max(0, m - time);
                }
            }
        }
        System.out.println(count[300][300]);
    }
    

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
