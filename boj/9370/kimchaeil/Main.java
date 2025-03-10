//문제: BOJ 9370번 미확인 도착지
//메모리: 33576 KB
//시간: 436 ms

/*
 * 출발지로부터 다익스트라
 * g와 h중 출발지로부터 멀리 있는 교차로가 g-h도로에서 나중에 지나는 교차로
 * -> 이때, 먼저 지나는 교차로를 g, 나중에 지나는 교차로를 h라고 하자
 * h로부터 다익스트라
 * (출발지->g 거리) + (g->h 거리) + (h->목적지 거리)가 (출발지->목적지 거리)와 같다면
 * 해당 목적지는 g-h 도로를 지난 것이다.
 */

import java.io.*;
import java.util.*;

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		int T = nextInt();

		for (int testCase = 0; testCase < T; testCase++) {
			int n = nextInt(), m = nextInt(), t = nextInt();
			int s = nextInt(), g = nextInt(), h = nextInt();
			List<List<int[]>> road = new ArrayList<>();
			for (int i = 0; i <= n; i++) {
				road.add(new ArrayList<>());
			}
			int g_h = 0;
			for (int i = 0; i < m; i++) {
				int a = nextInt(), b = nextInt(), distance = nextInt();
				road.get(a).add(new int[] { b, distance }); //도로 추가
				road.get(b).add(new int[] { a, distance });
				if ((a == h && b == g) || (b == h && a == g)) //만약, g-h도로의 정보라면 g-h도로의 길이를 저장
					g_h = distance;
			}

			int[] destination = new int[t];
			for (int i = 0; i < t; i++) {
				destination[i] = nextInt(); //목적지 후보 입력
			}
			mergeSort(0, t, destination); //목적지 후보 정렬

			//시작지로부터 다익스트라
			int[] fromStart = new int[n + 1];
			for (int i = 1; i <= n; i++) {
				fromStart[i] = 1_000_000_000;
			}
			fromStart[s] = 0;
			Queue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
			pq.add(new int[] { s, 0 });
			while (!pq.isEmpty()) {
				int[] now = pq.poll();
				if (now[1] > fromStart[now[0]])
					continue;
				for (int[] next : road.get(now[0])) {
					int newDist = now[1] + next[1];
					if (newDist < fromStart[next[0]]) {
						fromStart[next[0]] = newDist;
						pq.add(new int[] { next[0], newDist });
					}
				}
			}
			
			//g와 h중 출발지로부터 멀리 떨어진 곳에서부터 다익스트라
			//g와 h중 출발지로부터 멀리 떨어진 곳을 stopover, 가까운 곳을 before이라고 하자
			int stopover = fromStart[h] > fromStart[g] ? h : g;
			int before = fromStart[h] > fromStart[g] ? g : h;
			int[] fromStopover = new int[n + 1];
			for (int i = 1; i <= n; i++) {
				fromStopover[i] = 1_000_000_000;
			}
			fromStopover[stopover] = 0;
			pq.add(new int[] { stopover, 0 });
			while (!pq.isEmpty()) {
				int[] now = pq.poll();
				if (now[1] > fromStopover[now[0]])
					continue;
				for (int[] next : road.get(now[0])) {
					int newDist = now[1] + next[1];
					if (newDist < fromStopover[next[0]]) {
						fromStopover[next[0]] = newDist;
						pq.add(new int[] { next[0], newDist });
					}
				}
			}
			
			for (int d : destination) {
				if(fromStart[d]==fromStart[before]+g_h+fromStopover[d]) { //만약, (출발지->목적지후보 최단 거리)와 (출발지->before) + (before->stopover) + (stopover->목적지후보)가 같다면 g-h도로를 지나므로 가능한 목적지 후보다.
					sb.append(d).append(' ');
				}
			}
			sb.append('\n');
		}
		System.out.println(sb);

	}

	static void mergeSort(int start, int end, int[] arr) { //목적지 후보를 정렬하기 위한 머지소트
		if (end - start == 1)
			return;

		int mid = (start + end) / 2;
		mergeSort(start, mid, arr);
		mergeSort(mid, end, arr);

		int[] tempArr = new int[end - start];
		int idx = 0;
		int idx1 = start;
		int idx2 = mid;
		while (idx1 < mid || idx2 < end) {
			if (idx1 >= mid) {
				tempArr[idx] = arr[idx2++];
			} else if (idx2 >= end) {
				tempArr[idx] = arr[idx1++];
			} else {
				if (arr[idx1] < arr[idx2]) {
					tempArr[idx] = arr[idx1++];
				} else {
					tempArr[idx] = arr[idx2++];
				}
			}
			idx++;
		}
		for (int i = 0; i < tempArr.length; i++) {
			arr[start + i] = tempArr[i];
		}
		return;
	}

	static int nextInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
}
