import java.io.*;
import java.util.*;

/*
22936KB, 136ms

괄호 -> 스택 문제

()가 올바른 괄호 문자열일 때, 괄호 내부에 있는 문자열도 올바를 경우 전체 문자열이 올바른 괄호 문자열이므로
먼저 문자열을 한 번 순회하며 어떤 위치가 올바른 괄호 문자열인지를 boolean 배열에 체크하고
boolean 배열을 순회하며 연속되면서 가장 긴 true의 길이를 출력하면 된다.
 */

public class Main {
    static class Data {
        char c;
        int idx;

        public Data(char c, int idx) {
            this.c = c;
            this.idx = idx;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String str = br.readLine();

        int res = 0;
        boolean[] arr = new boolean[n];
        //먼저 스택으로 문자열을 순회하며 어떤 위치가 올바른지를 마킹
        //isEmpty 쓰기 귀찮아서 더미 데이터 하나 삽입
        ArrayDeque<Data> s = new ArrayDeque<>(); s.add(new Data('.', -1));
        for (int i = 0; i < n; i++) {
            char cur = str.charAt(i);
            //여는 괄호라면 현재 위치와 함께 데이터 삽입
            if (cur == '(') {
                s.addLast(new Data('(', i));
            } else {
                //올바른 경우라면 두 위치를 배열에 마킹
                if (s.peekLast().c == '(') {
                    Data d = s.removeLast();
                    arr[d.idx] = arr[i] = true;
                }
            }
        }

        //이후 배열에 기록된 정보를 순회하며 가장 긴 true의 길이를 구한다.
        int len = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i]) {
                len++;
                res = Math.max(res, len);
            } else {
                len = 0;
            }
        }

        System.out.print(res);
    }
}
