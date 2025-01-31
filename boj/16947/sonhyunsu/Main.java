//제출번호: 89243288
//메모리:	27380 KB
//실행시간:	312 ms

import java.util.*;
import java.io.*;

public class Main
{
    static boolean[] isVisited = new boolean[3001];
    static boolean isFoundCycle = false;
    static boolean isMeetStart = false;
    static int[] dist = new int[3001];
    static int start = 0;
    static int n;
    static List<Integer>[] graph = new List[3001];
    static Queue<Integer> q = new LinkedList<>();
    
    public static void findCycle(int node, int prev) {
        isVisited[node] = true;
        
        for (int next : graph[node]) {
            if (!isVisited[next]) {
                findCycle(next, node);
                
                if (isFoundCycle) {
                    if (!isMeetStart) {
                        isVisited[node] = true;
                    }
                    
                    if (node == start) {
                        isMeetStart = true;
                    }
                    
                    break;
                }
                
            } else if (next != prev) {
                start = next; //싸이클의 시작점
                isFoundCycle = true;
                
                for (int i = 1; i <= n; i++) {
                    isVisited[i] = false;
                }
                
                isVisited[node] = true;
                break;
            }
        }
        
        if (isFoundCycle && isVisited[node] && graph[node].size() > 2) {
            q.add(node);
        }
    }
    
    public static void calcDist(int node) {
        for (int next : graph[node]) {
            if (!isVisited[next]) {
                isVisited[next] = true;
                dist[next] = dist[node] + 1;
                calcDist(next);
            }
        }
    }
    
	public static void main(String[] args) throws IOException {
	    BufferedReader re = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
	    
	    n = Integer.parseInt(re.readLine());
	    for (int i = 0; i <= n; i++) {
	        graph[i] = new ArrayList<>();
	    }
	    
	    for (int i = 0; i < n; i++) {
	        StringTokenizer st = new StringTokenizer(re.readLine());
	        int x = Integer.parseInt(st.nextToken());
	        int y = Integer.parseInt(st.nextToken());
	        
	        graph[x].add(y);
	        graph[y].add(x);
	    }
	    
	    findCycle(1, 0);
	    
	    while (!q.isEmpty()) {
	        calcDist(q.poll());
	    }
	    
	    for (int i = 1; i <= n; i++) {
	        wr.append(String.format("%d ", dist[i]));
	    }
	    
	    wr.flush();
	}
}