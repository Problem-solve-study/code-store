
/*
 * 문자열 S와 정수 N이 주어졌을 때, S안에 P[N] 이 몇 군데 포함되어 있는지 구하는 프로그램 작성
 * N <= 1,000,000
 * S의 길이 M <= 1,000,000
 * P[1] = IOI, P[2] = IOIOI, P[3] = IOIOIOI
 * P[2]에는 P[1]이 2군데 포함, P[3]에는 P[1]이 3군데 포함, P[4] 에는 P[1]이 4군데 포함
 * P[3]에는 P[2]이 2군데 포함, P[4]에는 P[2]이 3군데 포함.
 * 
 * 문자열 S 탐색하면서 이어지는 P 구간을 찾는다.
 * 종료돼서 P 구간 길이 구했으면, P[N]이 몇 개 들어가는지 더한다.
 * 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        String S = br.readLine();

        int i = 0, cnt, res = 0;
        while (i < M) {
            char t = S.charAt(i);
            if (t == 'I') {
                cnt = 0;
                int j = i + 1;
                boolean flag = false;
                // O, I 번갈아서 검사
                while (j < M) {
                    t = S.charAt(j);
                    if (!flag && t == 'O') {
                        flag = true;
                        j++;
                    } else if (flag && t == 'I') {
                        flag = false;
                        j++;
                        cnt++;
                    } else
                        break;
                }
                i = j;
                res += Math.max((cnt - N) + 1, 0);
            } else
                i++;
        }
        System.out.println(res);
    }
}