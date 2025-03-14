//문제: BOJ 1182번
//메모리: 11528 KB
//시간: 76 ms

/*
 * 부분집합으로 해결하였다.
 * s가 0일 경우 공집합을 제외해야 하는 것을 늦게 생각해 조금 헤맸다.
 */

import java.io.*;
import java.util.*;

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int n, s, result;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        n = nextInt();
        s = nextInt();
        arr = new int[n];
        for(int i=0;i<n;i++)
            arr[i]=nextInt();
        subset(0,0);
        System.out.println(result - (s==0?1:0)); //s가 0이면 공집합 제외
    }
    static void subset(int depth, int sum){
        if(depth==n){
            if(sum==s)result++;
            return;
        }
        subset(depth+1,sum+arr[depth]);
        subset(depth+1,sum);
    }
    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
