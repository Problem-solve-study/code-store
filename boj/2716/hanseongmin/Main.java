import java.io.*;

/*
11436KB, 60ms

스택과 트리를 적절히 활용해 직접 값을 구하려고 하면 문제 난이도가 상당히 올라감
트리를 좀 그려보면 덩굴이 나눠지는 지점에서의 값이 2의 배수 꼴로 나타나는걸 볼 수 있음
따라서 괄호를 보며 최대 깊이를 구한 후 깊이만큼의 2의 거듭제곱을 출력하면 됨
 */

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (N --> 0) {
            String str = br.readLine();
            int ans = 1;
            int d = 0;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '[') d++;
                else d--;
                ans = Math.max(ans, (1 << d));
            }
            sb.append(ans).append('\n');
        }
        System.out.print(sb);
    }
}
