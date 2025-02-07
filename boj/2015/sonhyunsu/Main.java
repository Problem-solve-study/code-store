//제출번호: 89721238
//메모리:   34984 KB
//실행시간: 352 ms
import java.io.*;
import java.util.*;

//처음에는 투포인터로 접근, 하지만 음수 떄문에 투포인터의 이동 조건을 설정할 수 없음
//n이 20만으로 크기 때문에 사용할 알고리즘은 최대 NlgN까지 가능
//누적합을 사용 해야하는 것은 알았지만 다음을 생각하기 어려웠음 (이분탐색 사용 불가능)
//--- 해설 ---
//[a, b] : a부터 b까지의 합
//[a, b] == [1, b] - [1, a-1] == k
//<==> [1, b] - k == [1, a-1]
//따라서 b 위치에서 가능한 a의 개수를 모두 더하면 정답을 얻을 수 있음
//[1, a-1]은 Map을 이용해 저장하면 개수를 바로 가져올 수 있음
public class Main {
	public static void main(String[] args) throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    
	    int n = Integer.parseInt(st.nextToken());
	    int k = Integer.parseInt(st.nextToken());
	    
	    st = new StringTokenizer(br.readLine());
	    long sum = 0; 
	    long res = 0; //(20만 * 20만 / 2)는 int 범위를 넘어가므로 long으로 설정
	    Map<Long, Integer> sumCount = new HashMap<>();
	    
	    //아무것도 더하지 않은 것도 하나의 가짓수이므로 1을 저장해둠
	    sumCount.put(0L, 1);
	    for (int i = 0; i < n; i++) {
	        sum += Integer.parseInt(st.nextToken()); //누적합 갱신
	        res += sumCount.getOrDefault(sum - k, 0); //[1, b] - k를 만족하는 [1, a-1]의 개수를 가져옴
	        sumCount.put(sum, sumCount.getOrDefault(sum, 0) + 1); //[1, i]는 새로운 [1, a-1]이 되어 Map에 갱신
	    }
	    
	    System.out.print(res);
	}
}