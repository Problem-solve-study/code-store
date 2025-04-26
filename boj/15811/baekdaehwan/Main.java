/**
 * 	15040KB	412ms
 * 이전의 hello world와 유사한 문제다.
 * 하지만 키값이 정해져 있지 않으므로, 해시맵으로 키값을 별도로 지정해주었다.
 * 연속된 26개 값에 대한 키만 있으면 되기 때문에 사실 toKey는 배열로 구현해도 된다.
 */

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] A;
    static int[] ka, kb, kc;
    static char[] a, b, c;
    static boolean[] visited;
    static Map<Character, Integer> toKey;

    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = st.nextToken().toCharArray();
        b =	st.nextToken().toCharArray();
        c =	st.nextToken().toCharArray();

    	toKey = new HashMap<>();
    	addKey(a);
    	addKey(b);
    	addKey(c);

    	ka = new int[a.length];
    	kb = new int[b.length];
    	kc = new int[c.length];
        for (int i=0; i<ka.length; i++) ka[i] = toKey.get(a[i]);
        for (int i=0; i<kb.length; i++) kb[i] = toKey.get(b[i]);
        for (int i=0; i<kc.length; i++) kc[i] = toKey.get(c[i]);

    	N = toKey.size();
        A = new int[N];
        visited = new boolean[10];
        bt(0);
        System.out.println("NO");
    }

    static void addKey(char[] arr) {
    	for (char c: arr) {
    		if (!toKey.containsKey(c)) toKey.put(c, toKey.size());
    	}
    }
    
    static void bt(int idx) {
        if (idx == N) {
            int n1 = 0;
            int n2 = 0;
            int res = 0;
            for (int i: ka) n1 = n1*10 + A[i];
            for (int i: kb) n2 = n2*10 + A[i];
            for (int i: kc) res = res*10 + A[i];
            
            if (res == n1+n2) {
                System.out.println("YES");
                System.exit(0);
            }
            return;
        }
        for (int n=0; n<10; ++n) {
            if (!visited[n]) {
                visited[n] = true;
                A[idx] = n;
                bt(idx+1);
                visited[n] = false;
            }
        }
    }
}
