import java.io.*;

/*
11492KB, 60ms

그냥 문제에서 주어진대로 구현만 하면 됨
키보드를 하나의 문자열로 만들어버리고 indexOf - 1의 문자를 출력하는 형태로 구현
 */

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String keyboard = "`1234567890-=QWERTYUIOP[]\\ASDFGHJKL;'ZXCVBNM,./";
        StringBuilder ans = new StringBuilder();
        String input;
        while ((input = br.readLine()) != null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < input.length(); i++) {
                char curChar = input.charAt(i);
                if (curChar == ' ') {
                    sb.append(curChar);
                } else {
                    sb.append(keyboard.charAt(keyboard.indexOf(curChar) - 1));
                }
            }
            ans.append(sb).append('\n');
        }
        System.out.print(ans);
    }
}
