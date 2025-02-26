/**
 * 	14480KB	100ms
 *
 * 	[사고흐름]
 * 	N<10에 K<4? 최악이 10!/6!이기 때문에 브루트포스로 접근함
 *
 * 	[알고리즘 설명]
 * 	흔하게 사용하는 재귀 순열,
 * 	인자로 문자열 넘겨서 트리셋에 최종으로 만들어지는 수를 기록함.
 */

 import java.util.*;
 import java.io.*;
 
 public class Main {
     static String[] A;
     static int N, K;
     static int v;
     static TreeSet<Integer> set = new TreeSet<>();
 
     public static void main(String[] args) throws Exception {
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         N = Integer.parseInt(br.readLine());
         K = Integer.parseInt(br.readLine());
         A = new String[N];
         for (int i=0; i<N; i++) A[i] = br.readLine();
 
         solve(K, "");
         System.out.println(set.size());
     }
 
     public static void solve(int c, String s) {
         if (c==0) {
             set.add(Integer.parseInt(s));
             return;
         }
         for (int i = 0; i < N; i++) {
             if ((v & 1<<i) == 0) {
                 v |= 1<<i;
                 solve(c-1, s+A[i]);
                 v &= ~(1<<i);
             }
         }
     }
 }