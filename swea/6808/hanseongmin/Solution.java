import java.io.*;
import java.util.*;

/*
27956KB, 2408ms

문제에서 친절히 순열 문제라고 알려주고 있다. 순열을 뽑고 주어진 조건대로 연산해주면 된다.
 */

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StreamTokenizer st = new StreamTokenizer(br);
    static int w, l;
    static ArrayList<Integer> card1;
    static ArrayList<Integer> card2;
    static boolean[] visited;
    
    public static void main(String[] args) throws Exception {
    	int T = nextInt();
    	StringBuilder sb = new StringBuilder();
    	for (int t = 1; t <= T; t++) {
    		visited = new boolean[9];
    		w = 0;
    		l = 0;
    		card1 = new ArrayList<>();
    		card2 = new ArrayList<>();
    		
    		//규영이의 카드를 입력받음
    		for (int i = 0; i < 9; i++) {
    			card2.add(nextInt());
    		}
    		
    		//인영이의 카드를 뽑음
    		for (int i = 1; i <= 18; i++) {
    			if (!card2.contains(i)) {
    				card1.add(i);
    			}
    		}
    		
    		//순열 뽑기
    		dfs(0, new int[9]);
    		sb.append(String.format("#%d %d %d\n", t, w, l));
    	}
    	
    	bw.write(sb.toString());
    	bw.flush();
    }
    
    static void dfs(int cnt, int[] sel) {
    	//인영이의 카드를 고름
    	if (cnt == 9) {
    		//한 장씩 카드를 낸 다음 카드에 적힌 수를 비교해서 점수 계산
    		int score1 = 0;
    		int score2 = 0;
    		for (int i = 0; i < 9; i++) {
    			//이겼다면 두 카드에 적힌 수의 합만큼 점수를 얻음
    			if (sel[i] > card2.get(i)) {
    				score1 += (sel[i] + card2.get(i));
    			} else if (sel[i] < card2.get(i)) {
    				score2 += (sel[i] + card2.get(i));
    			}
    		}
    		
    		//score1이 이겼다면 규영이가 패배한 것
    		if (score1 > score2) {
    			l++;
    		} else if (score1 < score2) {
    			w++;
    		}
    		return;
    	}
    	
    	//순열 뽑기
    	for (int i = 0; i < 9; i++) {
    		if (!visited[i]) {
    			visited[i] = true;
    			sel[cnt] = card1.get(i);
    			dfs(cnt + 1, sel);
    			visited[i] = false;
    		}
    	}
    }
    
    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}