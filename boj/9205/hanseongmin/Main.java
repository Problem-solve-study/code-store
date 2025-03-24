import java.io.*;
import java.util.*;

/*
 * 13796KB, 108ms
 * 
 * 다음 목표거리까지 1000 이하라면 갈 수 있음
 * 그게 아니라면 불가능
 * 다음 지점으로 갈 수 있다면 가는 것이 이득? 
 * 현재 위치에서 바로 목적지까지 도달할 수 있는데 다음 편의점이 더 가까워서 목적지로 도달못하는 경우가 있나?
 * 있다. 그리디로는 문제가 있을지도
 * 
 * 그럼 어떡하지 BFS 돌리면 가능할 것 같은데..
 * 값의 범위가 -32768 ~ 32767이니까 2차원으로 배열 만들어버리면 터질 것 같은데.
 * 트리맵이랑 셋으로 정점이랑 간선 관리하면 되려나? 메모리가 128MB라서 맵, 셋 쓰면 터질수도? 일단 해보자
 * 하고 AC
 */

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	static final String HAPPY = "happy";
	static final String SAD = "sad";
	static int T;
	static int n;
	static Vertex[] vertex;
	static TreeMap<Vertex, ArrayList<Vertex>> adj;
	static TreeSet<Vertex> v;
	static StringBuilder sb = new StringBuilder();
	
	//정점 커스텀 클래스
	//트리셋, 맵을 이용하기 위해, 즉 Comparable을 사용하기 위해 선언해서 사용
	//hashCode, equels 오버라이딩하고 해시셋, 맵을 사용할 수도 있는데
	//2개나 오버라이딩해야하고 IDE 없는 상황에서 작성하기가 까다로워
	//이런 경우 보통 트리셋, 맵 사용하고 Comparable 상속하는 편
	static class Vertex implements Comparable<Vertex> {
		int x;
		int y;
		
		public Vertex(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Vertex o) {
			int comp1 = Integer.compare(x, o.x);
			if (comp1 != 0) return comp1;
			return Integer.compare(y, o.y);
		}
	}
	
    public static void main(String[] args) throws Exception {
    	T = nextInt();
    	for (int t = 0; t < T; t++) {
	    	n = nextInt();
	    	vertex = new Vertex[n + 2];
	    	for (int i = 0; i < n + 2; i++) {
	    		vertex[i] = new Vertex(nextInt(), nextInt());
	    	}
	    	
	    	adj = new TreeMap<>();
	    	v = new TreeSet<>();
			//n이 작아서 그냥 i, j 모두 0부터 돌림
	    	for (int i = 0; i < n + 2; i++) {
	    		adj.putIfAbsent(vertex[i], new ArrayList<>());
	    		for (int j = 0; j < n + 2; j++) {
	    			if (i == j) continue;
					//맨해튼 거리가 1000 이하라면 갈 수 있는 정점임 -> 간선을 추가해줌
	    			if (Math.abs(vertex[i].x - vertex[j].x) + Math.abs(vertex[i].y - vertex[j].y) <= 1000) {
	    				adj.get(vertex[i]).add(vertex[j]);
	    			}
	    		}
	    	}
	    	
			//BFS 로직
	    	Queue<Vertex> q = new ArrayDeque<>();
	    	q.add(vertex[0]);
	    	v.add(vertex[0]);
	    	boolean isHappy = false;
	    	while (!q.isEmpty()) {
	    		Vertex cur = q.remove();
				//compareTo가 0이다 -> 같다.
	    		if (cur.compareTo(vertex[n + 1]) == 0) {
	    			isHappy = true;
	    			break;
	    		}
	    		
	    		for (Vertex next : adj.get(cur)) {
	    			if (!v.contains(next)) {
	    				v.add(next);
	    				q.add(next);
	    			}
	    		}
	    	}
	    	
	    	if (isHappy) {
	    		sb.append(HAPPY);
	    	} else {
	    		sb.append(SAD);
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