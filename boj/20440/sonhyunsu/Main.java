//제출번호: 90135435
//메모리:   346472 KB
//실행시간: 900 ms
import java.util.*;
import java.io.*;

//입장 시각과 퇴장 시각의 범위가 매우 넓으므로 단순히 시각 배열을 이용해서 구하기는 힘듦
//모기의 입장, 퇴장 시각(구간의 양 끝)에만 값이 변화하고 그 사이는 값의 변화가 없기 때문에
//그 특성을 이용해 입장과 퇴장 시각만 사용함. 
public class Main {
    
    static List<Data> times = new ArrayList<>();
    static ArrayDeque<Integer> dq = new ArrayDeque<>();
    
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    
	    int n = Integer.parseInt(br.readLine());
	    StringTokenizer st;
	    for (int i = 1; i <= n; i++) {
	        st = new StringTokenizer(br.readLine());
	        int enter = Integer.parseInt(st.nextToken());
	        int leave = Integer.parseInt(st.nextToken());
	        
	        times.add(new Data(enter, i));
	        times.add(new Data(leave, -i));
	    }
	    
	    times.sort((e1, e2) -> {
	        if (e1.time == e2.time) {
                //시간이 같다면 퇴장을 먼저 처리하도록 한다.
	            return Integer.compare(e1.order, e2.order);
	        }
	        
            //입퇴장 시각을 우선적으로 정렬한다.
	        return Integer.compare(e1.time, e2.time); 
	    });
	    
	    int max = 0;
	    int enterRange = -1;
	    int leaveRange = -1;
	    
	    for (Data time : times) {
	        if (time.order > 0) {
                //입장한다면 스택에 입장 시각을 기록한다.
	            dq.addLast(time.time);
	        } else {
                //퇴장한다면 현재 최댓값과 비교해 더 크다면 구간을 갱신한다.
	            if (dq.size() > max) {
	                max = dq.size();
	                enterRange = dq.getLast();
	                leaveRange = time.time;
                //만약에 최댓값은 같은데 구간이 이어진다면 구간을 연장한다.
	            } else if (dq.size() == max && leaveRange == dq.getLast()) {
	                leaveRange = time.time;
	            }
                //가장 최근 입장 시각을 스택에서 제외한다.
	            dq.pollLast();
	        }
	    }
	    
	    System.out.println(max);
	    System.out.printf("%d %d", enterRange, leaveRange);
 	}
	
	static class Data {
	    int time;
	    int order;
	    
	    Data(int time, int order) {
	        this.time = time;
	        this.order = order;
	    }
	}
}