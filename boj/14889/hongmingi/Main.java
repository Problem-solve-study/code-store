// 16544KB	444ms : Bit Masking
// 18896KB  396ms : DFS
import java.io.*;
import java.util.*;
/*
 * 백트래킹 연습문제.
 * 처음에는 dfs를 통해 완탐하면서 최솟값을 찾았는데 풀고나서 생각을 해보니 오늘 배운 비트마스킹을 활용해서
 * 풀수도 있겠다고 생각해서 비트마스킹으로도 다시 풀어봄.
 * N 크기가 작아서 그런지 모르겠는데 DFS가 비트마스킹 대비 메모리는 조금 더 먹어도 시간 자체는 더 빨랐랐음.
 */

public class Main{
    static int N, balance;
    static int[][] stat;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        stat = new int[N][N];
        StringTokenizer st;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                stat[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        balance = Integer.MAX_VALUE;
        int team = 1<<N;
        for(int t=0; t<team; t++){
            if(Integer.bitCount(t)!=N/2)    continue;
            int start = 0;
            int link = 0;
            for(int i=0; i<N; i++){
                if((t&(1<<i))!=0){
                    for(int j=0; j<N; j++){
                        if(j!=i && (t&(1<<j))!=0){
                            start+=stat[i][j];
                        }
                    }
                }else{
                    for(int j=0; j<N; j++){
                        if(j!=i && (t&(1<<j))==0){
                            link+=stat[i][j];
                        }
                    }
                }
            }
            
            balance = Math.min(Math.abs(start-link), balance);
        }
        System.out.println(balance);
    }

}

// public class Main{
//     static int N, balance;
//     static int[][] stat;
//     static boolean[] team;
//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         N = Integer.parseInt(br.readLine());
//         stat = new int[N][N];
//         team = new boolean[N];
//         StringTokenizer st;
//         for(int i=0; i<N; i++){
//             st = new StringTokenizer(br.readLine());
//             for(int j=0; j<N; j++){
//                 stat[i][j] = Integer.parseInt(st.nextToken());
//             }
//         }

//         int cnt = 0;
//         balance = Integer.MAX_VALUE;
//         int idx = 0;
//         dfs(idx, cnt);

//         System.out.println(balance);
//     }

//     static void dfs(int idx, int cnt){
        // if(cnt==N/2){
        //     int start = 0;
        //     int link = 0;
        //     for(int i=0; i<N; i++){
        //         if(team[i]){
        //             for(int j=0; j<N; j++){
        //                 if(j!=i && team[j]){
        //                     start+=stat[i][j];
        //                 }
        //             }
        //         }else{
        //             for(int j=0; j<N; j++){
        //                 if(j!=i && !team[j]){
        //                     link+=stat[i][j];
        //                 }
        //             }
        //         }
        //     }

        //     balance = Math.min(Math.abs(start-link), balance);
//             return;
//         }

//         if(idx>=N)   return;
        
//         team[idx] = true;
//         dfs(idx+1, cnt+1);
//         team[idx] = false;
//         dfs(idx+1, cnt);
        
//     }
// }