import java.io.*;
import java.util.*;

/*
11720KB, 64ms

진짜 실제로 숫자를 영어로 변환한 뒤 정렬하여 출력하기
map을 2개 사용하여 구현
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static HashMap<Character, String> map = new HashMap<>();
    static TreeMap<String, Integer> map2 = new TreeMap<>();
    static {
        map.put('0', "zero"); map.put('1', "one"); map.put('2', "two");
        map.put('3', "three"); map.put('4', "four"); map.put('5', "five");
        map.put('6', "six"); map.put('7', "seven"); map.put('8', "eight");
        map.put('9', "nine");
    }

    public static void main(String[] args) throws Exception {
        int N = nextInt(); int M = nextInt();
        for (int i = N; i <= M; i++) {
            StringBuilder sb = new StringBuilder();
            String cur = String.valueOf(i);
            for (int j = 0; j < cur.length(); j++) {
                sb.append(map.get(cur.charAt(j))).append(' ');
            }
            map2.put(sb.toString(), i);
        }

        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for (int v : map2.values()) {
            sb.append(v);
            cnt++;
            if (cnt == 10) {
                cnt = 0;
                sb.append('\n');
            } else {
                sb.append(' ');
            }
        }
        System.out.print(sb);
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
