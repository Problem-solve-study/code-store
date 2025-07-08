import java.io.*;
import java.util.*;

/**
 * 12408KB	116ms
 * 가능한 모든 경우의 수 완탐 (but 좌표 크기가 크므로 별똥별 존재 가능한 좌표에 모두 넣어보기)
 */

public class Main {
    static int MAX = 500000;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N, M, L, K;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[][] sstar = new int[2][K];

        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            sstar[0][i] = Integer.parseInt(st.nextToken());
            sstar[1][i] = Integer.parseInt(st.nextToken());
        }
        int max_pop = 0;
        
        for(int i = 0; i < K; i++){
            int x = sstar[0][i];
            for(int j = 0; j < K; j++){
                int y = sstar[1][j];
                int cnt = 0;
                for(int s = 0; s < K; s++){
                    int sx = sstar[0][s];
                    int sy = sstar[1][s];
                    if(sx >= x && sx <= x + L && sy >= y && sy <= y + L){
                        cnt++;
                    }
                }
                max_pop = Math.max(max_pop, cnt); // 가장 많이 튕겨졌을 때 찾기
            }
        }

        System.out.println(K - max_pop);
    }
}
