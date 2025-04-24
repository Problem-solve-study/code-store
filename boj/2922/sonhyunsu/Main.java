//제출번호: 93516070
//메모리:   11496 KB
//실행시간: 68 ms
import java.io.*;

//처음에 자음 또는 모음이 연속 3개 나오면 안 된다는 조건을 보고 DP라고 생각해서 구현
//구현하다 보니까 L이 반드시 필요하다고 해서 DP의 차원을 하나 더 늘림
//보니까 DP 차원이 4차원이 되길래 이게 맞나 싶었는데 되긴 할 거 같아서 그대로 구현함
//차원이 늘어나니까 식이 너무 헷갈렸음..
//결과를 int로 저장해서 1틀 => long으로 바꾸고 통과 

//DP[a][b][c][d]
//a: 0 or 1, L 사용 여부를 저장
//b: 0 or 1, 0이면 모음, 1이면 자음에 대한 정보를 저장
//c: 0 1 2, b 타입 알파벳에 대해서 연속으로 사용한 개수를 저장
//d: 문자열의 길이를 저장 
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] line = br.readLine().toCharArray();

        long[][][][] dp = new long[2][2][3][line.length + 1];
        dp[0][0][0][0] = dp[0][1][0][0] = 1; //모음과 자음을 모두 0개 쓴 경우의 수 세팅

        for (int i = 1; i <= line.length; i++) {
            if (line[i-1] == '_') {
				//만약 현재가 _라면
                for (int j = 1; j < 3; j++) {
                    dp[1][1][j][i] += dp[0][1][j-1][i-1] + dp[1][1][j-1][i-1]; //L을 사용한 경우
                    for (int l = 0; l < 2; l++) {
                        dp[l][0][j][i] += dp[l][0][j-1][i-1] * 5; //모음을 연속해서 사용한 경우
                        dp[l][1][j][i] += dp[l][1][j-1][i-1] * 20; //L을 제외한 자음을 연속해서 사용한 경우
                    }
                }
                for (int l = 0; l < 2; l++) {
                    for (int j = 1; j < 3; j++) {
                        dp[l][1][0][i] += dp[l][0][j][i]; //i번째에서 모음을 1개 이상 사용했다면 자음은 0개 사용한 것
                        dp[l][0][0][i] += dp[l][1][j][i]; //i번째에서 자음을 1개 이상 사용했다면 모음은 0개 사용한 것
                    }
                }
            } else if (line[i-1] == 'L') {
				//만약 현재가 L이라면
                for (int j = 1; j < 3; j++) {
					//L를 사용했기 때문에 DP식에서 a자리는 항상 1 고정임
					//직전까지 L을 한 번도 사용하지 않은 경우와 직전까지 L이 적어도 1번 이상 나온 경우를 모두 계산
                    dp[1][1][j][i] += dp[0][1][j-1][i-1] + dp[1][1][j-1][i-1]; 
                    dp[1][0][0][i] += dp[1][1][j][i]; //i번째에서 모음은 0번 사용함
                }
            } else if (isVowel(line[i-1])) {
				//만약 현재가 모음이라면
                for (int l = 0; l < 2; l++) {
                    for (int j = 1; j < 3; j++) {
                        dp[l][0][j][i] += dp[l][0][j-1][i-1]; //모음을 연속으로 사용했다는 정보를 업데이트
                        dp[l][1][0][i] += dp[l][0][j][i]; //i번째에서 자음은 0번 사용했음
                    }
                }
            } else {
				//만약 현재가 자음이라면
                for (int l = 0; l < 2; l++) {
                    for (int j = 1; j < 3; j++) {
                        dp[l][1][j][i] += dp[l][1][j-1][i-1]; //자음을 연속으로 사용했다는 정보를 업데이트
                        dp[l][0][0][i] += dp[l][1][j][i]; //i번째에서 모음은 0번 사용함
                    }
                }
            }
        }

		//모음을 0번 사용한 경우와 자음을 0번 사용한 경우를 더하면
		//(자음을 1, 2번 사용한 경우의 합) + (모음을 1, 2번 사용한 경우의 합)이 됨
		//DP식에서 그렇게 업데이트 했기 때문
        System.out.print(dp[1][0][0][line.length] + dp[1][1][0][line.length]);
    }

    static boolean isVowel(char chr) {
        switch (chr) {
            case 'A': case 'E': case 'I': case 'O': case 'U': return true;
            default: return false;
        }
    }
}