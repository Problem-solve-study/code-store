/**
 * 41224KB	440ms
 * 그냥 BFS입니다.
 */

import java.io.*;
import java.util.*;

public class Main {
	static Set<Node> v;
	static Queue<Node> q;
	
	static StreamTokenizer st = new StreamTokenizer(new InputStreamReader(System.in));
	public static void main(String[] args) throws Exception {
		v = new TreeSet<>();
		q = new ArrayDeque<>();
		
		Node start = new Node(ni(), ni(), ni());
		q.add(start);
		v.add(start);
		while (!q.isEmpty()) {
			Node cur = q.poll();
			if (cur.arr[0]==cur.arr[1] && cur.arr[1]==cur.arr[2]) {
				System.out.println(1);
				System.exit(0);
			}
			
			for (int i=0; i<3; ++i) {
				int j = (i+1)%3;
				int k = (i+2)%3;
				int a = Math.min(cur.arr[i], cur.arr[j]);
				int b = Math.max(cur.arr[i], cur.arr[j]);
				Node next = new Node(a+a, b-a, cur.arr[k]);
				if (next.arr[0]==0 || next.arr[1]==0 || next.arr[2]==0) continue;
				if (!v.contains(next)) {
					v.add(next);
					q.add(next);
				}
			}
		}
		System.out.println(0);
		System.exit(0);
	}
	
	static class Node implements Comparable<Node>{
		int[] arr;
		int key;
		public Node(int a, int b, int c) {
		    arr = new int[]{a, b, c};
		    Arrays.sort(arr);
		    for (int i=0; i<3; ++i) key = key*1000 + arr[i];
		}
		@Override
		public int compareTo(Node o) {
			return key - o.key;
		}
	}
	
	static int ni() throws Exception {
		st.nextToken();
		return (int) st.nval;
	}
}