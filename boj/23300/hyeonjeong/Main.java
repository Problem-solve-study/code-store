// 13008KB 84ms

import java.io.*;
import java.util.*;

/**
 * 스택 & 시뮬레이션
 * 압축은 이전 값과 비교해서 새로운 위치의 값을 갱신
 */
class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    
    public static void main(String[] args) throws IOException {
        int p = nextInt();
        int Q = nextInt();

        int current = -1;

        int[] back = new int[Q];
        int btop = -1;
        int[] forward = new int[Q];
        int ftop = -1;

        for (int q = 0; q < Q; q++) {
            String command = nextString();
            if (command.equals("A")) {
                ftop = -1;
                if (current != -1) {
                    back[++btop] = current;
                }

                current = nextInt();

                continue;
            }

            if (command.equals("B")) {
                if (btop == -1) {
                    continue;
                }

                forward[++ftop] = current;
                current = back[btop--];
                continue;
            }

            if (command.equals("F")) {
                if (ftop == -1) {
                    continue;
                }

                back[++btop] = current;
                current = forward[ftop--];
                continue;
            }

            if (command.equals("C")) {
                int newTop = -1;

                for (int i = 0; i <= btop; i++) {
                    if (i !=0 && back[i] == back[i - 1]) {
                        continue;
                    }

                    back[++newTop] = back[i];
                }
                
                btop = newTop;
            }
        }

        StringBuilder sb = new StringBuilder();

        sb.append(current).append('\n');
        appendStack(sb, back, btop);
        appendStack(sb, forward, ftop);

        System.out.print(sb);
    }

    static void appendStack(StringBuilder sb, int[] stack, int top) {
        if (top == -1) {
            sb.append("-1");
        }
        else {
            while(top > 0) {
                sb.append(stack[top--]).append(' ');
            }
            sb.append(stack[top--]);
        }

        sb.append('\n');
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    static String nextString() throws IOException {
        st.nextToken();
        return st.sval;
    }
}
