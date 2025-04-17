import java.io.*;
import java.util.*;

/*
 * 50044KB, 308ms
 * 
 * 문제 생긴게 너무 세그먼트 트리였음
 * 처음에 주어지는 값이 없으니 init은 구현할 필요는 없고 update와 query만 구현해주면 된다.
 * 쿼리의 결과로 변화하는 양을 출력하는 것이니 노드에는 변화한 양을 담아주고, 왼쪽 서브트리와 오른쪽 서브트리의 합을 구하면
 * 구간 내 변화한 양을 구할 수 있다.
 */

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	static int N, Q;
	static long[] tree;
	
	public static void main(String[] args) throws Exception {
		N = nextInt(); Q = nextInt();
		tree = new long[4 * N];
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < Q; i++) {
			int order = nextInt();
			int a = nextInt();
			int b = nextInt();
			if (order == 1) {
				update(a, b, 1, 1, N);
			} else {
				sb.append(query(a, b, 1, 1, N)).append('\n');
			}
		}
		System.out.print(sb);
	}
	
	static long update(int p, int x, int treeIdx, int l, int r) {
		//p일에 업데이트를 해야하므로 현재 구간이 p를 벗어나면 바로 나감
		if (p < l || r < p) return 0;
		//리프 노드까지 왔다면 값을 업데이트
		if (l == r) {
			tree[treeIdx] += x;
			return tree[treeIdx];
		}
		
		//재귀적으로 왼쪽 서브트리, 오른쪽 서브트리를 업데이트 해주고
		int mid = (l + r) / 2;
		update(p, x, treeIdx * 2, l, mid);
		update(p, x, treeIdx * 2 + 1, mid + 1, r);
		//현재 노드값을 업데이트
		return tree[treeIdx] = tree[treeIdx * 2] + tree[treeIdx * 2 + 1];
	}
	
	static long query(int ql, int qr, int treeIdx, int l, int r) {
		//쿼리로 주어진 구간이 현재 구간 바깥에 있다면 나감
		if (qr < l || r < ql) return 0;
		//쿼리로 주어진 구간이 현재 주어진 구간을 완전히 포함하면 값을 바로 반환
		if (ql <= l && r <= qr) return tree[treeIdx];
		
		//재귀적으로 왼쪽 서브트리의 쿼리 값, 오른쪽 서브트리의 쿼리 값을 구함
		int mid = (l + r) / 2;
		long leftV = query(ql, qr, treeIdx * 2, l, mid);
		long rightV = query(ql, qr, treeIdx * 2 + 1, mid + 1, r);
		//왼쪽 구간의 변화한 양과 오른쪽 구간의 변화한 양을 더해주면 됨
		return leftV + rightV;
	}
	
	static int nextInt() throws Exception {
		st.nextToken();
		return (int) st.nval;
	}
}