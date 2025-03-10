import java.io.*;
import java.util.HashMap;

/*
 * 11880KB, 76ms
 * 
 * 처음에 입력받은 차들의 순서가 나올 때도 유지가 되어야 함
 * 문자열을 입력받을 때 들어온 순서를 맵에 저장해둔다.
 * 이후 나온 차들의 들어온 순서를 map에서 꺼내 배열로 만들어놓고
 * 들어왔을 때 나보다 앞에 있었지만 나올 때 뒤에 있다면 해당 차는 추월한 것이므로 
 * 각각의 차를 대상으로 해당 차 뒤에 먼저 터널에 들어왔던 차가 있는지를 탐색
 * 
 * N이 1000이므로 충분히 시간 내에 돈다
 */

public class Main {
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int N = Integer.parseInt(br.readLine());
    	//맵에 원래 들어왔던 순서를 문자열별로 저장한다.
    	HashMap<String, Integer> map = new HashMap<>();
    	for (int i = 0; i < N; i++) {
    		map.put(br.readLine(), i);
    	}
    	
    	//터널에 나온 차들을 대상으로 원래 몇 번째로 들어왔는지를 저장
    	int[] arr = new int[N];
    	for (int i = 0; i < N; i++) {
    		arr[i] = map.get(br.readLine());
    	}
    	
    	int cnt = 0;
    	for (int i = 0; i < N; i++) {
    		boolean flag = false;
    		for (int j = i + 1; j < N; j++) {
    			//나보다 뒤에있는데 들어온 순서는 먼저라면 i번째 차는 추월한 것
    			if (arr[i] > arr[j]) {
    				flag = true;
    				break;
    			}
    		}
    		if (flag) cnt++;
    	}
    	System.out.println(cnt);
    }
}