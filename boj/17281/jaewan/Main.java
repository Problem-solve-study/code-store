import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, maxScore;
    static int[] order = new int[9];
    static int[][] players;
    static boolean[] selected = new boolean[9];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        players = new int[N][9];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++)
                players[i][j] = Integer.parseInt(st.nextToken());
        }
        generateOrder(0);
        System.out.println(maxScore);
    }

    static void generateOrder(int depth) {
        if (depth == 9) {
            int score = playGame();
            maxScore = Math.max(maxScore, score);
            return;
        }
        if (depth == 3) { // 항상 선수 '0'이 네 번째 타자로 고정
            order[depth] = 0;
            generateOrder(depth + 1);
        } else {
            for (int i = 1; i < 9; i++) { // 선수 '0' 제외
                if (!selected[i]) {
                    selected[i] = true;
                    order[depth] = i;
                    generateOrder(depth + 1);
                    selected[i] = false;
                }
            }
        }
    }

    static int playGame() {
        int score = 0;
        int playerIdx = 0; // 현재 타자 인덱스
        for (int inning = 0; inning < N; inning++) {
            int[] result = playInning(inning, playerIdx);
            score += result[0]; // 이닝에서 얻은 점수 추가
            playerIdx = result[1]; // 다음 이닝 시작 타자 인덱스
        }
        return score;
    }

    static int[] playInning(int inning, int startPlayer) {
        int outCount = 0; // 아웃 카운트
        int score = 0; // 이닝 점수
        boolean[] bases = new boolean[3]; // 각 루 상태
        int playerIdx = startPlayer;

        while (outCount < 3) {
            int hit = players[inning][order[playerIdx]]; // 현재 타자의 결과
            switch (hit) {
                case 0: // 아웃
                    outCount++;
                    break;
                case 1: // 안타
                    score += bases[2] ? 1 : 0; // 홈으로 들어온 주자 점수 추가
                    bases[2] = bases[1];
                    bases[1] = bases[0];
                    bases[0] = true; // 현재 타자는 출루
                    break;
                case 2: // 2루타
                    score += bases[2] ? 1 : 0;
                    score += bases[1] ? 1 : 0;
                    bases[2] = bases[0];
                    bases[1] = true; 
                    bases[0] = false; 
                    break;
                case 3: // 삼루타
                    score += (bases[2] ? 1 : 0) + (bases[1] ? 1 : 0) + (bases[0] ? 1 : 0);
                    bases[2] = true; 
                    bases[1] = false; 
                    bases[0] = false; 
                    break;
                case 4: // 홈런
                    score += (bases[2] ? 1 : 0) + (bases[1] ? 1 : 0) + (bases[0] ? 1 : 0) + 1; 
                    bases[2] = false; 
                    bases[1] = false; 
                    bases[0] = false; 
                    break;
            }
            playerIdx = (playerIdx + 1) % 9; // 다음 타자로 이동
        }
        return new int[]{score, playerIdx}; // 점수와 다음 타자 인덱스 반환
    }
}
