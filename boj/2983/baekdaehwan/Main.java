/**
 * 	80976KB	764ms
 * 
 * [사고흐름]
 * 어... 트리셋 쓰면 쉽게 풀릴텐데 메모리초과 안나나?
 * -> 안났습니다...
 * 
 * 
 * [알고리즘 설명]
 * 이 문제에서 사용하는 트리셋은 모두 point의 x값 기준으로 정렬됩니다.
 * 
 * 편의 상 다음과 같이 표현하겠습니다.
 * ascX  : Point에서 기울기가 1(ASC)인 선을 그었을 때, x축과 교점의 x값
 * descX : Point에서 기울기가 -1(DESC)인 선을 그었을 때, x축과 교점의 x값
 * 
 * 각 식물에 대해 다음 연산을 수행합니다.
 * 1. 식물의 좌표에서 ascX에 대한 트리셋을 생성합니다.(이미 존재한다면 생성X)
 * 2. 동일한 방법으로 descX에 대한 트리셋을 생성합니다.(이미 존재한다면 생성X)
 * 3. 생성된 트리셋에 현재 Point를 삽입합니다.
 * 
 * 이제 각 Point에 대한 A, B, C, D를 쉽게 구할 수 있습니다.
 * 아래와 같이 logN으로 각 Point를 찾을 수 있기 때문입니다.
 * - Point의 A를 구하고 싶다면, A = getAscTreeSet(ascX).higher(Point);
 * 
 * java의 경우 higher, lower가 없다면 null을 반환하니, 이를 응용하면 됩니다.
 * 만약 트리셋을 이렇게 사용해본적이 없다면, 꼭 사용해보시기를 추천합니다.
 */

import java.io.*;
import java.util.*;

public class Main {
	static int N, K;
	static char[] jump;
	static Map<Integer, TreeSet<Point>> asc, desc;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		jump = br.readLine().toCharArray();

		asc = new HashMap<>();
		desc = new HashMap<>();

		st = new StringTokenizer(br.readLine());
		Point cp = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		newPoint(cp);
		
		for (int i=1; i<N; ++i) {
			st = new StringTokenizer(br.readLine());
			Point np = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			newPoint(np);
		}
		
		for (char opr: jump) {
			Point np = null;
			if (opr == 'A') np = asc.get(cp.getAscX()).higher(cp);
			else if (opr == 'B') np = desc.get(cp.getDescX()).higher(cp);
			else if (opr == 'C') np = desc.get(cp.getDescX()).lower(cp);
			else if (opr == 'D') np = asc.get(cp.getAscX()).lower(cp);
			
			if (np != null) {
				remove(cp);
				cp = np;
			}
		}
		System.out.println(cp.x + " " + cp.y);
    }
	
	static void remove(Point p) {
		asc.get(p.getAscX()).remove(p);
		desc.get(p.getDescX()).remove(p);
	}
	
	static void newPoint(Point np) {
		if (!asc.containsKey(np.getAscX())) asc.put(np.getAscX(), new TreeSet<>());
		asc.get(np.getAscX()).add(np);
		if (!desc.containsKey(np.getDescX())) desc.put(np.getDescX(), new TreeSet<>());
		desc.get(np.getDescX()).add(np);
	}
	
	static class Point implements Comparable<Point>{
		int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		int getAscX() {
			return x-y;
		}
		int getDescX() {
			return x+y;
		}
		@Override
		public int compareTo(Point o) {
			return Integer.compare(x, o.x);
		}
	}
}
