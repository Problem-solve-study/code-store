//문제: BOJ 1933번 스카이라인
//메모리: 34564 KB
//시간: 3796 ms

import java.io.*;
import java.util.*;

class Building {
	int start;
	int end;
	int height;

	public Building(int start, int height, int end) {
		this.start = start;
		this.end = end;
		this.height = height;
	}
}

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		int n = nextInt();
		List<Building> buildings = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			buildings.add(new Building(nextInt(), nextInt(), nextInt()));
		}
		Queue<Building> startQueue = new ArrayDeque<>();
		Queue<Building> endQueue = new ArrayDeque<>();
		buildings.sort((o1, o2) -> {
			if (o1.start != o2.start)
				return o1.start - o2.start;
			else {
				return o2.height - o1.height;
			}
		});
		for (Building building : buildings)
			startQueue.add(building);
		buildings.sort((o1, o2) -> {
			if (o1.end != o2.end)
				return o1.end - o2.end;
			else {
				return o1.height - o2.height;
			}
		});
		for (Building building : buildings)
			endQueue.add(building);

		int nowHeight = 0;
		int nowX = 0;

		Queue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
		pq.add(0);
		while (!startQueue.isEmpty() || !endQueue.isEmpty()) {
			if (startQueue.isEmpty() || startQueue.peek().start > endQueue.peek().end) {
				Building building = endQueue.poll();
				nowX = building.end;
				pq.remove(building.height);
				if (nowHeight > pq.peek()) {
					nowHeight = pq.peek();
					sb.append(nowX).append(' ').append(nowHeight).append(' ');
				}
			} else if (startQueue.peek().start <= endQueue.peek().end) {
				Building building = startQueue.poll();
				nowX = building.start;
				pq.add(building.height);
				if (nowHeight < pq.peek()) {
					nowHeight = pq.peek();
					sb.append(nowX).append(' ').append(nowHeight).append(' ');
				}
			}
		}
		System.out.println(sb);
	}

	static int nextInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
}
