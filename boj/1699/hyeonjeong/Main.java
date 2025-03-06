// 11792, 212ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    static final int MAX_NUMBER = 100_000;
    static final int SQUARE_SIZE = (int) Math.sqrt(MAX_NUMBER);

    static boolean[] numbers = new boolean[MAX_NUMBER + 1];
    static int[] squares = new int[SQUARE_SIZE];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        for (int i = 0; i < SQUARE_SIZE; i++) {
            squares[i] = (i + 1) * (i + 1);
            numbers[squares[i]] = true;
        }
        
        int target = Integer.parseInt(br.readLine());

        int count = 1;
        for (int i = 0; i < SQUARE_SIZE; i++) {
            if (numbers[target]) {
                break;
            }

            search();
            
            count++;
        }

        System.out.println(count);
    }

    static void search() {
        for (int n = MAX_NUMBER; n > 0; n--) {
            for (int square : squares) {
                if (numbers[n] == false)
                    continue;

                int next = n + square;
                if (next <= MAX_NUMBER && numbers[next] == false) {
                    numbers[next] = true;
                }
            }
        }
    }
}
