import java.io.*;
import java.util.*;
public class Main {
    static int A,B;
    static int minCnt;
    public static void main(String[] args)throws IOException {
        Scanner sc = new Scanner(System.in);
        A = sc.nextInt();
        B = sc.nextInt();

        minCnt = Integer.MAX_VALUE;
        dfs(A, 1);
        if (minCnt == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(minCnt);
        sc.close();
    }
    public static void dfs(long num, int idx){
        if (num >= B){
            if (num == B)
                minCnt = Math.min(minCnt, idx);
            return ;
        }

        dfs(num * 2, idx + 1);
        dfs(Long.parseLong(num + "1"), idx + 1);
    }
}
