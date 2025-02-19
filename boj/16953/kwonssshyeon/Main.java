// 	997828KB	628ms
/**
 * 큐 이용해서 풀었는데 뭘 잘못했길래 성능이 저렇게 나올까요...
 */
import java.io.*;
import java.util.*;

public class Main {
	static int a,b;
	static boolean[] visited = new boolean[1_000_000_001];
	static class Node {
		long num;
		int cnt;
		Node (long num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());

		int answer = -1;
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(a, 0));
		visited[a] = true;
		while (!queue.isEmpty()) {
			Node node = queue.poll();

			if (node.num == b) {
				answer = node.cnt + 1;
				break;
			}

			for (long next : new long[] {node.num * 2, node.num * 10 + 1}) {
				if (next <= b && !visited[(int)next]) {
					visited[(int)next] = true;
					queue.add(new Node(next, node.cnt + 1));
				}
			}
		}
		System.out.print(answer);
	}
}