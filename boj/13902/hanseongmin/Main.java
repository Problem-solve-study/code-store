import java.io.*;
import java.util.*;

/*
 * 22772KB, 836ms
 * 
 * 그리디인가 싶어서 이리저리 해보다가 결국 완탐스럽게 접근해야하는거 같아 DP로 풀었다.
 * 두 개의 웍으로 동시 조리가 가능하므로 특정한 원소의 합 역시 하나의 웍으로 취급할 수 있다. 
 * 따라서 웍을 모두 입력받은 후 가능한 모든 조합을 확인하여 원소의 합에 해당하는 값도 추가로 넣어준다.
 * 
 * dp[i]: 짜장면의 수가 i일 때 조리할 수 있는 최소의 요리 횟수로 테이블을 정의하자.
 * 초기화로는 웍의 값에 해당하는 곳에 1을 넣어준다.
 * 
 * 이후 테이블을 왼쪽부터 시작하여 현재 테이블이 초깃값이 아니라면 어떤 방식으로든 현재 짜장면의 수를 조리할 수 있는 상태이므로
 * 웍의 값들을 순회하며 테이블을 갱신한다.
 * 
 * 웍의 조합을 구할 때 무작정 list에 넣었었는데 중복된 값이 너무 많이 나와 1틀
 * 이후 set으로 변경하여 맞았다.
 */

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	
	public static void main(String[] args) throws Exception {
		int N = nextInt(); int M = nextInt();
		ArrayList<Integer> list = new ArrayList<>();
		TreeSet<Integer> set = new TreeSet<>();
		//웍을 입력
		for (int i = 0; i < M; i++) {
			list.add(nextInt());
			set.add(list.get(list.size() - 1));
		}
		
		//웍의 가능한 모든 조합을 추가적으로 add
		for (int i = 0; i < M; i++) {
			for (int j = i + 1; j < M; j++) {
				set.add(list.get(i) + list.get(j));
			}
		}
		
		//dp의 초기화
		int[] dp = new int[N + 1];
		//일단 적당히 큰 값을 넣어두고
		Arrays.fill(dp, 100_000_000);
		//1번의 조리로 가능한 경우를 1로 갱신
		for (int e : set) {
			if (e <= N) {
				dp[e] = 1;
			}
		}
		
		//이제 테이블의 맨 왼쪽부터 보기 시작하여
		for (int i = 1; i <= N; i++) {
			//최초값이 아니라면 i개의 짜장면을 만들 수 있는 경우이므로 테이블 갱신 시작
			if (dp[i] != 100_000_000) {
				//조리 가능한 웍을 모두 보면서
				for (int e : set) {
					if (i + e <= N) {
						//현재 짜장면 개수에서 추가적으로 조리가능한 짜장면의 개수를 갱신시킨다. 
						dp[i + e] = Math.min(dp[i + e], dp[i] + 1);
					} else {
						break;
					}
				}
			}
		}
		
		System.out.print(dp[N] == 100_000_000 ? -1 : dp[N]);
	}

	static int nextInt() throws Exception {
		st.nextToken();
		return (int) st.nval;
	}
}