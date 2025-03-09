// 	18324KB 188ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 같은 문자로 시작하는 단어 중 길이가 긴 단어부터
// 현재 접두사X집합에 속한 단어의 접두사인지 확인
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String[] words = new String[n];
        for (int i = 0; i < n; i++) {
            words[i] = br.readLine();
        }

        // 단어의 첫번째 글자 사전순, 단어의 길이 내림차순으로 stable sort
        Arrays.sort(words, (str1, str2) -> str1.charAt(0) != str2.charAt(0)  ? str1.charAt(0) - str2.charAt(0) : str2.length() - str1.length());

        String[] nonPrefixes = new String[n];
        nonPrefixes[0] = words[0];
        int size = 1;
        for (int i = 1; i < n; i++) {
            boolean isPrefix = false;
            for (int ti = 0; ti < size; ti++) {
                // 나보다 빠른 단어 무시
                if (nonPrefixes[ti].charAt(0) < words[i].charAt(0)) {
                    continue;
                }

                // 나보다 느린 단어 만나면 종료
                if (nonPrefixes[ti].charAt(0) > words[i].charAt(0)) {
                    break;
                }

                if (startWith(nonPrefixes[ti], words[i])) {
                    isPrefix = true;
                    break;
                }
            }

            if (!isPrefix) {
                nonPrefixes[size++] = words[i];
            }
        }

        System.out.println(size);
    }

    static boolean startWith(String word, String prefix) {
        for (int i = 0; i < prefix.length(); i++) {
            if (word.charAt(i) != prefix.charAt(i)) {
                return false;
            }
        }

        return true;
    }
}
