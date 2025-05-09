//제출번호: 94070802
//메모리:   47464 KB
//실행시간: 164 ms
import java.io.*;

//전개가 LCS와 비슷함
//x의 i번째 문자, y의 j번째 문자를 기준으로, 같은 위치에 두기, i번째 문자를 공백과 매치하기, j번째 문자를 공백과 매치하기
//이 3가지 경우만 잘 보면 됨
public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        int a = nextInt(), b = nextInt(), c = nextInt();
        String x = nextString(), y = nextString();

        int[][] dp = new int[x.length() + 1][y.length() + 1];

        //y의 1~i번째 문자까지 모두 x 앞에 있으면 비용은 b * i만큼 듦
        for (int i = 1; i <= y.length(); i++) {
            dp[0][i] = b*i;
        }

        for (int i = 1, pi = 0; pi < x.length(); i++, pi++) {
            dp[i][0] = b*i; //x의 1~i번째 문자까지 모두 y 앞에 있으면 비용은 b * i만큼 듦
            char xChr = x.charAt(pi);
            for (int j = 1, pj = 0; pj < y.length(); j++, pj++) {
                int yChr = y.charAt(pj);
                //x의 i번째 문자와 y의 j번째 문자를 같은 위치에 두는 경우:
                //    (i-1번째 문자와 j-1번째 문자를 배치했을 때의 최댓값을 이용) - dp[i-1][j-1] + (a or c)
                //x의 i번째 문자를 공백과 매칭하는 경우: 
                //    (i-1번째 문자와 j번째 문자를 배치했을 때의 최댓값을 이용) - dp[i-1][j] + b
                //y의 j번째 문자를 공백과 매칭하는 경우: 
                //    (i번째 문자와 j-1번째 문자를 배치했을 때의 최댓값을 이용) - dp[i][j-1] + b
                dp[i][j] = Math.max(dp[i-1][j-1] + (xChr == yChr ? a : c), Math.max(dp[i-1][j], dp[i][j-1]) + b);
            }
        }
        
        //모든 문자를 배치했을 때의 최댓값 출력
        System.out.print(dp[x.length()][y.length()]);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    static String nextString() throws IOException {
        st.nextToken();
        return st.sval;
    }
}