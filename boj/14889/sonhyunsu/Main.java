//제출번호: 90529872
//메모리:   14244 KB
//실행시간: 300 ms
import java.io.*;

//n이 매우 작기 때문에 모든 경우를 탐색해도 괜찮을 것이라 생각했음
//i와 j가 같은 팀이면 power[i][j] + power[j][i]를 해야하기 때문에 
//누가 같은 팀인지 기록해야 함
//마지막에 나눠진 두 팀에 대해서 올바르게 능력치를 구하면 해결
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StreamTokenizer st = new StreamTokenizer(br);
    static int n;
    static int[][] power = new int[21][21];
    static boolean[] isATeam = new boolean[21];
    static int res = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        n = nextInt();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                power[i][j] = nextInt();
            }
        }

        comb(0, 0);
        System.out.print(res);
    }

    static void comb(int cnt, int start) {
        //만약 정확하게 두 팀으로 나눴으면
        if (2 * cnt == n) {
            //a팀과 b팀에 대해서 능력치를 계산함
            int aTeamVal = calcTeamVal(true);
            int bTeamVal = calcTeamVal(false);

            //능력치의 최소를 res에 저장
            res = Math.min(res, Math.abs(aTeamVal - bTeamVal));
            return;
        }

        for (int i = start; i < n; i++) {
            isATeam[i] = true; //선택된 i를 a팀으로 기록한다.
            comb(cnt + 1, i + 1);
            isATeam[i] = false; //원상복구
        }
    }

    //플래그를 이용해서 a팀과 b팀을 구별한다. true면 a팀의 능력치를 계산, false면 b팀의 능력치를 계산
    static int calcTeamVal(boolean flag) {
        int teamVal = 0;
        for (int i = 0; i < n; i++) {
            if (flag ? isATeam[i] : !isATeam[i]) {
                continue;
            }

            for (int j = i + 1; j < n; j++) {
                if (flag ? isATeam[j] : !isATeam[j]) {
                    continue;
                }

                //i와 j가 같은 팀이면 능력치를 더함
                teamVal += power[i][j] + power[j][i];
            }
        }

        return teamVal;
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}