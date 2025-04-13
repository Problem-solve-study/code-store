//Memory 36692kb Time 304ms

import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class Main{
	static int n, s, d;
	static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
	static int[] parent;
	static boolean[] isLeaf;
	
	static void input() throws IOException{
		n = nextInt();
		s = nextInt();
		d = nextInt();
		parent = new int[n+1];
		isLeaf = new boolean[n+1];
		for(int i =0;i<=n;i++) {
			tree.add(new ArrayList<Integer>());
		}
		
		for(int i =1;i<n;i++) {
			int u = nextInt();
			int v = nextInt();
			
			tree.get(u).add(v);
			tree.get(v).add(u);
		}
	}
	
	static void dfs() {//리프노드 탐색
		Stack<Integer> stack = new Stack<>();
	    stack.push(s);
	    boolean[] visit = new boolean[n+1];
	    visit[s] = true;

	    while (!stack.isEmpty()) {
	        int node = stack.pop();
	        boolean leaf = true;

	        for (int next : tree.get(node)) {
	            if (!visit[next]) {
	                leaf = false;
	                parent[next] = node;
	                visit[next] = true;
	                stack.push(next);
	            }
	        }

	        isLeaf[node] = leaf;
	    }
	}
	
	static int getDistance() { //리프 노드 기준으로 거리 측정
		boolean[] visit = new boolean[n+1];
		int cnt = 0;
		parent[s] = 0;
		visit[0] = true;
		visit[s] = true;
		for(int i=1;i<=n;i++) {
			if(!isLeaf[i]) continue;
			int k = i;
			
			for(int j=0;j<d;j++) { //던지는 장소까지 이동
				if (k == 0) break;
				k = parent[k];
			}
			//던지는 장소가 Root 이거나 이미 방문한 장소라면 PASS
			while(!visit[k]) {
				cnt+=1;
				visit[k] = true;
				k= parent[k];
			}
		}
		return cnt*2;
	}
	
	public static void main(String[] args) throws IOException {
		input();
		
		dfs();
		StringBuilder sb = new StringBuilder().append(getDistance());
		System.out.print(sb);
	}
	
	static int nextInt() throws IOException{
        int c;
        while((c=System.in.read())<=32);
        int n = c&15;
        while ((c = System.in.read()) >47)
            n=(n<<3)+(n<<1)+(c&15);

        return n;
    }
}