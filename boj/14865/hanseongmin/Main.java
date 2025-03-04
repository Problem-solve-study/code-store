import java.io.*;
import java.util.*;

/*
71932KB, 832ms

애초에 문제를 잘못 읽어서 계속 붙잡고 있었던 문제.
솔직히 스택을 쓴다는 생각이 안나서 스택 안쓰고 그냥 풀었다. 봉우리만 구했다면 스택을 쓰든 아니든
O(N)에 풀 수 있다.

주의할만한 사항은 3가지 정도 있는듯
1. 봉우리 좌표 저장할 때 y좌표의 곱이 0보다 작은지 여부로 판별하게 되면 오버플로우 발생
2. 좌표는 좌하단에서부터 주어지는 것이 아님. 방향이 그렇다는 것.
3. 시작 좌표가 y 기준 양수일 때를 주의.
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int N;
    
    static class Line implements Comparable<Line> {
        int s;
        int e;
        
        Line(int s, int e) {
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Line o) {
            int comp1 = Integer.compare(s, o.s);
            if (comp1 != 0) return comp1;
            return Integer.compare(e, o.e);
        }
    }
    
    public static void main(String[] args) throws Exception {
    	N = nextInt();
    	
    	//시작 좌표를 구하기 위해 입력을 받으며 좌하단 좌표를 구함
    	ArrayList<Line> inputs = new ArrayList<>();
    	int idx = 0;
    	Line start = null;
    	for (int i = 0; i < N; i++) {
    		Line cur = new Line(nextInt(), nextInt());
    		inputs.add(cur);
    		if (start == null) {
    			idx = i;
    			start = inputs.get(inputs.size() - 1);
    		} else {
    			if (start.s > cur.s) {
    				idx = i;
    				start = cur;
    			} else if (start.s == cur.s) {
    				if (start.e > cur.e) {
    					idx = i;
    					start = cur;
    				}
    			}
    		}
    	}
    	
    	//이후 봉우리를 추출
    	//만일 시작 지점의 위치가 y 기준 양수일 경우 최초로 식별된 x 좌표는 무시하고
    	//마지막으로 식별된 좌표와 이어줘야 함.
    	ArrayList<Line> mountains = new ArrayList<>();
        Integer s = null;
        Integer e = null;
        int x1 = start.s;
        int y1 = start.e;
        Integer temp = null;
        boolean isPlus = y1 > 0;
        idx = (idx + 1) % N;
        for (int i = 0; i < N - 1; i++) {
            int x2 = inputs.get(idx).s;
            int y2 = inputs.get(idx).e;
            
            if ((y1 > 0 && y2 < 0) || (y1 < 0 && y2 > 0)) {
                if (s == null) {
                	if (isPlus && temp == null) {
                		temp = x1;
                	} else {
                		s = x1;
                	}
                } else {
                    e = x1;
                    mountains.add(new Line(Math.min(s, e), Math.max(s, e)));
                    s = null; e = null;
                }
            }
            
            x1 = x2;
            y1 = y2;
            idx = (idx + 1) % N;
        }
        if (isPlus) {
        	mountains.add(new Line(Math.min(temp, s), Math.max(temp, s)));
        }
        //시작점 기준으로 정렬
        Collections.sort(mountains);
        
        int size = mountains.size();
        //다른 봉우리를 포함 중
        boolean[] b1 = new boolean[size];
        //다른 봉우리에 포함 됨
        boolean[] b2 = new boolean[size];
        for (int i = 0; i < size; i++) {
            Line cur = mountains.get(i);
            
            for (int j = i + 1; j < size; j++) {
                Line next = mountains.get(j);
                if (next.s < cur.e) {
                	//i번째 봉우리는 다른 봉우리를 포함 중
                    b1[i] = true;
                    //j번째 봉우리는 i번째 봉우리에 포함됨
                    //이전 봉우리에서 포함 여부를 확인했다면 그 이후로는 확인할 필요가 없다.
                    if (!b2[j]) {
                        b2[j] = true;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        
        int cnt1 = 0;
        int cnt2 = 0;
        for (int i = 0; i < size; i++) {
            if (!b1[i]) cnt1++;
            if (!b2[i]) cnt2++;
        }
        
        System.out.printf("%d %d", cnt2, cnt1);
    }
    
    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}