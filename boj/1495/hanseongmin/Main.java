import java.io.*;
import java.util.*;

/*
12984KB, 80ms

BFS 아니면 DP 일 것 같았고 일단 BFS로 구현해봤는데 메모리 초과가 뜨길래 DP로 접근
dp[i]: 음량이 i일 때 최대로 연주 가능한 곡의 인덱스로 테이블을 정의하고 돌리면 된다.
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws Exception {
        int N = nextInt();
        int S = nextInt();
        int M = nextInt();
        int[] V = new int[N];
        for (int i = 0; i < N; i++) {
            V[i] = nextInt();
        }

        int[] dp = new int[M + 1];
        Arrays.fill(dp, -2);
        //처음은 아무 곡도 연주하지 않은 상태이므로 -1
        dp[S] = -1;
        //테이블 값이 중간에 바뀌어버리면 DP 테이블 갱신이 정상적으로 안될 수 있으니 Lazy 업데이트를 위한 리스트
        ArrayList<Integer> target = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int curV = V[i];
            for (int j = 0; j <= M; j++) {
                //직전의 곡으로부터 현재 음량이 가능한 경우만 봄
                if (dp[j] != i - 1) continue;
                if (j - curV >= 0) {
                    target.add(j - curV);
                }

                if (j + curV <= M) {
                    target.add(j + curV);
                }
            }
            //Lazy 업데이트
            for (int idx : target) {
                dp[idx] = i;
            }
            target.clear();
        }

        int res = -1;
        //최댓값을 살펴봐야하므로 끝에서부터 연주 가능한 최대의 인덱스가 N - 1이라면 탐색 성공
        for (int i = M; i >= 0; i--) {
            if (dp[i] == N - 1) {
                res = i;
                break;
            }
        }
        System.out.print(res);
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
