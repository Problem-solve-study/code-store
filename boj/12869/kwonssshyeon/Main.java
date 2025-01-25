import java.util.*;
import java.io.*;

class Main {
    static int[][][] memo = new int[61][61][61];
    static int[][] attacks = {{9,3,1},{9,1,3},
                             {3,9,1},{3,1,9},
                             {1,9,3},{1,3,9}};
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] hp = new int[3];
        for (int i=0; i<n; i++) {
            hp[i] = Integer.parseInt(st.nextToken());
        }
        fillArray();
        dfs(hp[0], hp[1], hp[2], 0);

        System.out.println(answer);
    }

    static void fillArray() {
        for (int i=0; i<=60; i++) {
            for (int j=0; j<=60; j++) {
                for (int k=0; k<=60; k++) {
                    memo[i][j][k] = 60;
                }
            }
        }
    }

    static int answer = 60;
    static void dfs(int hp1, int hp2, int hp3, int count) {

        if (memo[hp1][hp2][hp3] <= count) return;
        
        if (answer < count) return;

        if (hp1 == 0 && hp2 == 0 && hp3 == 0) {
            answer = Math.min(answer, count);
            return;
        }

        memo[hp1][hp2][hp3] = count;

        for (int i=0; i<6; i++){
            int n1 = Math.max(hp1-attacks[i][0],0);
            int n2 = Math.max(hp2-attacks[i][1],0);
            int n3 = Math.max(hp3-attacks[i][2],0);
            dfs(n1, n2, n3, count+1);
        }
    }
}
