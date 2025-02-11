//제출번호: 89893600
//메모리:   11648 KB
//실행시간: 68 ms
import java.io.*;
import java.util.*;

//l과 k로 들어올 수 있는 수가 0~1023이기 때문에 bfs를 이용하면 쉽게 구할 수 있음
//bfs시 수행해야 할 연산을 제대로 구현하는 것이 중요함
//부호를 바꾸는 과정에서 등호하는 빼먹어서 최상단 직전의 비트를 toggle하지 않아 여러 번 틀림
public class Main {

    //실제로는 1023만큼만 필요하지만 혹시 몰라서 여유롭게 더 구했음
    static int[] d = new int[2048];
    static Queue<Integer> queue = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int l = Integer.parseInt(br.readLine(), 2); //입력받은 2진수 문자열을 정수로 변경
        int k = Integer.parseInt(br.readLine(), 2);
        
        //1부터 시작하게 해서 최소 탐색 횟수 & 방문여부를 동시에 나타냄 
        d[l] = 1;
        queue.add(l);
        while (!queue.isEmpty()) {
            int n = queue.poll();

            //2. 1 더하기 연산을 수행
            if (n < 2047 && d[n + 1] == 0) {
                d[n + 1] = d[n] + 1;
                queue.add(n + 1);
            }

            //3. 1 빼기 연산을 수행
            if (n > 0 && d[n - 1] == 0) {
                d[n - 1] = d[n] + 1;
                queue.add(n - 1);
            }

            //1. 한 자리 숫자의 보수를 바꾸는 연산을 수행
            //i*2가 n보다 크면 i는 최상단 비트임
            for (int i = 1; (i<<1) <= n ; i <<= 1) {
                if (d[n ^ i] == 0) {
                    d[n ^ i] = d[n] + 1;
                    queue.add(n ^ i);
                }
            }
        }

        //처음에 1부터 시작했기 때문에 1을 빼줌
        System.out.print(d[k] - 1);
    }
}