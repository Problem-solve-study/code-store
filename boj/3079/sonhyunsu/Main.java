//제출번호: 89811992
//메모리:   23068 KB
//실행시간: 272 ms
import java.util.*;
import java.io.*;

//n이 10만으로 크므로 사용가능한 알고리즘은 NlgN으로 한정됨
//m이 10억으로 매우 크므로 심사를 하나씩 시뮬레이션 할 수 없음
//보통 이렇게 n 또는 m이 크면서 최댓값/최솟값을 구하라는 경우 파라메트릭 서치(정답을 이분 탐색함)를 이용하는 경우가 많음
//NlgN = 결정함수(N) * 이분 탐색(lgN) | 이 문제는 엄밀하게 Nlg(M * max(T)) 인듯..
//여기서는 시간이 주어졌을 때 심사대마다 심사할 수 있는 인원의 합을 구한 다음 m과 비교하는 방법을 사용함 
public class Main {
	public static void main(String[] args) throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    
	    int n = Integer.parseInt(st.nextToken());
	    int m = Integer.parseInt(st.nextToken());
	    
	    int[] reviewTimes = new int[n];
	    for (int i = 0; i < n; i++) {
    	    reviewTimes[i] = Integer.parseInt(br.readLine());
	    }
	    
        //right값을 설정할 때 최악을 계산해야 하는데,
        //여기서는 심사대가 1개이면서 한 명당 10억의 시간이 걸리고, m이 10억이라고 가정하면
        //총 걸리는 시간은 10억 * 10억이라 1e18을 가장 오래 걸리는 시간으로 두고 이분 탐색을 진행
	    long left = 0, right = (long) 1e18, mid;
	    while (left <= right) {
	        mid = (left + right) / 2;
	        
	        long reviewablePeople = 0;
	        for (int i = 0; i < n; i++) {
	            reviewablePeople += mid / reviewTimes[i]; //심사대마다 심사할 수 있는 인원을 더함
	            
                //m보다 커지면 더 이상 계산할 필요가 없으므로 break
                //이 코드가 없으면 reviewablePeople이 오버플로우 날 수 있음. ★조심★
	            if (reviewablePeople > m) {
	                break;
	            }
	        }
	        
            //만약 mid 시간에 심사할 수 있는 인원이 m보다 많으면 시간을 좀 더 줄여봄
	        if (reviewablePeople >= m) {
	            right = mid - 1;
	        } else { //심사할 수 있는 인원이 m보다 적으면 시간을 좀 더 늘림
	            left = mid + 1;
	        }
	    }
	    
        //마지막에 left를 출력할 지, right를 출력할 지는
        //left == right일 때 left가 1 증가하는지, right가 1 감소하는지 보면 쉽게 판단할 수 있다.
        //여기서는 mid 시간에 m명을 심사할 수 있으면 right가 1 감소하고 m명을 심사할 수 없으며 left가 1 증가한다.
        //다르게 말하면 최종적으로 left는 m명을 심사할 수 있는 시간의 최솟값을 가지고, right는 m명을 심사할 수 없는 시간의 최댓값을 가진다.
	    System.out.print(left);
	}
}