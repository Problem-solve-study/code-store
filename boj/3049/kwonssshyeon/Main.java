// 	11524KB	64ms
import java.io.*;
import java.util.*;

/**
 * 사각형을 1개 만들때마다 대각선이 2개 -> 교차하는 점 1개가 생긴다.
 * => 고를 수 있는 사각형의 개수
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int comb[][] = new int[101][101];
        comb[0][0] = 1;
        for (int i=1; i<=100; i++) {
            for (int j=0; j<=100; j++) {
                if (j == 0) comb[i][j] = 1;
                else comb[i][j] = comb[i-1][j-1] + comb[i-1][j];
            }
        }
        System.out.println(comb[n][4]);
    }
}
