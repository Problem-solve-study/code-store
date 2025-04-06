import java.io.*;

/*
30116KB, 192ms

조건 2에 의해 재귀적으로 계속하여 팰린드롬인지 판별하는 문제
조건 1에 의해 S가 팰린드롬이라면 접두사와 접미사가 동일하다는 의미이므로 접미사는 볼 필요가 없음
 */

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        System.out.print(dq(s) ? "AKARAKA" : "IPSELENTI");
    }

    static boolean dq(String s) {
        //기저 조건 설정
        if (s.length() == 1) return true;
        //S의 팰린드롬 여부 판별
        if (!isPalindrome(s)) return false;
        int len = s.length();
        //S가 팰린드롬이므로 접두사만 보면 됨
        return dq(s.substring(0, len / 2));
    }

    static boolean isPalindrome(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) return false;
        }

        return true;
    }
}
