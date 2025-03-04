/**
 * 	325800KB	1436ms
 *
 * 	[사고 흐름]
 * 	일단 괄호 문제다. 괄호를 만들기만 하면, 최상위 괄호의 개수와 최하위 괄호의 개수만 구하면된다.
 * 	괄호 문자열과 같은 형태로 만들려면 어떻게 해야할까?
 *
 * 	1. (선택 X) 플립플롭처럼 x축을 지날 때 마다, 다음에 올 세로 선분의 여는괄호, 닫는괄호 여부를 변경한다.
 *  	=> 가능은 하겠으나, 구현 오류가 잦을 것 같다.
 *
 * 	2. (선택 X) 직전에 만들어진 괄호를 기준으로 판단한다.
 * 	    => 동일한 이유로, 구현 오류의 가능성이 높다.
 *
 * 	3. (선택 O) 모든 봉우리는 선이 x축 위로 올라갔다가 내려온다는 특징이 있다.
 * 	           올라갔다가 내려오는 것을 하나의 쌍으로 판단하고, 두 x값의 대소에 따라 여는괄호, 닫는괄호 여부를 판별한다.
 * 	    => 조건이 명확하며, 가장 구현 오류의 가능성이 적어보인다.
 *
 *
 * 	단, 처음 연산을 시작하는 선분이 x축 위로 올라가는 선분이 아닐 수도 있다.
 * 	이 경우 여는괄호 닫는괄호 여부를 반대로 연산해야한다. 이는 구현 오류의 가능성이 높다.
 *
 * 	코딩테스트에서는 구현 오류의 가능성을 낮추기 위해서, 문제의 일반화를 할 필요성이 있다.
 * 	그래서 괄호로 치환하기 전에, 처음 연산을 시작하는 선분은 무조건 X축 위로 올라가는 선분이 되도록 만든다.
 *      => ArrayDeque에 저장하고, 만약 peek가 내려가는 선분이라면 한 번만 시프트를 해주면 된다.
 *
 *
 * 이제, 기하학같이 보이던 문제가 괄호 문제로 치환되었다.
 * 덧붙이자면, 최상위의 괄호와 최하위의 괄호 개수만 판별하면 되기 때문에 스택은 필요없다.
 */

import java.io.*;
import java.util.*;

public class Main {
    static boolean OPEN = true;

    static StreamTokenizer st = new StreamTokenizer(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        int N = ni();
        int startX = ni();
        int startY = ni();
        int lastX = startX;
        int lastY = startY;
        ArrayDeque<Line> lines = new ArrayDeque<>();
        for (int i=1; i<N; ++i) {
            int curX = ni();
            int curY = ni();
            if ((0<lastY)!=(0<curY)) lines.add(new Line(lastX, lastY, curX, curY));
            lastX = curX;
            lastY = curY;
        }
        if ((0<lastY)!=(0<startY)) lines.add(new Line(lastX, lastY, startX, startY));

        // 처음 연산을 시작하는 선분은 무조건 X축 위로 올라가는 선분이 되도록 만든다.
        if (!lines.peekFirst().isAscY()) lines.addLast(lines.pollFirst());

        List<Parentheses> parentheses = new ArrayList<>();
        int pairX = 0;
        for (Line l: lines) {
            if (l.isAscY()) {
                pairX = l.x1;
            }
            else {
                if (pairX < l.x1) {
                    parentheses.add(new Parentheses(OPEN, pairX));
                    parentheses.add(new Parentheses(!OPEN, l.x1));
                }
                else {
                    parentheses.add(new Parentheses(OPEN, l.x1));
                    parentheses.add(new Parentheses(!OPEN, pairX));
                }
            }
        }

        Collections.sort(parentheses);
        boolean lastIsOpen = !OPEN;

        int cnt = 0;
        int a = 0;
        int b = 0;

        for (Parentheses x: parentheses) {
            if (x.isOpen) {
                cnt++;
            }
            else {
                cnt--;
                if (cnt == 0) a++;
                if (lastIsOpen == OPEN) b++;
            }
            lastIsOpen = x.isOpen;
        }

        System.out.println(a + " " + b);
    }

    static class Parentheses implements Comparable<Parentheses> {
        boolean isOpen;
        int x;
        public Parentheses(boolean isOpen, int x) {
            super();
            this.isOpen = isOpen;
            this.x = x;
        }
        @Override
        public int compareTo(Parentheses o) {
            return Integer.compare(x, o.x);
        }
    }

    static class Line {
        int x1, y1, x2, y2;
        public Line(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        public boolean isAscY() {
            return 0 < y2-y1;
        }
    }

    public static int ni() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
