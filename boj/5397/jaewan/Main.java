
// /*
//  * LinkedList
//  */
// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.util.LinkedList;
// import java.util.ListIterator;

// public class Main {

//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

//         int n = Integer.parseInt(br.readLine());
//         StringBuilder sb = new StringBuilder();

//         for (int i = 0; i < n; i++) {
//             LinkedList<Character> str = new LinkedList<>();
//             ListIterator<Character> iter = str.listIterator();
//             String inStr = br.readLine();
//             int len = inStr.length();
//             for (int j = 0; j < len; j++) {
//                 char t = inStr.charAt(j);
//                 switch (t) {
//                     case '<':
//                         if (iter.hasPrevious())
//                             iter.previous();
//                         break;
//                     case '>':
//                         if (iter.hasNext())
//                             iter.next();
//                         break;
//                     case '-':
//                         if (iter.hasPrevious()) {
//                             iter.previous();
//                             iter.remove();
//                         }
//                         break;
//                     default:
//                         iter.add(t);
//                         break;
//                 }
//             }
//             for (char t : str)
//                 sb.append(t);
//             sb.append('\n');
//         }
//         System.out.println(sb.toString());
//     }
// }

/*
 * Stack 2개 사용.
 * 왼쪽 스택에 push.
 * < 입력하면, 하나를 오른쪽 스택으로 옮기기.
 * > 입력하면, 오른쪽 하나를 왼쪽으로 옮기기.
 * - 입력하면, 왼쪽 하나 지우기.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            String inStr = br.readLine();
            int len = inStr.length();
            ArrayDeque<Character> left = new ArrayDeque<>(), right = new ArrayDeque<>();
            for (int j = 0; j < len; j++) {
                char t = inStr.charAt(j);
                switch (t) {
                    case '<':
                        if (left.isEmpty())
                            continue;
                        right.push(left.pop());
                        break;

                    case '>':
                        if (right.isEmpty())
                            continue;
                        left.push(right.pop());
                        break;
                    case '-':
                        if (left.isEmpty())
                            continue;
                        left.pop();
                        break;
                    default:
                        left.push(t);
                        break;
                }
            }
            while (!left.isEmpty())
                sb.append(left.removeLast());
            while (!right.isEmpty())
                sb.append(right.removeFirst());
            sb.append('\n');
        }
        System.out.println(sb.toString());
    }
}