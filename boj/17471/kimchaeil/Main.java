//문제: 17471번 개리맨더링
//메모리: 12584 KB
//시간: 84 ms

/*
 * 주석은 오늘 시간이 없어서 나중에 적겠습니다.
 */

import java.util.*;
import java.io.*;

public class Main {
	static int n;
	static int[] population;
	static int[][] arr;
	static List<Integer> sectionA = new ArrayList<>();
	static int[] sectionPopulation;
	static int result = 10000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		population = new int[n + 1];
		arr = new int[n + 1][];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++)
			population[i] = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			arr[i] = new int[k];
			for (int j = 0; j < k; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 1; i <= n / 2; i++) {
			combination(1, i);
		}
		System.out.println(result == 10000 ? -1 : result);
	}

	public static void combination(int start, int cnt) {
		if (cnt == 0) {
			populationDiff();
			return;
		}
		for (int i = start; i <= n; i++) {
			sectionA.add(i);
			combination(i + 1, cnt - 1);
			sectionA.remove(sectionA.size() - 1);
		}
	}

	public static void populationDiff() {
		sectionPopulation = new int[2];
		if (!isConnect(sectionA.get(0), sectionA, 0))
			return;
		List<Integer> sectionB = new ArrayList<>();
		for (int i = 1; i <= n; i++)
			if (!sectionA.contains(i))
				sectionB.add(i);
		if (!isConnect(sectionB.get(0), sectionB, 1))
			return;
		result = Math.min(result, Math.abs(sectionPopulation[0] - sectionPopulation[1]));
	}

	public static boolean isConnect(int start, List<Integer> al, int target) {
		boolean[] visited = new boolean[n + 1];
		visited[start] = true;
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(start);
		sectionPopulation[target] += population[start];
		int cnt = 1;
		while (!queue.isEmpty()) {
			int node = queue.poll();
			for (int next : arr[node]) {
				if (!visited[next] && al.contains(next)) {
					visited[next] = true;
					cnt++;
					queue.add(next);
					sectionPopulation[target] += population[next];
				}
			}
		}
		return cnt == al.size() ? true : false;
	}
}