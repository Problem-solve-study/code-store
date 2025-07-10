//제출번호: 96157882
//메모리:   30808 KB
//실행시간: 260 ms
import java.io.*;
import java.util.*;

//문제를 그대로 구현하면 큐를 10만개 만돌고 아이템 하나를 넣으면
//10만개의 큐를 순회해야 해서 O(N^2)이 되어서 시초남
//하지만 원소가 이동하는 방향을 잘 살펴보면
//스택은 무시하고 큐 하나만으로 구현할 수 있는 것을 알 수 있음 
public class Main {
    public static void main(String[] args) throws IOException {
        int n = nextInt();
        
        int[] type = new int[n];
        for (int i = 0; i < n; i++) {
            type[i] = nextInt();
        }

        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            int x = nextInt();
            if (type[i] == 0) {
                dq.addFirst(x);
            }
        }

        int m = nextInt();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            dq.addLast(nextInt());
            sb.append(dq.pollFirst()).append(' ');
        }

        System.out.print(sb);
    }

    static int nextInt() throws IOException {
        int n = System.in.read() & 15, c;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}