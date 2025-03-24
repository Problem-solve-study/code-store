
/*
 * 펠린드롬, 중점을 기준으로 대칭이 돼야 한다.
 * 종류별로 갯수를 세었을 때, 홀수 개인 알파벳이 1개 이하여야 함.
 * 알파벳 순으로, /2개만큼 순서대로 배치하면 펠린드롬 완성!
 * 
 */
import java.io.IOException;

public class Main {
    static int[] alphabet = new int[26];

    public static void main(String[] args) throws IOException {

        int t;
        while ((t = System.in.read()) > 64) {
            alphabet[t - 'A']++;
        }

        if (!func()) {
            System.out.println("I'm Sorry Hansoo");
        }
    }

    static boolean func() {
        int oddCnt = 0, oddIdx = -1;
        for (int i = 0; i < 26; i++)
            if ((alphabet[i] & 1) == 1) {
                oddCnt++;
                oddIdx = i;
            }
        if (oddCnt > 1)
            return false;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {

            for (int j = 0; j < alphabet[i] / 2; j++)
                sb.append((char) (i + 'A'));

        }

        if (oddCnt == 1)
            sb.append((char) (oddIdx + 'A'));

        for (int i = 25; i >= 0; i--) {

            for (int j = 0; j < alphabet[i] / 2; j++)
                sb.append((char) (i + 'A'));
        }
        System.out.println(sb.toString());
        return true;
    }

}