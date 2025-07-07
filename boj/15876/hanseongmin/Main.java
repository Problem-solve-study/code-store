import java.io.*;
import java.util.*;

/*
18152KB, 176ms

이진수 문자열을 구하여 시뮬레이션하기
수학으로도 풀 수 있는듯 하지만 n이 작길래그냥 시뮬레이션으로 품
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws Exception {
        int n = nextInt(); int k = nextInt();
        ArrayList<Character> list = new ArrayList<>();
        int cnt = 0;
        //0부터 시작
        int number = 0;
        int strIdx = 0;
        String numberString = Integer.toBinaryString(number);
        //진수가 말해야하는 숫자 5개를 찾기 전까지 반복
        while (list.size() < 5) {
            //현재 문자열을 모두 봤다면 다음 문자열로 교체
            if (strIdx == numberString.length()) {
                numberString = Integer.toBinaryString(++number);
                strIdx = 0;
            }

            char cur = numberString.charAt(strIdx++);
            //진수 차례가 왔으면 list에 추가
            if ((cnt % n + 1) == k) {
                list.add(cur);
            }
            cnt++;
        }

        //숫자 5개 출력
        StringBuilder sb = new StringBuilder();
        list.forEach(c -> sb.append(c).append(' '));
        System.out.print(sb);
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
