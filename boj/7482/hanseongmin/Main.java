import java.io.*;

/*
11816KB, 68ms

선물상자의 부피는 y = b(a - 2b)^2의 꼴로 나타난다.
a는 상수고 b는 변수이니 b를 x로 치환하면 y = x(a - 2x)^2으로, 삼차함수의 꼴로 나타난다.
위 함수는 a / 6에서 극대, a / 2에서 극소이므로 극댓값인 a / 6을 출력하면 정답
입력되는 a가 정수인줄 알고 정수로 입력받다가 2틀함
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws Exception {
        int n = (int) nextDouble();
        StringBuilder sb = new StringBuilder();
        while (n --> 0) {
            sb.append(String.format("%.10f", nextDouble() / 6)).append('\n');
        }
        System.out.print(sb);
    }

    static double nextDouble() throws Exception {
        st.nextToken();
        return st.nval;
    }
}
