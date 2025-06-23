import java.io.*;
import java.util.*;

/*
37364KB, 248ms

시작 문자, 끝 문자, 문자들의 개수로 compare 함수 구성으로 트리맵에 넣어버리기.
이후 문장이 입력되면 단어별로 잘라서 map에 같은 의미로 해석될 수 있는 단어들의 개수를 찾아 곱하면 된다.
 */

public class Main {
    static TreeMap<Word, Integer> map = new TreeMap<>();
    static class Word implements Comparable<Word> {
        char s;
        char e;
        int[] alphabetCnt = new int[26];

        public Word(String word) {
            //시작 문자, 끝 문자, 알파벳의 개수 저장
            s = word.charAt(0);
            e = word.charAt(word.length() - 1);
            for (char c : word.toCharArray()) {
                if (Character.isUpperCase(c)) {
                    alphabetCnt[c - 'A']++;
                } else {
                    alphabetCnt[c - 'a']++;
                }
            }
        }

        @Override
        public int compareTo(Word o) {
            int comp1 = Character.compare(s, o.s);
            if (comp1 != 0) return comp1;
            int comp2 = Character.compare(e, o.e);
            if (comp2 != 0) return comp2;
            for (int i = 0; i < 26; i++) {
                int comp = Integer.compare(alphabetCnt[i], o.alphabetCnt[i]);
                if (comp != 0) return comp;
            }
            return 0;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        //사전에 등재된 단어들을 map에 등록
        for (int i = 0; i < N; i++) {
            Word word = new Word(br.readLine());
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            //문장을 입력받고
            String[] sentence = br.readLine().split(" ");
            int cur = 1;
            //몇 가지로 해석될 수 있는지를 구하여 곱하기
            for (String word : sentence) {
                cur *= map.getOrDefault(new Word(word), 0);
            }
            sb.append(cur).append('\n');
        }
        System.out.print(sb);
    }
}
