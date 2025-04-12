//제출번호: 93022603
//메모리:   33644 KB
//실행시간: 464 ms
import java.io.*;
import java.util.*;

//N: 100, V: 1000, E:10000 인데
//다익스트라를 N번 돌려도 O(N(V+E)lgE) < 1400만 이라고 봐서
//그냥 무지성으로 N번 다익스트라 돌림
public class Main {
    
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    
	public static void main(String[] args) throws IOException {
	    int n = nextInt();
	    int v = nextInt();
	    int e = nextInt();
	    
	    int a = nextInt();
	    int b = nextInt();
	    
	    int[] members = new int[n];
	    for (int i = 0; i < n; i++) {
	        members[i] = nextInt();
	    }
	    
	    List<List<int[]>> graph = new ArrayList<>();
	    for (int i = -1; i < v; i++) {
	        graph.add(new ArrayList<>());
	    }
	    
	    for (int i = 0; i < e; i++) {
	        int x = nextInt();
	        int y = nextInt();
	        int w = nextInt();
	        
	        graph.get(x).add(new int[]{y, w});
	        graph.get(y).add(new int[]{x, w});
	    }
	    
	    PriorityQueue<int[]> pq = new PriorityQueue<>((i1, i2) -> Integer.compare(i1[0], i2[0]));
	    int[] dist = new int[v + 1];
	    boolean[] visited = new boolean[v + 1];
	    
	    int res = 0;
	    for (int i = 0; i < n; i++) {
            //배열 초기화 후
	        for (int j = 1; j <= v; j++) {
	            dist[j] = 100000000;
	            visited[j] = false;
	        }
	        
            //i번째 멤버에 대해서 다익스트라 돌리기
	        pq.add(new int[]{0, members[i]});
	        dist[members[i]] = 0;
	        
	        while (!pq.isEmpty()) {
	            int[] item = pq.poll();
	            
	            int node = item[1];
	            if (visited[node]) {
	                continue;
	            }
	            visited[node] = true;
	            
	            int cDist = item[0];
	            
	            for (int[] nItem : graph.get(node)) {
	                int nNode = nItem[0];
	                
	                if (visited[nNode]) {
	                    continue;
	                }
	                
	                int nDist = cDist + nItem[1];
	                if (dist[nNode] > nDist) {
	                    dist[nNode] = nDist;
	                    pq.add(new int[]{nDist, nNode});
	                }
	            }
	        }
	        
            //a, b로 가는 길이 있다면 그만큼 비용을 더하고, 없다면 -1을 더함
	        res += dist[a] == 100000000 ? -1 : dist[a];
	        res += dist[b] == 100000000 ? -1 : dist[b];
	    }
	    
	    System.out.print(res);
	}
	
	static int nextInt() throws IOException {
	    st.nextToken();
	    return (int) st.nval;
	}
}