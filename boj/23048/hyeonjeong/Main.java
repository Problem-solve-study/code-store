// 327860KB 2064ms
// -> 	28236KB 212ms

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

// 에라토스테네스의 체처럼 소수부터 그의 배수를 같은 수로 업데이트
// sysout이랑 bw랑,,, String.format이랑 StringBuilder가,, 차이가 많이 나네요
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] colors = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            colors[i] = i;
        }

        int color;
        int count = 1;
        for (int i = 2; i < n + 1; i++) {
            // 소수가 아니면 무시
            if (colors[i] != i) {
                continue;
            }

            // 소수를 기반으로 업데이트
            colors[i] = ++count;
            color = i * 2;
            while (color < n + 1) {
                colors[color] = colors[i];
                color += i;
            }
        }

        // bw.write(String.format("%d\n", count));
        // for (int i = 1; i <= n; i ++) {
        //     bw.write(String.format("%d ", colors[i]));
        // }
        // bw.flush();

        StringBuilder sb = new StringBuilder();
        sb.append(count).append('\n');
    	for (int i = 1; i <= n; i++) {
    		sb.append(colors[i]).append(' ');
    	}
        
    	System.out.println(sb);
    }
}
