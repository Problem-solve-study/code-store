import java.io.*;
import java.util.*;

/*
 * 295816KB, 720ms
 * 
 * 친구 관계의 수가 4000으로 꽤나 적은 편이니 이거 N^3 브루트포스가 뚫리지 않을까 싶었다.
 * 믿음으로 제출했는데 맞음
 */

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	static int N, M;
	static HashSet<Integer>[] adj;
	static int res = Integer.MAX_VALUE;
	
    public static void main(String[] args) throws Exception {
    	N = nextInt();
    	M = nextInt();
    	adj = new HashSet[N + 1];
    	for (int i = 0; i <= N; i++) {
    		adj[i] = new HashSet<>();
    	}
    	
    	for (int i = 0; i < M; i++) {
    		int a = nextInt();
    		int b = nextInt();
    		adj[a].add(b);
    		adj[b].add(a);
    	}
    	
        //일단 친구 관계인 두 명을 뽑음
    	for (int i = 1; i <= N; i++) {
    		for (int j = i + 1; j <= N; j++) {
    			if (adj[i].contains(j)) {
                    //두 명이 친구라면 다른 한 명을 마저 탐색
    				find(i, j);
    			}
    		}
    	}
    	
    	System.out.println(res == Integer.MAX_VALUE ? -1 : res);
    }
    
    static void find(int a, int b) {
    	for (int next : adj[a]) {
    		if (check(a, b, next)) {
                //a, b, next가 모두 친구라면 갱신
    			res = Math.min(res, adj[a].size() + adj[b].size() + adj[next].size() - 6);
    		}
    	}
    }
    
    static boolean check(int pp, int p, int cur) {
    	return adj[pp].contains(p) && adj[p].contains(cur) && adj[cur].contains(pp);
    }
    
    static int nextInt() throws Exception {
    	st.nextToken();
    	return (int) st.nval;
    }
}