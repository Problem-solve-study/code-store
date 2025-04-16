//제출번호: 93179440
//메모리:   20828 KB
//실행시간: 92 ms
import java.io.*;

//처음에 DP식으로 구현하려고 했는데 식이 잘 안 나오고, 믿음으로 제출해봤지만 1틀
//그냥 재귀 + 메모이제이션으로 구현하자고 생각하고 구현했음 
public class Main {
    
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int n;
    static char[] drugs, day;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        n = nextInt() * 3; //아침, 점심, 저녁이 있으므로 일수 * 3을 n에 저장함
        drugs = nextString().toCharArray();
        day = new char[]{'B', 'L', 'D'}; //아침, 점심, 저녁

        //dp를 -1로 모두 초기화 함
        dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }

        //[0, n - 1] 구간의 최대값을 구함
        System.out.print(calc(0, n - 1));
    }

    //[start, end] 구간에서 약을 먹을 수 있는 최대 개수
    static int calc(int start, int end) {
        //이미 계산했던 구간이면 저장했던 값 리턴
        if (dp[start][end] != -1) {
            return dp[start][end];
        }

        //만약 끝까지 왔다면 마지막에 먹는 약은 'D'인 저녁 약이므로 'D'면 1 아니면 0임
        if (start == end) {
            return dp[start][end] = drugs[start] == 'D' ? 1 : 0;
        }

        //구간의 길이를 구하고, 길이를 이용해서 이번 구간에 먹는 약의 종류를 구함
        int len = end - start + 1;
        char eatDrug = day[(n - len) % 3]; 

        //만약 현재 구간의 왼쪽에 지금 먹을 수 있는 약이 있다면
        if (drugs[start] == eatDrug) {
            //다음 구간의 최대를 구한 다음 +1 한 값이 현재 구간의 최대임
            dp[start][end] = calc(start + 1, end) + 1;
        }

        //먄약 현재 구간의 오른쪽에 지금 먹을 수 있는 약이 있다면
        if (drugs[end] == eatDrug) {
            //(다음 구간의 최대를 구한 다음 +1 한 값)과 (이전 if에서 계산한 값) 중 최대가 현재 구간의 최대임
            dp[start][end] = Math.max(dp[start][end], calc(start, end - 1) + 1);
        }

        //dp가 업데이트 되지 않았다면 현재 구간에서 먹을 수 있는 약이 없는 것이기 때문에
        //현재 구간의 최대는 0이 됨
        if (dp[start][end] == -1) {
            dp[start][end] = 0;
        }

        //구간의 최대 리턴
        return dp[start][end];
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