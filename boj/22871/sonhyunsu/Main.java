//제출번호: 93553134
//메모리:   13352 KB
//실행시간: 160 ms
import java.io.*;

//그냥 문제에서 요구하는대로 dp식을 만들어서 O(N^2)으로 돌림
//dp[i]: i번째에서 오른쪽 끝까지 갈 때 필요한 최소 힘의 크기
public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        int n = nextInt();
        int[] d = new int[n];
        for (int i = 0; i < n; i++) {
            d[i] = nextInt();
        }

        long[] dp = new long[n];
        for (int i = n-2; i >= 0; i--) {
            dp[i] = Integer.MAX_VALUE; //현재 힘을 최대치로 잡고
			//i+1부터 끝까지 돌을 보면서
            for (int j = i+1; j < n; j++) {
				//각각의 돌마다 필요한 힘을 구하고, 그 중 최솟값을 dp[i]에 저장
                dp[i] = Math.min(dp[i], Math.max(dp[j], 1L * (j-i) * (Math.abs(d[j] - d[i]) + 1)));
            }
        }

		//첫 번째 돌에서 마지막 돌까지 가기 위한 힘의 크기 출력
        System.out.print(dp[0]);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}