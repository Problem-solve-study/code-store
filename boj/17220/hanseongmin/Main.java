import java.io.*;
import java.util.*;

/*
11676KB, 72ms

그래프를 구성하고 parent가 없는 마약 원산지를 찾은 후
특정 정점을 제거한 후 트리 순회하며 방문 가능한 정점 카운팅하기.
오늘 A번이 트리 문제라서 당연히 트리라고 생각하면서 풀다가 계속 틀림..
 */

public class Main {
	//노드 객체, 트리가 아니므로 부모도 여러개 있을 수 있음
	static class Node {
		int num;
		HashSet<Integer> parents;
		HashSet<Integer> childs;
		
		Node(int n) {
			num = n;
			parents = new HashSet<>();
			childs = new HashSet<>();
		}
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StreamTokenizer st = new StreamTokenizer(br);
	static HashSet<Integer> src = new HashSet<>();
	static int n, m, res;
	static Node[] nodes;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		//입력
		n = nextInt();
		m = nextInt();
		nodes = new Node[n];
		for (int i = 0; i < n; i++) {
			nodes[i] = new Node(i);
		}
		
		//노드 간 관계 설정
		for (int i = 0; i < m; i++) {
			Node p = nodes[nextToken()];
			Node c = nodes[nextToken()];
			p.childs.add(c.num);
			c.parents.add(p.num);
		}
		
		//원산지 탐색. 원산지는 부모가 없음
		for (int i = 0; i < n; i++) {
			if (nodes[i].parents.isEmpty()) {
				src.add(i);
			}
		}
		
		//경찰이 파악한 노드들을 그래프 상에서 없앰
		int findCnt = nextInt();
		for (int i = 0; i < findCnt; i++) {
			Node target = nodes[nextToken()];
			
			//부모로 올라가서 자식의 레퍼런스를 끊고
			for (int parentNum : target.parents) {
				nodes[parentNum].childs.remove(target.num);
			}			
			//자식으로 내려가서 부모의 레퍼런스도 끊어준 다음
			for (int childNum : target.childs) {
				nodes[childNum].parents.remove(target.num);
			}
			
			//자신의 부모와 자식도 전부 없앰
			target.parents.clear();
			target.childs.clear();
		}
		
		//이제 마약 원산지로부터 그래프 탐색
		visited = new boolean[n];
		for (int srcIdx : src) {
			bfs(srcIdx);
		}
		
		bw.write(String.valueOf(res));
		bw.flush();
	}
	
	static void bfs(int drugSrc) {
		Queue<Integer> q = new ArrayDeque<>();
		q.add(drugSrc);
		visited[drugSrc] = true;
		while (!q.isEmpty()) {
			Node cur = nodes[q.remove()];
			if (!src.contains(cur.num)) {
				res++;
			}
			
			for (int childNum : cur.childs) {
				if (!visited[childNum]) {
					visited[childNum] = true;
					q.add(childNum);
				}
			}
		}
	}
	
	static int nextInt() throws Exception {
		st.nextToken();
		return (int) st.nval;
	}
	
	static int nextToken() throws Exception {
		st.nextToken();
		return st.sval.charAt(0) - 'A';
	}
}
