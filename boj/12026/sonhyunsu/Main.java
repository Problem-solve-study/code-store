//제출번호: 90625196
//메모리: 	11692 KB
//실행시간: 76 ms
import java.io.*;

//입력받은 문자열이 B, O, J 밖에 없고, 밟는 순서도 정해져 있으니
//밟는 순서에 맞게 dp를 계산하면 됨.
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StreamTokenizer st = new StreamTokenizer(br);

    public static void main(String[] args) throws IOException {
        st.nextToken();
        int n = (int) st.nval;

        st.nextToken();
        String line = st.sval;

        int[] values = new int[n], nValues = new int[n];
        for (int i = 0; i < n; i++) {
            switch (line.charAt(i)) {
                //내 위치, 내가 다음에 밟을 수 있는 문자를 저장해 둠
                case 'B': values[i] = 0; nValues[i] = 1; break;
                case 'O': values[i] = 1; nValues[i] = 2; break;
                case 'J': values[i] = 2; nValues[i] = 0; break;
            }
        }
        
        int maxVal = (int) 1e9;

        int[] dp = new int[n], square = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = maxVal;
            square[i] = i * i; //각각의 제곱을 계산해 둠
        }

        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            
            //다음부터 내가 밟을 수 있는 위치를 찾아봄
            for (int j = i + 1; j < n; j++) {

                //내가 밟을 수 있으면 그곳으로 가기 위해 필요한 에너지 양을 계산해서
                //그 위치에 업데이트 함
                if (nValues[i] == values[j]) {
                    dp[j] = Math.min(dp[j], dp[i] + square[j - i]);
                }
            }
        }

        n--;
        System.out.print(dp[n] == maxVal ? -1 : dp[n]);
    }
}