//제출번호:	93114895
//메모리:	11952 KB
//실행시간:	100 ms
import java.io.*;

//N이 10이므로 외판원 순회를 그냥 N!돌았음
public class Main {
    
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int n;
    static int[][] matrix;
    
	public static void main(String[] args) throws IOException {
	    n = nextInt();
	    matrix = new int[n][n];
	    for(int i = 0; i < n; i++) {
	        for (int j = 0; j < n; j++) {
	            matrix[i][j] = nextInt();
	        }
	    }
	    
	    System.out.print(tsp((1 << n) - 2, 0));
	}
	
	static int tsp(int canGoSet, int city) {
		//모든 도시를 다 돌았다면 0번 도시로 돌아갈 길이 있는지 한 번 확인해 봄
	    if (canGoSet == 0) {
	        return matrix[city][0] == 0 ? Integer.MAX_VALUE : matrix[city][0];
	    }
	    
	    int cost = Integer.MAX_VALUE;
	    for (int i = 0; i < n; i++) {
			//현재 도시에서 다음 도시로 가는 길이 없다면 다른 도시를 방문해 봄
	        if (matrix[city][i] == 0) {
	            continue;
	        }
	        
			//만약 다음 도시를 아직 방문하지 않았다면
	        if ((canGoSet & 1 << i) != 0) {
				//다음 도시에서 0번 도시로 가는 비용을 구해봄
	            int nextCost = tsp(canGoSet ^ 1 << i, i);
	            
				//만약 못 간다면 다른 도시 방문하기
	            if (nextCost == Integer.MAX_VALUE) {
	                continue;
	            }
	            
				//지금 cost와 ([다음 도시 -> 0번 도시의 비용] + [현재 도시 -> 다음 도시의 비용])의 합 중 작은 것 고르기
	            cost = Math.min(cost, nextCost + matrix[city][i]);
	        }
	    }
	    
	    return cost;
	}
	
	static int nextInt() throws IOException {
	    st.nextToken();
	    return (int) st.nval;
	}
}