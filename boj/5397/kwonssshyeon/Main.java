//	153672KB	2344ms
import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t=0; t<T; t++) {
            String input = br.readLine();
            int cursor = 0, length = 0;
            LinkedList<Character> password = new LinkedList<>();
            for (int i=0; i<input.length(); i++) {
                char c = input.charAt(i);
                if (c == '>') {
                    cursor = Math.min(length, cursor + 1);
                } else if (c == '<') {
                    cursor = Math.max(0, cursor - 1);
                } else if (c == '-') {
                    if (cursor - 1 < 0) continue;
                    password.remove(cursor-1);
                    length--;
                    cursor--;
                } else {
                    password.add(cursor++, c);
                    length++;
                }
            }
            for (char p : password) {
                answer.append(p);
            }
            answer.append("\n");
        }
        System.out.print(answer);
    }
}