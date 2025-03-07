import java.io.*;
import java.util.*;

/*
 *  32696KB, 336ms
 *  
 *  출발지를 주고 도착지 후보를 준다. 그리고 반드시 지나가야 하는 정점들을 준다.
 *  최단 경로 얘기는 문제에서 주어지고 출발지가 1개이며 정점의 수가 많으니 플로이드 워셜은 아니고 
 *  음수 간선도 없으니 벨만 포드도 아니라고 생각, 다익스트라로 풀이 방향을 정했다.
 *  
 *  시작 지점으로부터 다익스트라를 돌리면 출발지로부터 모든 정점에 대한 최단 경로가 구해질 것이고
 *  또한 경로 역추적을 통해 출발지 ~ 도착지로의 경로를 추적할 수 있다.
 *  
 *  문제에서 반드시 최단거리로 이동한다고 했으므로 목적지는 다익스트라로 뽑힌 경로에서 반드시 주어진 정점을 지나야 한다. 
 *  따라서 시작지점으로부터 다익스트라를 돌려서 최단거리로 갔을 때의 경로를 추출하고
 *  추출된 경로로부터 그래프 탐색을 통해 주어진 정점들을 반드시 지나가는지를 확인한다.
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static class Node implements Comparable<Node> {
    	int v;
    	int d;
    	
    	Node(int v, int d) {
    		this.v = v;
    		this.d = d;
    	}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(d, o.d);
		}
    }
    
    public static void main(String[] args) throws Exception {
    	int T = nextInt();
    	StringBuilder sb = new StringBuilder();
    	for (int tc = 1; tc <= T; tc++) {
    		//교차로의 개수
    		int n = nextInt();
    		//도로의 개수
    		int m = nextInt();
    		//목적지 후보의 개수
    		int t = nextInt();
    		//출발지
    		int s = nextInt();
    		//듀오는 g, h  사이에 있는 도로를 지나갔음
    		int g = nextInt();
    		int h = nextInt();
    		
    		//도로 입력
    		ArrayList<Node>[] adj = new ArrayList[n + 1];
    		HashSet<Integer>[] paths = new HashSet[n + 1];
    		for (int i = 0; i <= n; i++) {
    			adj[i] = new ArrayList<>();
    			paths[i] = new HashSet<>();
    		}
    		
    		for (int i = 0; i < m; i++) {
    			int a = nextInt();
    			int b = nextInt();
    			int d = nextInt();
    			adj[a].add(new Node(b, d));
    			adj[b].add(new Node(a, d));
    		}
    		
    		//다익스트라 알고리즘. 힙을 이용한 최적화
    		//거리 배열을 적당히 큰 값으로 초기화
    		int[] dist = new int[n + 1];
    		Arrays.fill(dist, 100_000_000); dist[s] = 0;
    		PriorityQueue<Node> pq = new PriorityQueue<>();
    		pq.add(new Node(s, 0));
    		while (!pq.isEmpty()) {
    			//지나온 거리가 가장 작은 노드를 꺼냄
    			Node cur = pq.remove();
    			//현재 지나온 거리가 거리 배열에 저장된 값보다 크면 살펴볼 필요가 없다
    			if (dist[cur.v] < cur.d) continue;

    			//인접 리스트에서 갈 수 있는 다음 정점을 꺼내보고
    			for (Node next : adj[cur.v]) {
    				int newD = cur.d + next.d;
    				//다음 정점으로 갈 수 있는 거리가 거리 배열에 저장된 거리보다 작다면 새로운 최단 경로가 발견된 것
    				if (newD < dist[next.v]) {
    					//따라서 힙에 새로운 노드를 넣어주고
    					pq.add(new Node(next.v, newD));
    					//거리 배열도 갱신
    					dist[next.v] = newD;
    					//새로운 최단 경로가 발견된 것이니 경로 배열은 초기화 후 다음 정점 넣어주기
    					paths[next.v].clear();
    					paths[next.v].add(cur.v);
    				} else if (newD == dist[next.v]) {
    					//만일 거리가 같다면 이 경로도 이용할 수 있으므로 추가
    					paths[next.v].add(cur.v);
    				}
    			}
    		}
    		
    		//목적지 후보. 출력을 오름차순으로 해야하니 트리셋에 넣기
    		TreeSet<Integer> tCandidate = new TreeSet<>();
    		for (int i = 0; i < t; i++) {
    			tCandidate.add(nextInt());
    		}
    		
    		//경로 역추적 및 g, h를 지나는지 확인
    		for (int cur : tCandidate) {
    			//만일 시작 지점이 g, h면 일단 해당 지점은 방문한 것이 된다.
    			boolean findG = cur == g;
    			boolean findH = cur == h;

    			//bfs를 통해 경로 배열을 통한 경로 역추적
    			//경로 역추적은 visited 배열이 필요가 없나 싶었는데 일단 안전하게 사용하는 방식으로 함
    			//추후 visited 없이 제출해보니 메모리 초과남 필요한듯
    			boolean[] v = new boolean[n + 1];
    			Queue<Integer> q = new ArrayDeque<>();
    			for (int prevV : paths[cur]) {
    				q.add(prevV);
    				v[prevV] = true;
    			}
    			
    			while (!q.isEmpty()) {
    				int prevV = q.remove();
    				if (prevV == g) 
    					findG = true;
    				else if (prevV == h) 
    					findH = true;
    				if (findG && findH) break;
    				
    				for (int nextPrevV : paths[prevV]) {
    					if (!v[nextPrevV]) {
    						q.add(nextPrevV);
    						v[nextPrevV] = true;
    					}
    				}
    			}
    			
    			//g랑 h를 모두 지나간다면 결과로 출력
    			if (findG && findH) {
    				sb.append(cur).append(' ');
    			}
    		}
    		sb.append('\n');
    	}
    	System.out.println(sb);
    }
    
    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}