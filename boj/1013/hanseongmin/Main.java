import java.io.*;

/*
15884KB, 132ms

그냥 정규식 쓰는 문제. matches 안쓰고 replaceAll 쓰다가 1번 틀림
예전에 정규식 문제를 많은 조건 분기로 풀어본 적 있었는데 사람 할 짓이 아닌것 같아 이번에는 라이브러리로 해결
 */

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T --> 0) {
            String signal = br.readLine();
            sb.append(signal.matches("(100+1+|01)+") ? "YES" : "NO").append('\n');
        }
        System.out.print(sb);
    }
}
