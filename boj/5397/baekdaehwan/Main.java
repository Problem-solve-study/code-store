/**
 * 255308KB	612ms
 * 
 * [사고 흐름]
 * 일반적으로 잘 알려진 문제입니다. 
 * 스택 두 개를 사용하는 것이 정해라고 알고 있어서 그대로 풀었습니다.
 * 
 * [알고리즘 설명]
 * 가상의 커서가 있다고 가정하겠습니다.
 * 이 커서는 L의 top과 R의 top 사이에 있다고 정의하겠습니다.
 * 이 커서를 움직일 때마다 적절히 양쪽 스택 간에 원소를 옮기면 편집기와 같은 기능을 구현할 수 있습니다.
 * 
 * 코드가 짧으니 추가적인 주석은 적지 않았습니다.
 */

import java.io.*;
import java.util.*;

public class Main {
    static Deque<Character> L;
    static Deque<Character> R;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        StringBuilder res = new StringBuilder();
        for (int i=0; i<N; ++i) {
            String S = br.readLine();
            int len = S.length();

            L = new ArrayDeque<>(len);
            R = new ArrayDeque<>(len);
            for (char c: S.toCharArray()) {
            	if (c=='<') {
            		if (!L.isEmpty()) R.addFirst(L.pollLast());
            	} else if (c=='>') {
            		if (!R.isEmpty()) L.addLast(R.pollFirst());
            	} else if (c=='-') {
            		if (!L.isEmpty()) L.pollLast();
            	} else {
            		L.addLast(c);
            	}
            }
            while (!L.isEmpty()) res.append(L.pollFirst());
            while (!R.isEmpty()) res.append(R.pollFirst());
            res.append('\n');
        }
        System.out.print(res.toString());
    }
}
