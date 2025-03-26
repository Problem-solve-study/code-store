import java.io.*;
import java.util.*;

/*
 * 30512KB, 572ms
 * 
 * 자기가 먹을 수 있는 것들 중 가장 크기가 큰 상어를 먹는 것이 이득이다.
 * 민힙과 맥스힙 2개를 선언하고 맥스힙엔 현재 크기에서 먹을 수 있는 상어들을 넣어준다.
 * 맥스힙에서 root를 꺼내 먹어서 몸집이 커지면 민힙에서 먹을 수 있게 된 상어들을 맥스힙으로 옮긴다.
 * 이 과정을 K만큼 반복하면 답을 구할 수 있다.
 */

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	
    public static void main(String[] args) throws Exception {
    	int N = nextInt();
    	int K = nextInt();
    	long T = nextInt();
    	PriorityQueue<Long> minH = new PriorityQueue<>();
    	PriorityQueue<Long> maxH = new PriorityQueue<>(Collections.reverseOrder());
    	for (int i = 0; i < N; i++) {
    		int cur = nextInt();
    		//먹을 수 있다면 맥스힙에, 없다면 민힙에 저장
    		if (cur < T) {
    			maxH.add((long) cur);
    		} else {
    			minH.add((long) cur);
    		}
    	}
    	
    	for (int i = 0; i < K && !maxH.isEmpty(); i++) {
    		//현재 먹을 수 있는 상어 중 가장 몸집이 큰 상어를 먹고
    		T += maxH.remove();
    		//민힙에 존재하는 상어 중 먹을 수 있게된 상어들을 옮긴다.
    		while (!minH.isEmpty() && T > minH.peek()) {
    			maxH.add(minH.remove());
    		}
    	}
    	
    	System.out.println(T);
    }
    
    static int nextInt() throws Exception {
    	st.nextToken();
    	return (int) st.nval;
    }
}