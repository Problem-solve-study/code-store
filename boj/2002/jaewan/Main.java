// 11780 KB, 76 ms
/*
 * 원래 순서 a b c d e
 * 보다 먼저 나오는 놈들은 추월을 한 녀석들임.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayDeque<String> q = new ArrayDeque<>();
        for (int i = 0; i < N; i++)
            q.offer(br.readLine());

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            // 원래 나와야 하는 녀석이 아니면 추월한 녀석
            String inStr = br.readLine();
            if (!q.peek().equals(inStr))
                cnt++;
            q.remove(inStr);
        }
        System.out.println(cnt);
    }
}