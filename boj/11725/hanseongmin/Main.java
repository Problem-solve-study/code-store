import java.io.*;
import java.util.*;

/*
48912KB, 412ms

트리를 어떻게 만드는지 배울 수 있는 좋은 문제
이 문제처럼 트리의 부모와 자식이 불분명한 경우
일단 간선들을 모두 입력받은 후에 재귀적으로 트리를 만들어주면 된다.
 */

public class Main {
	static class Node {
		int num;
		Node parent;
		
		Node(int n, Node p) {
			num = n;
			parent = p;
		}
	}
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StreamTokenizer st = new StreamTokenizer(br);
	static ArrayList<Integer>[] adj;
	static Node[] nodes;
	
	public static void main(String[] args) throws Exception {
		int n = nextInt();
		//인접리스트 및 노드 배열 할당
		adj = new ArrayList[n + 1];
		nodes = new Node[n + 1];
		for (int i = 0; i <= n; i++) {
			adj[i] = new ArrayList<>();
			nodes[i] = new Node(i, null);
		}
		
		//인접리스트에 값 입력받기
		for (int i = 0; i < n - 1; i++) {
			int v1 = nextInt();
			int v2 = nextInt();
			adj[v1].add(v2);
			adj[v2].add(v1);
		}
		
		//재귀적으로 트리 구성
		//1번 노드가 루트임은 확실하므로 1번 노드에서 돌림
		makeTree(nodes[1]);
		
		//트리 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 2; i <= n; i++) {
			sb.append(nodes[i].parent.num).append('\n');
		}
		
		bw.write(sb.toString());
		bw.flush();
	}
	
	static int nextInt() throws Exception {
		st.nextToken();
		return (int) st.nval;
	}
	
	static void makeTree(Node parent) {
		//재귀적으로 트리 생성
		for (int childNum : adj[parent.num]) {
			Node child = nodes[childNum];
			if (childNum != 1 && child.parent == null) {
				child.parent = parent;
				makeTree(child);
			}
		}
	}
}
