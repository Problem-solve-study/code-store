//메모리: 87628KB
//시간: 1516ms

import java.util.*;
import java.io.*;

public class Main {
	static int[] unionFind;
	static int[] cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());

		cnt = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++)
			cnt[i] = st.nextToken().equals("1") ? 1 : -1;

		unionFind = new int[n + 1];
		Arrays.fill(unionFind, -1);
		int a, b;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			union(a, b);
		}

		int k;
		for (int i = 0; i < q; i++) {
			k = Integer.parseInt(br.readLine());
			System.out.println(cnt[find(k)] > 0 ? 1 : 0);
		}

	}

	public static void union(int a, int b) {
		int fa = find(a);
		int fb = find(b);
		if (fa == fb)
			return;
		if (unionFind[fa] < unionFind[fb]) {
			unionFind[fa] += unionFind[fb];
			unionFind[fb] = fa;
			cnt[fa] = cnt[fa] + cnt[fb];
		} else {
			unionFind[fb] += unionFind[fa];
			unionFind[fa] = fb;
			cnt[fb] = cnt[fa] + cnt[fb];
		}

	}

	public static int find(int a) {
		if (unionFind[a] < 0)
			return a;
		else
			return unionFind[a] = find(unionFind[a]);
	}
}
