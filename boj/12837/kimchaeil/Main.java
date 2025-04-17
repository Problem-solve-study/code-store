//문제: BOJ 12837 가계부
//메모리: 49972 KB
//시간: 300 ms

/*
 * 세그먼트 트리
 * 초기값은 딱히 없으므로 init 메소드 없이 update만 있으면 됨
 * 처음에 update가 입력값을 더하는게 아니라 입력값으로 변경되는 것으로 문제를 잘 못 해석하여 틀림
 * 이를 수정하니 정답
 */

import java.io.*;

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	static long[] tree;
	static int start = 1;

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		int n = nextInt(), q = nextInt();
		tree = new long[n << 2];
		while (start < n)
			start <<= 1;
		int end = start - 1;
		while (q-- > 0) {
			int op = nextInt(), p = nextInt() - 1, x = nextInt();
			if (op == 1) {
				update(p, x);
			} else {
				sb.append(find(0, end, p, x - 1, 1)).append('\n');
			}
		}
		System.out.println(sb);
	}

	static void update(int i, int v) {
		int idx = start + i;
		while (idx > 0) {
			tree[idx] += v;
			idx >>= 1;
		}
	}

	static long find(int s, int e, int left, int right, int now) {
		if (right < s || left > e)
			return 0;
		if (left <= s && right >= e)
			return tree[now];
		int next = now << 1;
		int mid = (s + e) >> 1;
		return find(s, mid, left, right, next) + find(mid + 1, e, left, right, next + 1);
	}

	static int nextInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
}
