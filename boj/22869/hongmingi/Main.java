// 13064KB	144ms
import java.io.*;
import java.util.*;
/*
 * DP 연습문제. 건너갈 수 있는 돌인지를 boolean 배열로 선언해놓고 차례로 갈 수 있는지 확인하는 형태로 구현함.
 */

public class Main{
    static int N, K;
    static int[] stones;
    static boolean[] canMove;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        stones = new int[N];
        canMove = new boolean[N];
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            stones[i] = Integer.parseInt(st.nextToken());
        }
        int t=1;
        int l, r;
        canMove[0] = true;

        boolean move = true;

        while(t<N){
            r = stones[t];

            for(int i=0; i<t; i++){
                if(!canMove[i]) continue;
                l = stones[i];
                if((t-i)*(1+Math.abs(stones[t]-stones[i]))<=K){
                    canMove[t] = true;
                    break;
                }
            }

            t++;
        }
        if(canMove[N-1]){
            System.out.println("YES");
        }else{
            System.out.println("NO");
        }
    }
}
