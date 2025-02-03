// 11604KB, 68ms

import java.util.*;
import java.io.*;
import java.lang.Exception;

public class Main {
    static final int HOR = 0;
    static final int VER = 1;
    static final int DIAG = 2;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++)
                matrix[i][j] = Integer.parseInt(st.nextToken());
        }
        
        int[][][] memory = new int[n][n][3];
        memory[0][1][0] = 1;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j != n-1 && matrix[i][j+1] != 1)
                    memory[i][j+1][HOR] += memory[i][j][HOR] + memory[i][j][DIAG];

                if (i != n-1 && matrix[i+1][j] != 1)
                    memory[i+1][j][VER] += memory[i][j][VER] + memory[i][j][DIAG];

                if (i != n-1 && j != n-1 && matrix[i+1][j] != 1 && matrix[i][j+1] != 1 && matrix[i+1][j+1] != 1)
                    memory[i+1][j+1][DIAG] += memory[i][j][HOR] + memory[i][j][VER] + memory[i][j][DIAG];
            }
        }

        int sum = 0;
        for (int i = 0; i < 3; i++)
            sum += memory[n-1][n-1][i];
        System.out.println(sum);
    }
}