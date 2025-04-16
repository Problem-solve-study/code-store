//문제: BOJ 14677 병약한 윤호
//메모리: 13872 KB
//시간: 68 ms

/*
 * BFS
 * 약 봉지의 시작(맨 앞)과 끝(맨 뒤), 현재까지 먹은 약 개수를 저장하는 Node클래스를 만들어 BFS를 구현했다.
 */

import java.io.*;
import java.util.*;

public class Main {
	static class State {
		int start, end, cnt; // 약봉지는 start 이상 end 미만의 범위

		public State(int start, int end, int cnt) {
			this.start = start;
			this.end = end;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int result = 0;

		int n = Integer.parseInt(br.readLine());
		char[] input = br.readLine().toCharArray();

		int length = n * 3;
		int[] arr = new int[length];
		for (int i = 0; i < length; i++)
			arr[i] = input[i] % 3; // 'B' -> 0, 'L' -> 1, 'D' -> 2

		boolean[][] visited = new boolean[length + 1][length + 1];
		Queue<State> queue = new ArrayDeque<>();
		queue.add(new State(0, length, 0));
		while (!queue.isEmpty()) {
			State now = queue.poll();
			int start = now.start, end = now.end, cnt = now.cnt;
			result = result < cnt ? cnt : result;
			if (start == end) // 모든 약을 먹음 -> 최대 개수 = 약 봉지 길이
				break;

			int target = cnt % 3; // 먹어야 할 약
			if (arr[start] == target && !visited[start + 1][end]) { 
				//맨 앞이 먹어야 할 약이고 맨 앞을 먹었을 때 범위가 이미 확인한 범위가 아니라면 맨 앞 먹기
				queue.add(new State(start + 1, end, cnt + 1));
				visited[start + 1][end] = true;
			}
			if (start != end && arr[end -= 1] == target && !visited[start][end]) { 
				//시작과 끝이 같지 않고 맨 뒤가 먹어야 할 약이고 맨 뒤를 먹었을 때 범위가 이미 확인한 범위가 아니라면 맨 뒤 먹기
				//시작과 끝이 같다면 위 조건문에서 이미 처리됨
				queue.add(new State(start, end, cnt + 1));
				visited[start][end] = true;
			}
		}
		System.out.println(result);
	}
}
