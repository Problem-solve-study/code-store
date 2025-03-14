import java.io.*;
import java.util.*;

/*
 * 16512KB, 72ms
 * 
 * BFS + 역추적
 * 요즘 왜 이렇게 역추적 문제가 많이 나오는지 모르겠다.
 * 그 전에 DP로 푼게 있길래 이번엔 BFS + 역추적으로 풀었다.
 * 역추적은 BFS를 수행하며 추가 배열을 사용해서 자신의 부모를 기록한 뒤 재귀적으로 추적한다.
 */

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	static int N;
	static int[] path;
	static boolean[] v;
	static ArrayList<Integer> list = new ArrayList<>();
	
    public static void main(String[] args) throws Exception {
    	N = nextInt();
    	path = new int[N + 1];
    	v = new boolean[N + 1];

        //BFS 로직
    	Queue<Integer> q = new ArrayDeque<>();
    	q.add(N);
    	v[N] = true;
    	while (!q.isEmpty()) {
    		int cur = q.remove();
    		if (cur == 1) {
    			break;
    		}
    		
    		if (cur % 3 == 0 && !v[cur / 3]) {
    			q.add(cur / 3);
    			v[cur / 3] = true;
    			path[cur / 3] = cur;
    		}
    		if (cur % 2 == 0 && !v[cur / 2]) {
    			q.add(cur / 2);
    			v[cur / 2] = true;
    			path[cur / 2] = cur;
    		}
    		if (!v[cur - 1]) {
    			q.add(cur - 1);
    			v[cur - 1] = true;
    			path[cur - 1] = cur;
    		}
    	}

        //역추적
    	getPath(1);
    	StringBuilder sb = new StringBuilder();
    	for (int i = list.size() - 1; i >= 0; i--) {
    		sb.append(list.get(i)).append(' ');
    	}
    	
    	System.out.println(list.size() - 1);
    	System.out.println(sb);
    }
    
    static void getPath(int i) {
        //처음 들어온 1을 추가
    	if (i == 1) list.add(1);
        //끝까지 도달했으면 탈출
    	if (path[i] == 0) return;
    	list.add(path[i]);
    	getPath(path[i]);
    }
    
    static int nextInt() throws Exception {
    	st.nextToken();
    	return (int) st.nval;
    }
}