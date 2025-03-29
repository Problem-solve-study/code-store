/**
 * 30740KB	324ms
 * 
 * 단순 구현 문제이니 추가적 설명은 하지 않습니다.
 * 저는 이 문제 싫어요
 */

import java.io.*;
import java.util.*;

public class Main {
    static Deque<Integer> stack = new ArrayDeque<>();
    static List<Opr> oprs = new ArrayList<>();

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        StringBuilder res = new StringBuilder();
        A:
        while (true) {
            oprs.clear();

            while (true) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String oprStr = st.nextToken();
                if (oprStr.equals("QUIT")) break A;
                else if (oprStr.equals("END")) break;
                else if (oprStr.equals("NUM")) oprs.add(new Opr("NUM", Integer.parseInt(st.nextToken())));
                else oprs.add(new Opr(oprStr, 0));
            }

            int n = Integer.parseInt(br.readLine());
            for (int i = 0; i < n; ++i) {
                try {
                    stack.clear();
                    stack.addLast(Integer.parseInt(br.readLine()));
                    for (Opr opr : oprs) {
                        if (opr.opr.equals("NUM")) stack.addLast(opr.value);
                        else if (opr.opr.equals("POP")) stack.removeLast();
                        else if (opr.opr.equals("INV")) stack.addLast(-stack.removeLast());
                        else if (opr.opr.equals("DUP")) stack.addLast(stack.getLast());
                        else if (opr.opr.equals("SWP")) {
                            int a = stack.removeLast();
                            int b = stack.removeLast();
                            stack.addLast(a);
                            stack.addLast(b);
                        } else if (opr.opr.equals("ADD")) {
                            long a = (long) stack.removeLast() + stack.removeLast();
                            if (Math.abs(a) > 1_000_000_000) throw new Exception();
                            stack.addLast((int) a);
                        } else if (opr.opr.equals("SUB")) {
                            long a = (long) -stack.removeLast() + stack.removeLast();
                            if (Math.abs(a) > 1_000_000_000) throw new Exception();
                            stack.addLast((int) a);
                        } else if (opr.opr.equals("MUL")) {
                            long a = (long) stack.removeLast() * stack.removeLast();
                            if (Math.abs(a) > 1_000_000_000) throw new Exception();
                            stack.addLast((int) a);
                        } else if (opr.opr.equals("DIV")) {
                            int a = stack.removeLast();
                            int b = stack.removeLast();
                            stack.addLast(b/a);
                        } else if (opr.opr.equals("MOD")) {
                            int a = stack.removeLast();
                            int b = stack.removeLast();
                            boolean sign = b>0;
                            stack.addLast(Math.abs(b) % Math.abs(a) * (sign?1:-1));
                        }
                    }
                    if (stack.size() == 1) {
                        res.append(stack.removeLast()).append('\n');
                    } else {
                        res.append("ERROR\n");
                    }
                } catch (Exception e) {
                    res.append("ERROR\n");
                }
            }
            res.append('\n');
            br.readLine();
        }
        System.out.print(res);
    }

    static class Opr {
        String opr;
        int value;

        public Opr(String opr, int value) {
            this.opr = opr;
            this.value = value;
        }
    }
}