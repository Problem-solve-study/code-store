//문제: BOJ 17089번 세 친구
//메모리: 14840 KB
//시간: 128 ms

/*
 * BFS와 DFS, 사이클 감지를 통해 풀어보려 했으나 반례가 많거나 추가적인 처리를 해야하는 경우가 생겨 기각
 * 조합으로 풀게 되면 최악의 경우 4000C3이 되므로 기각
 * 입력으로 들어온 edge들이 사실상 2개를 미리 조합해 놓은 것과 다름이 없다.
 * 그렇다면 edge의 한쪽 사람의 임의의 친구에 대해 다른 쪽 사람의 친구인지만 확인하면 된다고 생각
 * 또한 A와 B, 두 명과 모두 친구인 사람의 수는 많아도 A의 친구의 수와 B의 친구의 수 중 작은 쪽이다.
 * 그래서 입력으로 들어왔던 edge에 대해 친구 수가 더 적은 사람의 친구들을 탐색하며
 * 그 친구가 친구 수가 더 많은 사람의 친구에 속해있는지 확인해 주었다.
 * 특정 인물의 친구들을 저장하는 것은 contains를 사용하므로 HashSet을 사용하였다.
 */

import java.io.*;
import java.util.*;

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

	public static void main(String[] args) throws IOException {
		int n = nextInt(), m = nextInt();
		List<Set<Integer>> friends = new ArrayList<>(); //특정 인물의 친구들을 저장할 HashSet들의 List
		List<int[]> edges = new ArrayList<>(); //입력으로 들어오는 edge들을 저장할 List
		int result = 4001;

		for (int i = 0; i <= n; i++) {
			friends.add(new HashSet<>());
		}

		while (m-- > 0) {
			int a = nextInt();
			int b = nextInt();
			friends.get(a).add(b); //a 친구에 b 추가
			friends.get(b).add(a); //b 친구에 a 추가
			edges.add(new int[] { a, b }); //a-b edge 저장
		}

		for (int[] edge : edges) {
			if (friends.get(edge[0]).size() > friends.get(edge[1]).size()) { //친구가 더 적은 쪽을 edge[0]으로
				int temp = edge[0];
				edge[0] = edge[1];
				edge[1] = temp;
			}
			int sum = friends.get(edge[0]).size() + friends.get(edge[1]).size() - 6; //edge[0]과 edge[1]의 사이즈 합과 세 친구의 서로서로를 제외하기 위한 -6은 미리 해주었다.
			for (int person : friends.get(edge[0])) { //edge[0]의 친구들 탐색
				if (person != edge[1] && friends.get(edge[1]).contains(person)) { //해당 친구가 edge[1]의 친구라면
					result = Math.min(sum + friends.get(person).size(), result); //친구들의 합 최소값 초기화
				}
			}
		}
		System.out.println(result == 4001 ? -1 : result); //조건에 맞는 세 친구가 없다면 -1 출력
	}

	static int nextInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
}
