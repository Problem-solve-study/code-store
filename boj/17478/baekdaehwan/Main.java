
/** 12068KB	72ms
 * 그래서 재귀함수가 뭐죠...?
 */

import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static String prefix = "____";
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringBuilder res = new StringBuilder();
        Stack<String> stack = new Stack<>();

        res.append("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) res.append(prefix);
            res.append("\"재귀함수가 뭔가요?\"\n");
            for (int j = 0; j < i; j++) res.append(prefix);
            res.append("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n");
            for (int j = 0; j < i; j++) res.append(prefix);
            res.append("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n");
            for (int j = 0; j < i; j++) res.append(prefix);
            res.append("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n");

            sb = new StringBuilder();
            for (int j = 0; j < i; j++) sb.append(prefix);
            sb.append("라고 답변하였지.\n");
            stack.push(sb.toString());
        }

        sb = new StringBuilder();
        for (int i = 0; i < N; i++) sb.append(prefix);
        sb.append("\"재귀함수가 뭔가요?\"\n");
        for (int i = 0; i < N; i++) sb.append(prefix);
        sb.append("\"재귀함수는 자기 자신을 호출하는 함수라네\"\n");
        for (int i = 0; i < N; i++) sb.append(prefix);
        sb.append("라고 답변하였지.\n");

        res.append(sb.toString());
        while (!stack.isEmpty())
            res.append(stack.pop());

        System.out.println(res);
    }
}