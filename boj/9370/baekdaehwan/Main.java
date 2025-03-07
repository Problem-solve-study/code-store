/**
 * 	77868KB	488ms
 * 
 * [사고 흐름]
 * 일단 다익스트라다.
 * 나중에 방문하는 교차로에서 각 목적지까지의 거리와 시작점에서 각 목적지까지의 거리를 비교하는 방식으로 해결하면 깔끔하게 풀릴것 같다는 생각이 들었다.
 * 
 * 그러면 교차로 방문 순서에 대해서 판별을 해야한다.
 * 방문 순서를 정하기 위해서 다음과 같이 생각해보았다.
 * 
 * S와 가장 가까운 교차로가 먼저 방문되는 교차로다
 * => S부터 두 교차로까지의 거리가 동일할 수도 있지 않을까?
 * => S->G, S->H가 같은 거리인 경우는 생기지 않는다. (S->G는 S->G까지의 거리를 뜻한다)
 * => G != H && S->G == S->H && S->G->H == S->H라는 모순이 생기기 때문에
 *    그런 교차로를 지나는 경우의 수는 주어지지 않는다.
 * 
 * 위 생각을 바탕으로 문제 풀이를 시작하였다.
 */

import java.io.*;
import java.util.*;

public class Main {
	static int N, M, T, S, G, H;
    static List<List<int[]>> adj;
    static boolean[] target;

    static StreamTokenizer st = new StreamTokenizer(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
    	int TC = ni();
    	StringBuilder sb = new StringBuilder();
    	for (int tc=0; tc<TC; ++tc) {
    		N = ni()+1;
    		M = ni();
    		T = ni();
    		
    		S = ni();
    		G = ni();
    		H = ni();
//    		S에서 시작, G, H 사이의 교차로를 지나갔다.
    		
    		adj = new ArrayList<>();
    		target = new boolean[N];
    		
    		for (int i=0; i<N; ++i) adj.add(new ArrayList<>());
    		for (int i=0; i<M; ++i) {
    			int a = ni();
    			int b = ni();
    			int cost = ni();
    			adj.get(a).add(new int[]{b, cost});
    			adj.get(b).add(new int[]{a, cost});
    		}
    		for (int i=0; i<T; ++i) target[ni()] = true;

    		
//    		예술가는 무조건 최단경로로 이동하기 때문에, S에서 가까운 교차로를 먼저 방문한다.
//			문제를 단순화하기 위해서 가까운 교차로를 무조건 G로 설정한다.
//    		두 교차로까지의 거리가 동일한 경우는 생기지 않는다. 그런 경우에는 
//    		G != H && S->G == S->H && S->G->H == S->H라는 모순이 생기기 때문에 예술가는 그런 교차로를 지나지 않는다.
    		
    		int[] dijkS = dijk(S);
    		if (dijkS[G] > dijkS[H]) {
    			int tmp = G; G = H; H = tmp;
    		}
    		
    		int[] dijkH = dijk(H);
    		int distSH = dijkS[G] + dijkH[G];

//  		H의 dijk에 (S->G), (G->H)까지 거리를 모두 더해준다.
    		for (int i=1; i<N; ++i) dijkH[i] += distSH;
    		
//    		이제 목적지이면서 dijkS과 dijkH가 같은 값을 구한다.
    		for (int i=1; i<N; ++i) {
    			if (target[i] && dijkS[i] == dijkH[i]) sb.append(i).append(' ');
    		}
    		sb.append('\n');
    	}
    	System.out.println(sb);
    }
    
    public static int[] dijk(int si) {
    	int[] dijk = new int[N];
    	boolean[] confirmed = new boolean[N];
    	Arrays.fill(dijk, Integer.MAX_VALUE);
    	dijk[si] = 0;
    	confirmed[si] = true;
    	
    	int cur = si;
    	for (int i=1; i<N; ++i) {
//    		한 번 돌때마다 하나씩 확정
    		for (int[] e: adj.get(cur)) dijk[e[0]] = Math.min(dijk[e[0]], dijk[cur]+e[1]);
    		
    		int min = Integer.MAX_VALUE;
    		for (int j=1; j<N; ++j) {
    			if (!confirmed[j] && min > dijk[j]) {
    				min = dijk[j];
    				cur = j;
    			}
    		}
			confirmed[cur] = true;
    	}
    	
    	return dijk;
    }
    
    public static int ni() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}