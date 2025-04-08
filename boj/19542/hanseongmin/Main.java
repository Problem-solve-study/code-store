import java.io.*;
import java.util.*;

/*
 * 44616KB, 284ms
 * 
 * 쉬운 트리 문제. 심지어 루트도 준다.
 * 트리를 구성하고, 구성하면서 동시에 자신의 자식들 중 리프 노드인 자식과의 거리의 최댓값을 노드마다 저장해준다.
 * 해당 값이 D보다 클 경우 현재 힘으로 모든 노드에게 전달할 수 없는 경우이니 그때만 노드를 방문하고
 * 그게 아니면 방문하지 않는다.
 * 왔다갔다 해야하니 방문할 때마다 카운트를 2씩 증가시키고 값을 출력하면 된다.
 */

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	static int N, S, D, res;
	static ArrayList<Integer>[] adj;
	static Node[] nodes;
	static class Node {
		int n; //자신이 몇 번째 노드인지
		Node parent; //자신의 부모가 누구인지
		int distance; //자신의 자식들 중 리프 노드인 자식들과의 거리 최댓값
		
		Node(int n) {
			this.n = n;
		}
	}
	
	public static void main(String[] args) throws Exception {
		N = nextInt(); S = nextInt(); D = nextInt();
		adj = new ArrayList[N + 1];
		nodes = new Node[N + 1];
		for (int i = 0; i <= N; i++) {
			adj[i] = new ArrayList<>();
			nodes[i] = new Node(i);
		}
		
		for (int i = 0; i < N - 1; i++) {
			int a = nextInt();
			int b = nextInt();
			adj[a].add(b);
			adj[b].add(a);
		}
		
		Node root = nodes[S];
		root.parent = root;
		root.distance = makeTree(root);
		getAns(root);
		System.out.print(res);
	}
	
	//트리를 만들면서 거리 갱신
	static int makeTree(Node cur) {
		int distance = 0;
		
		for (int next : adj[cur.n]) {
			Node nextNode = nodes[next];
			if (nextNode.n == cur.parent.n) continue;
			nextNode.parent = cur;
			nextNode.distance = makeTree(nextNode) + 1;
			distance = Math.max(distance, nextNode.distance);
		}
		
		return distance;
	}
	
	//그 다음 트리를 순회한다.
	static void getAns(Node node) {
		for (int next : adj[node.n]) {
			Node nextNode = nodes[next];
			if (nextNode.n == node.parent.n) continue;
			if (nextNode.distance > D) {
				res++;
				getAns(nextNode);
				res++;
			}
		}
	}
	
	static int nextInt() throws Exception {
		st.nextToken();
		return (int) st.nval;
	}
}