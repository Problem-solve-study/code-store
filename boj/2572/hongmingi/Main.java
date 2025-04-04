// 24488KB	988ms
import java.io.*;
import java.util.*;
/*
 * 카드 사용 순서, 현재 마을 위치를 고려한 2차원 dp를 통해 bottom-up으로 구현했다.
 * 두 지점이 연결되어 있을 때 출발 지점의 n카드번째 최댓값과 루트를 고려해서 최댓값을 찾는 형태로 구현.
 */
public class Main {
      static int[][] dp;
      public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[] cards = new char[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) cards[i] = st.nextToken().charAt(0);
        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        char[][] map = new char[M+1][M+1];
        for(int i=1; i<=M; i++) Arrays.fill(map[i], '-');

        int from, to;
        for(int i=0; i<K; i++){
          st = new StringTokenizer(br.readLine());
          from = Integer.parseInt(st.nextToken());
          to = Integer.parseInt(st.nextToken());
          map[from][to] = map[to][from] = st.nextToken().charAt(0);
        }
        
        dp = new int[N+1][M+1]; // 카드, 이동할 마을 위치. 이 마을로 이동했을 때 얻을 수 있는 점수
        for(int i=0; i<=N; i++) Arrays.fill(dp[i], -1);
        dp[0][1] = 0; //1에서 시작.
        for(int i=0; i<N; i++){ 
          for(int j=1; j<=M; j++){
            if(dp[i][j]==-1) continue; // 가지 않은 경우 continue;
            for(int k=1; k<=M; k++){ //j와 연결된 지점들
              if(map[j][k]=='-') continue; // 연결 안되어 있으면 continue;
              dp[i+1][k] = Math.max(dp[i+1][k], dp[i][j]+(cards[i]==map[j][k]?10:0)); // j, k가 연결되어 있을 때 이 색이 card와 같을 경우 10 증가시키고 최댓값 비교.
            }
          }
        }
        int maxScore = (int)Arrays.stream(dp[N]).max().getAsInt();
        System.out.println(maxScore!=-1?maxScore:0);
    }
}
