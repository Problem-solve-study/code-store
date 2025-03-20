//제출번호: 91690604
//메모리:   214564 KB
//실행시간: 616 ms
import java.io.*;
import java.util.*;

//스택 2개로 커서를 구현하면 쉬운 문제
//커서를 기준으로 앞의 문자들은 front, 뒤의 문자들은 back으로 관리한다.
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Deque<Character> front = new ArrayDeque<>(), back = new ArrayDeque<>();

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int t = 0; t < T; t++) {
            String line = br.readLine();

            //각각의 문자에 대해서 작업을 수행한다.
            for (char cmd : line.toCharArray()) {
                switch (cmd) {
                    case '<':
                        //왼쪽으로 커서를 옮길 때는 커서의 앞에 문자가 있는 지 확인해야 함
                        if (!front.isEmpty()) {
                            back.addFirst(front.pollLast());
                        }
                        break;
                
                    case '>':
                        //오른쪽으로 커서를 옮길 때는 커서의 뒤에 문자가 있는 지 확인해야 함
                        if (!back.isEmpty()) {
                            front.addLast(back.pollFirst());
                        }
                        break;
                    
                    case '-':
                        //문자를 삭제할 때는 커서의 앞에 문자가 있는 지 확인해야 함
                        if (!front.isEmpty()) {
                            front.pollLast();
                        }
                        break;
                        
                    default:
                        //나머지 문자들은 비밀번호 문자들이므로 문자 입력 후 커서가 이동
                        front.addLast(cmd);
                }
            } 
            
            //커서 앞 뒤에 있는 모든 문자열을 차례대로 출력
            while (!front.isEmpty()) {
                sb.append(front.pollFirst());
            }
            while (!back.isEmpty()) {
                sb.append(back.pollFirst());
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }
}