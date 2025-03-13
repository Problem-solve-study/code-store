// 11528KB	80ms
import java.io.*;
import java.util.*;
/*
 * 부분집합을 통한 완전탐색 구현을 통해 count. 단 sum 값이 0일 때는 공집합도 포함되기 때문에 이는 제외.
 * test case가 매우 친절했음.
 */
public class Main{
    static int N, S, cnt;
    static boolean[] visited;
    static int[] arr;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        cnt = 0;
        
        
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken());

        dfs(0, 0);
        if(S==0) cnt--;
        System.out.println(cnt);
    }

    static void dfs(int idx, int sum){
        if(idx==N){
            if(sum==S)  cnt++;
            return;
        }
        dfs(idx+1, sum+arr[idx]);
        dfs(idx+1, sum);
    }
}
