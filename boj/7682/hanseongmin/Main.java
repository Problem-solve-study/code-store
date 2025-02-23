import java.io.*;

/*
11632KB 64ms

X를 먼저 두고 그 다음 O를 두므로 기본적으로 X의 개수가 O의 개수보다 1 많거나 혹은 같아야 한다.
이 조건을 만족하지 못한다면 기본적으로 불가능한 상태. 만일 이 조건을 만족시킨다면 가능한 경우는 아래의 4가지

1. X 승리 O 패배 -> x가 두고 이겨야 하므로 x개수가 o개수보다 1 많아야 함
2. X 패배 O 승리 -> o가 두고 이겨야 하므로 x개수와 o개수가 같아야 함
3. X 승리 O 승리 -> 둘다 이기는 경우는 불가능
4. X 패배 O 패배 -> 현재 게임판이 모두 차있다면 가능

위 4개의 조건을 걸고 출력해주면 된다.
문제 자체는 어렵지 않지만 실수하기 쉬운 문제인듯
 */

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int xCnt, oCnt;
    static char[][] map = new char[3][3];

    public static void main(String[] args) throws Exception {
        String line = "";
        StringBuilder sb = new StringBuilder();
        while (!(line = br.readLine()).equals("end")) {
            xCnt = 0;
            oCnt = 0;
            //게임판 상태 입력
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    map[i][j] = line.charAt(3 * i + j);
                    if (map[i][j] == 'X') xCnt++;
                    else if (map[i][j] == 'O') oCnt++;
                }
            }

            if (xCnt - oCnt == 1 || xCnt - oCnt == 0) {
                boolean xCheck = checkWin('X');
                boolean oCheck = checkWin('O');

                if (xCheck && !oCheck) sb.append(xCnt - oCnt == 1 ? "valid" : "invalid");
                else if (!xCheck && oCheck) sb.append(xCnt == oCnt ? "valid" : "invalid");
                else if (xCheck && oCheck) sb.append("invalid");
                else sb.append(xCnt + oCnt == 9 ? "valid" : "invalid");
            } else {
                sb.append("invalid");
            }
            sb.append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
    }

    static boolean checkWin(char c) {
        //가로 및 세로줄 체크
        for (int i = 0; i < 3; i++) {
            if ((map[i][0] == c && map[i][1] == c && map[i][2] == c) ||
                (map[0][i] == c && map[1][i] == c && map[2][i] == c)) {
                return true;
            }
        }

        //대각선 체크
        return ((map[0][0] == c && map[1][1] == c && map[2][2] == c) ||
                (map[0][2] == c && map[1][1] == c && map[2][0] == c));
    }
}
