// 34064 KB, 388 ms
/*
 * 문자열을 규칙에 따라 정렬
 * ComparaTo 정의해서 그냥 정렬하자
 * O(N log N) 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Data[] data = new Data[N];
        for (int i = 0; i < N; i++)
            data[i] = new Data(br.readLine().toCharArray());

        Arrays.sort(data);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++)
            sb.append(String.valueOf(data[i].chars)).append('\n');
        System.out.print(sb);
    }

    static class Data implements Comparable<Data> {
        char[] chars;

        public Data(char[] chars) {
            this.chars = chars;
        }

        @Override
        public int compareTo(Main.Data o) {
            int pA = 0, pB = 0, lenA = this.chars.length, lenB = o.chars.length;

            while (true) {
                // 다 같았으면 짧은 게 먼저
                if (pA == lenA)
                    return -1;
                if (pB == lenB)
                    return 1;

                // 한쪽만 숫자면 숫자열이 먼저
                if (Character.isDigit(chars[pA]) &&
                        Character.isAlphabetic(o.chars[pB])) {
                    return -1;
                }
                if (Character.isAlphabetic(chars[pA]) &&
                        Character.isDigit(o.chars[pB]))
                    return 1;

                // 둘 다 문자
                if (Character.isAlphabetic(chars[pA]) &&
                        Character.isAlphabetic(o.chars[pB])) {
                    // 같으면 넘어감
                    if (chars[pA] == o.chars[pB]) {
                        pA++;
                        pB++;
                        continue;
                    }

                    // 같은 문자의 대, 소문자면 대문자 먼저
                    if (Character.toUpperCase(chars[pA]) == Character.toUpperCase(o.chars[pB]))
                        return Character.compare(chars[pA], o.chars[pB]);

                    // 다른 문자면 순서대로
                    return Character.compare(
                            Character.toUpperCase(chars[pA]),
                            Character.toUpperCase(o.chars[pB]));
                }

                // 둘 다 숫자
                if (Character.isDigit(chars[pA]) && Character.isDigit(o.chars[pB])) {
                    // 숫자 시작 위치 저장
                    int startA = pA, startB = pB;

                    // 연속된 숫자 찾기
                    while (pA < lenA && Character.isDigit(chars[pA]))
                        pA++;
                    while (pB < lenB && Character.isDigit(o.chars[pB]))
                        pB++;

                    // 전체 길이 (0 포함)
                    int totalLenA = pA - startA;
                    int totalLenB = pB - startB;

                    // 앞의 0 제거
                    while (startA < pA && chars[startA] == '0')
                        startA++;
                    while (startB < pB && o.chars[startB] == '0')
                        startB++;

                    // 남은 숫자의 길이
                    int lenNumA = pA - startA;
                    int lenNumB = pB - startB;

                    // 길이가 다르면 짧은 것이 우선 (실제 값이 작은 것)
                    if (lenNumA != lenNumB)
                        return lenNumA - lenNumB;

                    // 같은 길이면 앞에서부터 비교
                    for (int i = 0; i < lenNumA; i++) {
                        if (chars[startA + i] != o.chars[startB + i]) {
                            return chars[startA + i] - o.chars[startB + i];
                        }
                    }

                    // 숫자가 같으면 전체 길이로 비교 (앞의 0이 적은 것이 우선)
                    if (totalLenA != totalLenB)
                        return totalLenA - totalLenB;
                }
            }
        }

    }
}
