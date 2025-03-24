//제출번호: 91904446
//메모리:   11512 KB
//실행시간: 68 ms
import java.io.*;

//펠린드롬을 만들기 위해서는 문자열이 홀수 길이일 때는 각 알파벳 개수 중 하나만 홀수여야 하고
//문자열이 짝수길이일 때는 각 알파벳 개수가 모두 짝수여야 함
//그리고 사전 순으로 잘 배치하면 됨
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String name = br.readLine();
        int[] alphabets = new int[26];

        int len = name.length();
        for (int i = 0; i < len; i++) {
            //알파벳 개수를 세어둠
            alphabets[name.charAt(i) - 'A']++;
        }

        boolean isPossible = true; //펠린드롬을 만들 수 있는 지를 판단하는 변수
        int insertPos = 0; //다음에 펠린드롬 알파벳을 저장할 위치

        char[] palindrome = new char[len]; //문자열 길이만큼 만듦
        for (int i = 0; i < 26; i++) {
            char alphabet = (char) (i + 65);

            //만약 지금 문자가 홀수 개 있다면
            if ((alphabets[i] & 1) == 1) {
                //만약 문자열의 길이가 짝수거나
                //펠린드롬 문자열의 가운데에 이미 다른 문자가 있다면 (홀수 개인 문자가 2개 이상이라면)
                //펠린드롬 문자열을 만들 수 없음
                if ((len & 1) == 0 || palindrome[len >> 1] > 64) {
                    isPossible = false;
                    break;
                }

                //지금 문자을 가운데에 배치함
                alphabets[i]--;
                palindrome[len >> 1] = alphabet;
            }

            //펠린드롬 양 쪽에 배치할 개수를 구하고
            int adder = alphabets[i] >> 1;
            //양쪽에 배치함
            for (int j = 0; j < adder; j++) {
                palindrome[insertPos + j] = palindrome[len - insertPos - j - 1] = alphabet;
            }
            insertPos += adder;
        }

        StringBuilder sb = new StringBuilder();
        //만약 만들 수 있다면 펠린드롬 문자열 출력, 만들 수 없다면 불가능하다고 출력
        if (isPossible) {
            for (int i = 0; i < len; i++) {
                sb.append(palindrome[i]);
            }
        } else {
            sb.append("I'm Sorry Hansoo");
        }

        System.out.print(sb);
    }
}