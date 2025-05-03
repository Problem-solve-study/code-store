//제출번호: 93858808
//메모리:   77564 KB
//실행시간: 420 ms
import java.io.*;
import java.util.*;

//펭귄으로부터 가장 가까운 지지대 2개만 고르면 됨 - bfs를 이용해서 탐색
//꼼수로 지지대 -> 펭귄 방향 그래프 탐색으로 풀어보려고 했다가 2틀
public class Main {
    
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    
	public static void main(String[] args) throws IOException {
	    int n = nextInt();
	    int s = nextInt();
	    int p = nextInt();
	    
	    List<List<Integer>> graph = new ArrayList<>();
	    for (int i = -1; i < n; i++) {
	        graph.add(new ArrayList<>());
	    }
	    
	    for (int i = 1; i < n; i++) {
	        int a = nextInt();
	        int b = nextInt();
	        
	        graph.get(a).add(b);
	        graph.get(b).add(a);
	    }
	    
	    Queue<int[]> q = new ArrayDeque<>();
	    boolean[] visited = new boolean[n + 1];
	    
	    boolean isChecked = false;
	    int res = 0;
	    q.add(new int[]{p, 0}); //펭귄의 위치를 시작점으로 잡음
	    while (!q.isEmpty()) {
	        int[] item = q.poll();
	        
			//만약 지지대라면
	        if (item[0] <= s) {
                res += item[1]; //결과에 저장
                
	            if (isChecked) {
					//2개를 모두 골랐으면 탐색 종료
	                break;
	            } else {
	                isChecked = true;
	            }
	        }
	        
	        for (int nNode : graph.get(item[0])) {
	            if (!visited[nNode]) {
	                visited[nNode] = true;
	                q.add(new int[]{nNode, item[1] + 1});
	            }
	        }
	    }
	    
		//2개의 지지대까지의 경로에 있는 얼음과 펭귄이 있는 얼음을 제외하고 모두 부술 수 있음
	    System.out.print(n - res - 1);
	}
	
	static int nextInt() throws IOException {
	    st.nextToken();
	    return (int) st.nval;
	}
}