import java.io.*;
import java.util.*;

/*
 * 43920KB, 360ms
 * 
 * 그냥 보자마자 각 쿼리당 N으로 문자열 전체를 훑고 적절히 3번과 4번을 구하면 될 것 같았음.
 * 자세한 풀이는 아래 코드의 주석으로 설명
 */

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while (T --> 0) {
			String W = br.readLine();
			int K = Integer.parseInt(br.readLine());
			//특정한 문자를 정확히 K개 포함해야하므로 
			//각 문자가 어떤 위치에서 등장하는지를 저장하는 맵을 선언
			HashMap<Character, ArrayList<Integer>> map = new HashMap<>();
			for (int i = 0; i < W.length(); i++) {
				//현재 위치의 문자를 추출하고
				char c = W.charAt(i);
				//맵에 c가 i에 위치했다는 정보를 저장함
				map.putIfAbsent(c, new ArrayList<>());
				map.get(c).add(i);
			}

			//min은 3번의 정답, max는 4번의 정답
			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;
			for (char c = 'a'; c <= 'z'; c++) {
				ArrayList<Integer> list = map.get(c);
				//3번과 4번 모두 어떤 문자를 정확히 K개 포함해야하므로
				//현재 문자가 등장한 횟수가 K개 미만이라면 절대 조건을 만족할 수 없으므로 넘어감
				if (list == null || list.size() < K) continue;
				int tail = 0;
				int head = K - 1;
				//이후 리스트를 훑으며 3번과 4번 조건에 대한 답을 구함
				//4번은 문자열의 첫 번째와 마지막 글자가 같아야하므로 이렇게 해야하는게 직관적인데,
				//3번의 경우에도 자세히 생각해보면 정확히 특정 문자를 K개 포함하며 동시에 가장 짧아야 하므로
				//당연히 양 끝이 해당 문자로 같을 수밖에 없다(다른 문자가 포함돼어버리는 순간 가장 짧은 문자열을 구할 수 없음)
				for (; head < list.size(); tail++, head++) {
					min = Math.min(min, list.get(head) - list.get(tail) + 1);
					max = Math.max(max, list.get(head) - list.get(tail) + 1);
				}
			}
			
			//min이 초기값 그대로면 못찾은 경우이므로 -1 출력
			if (min == Integer.MAX_VALUE) {
				sb.append(-1).append('\n');
			} else {
				sb.append(min).append(' ').append(max).append('\n');
			}
		}
		System.out.print(sb);
	}
}