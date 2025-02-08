//제출번호: 89787814
//메모리:   159592 KB
//실행시간: 808 ms
import java.io.*;
import java.util.*;

//문제를 보고 건물을 잇는 최소 도로라는 말을 보고 최소 신장 트리를 가장 먼저 떠올림
//문제에서는 오르막길을 최대한 많이 선택하는 경우와 내리막길을 최대한 많이 선택하는 경우
//총 2가지를 구한 다음에 각각의 피로도의 차이를 답으로 출력
//여기서는 간선 정보만 저장한 다음 크루스칼 알고리즘 적용 (간선 정보 + 유니온 파인드)
public class Main {
    static List<Edge> edges = new ArrayList<>();
    static int[] p = new int[1001];
    static int n;
    
	public static void main(String[] args) throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    
	    n = Integer.parseInt(st.nextToken());
	    int m = Integer.parseInt(st.nextToken());
	    
	    for (int i = -1; i < m; i++) {
	        st = new StringTokenizer(br.readLine());
            //간선 정보를 저장할 때 오르막길을 1, 내리막길을 0으로 저장한다. 
	        edges.add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) ^ 1));
	    }
	    
        //아래 코드에서는 선택된 간선이 n-1개 일 때 반복문을 종료하는 코드가 없지만
        //시간을 좀 더 최적화하고 싶다면 넣는 게 좋음
	    int up = 0;
	    resetUnion(); //집합 관계 초기화
	    edges.sort((e1, e2) -> -Integer.compare(e1.isUp, e2.isUp)); //오르막길이 우선적으로 선택되도로 정렬한다.
	    for (Edge edge : edges) {
	        if (find(edge.a) == find(edge.b)) {
	            continue;
	        }
	        
            //두 집합을 하나로 합친다.
	        union(edge.a, edge.b);
	        up += edge.isUp; //간선이 오르막길이면 개수를 더한다.
	    }
	    
	    int res = up * up; //피로도를 구한다.
	    up = 0; 
	    resetUnion(); //집합 관계 초기화
	    edges.sort((e1, e2) -> Integer.compare(e1.isUp, e2.isUp)); //내리막길이 우선적으로 선택되도록 정렬한다.
	    for (Edge edge : edges) {
	        if (find(edge.a) == find(edge.b)) {
	            continue;
	        }
	        
	        union(edge.a, edge.b);
	        up += edge.isUp;
	    }
	    
	    System.out.print(res - up * up);
    }
    
    static void resetUnion() {
	    for (int i = 0; i <= n; i++) {
	        p[i] = i;
	    }
    }
    
    static void union(int a, int b) {
        p[find(a)] = p[find(b)];
    }
    
    static int find(int k) {
        if (k == p[k]) {
            return k;
        }
        
        //방문할 때 항상 루트 노드를 저장해 find의 시간복잡도를 줄인다.
        return p[k] = find(p[k]);
    }
    
    static class Edge {
        int a;
        int b;
        int isUp;
        
        Edge(int a, int b, int isUp) {
            this.a = a;
            this.b = b;
            this.isUp = isUp;
        }
    }
}