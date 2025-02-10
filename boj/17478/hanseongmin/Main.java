import java.io.*;

/*
11876KB, 72ms

마지막 depth에 도달했을 때는 출력해야할 부분이 바뀐다.
이것만 체크했다면 그 다음은 적당히 재귀를 사용하여 구현하면 된다.
-가 아니라 _임에 주의
 */

public class Main {
    static int n;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        sb.append("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n");
        response(0);

        bw.write(sb.toString());
        bw.flush();
    }

    static void response(int depth) {
        print(depth, "\"재귀함수가 뭔가요?\"");
        if (depth < n) {
            print(depth, "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.");
            print(depth, "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.");
            print(depth, "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"");
            response(depth + 1);
        }
        if (depth == n) print(depth, "\"재귀함수는 자기 자신을 호출하는 함수라네\"");
        print(depth, "라고 답변하였지.");
    }

    static void print(int depth, String content) {
        for (int i = 0; i < depth; i++) {
            sb.append("____");
        }
        sb.append(content).append('\n');
    }
}