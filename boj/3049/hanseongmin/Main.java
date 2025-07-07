import java.io.*;

/*
11496KB, 72ms

4의 답이 1이라는 것이 힌트
다각형의 점 중 4개를 택하여 사각형을 만들면 교점은 하나가 되므로
다각형의 꼭짓점 중 4개를 택하면 된다.
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws Exception {
        int n = nextInt();
        System.out.print((n * (n - 1) * (n - 2) * (n - 3)) / 24);
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
