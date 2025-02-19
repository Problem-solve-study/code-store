//22768KB	136ms
import java.io.*;
import java.util.*;
/*
 * BFS 연습문제.
 * 정수의 크기 범위가 클 때는 변수의 자료형을 int가 아니라 long으로 해야 한다는 사실을 망각해서 한번 틀림.
 * 자주 나오는 실수라 잘 인지해야할듯.
 */
public class Main {
	static int A, B;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		long[] currNode = new long[]{A, 1};
		Queue<long[]> queue = new LinkedList<>();
		queue.add(currNode);
		while(!queue.isEmpty()) {
			currNode = queue.poll();
			if(currNode[0] == B) {
				System.out.println(currNode[1]);
				return;
			}
			if(currNode[0]<B) {
				queue.add(new long[]{currNode[0]*10+1, currNode[1]+1});
				queue.add(new long[]{currNode[0]*2, currNode[1]+1});
			}
		}
		
		System.out.println(-1);
		
	}
}
