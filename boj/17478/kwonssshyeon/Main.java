// 13448KB	96ms
import java.util.*;
import java.io.*;

/**
 * 주의할 부분
 * 1. 마지막은 response가 다르다.
 * 2. " 따옴표의 위치
 */

public class Main {
    static int n;
    static StringBuilder sb = new StringBuilder("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n");
    static String[] response = new String[] 
    {"\"재귀함수가 뭔가요?\"\n", 
    "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n",
    "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n",
    "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n",
    "\"재귀함수는 자기 자신을 호출하는 함수라네\"\n",
    "라고 답변하였지.\n"};
    static String underbar = "____";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        recursion(0);

        System.out.print(sb.toString());
        sc.close();
    }

    static void recursion(int depth) {
        if (depth == n) {
            appendResponse(depth, 0);
            appendResponse(depth, 4);
            appendResponse(depth, 5);
            return;
        }

        for (int i=0; i<4; i++) {
            appendResponse(depth, i);
        }   
        recursion(depth+1);
        appendResponse(depth, 5);
    }

    static void appendResponse(int depth, int idx) {
        for (int j=0; j<depth; j++)  sb.append(underbar);
        sb.append(response[idx]);
    }
}
