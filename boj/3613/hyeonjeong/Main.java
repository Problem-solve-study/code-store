// 11556KB 64ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 예외 처리 크아악
public class Main {
    static final int C = 1;
    static final int JAVA = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] variable = br.readLine().toCharArray();

        if (Character.isUpperCase(variable[0]) || variable[0] == '_') {
            System.out.println("Error!");
            return;
        }

        int flag = 0;
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < variable.length) {
            if (variable[i] == '_') {
                if (flag == JAVA) {
                    System.out.println("Error!");
                    return;
                }

                if (i == variable.length - 1) {
                    System.out.println("Error!");
                    return;
                }

                i++;

                if (variable[i] == '_' || Character.isUpperCase(variable[i])) {
                    System.out.println("Error!");
                    return;
                }

                sb.append(Character.toUpperCase(variable[i]));
                flag = C;
            }

            else if (Character.isUpperCase(variable[i])) {
                if (flag == C) {
                    System.out.println("Error!");
                    return;
                }

                sb.append('_');
                sb.append(Character.toLowerCase(variable[i]));
                flag = JAVA;
            }

            else {
                sb.append(variable[i]);
            }

            i++;
        }

        System.out.println(sb);
    }
}
