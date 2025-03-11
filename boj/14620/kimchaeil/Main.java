//문제: BOJ 14620번 꽃길
//메모리: 12352 KB
//시간: 72 ms

/*
 * 조합을 사용하되 꽃잎이 겹치는 경우를 고려해서
 * 씨앗을 심은 위치와 상하좌우 위치에 visited 처리를 해주어 꽃잎이 겹치는지 확인한다.
 * 꽃잎이 겹치지 않으면 씨앗을 심은 위치와 상하좌우 위치의 가격을 더하여 계산한다.
 * 만약, 현재까지 구한 가격이 지금까지 알아낸 최소 가격보다 크다면 가지치기 해준다.
 * 또한, 화단의 테두리의 경우에는 씨앗이 심길 위치로 선택 될 수 없으므로 가지치기 해준다.
 */

import java.io.*;

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	static int n, limit;
	static int[] map;
	static boolean[] visited;
	static int[] d;
	static int result = 3001; // 총 필요한 땅의 수 = 15, 지점당 최대 가격 = 200, 총 가격의 최댓값 = 3000

	public static void main(String[] args) throws IOException {
		n = nextInt();

		limit = n * (n - 1); // 아래 테두리 제외를 위해
		int length = limit + n; // 배열 길이

		map = new int[length];
		for (int i = 0; i < length; i++)
			map[i] = nextInt();

		visited = new boolean[length];
		d = new int[] { -n, -1, 1, n };

		backTracking(0, n + 1, 0); // 조합을 쉽게 구현하기 위해 1차원 배열로 변환, 위 테두리 제외를 위해 n+1부터 시작
		System.out.println(result);
	}

	static void backTracking(int depth, int start, int sum) {
		if (sum > result) // 가격이 이미 지금까지 알아낸 최소 가격보다 크면 가지치기, 땅 가격이 0원인 곳도 있기 때문에 같을 때는 진행한다.
			return;
		if (depth == 3) {
			result = result > sum ? sum : result;
			return;
		}
		loop: for (int i = start; i < limit; i++) {
			if (i % n == 0 || i % n == n - 1) // 좌우 테두리 제외
				continue;
			//씨앗이 다른 꽃과 곂치면 어짜피 씨앗이 피었을때 꽃잎도 겹치므로 씨앗은 방문한 곳인지 확인할 필요가 없다
			int cost = map[i];
			for (int dir = 0; dir < 4; dir++) {
				int next = i + d[dir];
				if (visited[next])
					continue loop;
				cost += map[next];
			}
			// 씨앗 심기가 가능하면
			visited[i] = visited[i - n] = visited[i - 1] = visited[i + 1] = visited[i + n] = true; // 씨앗,꽃잎 위치 방문기록
			backTracking(depth + 1, i + 1, sum + cost);
			visited[i] = visited[i - n] = visited[i - 1] = visited[i + 1] = visited[i + n] = false; // 방문기록 원상복구
		}
		return;
	}

	static int nextInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
}
