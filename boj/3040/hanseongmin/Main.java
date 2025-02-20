import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StreamTokenizer st = new StreamTokenizer(br);
    static StringBuilder sb = new StringBuilder();
    static int[] arr = new int[9];
    static int[] sel = new int[7];
    static boolean find = false;
    
    public static void main(String[] args) throws Exception {
    	//배열 입력
    	for (int i = 0; i < 9; i++) {
    		arr[i] = nextInt();
    	}
    	
    	//조합 탐색
    	dfs(0, 0, 0);
    	bw.write(sb.toString());
    	bw.flush();
    }
    
    static void dfs(int cnt, int idx, int sum) {
    	//선택된 수가 7개면 종료
    	if (cnt == 7) {
    		//합이 100이면 출력 후 플래그 설정
    		if (sum == 100) {
        		find = true;
        		Arrays.stream(sel).forEach(e -> sb.append(e).append('\n'));
    		}
    		return;
    	}
    	//정답을 찾았으면 더 탐색할 필요가 없음
    	if (find) return;
    	//합이 100을 넘긴 상태면 더 탐색할 필요가 없음
    	if (sum > 100) return;
    	
    	//조합
    	for (int i = idx; i < 9; i++) {
    		sel[cnt] = arr[i];
    		dfs(cnt + 1, i + 1, sum + arr[i]);
    	}
    }
    
    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}