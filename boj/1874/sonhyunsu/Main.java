//제출번호: 93179988
//메모리:   21060 KB
//실행시간: 212 ms
import java.io.*;
import java.util.*;

//원하는 스택 수열이 나오도록 실제로 시뮬레이션을 돌려봄
//만약에 중간에 불가능하면 만들 수 없는 것
public class Main {
    
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        Deque<Integer> stack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        boolean canMake = true;

        int n = nextInt();
        int putNum = 1;
        for (int i = 0; i < n; i++) {
            int num = nextInt();
            //숫자보다 크지 않으면서 아직 스택에 들어가지 않은 숫자가 있다면 모두 스택에 넣음 
            while (putNum <= num) {
                sb.append('+').append('\n');
                stack.addLast(putNum++);
            }

            //만약에 스택의 탑에 숫자가 있다면 뽑을 수 있음
            if (stack.peekLast() == num) {
                sb.append('-').append('\n');
                stack.pollLast();
            } else {
                //스택의 탑에 뽑아야 할 숫자가 없다면 스택 수열을 만들 수 없음
                canMake = false;
            }
        }

        //스택 수열을 만들 수 있다면 수열을 만드는 push, pop 명령어를 출력하고
        //만들 수 없다면 "NO"를 출력함
        System.out.print(canMake ? sb.toString() : "NO");
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}