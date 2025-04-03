// 11492KB 68ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final int TOP = 0;
    static final int UPPER = 1;
    static final int MIDDLE = 3;
    static final int LOWER = 4;
    static final int BOTTOM = 6;

    static final boolean[][] digits = {
        {true, true, true, false, true, true, true},
        {false, false, true, false, false, true, false},
        {true, false, true, true, true, false, true},
        {true, false, true, true, false, true, true},
        {false, true, true, true, false, true, false},
        {true, true, false, true, false, true, true},
        {true, true, false, true, true, true, true},
        {true, false, true, false, false, true, false},
        {true, true, true, true, true, true, true},
        {true, true, true, true, false, true, true}
    };

    static int size;
    static char[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        size = Integer.parseInt(st.nextToken());
        numbers = st.nextToken().toCharArray();
        
        StringBuilder sb = new StringBuilder();

        for (int height = 0; height < 5; height++) {
            // 가로획
            // 숫자마다 size 만큼 그리기
            if (height == 0 || height == 2 || height == 4) {
                for (int number : numbers) {
                    number -= '0';
                    
                    sb.append(' ');

                    int digit = -1;
                    switch (height) {
                        case 0: digit = TOP; break;
                        case 2: digit = MIDDLE; break;
                        case 4: digit = BOTTOM; break;
                    }

                    if (digits[number][digit]) {
                        for (int i = 0; i < size; i++) {
                            sb.append('-');
                        }
                    } else {
                        for (int i = 0; i < size; i++) {
                            sb.append(' ');
                        }
                    }

                    sb.append("  ");
                }

                sb.append('\n');
                continue;
            }

            // 세로획
            // size만큼 숫자마다 그리기
            for (int i = 0; i < size; i++) {
                for (int number : numbers) {
                    number -= '0';

                    int digit = -1;
                    switch (height) {
                        case 1: digit = UPPER; break;
                        case 3: digit = LOWER; break;
                    }

                    sb.append(digits[number][digit] ? '|' : ' ');
                    for (int j = 0; j < size; j++) {
                        sb.append(' ');
                    }
                    sb.append(digits[number][digit + 1] ? '|' : ' ');
                    sb.append(' ');
                }
                sb.append('\n');
            }
        }
    
        System.out.print(sb);
    }
}
