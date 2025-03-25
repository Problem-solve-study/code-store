// 35960KB 416ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// int와 int의 곱은,, int네요,,
public class Main {
    static final int MAX = 1_000_000_000;
    static final int MIN = 1_000_000_000 * -1;
    static String[] commands = new String[100_000];
    static int ctop;
    static int[] stack = new int[1000];
    static int top;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String command = br.readLine();
        while (!command.equals("QUIT")) {
            ctop = 0;
            
            while (!command.equals("END")) {
                commands[ctop++] = command;
                command = br.readLine();
            }
            
            int n = Integer.parseInt(br.readLine());
            for (int i = 0; i < n; i++) {
                int num = Integer.parseInt(br.readLine());
                
                boolean succed = simulate(num);
                
                if (!succed || top != 1) {
                    System.out.println("ERROR");
                } else {
                    System.out.println(stack[0]);
                }
            }
            System.out.println();

            br.readLine();
            command = br.readLine();
        }
    }

    static boolean simulate(int initValue) {
        top = 0;

        stack[top++] = initValue;

        for (int i = 0; i < ctop; i++) {
            String command = commands[i];

            if (command.startsWith("NUM")) {
                int x = Integer.parseInt(command.split(" ")[1]);
                stack[top++] = x;
                continue;
            }

            if (command.equals("POP")) {
                if (top == 0) {
                    return false;
                }

                top--;
                continue;
            }

            if (command.equals("INV")) {
                if (top == 0) {
                    return false;
                }

                stack[top - 1] *= -1;
                continue;
            }

            if (command.equals("DUP")) {
                if (top == 0) {
                    return false;
                }

                int topValue = stack[top - 1];
                stack[top++] = topValue;
                continue;
            }

            if (command.equals("SWP")) {
                if (top < 2) {
                    return false;
                }

                int temp = stack[top - 1];
                stack[top - 1] = stack[top - 2];
                stack[top - 2] = temp;
                continue;
            }

            if (command.equals("ADD")) {
                if (top < 2) {
                    return false;
                }

                int result = stack[top - 1] + stack[top - 2];
                if (result > MAX || result < MIN) {
                    return false;
                }

                top -= 2;
                stack[top++] = result;
                continue;
            }

            if (command.equals("SUB")) {
                if (top < 2) {
                    return false;
                }

                int result = stack[top - 2] - stack[top - 1];
                if (result > MAX || result < MIN) {
                    return false;
                }

                top -= 2;
                stack[top++] = result;
                continue;
            }

            if (command.equals("MUL")) {
                if (top < 2) {
                    return false;
                }

                long result = stack[top - 2] * (long) stack[top - 1];
                if (result > MAX || result < MIN) {
                    return false;
                }

                top -= 2;
                stack[top++] = (int) result;
                continue;
            }

            if (command.equals("DIV")) {
                if (top < 2) {
                    return false;
                }

                if (stack[top - 1] == 0) {
                    return false;
                }

                int result = abs(stack[top - 2]) / abs(stack[top - 1]);
                if (stack[top - 2] * (long) stack[top - 1] < 0) {
                    result *= -1;
                }

                if (result > MAX || result < MIN) {
                    return false;
                }

                top -= 2;
                stack[top++] = result;
                continue;
            }

            if (command.equals("MOD")) {
                if (top < 2) {
                    return false;
                }

                if (stack[top - 1] == 0) {
                    return false;
                }

                int result = abs(stack[top - 2]) % abs(stack[top - 1]);
                if (stack[top - 2] < 0) {
                    result *= -1;
                }

                if (result > MAX || result < MIN) {
                    return false;
                }

                top -= 2;
                stack[top++] = result;
                continue;
            }
        }

        return true;
    }

    static int abs(int x) {
        if (x >= 0) {
            return x;
        }

        return x * -1;
    }
}
