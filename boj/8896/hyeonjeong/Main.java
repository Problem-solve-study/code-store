// 11940KB 72ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 시뮬레이션
 */
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Map<Character, Integer> rsp = new HashMap<>();
        rsp.put('R', 0);
        rsp.put('S', 1);
        rsp.put('P', 2);

        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        for (; t > 0; t--) {
            int n = Integer.parseInt(br.readLine());

            String[] robots = new String[n];
            for (int i = 0; i < n; i++) {
                robots[i] = br.readLine();
            }
            boolean[] out = new boolean[n];
            int left = n;

            // 라운드 진행
            int R = robots[0].length();
            for (int round = 0; round < R; round++) {
                // R, S, P 출현 유무
                boolean[] cases = new boolean[3];
                for (int robot = 0; robot < n; robot++) {
                    if (out[robot]) {
                        continue;
                    }

                    cases[rsp.get(robots[robot].charAt(round))] = true;
                }

                // 승자 확인
                Character winner = getWinner(cases);
                if (winner == null) {
                    continue;
                }

                // 패자 아웃 처리
                for (int ri = 0; ri < n; ri++) {
                    if (out[ri] || robots[ri].charAt(round) == winner) {
                        continue;
                    }

                    out[ri] = true;
                    left--;
                }
            }

            if (left > 1) {
                sb.append("0\n");
                continue;
            }

            for (int ri = 0; ri < n; ri++) {
                if (!out[ri]) {
                    sb.append(ri + 1).append('\n');
                }
            }
        }

        System.out.println(sb);
    }

    static Character getWinner(boolean[] cases) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            if (cases[i]) {
                count++;
            }
        }

        if (count != 2) {
            return null;
        }

        if (cases[0] && cases[1]) {
            return 'R';
        }
        
        if (cases[0] && cases[2]) {
            return 'P';
        }

        return 'S';
    }
}
