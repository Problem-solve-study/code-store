// 11600KB 64ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

class Main {
    static final int MAX = 1000000 * 10;

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int n;
    static int[][] matrix;
    static int[][] cost;

    public static void main(String[] args) throws IOException {
        n = nextInt();
        matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = nextInt();
            }
        }
        
        cost = new int[n][(int) Math.pow(2, n)];
        visit(0, 1);

        System.out.println(cost[0][1]);
    }

    static int visit(int curr, int visited) {
        if (visited == Math.pow(2, n) - 1) {
            if (matrix[curr][0] == 0) {
                return MAX;
            }
            return matrix[curr][0];
        }

        if (cost[curr][visited] > 0) {
            return cost[curr][visited];
        }

        int min = MAX;
        for (int next = 1; next < n; next++) {
            if ((visited & (1 << next)) > 0 || matrix[curr][next] == 0) {
                continue;
            }

            int nextCost = visit(next, visited | (1 << next)) + matrix[curr][next];

            if (nextCost < min) {
                min = nextCost;
            }
        }

        cost[curr][visited] = min;

        return cost[curr][visited];
    }
    
    static void display() {
        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(cost[i]));
        }
        System.out.println();
    }

    static int nextInt() throws IOException {
        st.nextToken();

        return (int) st.nval;
    }
}
