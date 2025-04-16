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

/* 상향식 DP로 구현한 코드
import java.io.*;

public class Main {
    
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        int n = nextInt() * 3;
        char[] drugs = nextString().toCharArray();

        //구간의 길이를 작은 순에서 큰 순으로 거꾸로 올라가기 때문에
        //약도 반대로 먹어야 함  
        char[] day = {'D', 'L', 'B'};

        int[][] dp = new int[n][n];
        //구간의 길이를 가장 작은 순서부터 큰 순서로 dp를 업데이트 함
        for (int i = 0; i < n; i++) {
            char eatDrug = day[i % 3]; //현재 먹을 약
            for (int s = n - i - 1; s >= 0; s--) {
                int e = s + i; //[s, e] 구간에 대해서

                //s 위치가 지금 먹어야 할 약이면서 s가 전체 구간의 오른쪽 끝이 아니라면
                if (drugs[s] == eatDrug && s < n - 1) {
                    //[s+1, e] 구간의 최댓값 + 1이 최대임
                    dp[s][e] = Math.max(dp[s][e], dp[s+1][e] + 1);
                }
                
                //e 위치가 지금 먹어야 할 약이면서 e가 전체 구간의 왼쪽 끝이 아니라면 
                if (drugs[e] == eatDrug && e > 0) {
                    //[s, e-1] 구간의 최댓값 + 1이 최대임
                    dp[s][e] = Math.max(dp[s][e], dp[s][e-1] + 1);
                }

                //s == e인 상황에서는 if 둘 중 하나는 무조건 실행하기 때문에 최종적으로 dp가 업뎃됨
                //if 둘 다 안 된다면 dp[s][e] = 0이므로 불가능한 경우라는 뜻이 됨
            }
        }

        //전체 구간의 최댓값을 출력
        System.out.print(dp[0][n - 1]);
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
 */