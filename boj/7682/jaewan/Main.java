import java.io.*;

public class Main {
    static char[][] board;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        while (true) {
            String input = br.readLine();
            if (input.equals("end")) break;
            
            board = new char[3][3];
            int xCount = 0;
            int oCount = 0;
            
            // 입력
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    board[i][j] = input.charAt(i * 3 + j);
                    if (board[i][j] == 'X') xCount++;
                    else if (board[i][j] == 'O') oCount++;
                }
            }
            
            boolean xWin = isWin('X');
            boolean oWin = isWin('O');
            
            // 게임의 유효성 검사
            if (xWin && !oWin && xCount == oCount + 1) {
                sb.append("valid\n");
            } else if (!xWin && oWin && xCount == oCount) {
                sb.append("valid\n");
            } else if (!xWin && !oWin && xCount == 5 && oCount == 4) {
                sb.append("valid\n");
            } else {
                sb.append("invalid\n");
            }
        }
        System.out.print(sb);
    }
    
    // 승리 조건 체크
    static boolean isWin(char player) {
        // 가로 검사
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player)
                return true;
        }
        
        // 세로 검사
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == player && board[1][j] == player && board[2][j] == player)
                return true;
        }
        
        // 대각선 검사
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player)
            return true;
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player)
            return true;
            
        return false;
    }
}
