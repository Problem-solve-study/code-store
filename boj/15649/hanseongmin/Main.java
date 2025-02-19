import java.io.*;
import java.util.*;

/*
34164KB, 320ms

순열 기초문제. 순열을 뽑아 그걸 그대로 출력하기
 */

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StreamTokenizer st = new StreamTokenizer(br);
    static StringBuilder sb = new StringBuilder();
    static boolean[] visited;
    static int n, m;
    
    public static void main(String[] args) throws Exception {
    	n = nextInt();
    	m = nextInt();
    	visited = new boolean[n];
    	
    	//순열 추출
    	dfs(0, new int[m]);
    	
    	bw.write(sb.toString());
    	bw.flush();
    }
    
    static void dfs(int cnt, int[] arr) {
    	//m개를 다 뽑았으면 출력
    	if (cnt == m) {
    		Arrays.stream(arr).forEach(e -> sb.append(e).append(" "));
    		sb.append('\n');
    		return;
    	}
    	
    	for (int i = 0; i < n; i++) {
    		//현재 위치에 들어갈 원소 배정
    		if (!visited[i]) {
    			visited[i] = true;
    			arr[cnt] = i + 1;
    			dfs(cnt + 1, arr);
    			visited[i] = false;
    		}
    	}
    }
    
    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}