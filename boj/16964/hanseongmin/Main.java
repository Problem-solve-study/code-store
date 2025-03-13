import java.io.*;
import java.util.*;

/*
 * 46932KB, 408ms
 * 
 * 재밌었던 문제. 
 * 
 * DFS 순서가 맞는지 체크? -> 아 그럼 DFS를 일단 돌려보긴 해야하겠는데 -> 돌리면서 검증은 어떻게 하지
 * 입력으로 들어온 순서를 보면서 마지막 경로임을 체크하고 마지막 경로면 다시 위로 올라가고 이를 재귀적으로?
 * -> 너무 복잡한 것 같다 이 방법은 좀 아닌듯
 * -> 현재 시점에서 다음 순서로 갈 수 있는지만 체크하면 되지 않을까?
 * -> 잘못된 순서라면 방문하지 않은 정점이 분명 존재할테니 그걸로 판별하면 되겠다.
 * -> 입력으로 주어진 순서만큼만 연산할테니 복잡도도 O(N)이겠다.
 * -> 오케이 이걸로 가보자
 * -> 맞음
 */

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	static int N;
	static final int WA = 0;
	static final int AC = 1;
	static HashSet<Integer>[] adj;
	static boolean[] v;
	static int[] orders;
	static int orderIdx = 1;
	
    public static void main(String[] args) throws Exception {
    	//데이터 입력
    	N = nextInt();
    	adj = new HashSet[N + 1];
    	v = new boolean[N + 1];
    	for (int i = 0; i <= N; i++) {
    		adj[i] = new HashSet<>();
    	}
    	
    	for (int i = 0; i < N - 1; i++) {
    		int a = nextInt();
    		int b = nextInt();
    		adj[a].add(b);
    		adj[b].add(a);
    	}
    	
    	orders = new int[N];
    	for (int i = 0; i < N; i++) {
    		orders[i] = nextInt();
    	}
    	
    	//검증 시작
    	dfs(1);
    	
    	//올바른 순서로 들어왔다면 모든 정점 탐색 가능
    	if (check()) System.out.println(AC);
    	else System.out.println(WA);
    }
    
    static void dfs(int i) {
    	//현재 정점 방문함
    	v[i] = true;
    	//모든 순서를 다 봤으면 바로 빠져나옴
    	if (orderIdx >= N) return;
    
    	//현재 정점에서 입력으로 들어온 다음 순서로 갈 수 있는지를 체크
    	while (adj[i].contains(orders[orderIdx])) {
    		//갈 수 있으면 인덱스를 옮겨주고
    		orderIdx++;
    		//재귀적으로 탐색
    		dfs(orders[orderIdx - 1]);
    		if (orderIdx >= N) break;
    	}
    }
    
    static boolean check() {
    	for (int i = 1; i <= N; i++) {
    		if (!v[i]) return false;
    	}
    	
    	return true;
    }
    
    static int nextInt() throws Exception {
    	st.nextToken();
    	return (int) st.nval;
    }
}