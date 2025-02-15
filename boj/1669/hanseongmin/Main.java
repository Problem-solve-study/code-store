import java.io.*;
import java.util.*;

/*
11588KB, 68ms

규칙 찾아 푸는 문제.
1 + 2 + 3 .. + n + n - 1 + n - 2 ... + 1로 이루어진 수열이 있다고 할 때
1) 두 수의 차이가 n^2와 동일하다면 n + n - 1
2) 두 수의 차이가 (n^2, n^2 + n] 범위 내에 있다면 n + n (수열에서 n 이하의 값을 규칙에 맞는 곳에 끼워넣으면 됨)
3) 그 외의 경우에서는 n + n + 1이라는 관찰을 할 수 있다. (2의 결과로 나온 수열에 다시 1 ~ n 사이의 수를 끼워넣어야 함)

차이가 0이라면 0일을 출력함에 유의
 */

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int diff = y - x;

        if (diff == 0) {
            bw.write(String.valueOf(0));
        } else {
            int maxValue = (int) Math.sqrt(y - x);
            if (diff == Math.pow(maxValue, 2)) {
                bw.write(String.valueOf(2 * maxValue - 1));
            } else if (diff <= Math.pow(maxValue, 2) + maxValue) {
                bw.write(String.valueOf(2 * maxValue));
            } else {
                bw.write(String.valueOf(2 * maxValue + 1));
            }
        }

        bw.flush();
    }
}