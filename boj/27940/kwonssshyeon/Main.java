// 	231800KB	532ms
import java.io.*;
import java.util.*;

/**
 * 1층은 항상 비가 오기 때문에, 1층을 기준으로만 확인하면 된다.
 */
public class Main {
    static int n,m,k;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        int answer  = -1;
        long amount = 0L;
        int[] num = new int[m+1];
        for (int i=1; i<=m; i++) {
            st = new StringTokenizer(br.readLine());
            Integer.parseInt(st.nextToken());
            num[i] = Integer.parseInt(st.nextToken());
        }
        for (int i=1; i<=m; i++) {
            amount += num[i];
            if (amount > k) {
                answer = i;
                break;
            }
        }
        if (answer == -1) {
            System.out.println(answer);
        }
        else {
            System.out.println(answer+" "+1);
        }
    }
}