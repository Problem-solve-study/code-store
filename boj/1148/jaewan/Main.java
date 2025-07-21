
/*
 * 3 x 3 표로 단어 만들기.
 * 단어는 최소 4글자 이상, 한 글자당 최대 1번만 사용
 * 
 * 정중앙 글자는 반드시 사용
 * 
 * 어떤 글자를 중앙에 놓아야 만들 수 있는 단어의 개수가 많은지, 적어지는지.
 * 
 * 사전을 이루는 최대 20만 개의 단어가 주어짐.
 * 
 * 여러 퍼즐판이 주어짐.
 * 정답의 개수를 가장 적게, 많게 하기 위한 문자들과 정답 개수 출력
 * 
 * 문자열과 사전이 나왔으니 트라이 구조일까?
 * 사전 순으로 정렬해 주어진다.
 * 
 * 특정 보드판으로 만들 수 있는 단어의 수는 정해짐.
 * 여기서, 반드시 포함해야 할 단어 하나만 바꾸는 것.
 * 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int idx;
    static int[][] dic = new int[26][200_000];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while (!(str = br.readLine()).equals("-")) {
            for (int i = 0; i < str.length(); i++)
                dic[str.charAt(i) - 'A'][idx]++;
            idx++;
        }

        while (!(str = br.readLine()).equals("#")) {
            int[] board = new int[26];

            for (int i = 0; i < 9; i++)
                board[str.charAt(i) - 'A']++;

            int[] res = new int[26];
            for (int i = 0; i < idx; i++) {
                // 만들 수 있는지 개수 확인
                boolean flag = true;
                for (int j = 0; j < 26; j++) {
                    if (dic[j][i] > board[j]) {
                        flag = false;
                        break;
                    }
                }
                // 만들 수 있으면, 결과에 반영
                if (flag) {
                    for (int j = 0; j < 26; j++) {
                        if (dic[j][i] > 0)
                            res[j]++;
                    }
                }
            }

            // 결과에서 최대, 최소 값 찾기
            int min = Integer.MAX_VALUE, max = 0;
            for (int i = 0; i < 26; i++) {
                if (board[i] > 0) {
                    min = Math.min(min, res[i]);
                    max = Math.max(max, res[i]);
                }
            }
            // 출력

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                if (board[i] > 0 && res[i] == min)
                    sb.append((char) ('A' + i));
            }
            sb.append(' ').append(min).append(' ');
            for (int i = 0; i < 26; i++) {
                if (board[i] > 0 && res[i] == max)
                    sb.append((char) ('A' + i));
            }
            sb.append(' ').append(max);
            System.out.println(sb);
        }

    }
}