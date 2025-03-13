/**
 * 100608KB	496ms
 * 
 * [사고 흐름]
 * DFS 문제이긴 한데, 정해진 순서대로 탐색시키는 문제라고 파악했습니다.
 * 그래서 사용할 자료구조를 Set과 Queue라고 생각했습니다.
 * Set      - 인접 리스트 표현용
 * Queue    - 정해진 순서 확인용
 * 
 * 문제의 특징은 항상 정해진 순서대로 탐색해야한다는 것입니다. 
 * 즉, 현재 노드의 인접 노드 중 queue의 peek에 해당하는 노드가 없다면 
 * 현재 노드의 조상 노드에서 탐색을 이어나가야 합니다.
 * 
 * 이렇게 탐색을 이어나가는 도중 현재 노드와 조상 노드에 다음으로 방문해야할 노드가 없을 수도 있습니다.
 * 이 경우에는 탐색이 종료되고, 큐를 모두 비울수 없습니다. 
 * 즉, 탐색 종료 후 큐의 empty여부로 가능 여부를 판단할 수 있습니다.
 * 
 * 위 생각을 바탕으로 구현을 시작했습니다.
 * 
 * [알고리즘 설명]
 * 1. 인접 리스트를 구성합니다.
 * 2. 주어진 탐색 순서로 queue를 만듭니다.
 * 3. DFS를 시도합니다.
 * 4. DFS중 현재 노드의 인접노드 중 queue의 peek를 찾을 수 없을때까지 탐색합니다.
 */

import java.io.*;
import java.util.*;

public class Main {	
    static int N;
    static List<Set<Integer>> adj;
    static Queue<Integer> queue;
    
    static StreamTokenizer st = new StreamTokenizer(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
    	N = ni();
    	adj = new ArrayList<>();
    	queue = new ArrayDeque<>();
    	for (int i=-1; i<N; ++i) adj.add(new TreeSet<>());
    	for (int i=1; i<N; ++i) {
    		int a = ni();
    		int b = ni();
    		adj.get(a).add(b);
    		adj.get(b).add(a);
    	}
    	for (int i=0; i<N; ++i) queue.add(ni());
    	boolean isPossible = true;
    	
    	int start = queue.poll();
    	if (start == 1) dfs(1);
    	else isPossible = false;
        if (!queue.isEmpty()) isPossible = false;
    	System.out.println(isPossible? 1:0);
    }
    
    public static void dfs(int cur) {
    	while (queue.peek()!=null && adj.get(cur).contains(queue.peek())) dfs(queue.poll());
    }
    
    public static int ni() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}