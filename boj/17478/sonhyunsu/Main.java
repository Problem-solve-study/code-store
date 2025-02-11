//제출번호: 89893037
//메모리:   14520 KB
//실행시간: 116 ms

import java.io.*;
import java.util.*;

//재귀함수를 잘 구현하면 끝나는 문제,
//언더바를 하이폰으로 잘못 보고 제출했다가 한 번 틀림..
public class Main {

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n;

    public static void main(String[] args) throws IOException {
        n = new Scanner(System.in).nextInt();
        bw.write("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n");
        recursive(0);
        bw.flush();
    }

    static void recursive(int k) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++) {
            sb.append("____");
        }
        String dash = sb.toString();

        bw.write(String.format("%s\"재귀함수가 뭔가요?\"%n", dash));
        if (k == n) {
            bw.write(String.format("%s\"재귀함수는 자기 자신을 호출하는 함수라네\"%n", dash));
        } else {
            bw.write(String.format("%s\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.%n", dash));
            bw.write(String.format("%s마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.%n", dash));
            bw.write(String.format("%s그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"%n", dash));
            recursive(k + 1);
        }
        bw.write(String.format("%s라고 답변하였지.%n", dash));
    }
}