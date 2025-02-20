import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/*
1. 군집 내 인원은 모두 서로를 좋아해야함
2. 군집 외 인원들에 대해서는 모두를 싫어해야함
3. 군집은 적어도 2명 이상으로 구성

그래프가 주어졌을 때 안정 여부를 판별
안정되었다면 군집이 1개인지 여러 개인지를 판별

일단 분리 집합들을 식별하고
군집 내 모든 인원들을 대상으로 모두 간선으로 연결되었는지 판별
N이 100이라서 시간 내에 돌듯
 */

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StreamTokenizer st = new StreamTokenizer(br);
    static StringBuilder sb = new StringBuilder();
    static int[] disjointSet;
    static int[][] mat;
    static int n;
    //메모리 제한이 128로 빡빡하지만 N이 작으므로 터지지 않을 것이라 생각
    static TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<>();
    
    public static void main(String[] args) throws Exception {
    	n = nextInt();
    	disjointSet = new int[n];
    	mat = new int[n][n];
    	//분리집합 초기화
    	IntStream.range(0, n).forEach(e -> disjointSet[e] = e);
    	
    	//인접 행렬 입력
    	for (int i = 0; i < n; i++) {
    		for (int j = 0; j < n; j++) {
    			mat[i][j] = nextInt();
    			if (mat[i][j] == 0) union(i, j);
    		}
    	}
    	
    	//각 집합 인원 추출
    	for (int i = 0; i < n; i++) {
    		int p = find(i);
			if (!map.containsKey(p)) {
				map.put(p, new ArrayList<>());
			}
			
			map.get(p).add(i);
    	}
    	
    	ArrayList<ArrayList<Integer>> result = new ArrayList<>();
    	//안정된 집단인지 여부를 판별
    	outer:
    	for (Map.Entry<Integer, ArrayList<Integer>> e : map.entrySet()) {
    		int p = e.getKey();
    		ArrayList<Integer> people = e.getValue();
    		//군집은 적어도 2명이상으로 구성되어야 함
    		if (people.size() == 1) {
    			sb.append(0);
    			break outer;
    		}
    		
    		for (int target : people) {
    			//0의 개수가 people의 크기와 같아야
    			//군집 내 모든 인원은 모두 좋아하고
    			//군집 외 모든 인원은 싫어하는 것
    			int cnt = (int) Arrays.stream(mat[target]).filter(edge -> edge == 0).count();
    			if (cnt != people.size()) {
    				sb.append(0);
    				break outer;
    			}
    		}
    		
    		//위 루프를 무사히 빠져나왔다면 일단 안정된 집단
    		//오름차순으로 출력해야하므로 정렬
    		Collections.sort(people);
    		result.add(people);
    	}
    	
    	if (sb.length() == 0) {
    		bw.write(String.valueOf(map.size()) + '\n');
    		
	    	//소집단 간에서는 소집단에 속하는 제일 작은 번호의 오름차순으로 출력
	    	//정렬 기준을 첫 번째 원소로 설정
	    	Collections.sort(result, (l1, l2) -> {
	    		return Integer.compare(l1.get(0), l2.get(0));
	    	});
	    	
	    	for (ArrayList<Integer> l : result) {
	    		l.forEach(e -> sb.append(e + 1).append(" "));
	    		sb.append('\n');
	    	}
    	}
    	
    	bw.write(sb.toString());
    	bw.flush();
    }
    
    static int find(int i) {
    	if (disjointSet[i] == i) {
    		return i;
    	}
    	
    	return disjointSet[i] = find(disjointSet[i]);
    }
    
    static void union(int a, int b) {
    	int aP = find(a);
    	int bP = find(b);
    	if (aP != bP) {
    		disjointSet[a] = bP;
    	}
    }
    
    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}