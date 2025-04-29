import java.io.*;
import java.util.*;

/*
 * 93176KB 1056ms
 * 
 * 뭔가 수학같았는데 조금 생각해봐도 잘모르겠어서 그냥 완전 탐색할까 싶었다.
 * 계속 돌리다보면 사이클이 생길 것 같아 시간 초과가 걸릴 것 같지는 않아서 일단 BFS로 짜고 제출했다. 
 */

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	//배열을 넣을 것이기 때문에 Set<List>로 visited 선언
	static HashSet<ArrayList<Integer>> set = new HashSet<>();
	static Queue<ArrayList<Integer>> q = new ArrayDeque<>();
	
	public static void main(String[] args) throws Exception {
		int A = nextInt();
		int B = nextInt();
		int C = nextInt();
		
		//이후 BFS 돌리기
		ArrayList<Integer> list = new ArrayList<>();
		list.add(A); list.add(B); list.add(C); Collections.sort(list);
		set.add(list); q.add(list);
		int res = 0;
		while (!q.isEmpty()) {
			ArrayList<Integer> cur = q.remove();
			int a = cur.get(0); int b = cur.get(1); int c = cur.get(2);
			//a == b == c이면 가능한 경우
			if (a == b && b == c) {
				res = 1;
				break;
			}
			
			if (a != b) {
				addList(a, b, c);
			}
			
			if (a != c) {
				addList(a, c, b);
			}
			
			if (b != c) {
				addList(b, c, a);
			}
		}
		System.out.print(res);
	}
	
	static void addList(int a, int b, int c) {
		ArrayList<Integer> newList = new ArrayList<>();
		newList.add(Math.min(a, b) + Math.min(a, b));
		newList.add(Math.max(a, b) - Math.min(a, b));
		newList.add(c);
		Collections.sort(newList);
		if (!set.contains(newList)) {
			set.add(newList);
			q.add(newList);
		}
	}
	
	static int nextInt() throws Exception {
		st.nextToken();
		return (int) st.nval;
	}
}