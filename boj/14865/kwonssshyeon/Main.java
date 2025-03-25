// 352756KB	1008ms
import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int bigCount, smallCount;
    static final int OPEN = 0, CLOSE = 1, NONE = Integer.MAX_VALUE;
	static PriorityQueue<Node> queue = new PriorityQueue<>();
	static Stack<Node> stack = new Stack<>();
	static class Node implements Comparable<Node>{
		int pos, type;
		Node(int pos, int type) {
			this.pos = pos;
			this.type = type;
		}
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.pos, o.pos);
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		int x1, y1, x2, y2, prevX = NONE;
        StringTokenizer st;
        int[][] num = new int[2*n][2];
        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            num[i][0] = Integer.parseInt(st.nextToken());
            num[i][1] = Integer.parseInt(st.nextToken());
        }
        int start = 0;
        while (num[start][1] > 0 || num[start+1][1] < 0) {
            num[n+start] = num[start];
            start++;
        }

        for (int i=start; i<n+start; i+=2) {
            x1 = num[i][0];
            y1 = num[i][1];
            x2 = num[i+1][0];
            y2 = num[i+1][1];
            if ((y1 > 0 && y2 < 0) || (y1 < 0 && y2 > 0)) {
                if (prevX == NONE) {
                    prevX = x1;
                }
                else if (prevX != NONE) {
                    int minX = (x1 < prevX) ? x1 : prevX;
                    int maxX = (x1 > prevX) ? x1 : prevX;
                    queue.add(new Node(minX, OPEN));
                    queue.add(new Node(maxX, CLOSE));
                    prevX = NONE;
                }
            }
        }

        Node prev = null;
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.type == OPEN) {
                stack.push(node);
            }
            else if (node.type == CLOSE) {
                stack.pop();
                if (stack.isEmpty()) {
                    bigCount++;
                }
                if (prev != null && prev.type == OPEN) {
                    smallCount++;
                }
            }
            prev = node;
        }
		System.out.print(bigCount+" "+smallCount);
	}
}