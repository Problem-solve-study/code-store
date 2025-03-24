// 11532KB 64ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 짝수면 홀수개 문자 0개, 홀수면 홀수개 문자 1개까지 가능
// A부터 앞뒤로 붙여서 재구성
public class Main {
    static final String fail = "I'm Sorry Hansoo";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int[] count = new int['Z' - 'A' + 1];
        
        String name = br.readLine();
        for (Character ch : name.toCharArray()) {            
            count[ch - 'A']++;
        }

        int oddCount = 0;
        for (int i = 0; i < 'Z' - 'A'; i++) {
            if (count[i] % 2 == 1) {
                oddCount++;
            }
        }

        if (oddCount > 1) {
            System.out.println(fail);
            return;
        }

        if (name.length() % 2 == 0 && oddCount == 1) {
            System.out.println(fail);
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= 'Z' - 'A'; i++) {
            for (int j = 0; j < count[i] / 2; j++) {
                sb.append((char) ('A' + i));
            }
        }

        System.out.print(sb.toString());

        StringBuilder resultSb = new StringBuilder();
        for (int i = 0; i <= 'Z' - 'A'; i++) {
            if (count[i] % 2 == 1) {
                System.out.print((char) ('A' + i));
            }
        }

        System.out.print(sb.reverse().toString());
    }
}
