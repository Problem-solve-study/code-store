//제출번호: 92029993
//메모리:   29192 KB
//실행시간: 524 ms
import java.io.*;
import java.util.*;

//나보다 작은 상어와 나보다 큰 상어를 관리하면 풀 수 있음
//둘 다 PQ를 이용해서 관리..
public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        int n = nextInt();
        int k = nextInt();
        long t = nextInt();

        PriorityQueue<Integer> large = new PriorityQueue<>(Comparator.naturalOrder());
        PriorityQueue<Integer> small = new PriorityQueue<>(Comparator.reverseOrder());

        for (int i = 0; i < n; i++) {
            int size = nextInt();
            if (size < t) {
                //나보다 작은 상어를 담아둠
                small.add(size);
            } else {
                //나보다 큰 상어를 담아둠
                large.add(size);
            }
        }

        for (int i = 0; i < k; i++) {
            //내가 먹을 수 있는 상어가 없다면 종료
            if (small.isEmpty()) {
                break;
            }

            //먹을 수 있는 상어 중 가장 큰 상어를 먹음
            t += small.poll();

            //이제 먹을 수 있는 상어를 small로 옮김
            while (!large.isEmpty() && large.peek() < t) {
                small.add(large.poll());
            }
        }

        System.out.print(t);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}

/* 스택으로도 풀 수 있음
//제출번호: 92032415
//메모리:   23808 KB
//실행시간: 312 ms
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        int n = nextInt();
        int k = nextInt();
        long t = nextInt();

        int[] shark = new int[n];
        int sPos = 0;
        
        int[] stack = new int[n];
        int top = 0;

        for (int i = 0; i < n; i++) {
            shark[i] = nextInt();
        }
        Arrays.sort(shark); //상어를 오름차순으로 정렬

        for (int i = 0; i < k; i++) {
            //내가 먹을 수 있는 상어를 stack에 담아둠
            while (sPos < n && shark[sPos] < t) {
                stack[top++] = shark[sPos];
                sPos++;
            }

            //먹을 수 있는 상어가 없다면 종료
            if (top == 0) {
                break;
            }

            //먹을 수 있는 상어 중 가장 큰 상어를 먹음
            t += stack[--top];
        }

        System.out.print(t);
    }

    static int nextInt() throws IOException {
        int n = System.in.read() & 15;
        int c = System.in.read();

        while (c > 47) {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }

        return n;
    }
}
 */