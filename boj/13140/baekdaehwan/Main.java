/**
 * 15000KB	112ms
 * <단, h와 w는 0이 될 수 없다.>
 * 
 * 문제를 똑바로 읽읍시다...
 * [사고흐름]
 * 이건 브루트포스다. 다른 방법을 쓸 이유가 없다라고 생각하여, 브루트포스로 접근했습니다.
 * 경우의 수도 많진 않아서 그냥 for문으로 hello, world값 구했습니다.
 * 
 * [알고리즘 설명]
 * 백트래킹을 사용한 순열코드입니다.
 * 각 숫자를 만들때는 각 자릿수의 인덱스 번호를 배열로 저장해두고 사용했습니다(배열 a, b)
 */

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] A;
    static boolean[] visited;
    static int[] a = {2,1,3,3,4};
    static int[] b = {6,4,5,3,0};

    static StreamTokenizer st = new StreamTokenizer(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        N = ni();
        A = new int[7];
        visited = new boolean[10];
        bt(0);
        System.out.println("No Answer");
    }

    public static void bt(int idx) {
        if (idx == 7) {
            check();
            return;
        }
        for (int n=(idx==2||idx==6? 1:0); n<10; ++n) {
            if (!visited[n]) {
                visited[n] = true;
                A[idx] = n;
                bt(idx+1);
                visited[n] = false;
            }
        }
    }

    public static void check() {
        int n1 = 0;
        int n2 = 0;
        for (int i=0; i<5; i++) n1 = n1*10 + A[a[i]];
        for (int i=0; i<5; i++) n2 = n2*10 + A[b[i]];
        if (N == n1+n2) {
            System.out.printf("%7d\n+%6d\n-------\n%7d", n1, n2, N);
            System.exit(0);
        }
    }

    public static int ni() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
