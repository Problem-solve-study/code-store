
/**
 * 41172KB	304ms
 * 
 * [사고흐름]
 * 특정 노드 이후에 리프노드까지의 최장경로 길이를 저장해두고, (리프노드까지의 최장경로 >= D) 인 지점까지 방문하면 되겠다고 생각했습니다.
 * 
 * [알고리즘 설명]
 * dfs 한 번으로도 할 수 있겠지만 편의상 두 번 하였습니다.
 * 
 * maxLen   : 현재 노드로부터 리프노드까지의 최장 경로
 * init()   : maxLen 초기화 함수
 * dfs()    : 현재 노드부터 (리프노드까지의 최장경로 >= D) 인 모든 지점을 방문했을 때의 이동 거리 반환
 */

import java.io.*;
import java.util.*;

public class Main {
	static int N, S, D;
	static int[] maxLen;
	static boolean[] visited;
	static List<List<Integer>> adj;
	
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static void main(String[] args) throws Exception {
    	N = ni();
    	S = ni()-1;
    	D = ni();
    	
    	maxLen = new int[N];
    	visited = new boolean[N];
    	adj = new ArrayList<>();
    	for (int i=0; i<N; ++i) adj.add(new ArrayList<>());
    	for (int i=1; i<N; ++i) {
    		int a = ni()-1;
    		int b = ni()-1;
    		adj.get(a).add(b);
    		adj.get(b).add(a);
    	}
    	init(S);
    	
    	Arrays.fill(visited, false);
    	System.out.println(dfs(S)-2);
    }
    
    static int dfs(int c) {
    	visited[c] = true;
    	int len = 2;
    	for (int n: adj.get(c)) {    		
    		if (!visited[n] && D <= maxLen[n]) len += dfs(n); 
    	}
    	return len;
    }

    static int init(int c) {
    	visited[c] = true;
    	for (int n: adj.get(c)) {
    		if (!visited[n]) maxLen[c] = Math.max(maxLen[c], init(n));
    	}
    	return maxLen[c]+1;
    }
    
    static int ni() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}