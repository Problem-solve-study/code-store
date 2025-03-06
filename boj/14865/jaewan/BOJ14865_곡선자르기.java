
/*
 * 산악 지도 그리기
 * x 축 위쪽 영역의 봉우리들 중
 * 다른 봉우리에 의해 포함되지 않는 봉우리 개수, 다른 봉우리를 포함하지 않는 봉우리 개수
 * 곡선을 표현하는 꼭짓점의 개수 4 <= N <= 10^7 = 10,000,000
 * 좌표 절댓값은 10^9보다 작거나 같다. 1,000,000,000
 * 
 * 가장 왼쪽 수직 선분을 기준으로 아래에서 위 방향으로 점이 주어진다.
 * y축의 좌표가 바뀔 때 기준으로 구간의 시작 끝이 정해짐.
 * 
 * x축 위로, 아래로 가는 걸 한 쌍으로 봉우리의 (시작, 끝) 을 구할 수 있다.
 * 
 * 봉우리 시작점 기준으로 정렬했을 때
 *
 * 가장 바깥쪽 봉우리가 닫힘을 기준으로 다음에 열리는 봉우리가 바깥쪽 봉우리다.
 * 안쪽 봉우리는 다음 봉우리의 시작이 현재 봉우리 끝보다 클 때, 가장 안쪽 봉우리임을 알 수 있다.
 * 이외는 cnt 안함.
 * 
 */
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;

public class BOJ14865_곡선자르기 {
	public static void main(String[] args) throws IOException {
		int N = readInt();
		int a, b;
		ArrayList<Pos> list = new ArrayList<>();
		ArrayDeque<int[]> input = new ArrayDeque<>();
		int startX = Integer.MAX_VALUE, startY = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			a = readInt();
			b = readInt();
			input.add(new int[] { a, b });
			if (a <= startX) {
				startX = a;
				startY = Math.min(startY, b);
			}
		}

		while (input.peek()[0] != startX || input.peek()[1] != startY)
			input.addLast(input.removeFirst());

		boolean open = false;
		int[] prev = input.removeFirst();
		for (int i = 1; i < N; i++) {
			int[] cur = input.removeFirst();
			if ((prev[1] < 0 && cur[1] > 0) || (prev[1] > 0 && cur[1] < 0)) {
				if (open) {
					list.add(new Pos(Math.min(cur[0], prev[0]), Math.max(cur[0], prev[0])));
					prev[0] = cur[0];
					prev[1] = cur[1];
					open = false;
				} else {
					open = true;
					prev[0] = cur[0];
					prev[1] = cur[1];
				}
			} else if (!open) {
				prev[0] = cur[0];
				prev[1] = cur[1];
			}
		}

		list.sort(new Comparator<Pos>() {

			@Override
			public int compare(Pos o1, Pos o2) {
				return Integer.compare(o1.left, o2.left);
			}
		});

//		for(int i=0;i<list.size();i++) {
//			System.out.printf("(%d,%d)",list.get(i).left,list.get(i).right);
//		}

		int smallCnt = 1, bigCnt = 0, prevRight = Integer.MIN_VALUE;
		// 제일 우측의 봉우리는 제일 안쪽 봉우리 일 수 밖에 없으므로 1부터 시작.
		for (int i = 0; i < list.size(); i++) {

			// 현재 요소의 left가 이전 바깥 봉우리의 right 보다 크면 바깥 봉우리 cnt++
			if (prevRight < list.get(i).left) {
				prevRight = list.get(i).right;
				bigCnt++;
			}

			if (i < list.size() - 1) {
				// 다음 요소의 시작 지점이 현재 봉우리의 끝 지점보다 크면, smallCnt 증가
				if (list.get(i).right < list.get(i + 1).left)
					smallCnt++;
			}
		}

		System.out.printf("%d %d\n", bigCnt, smallCnt);
	}

	private static int readInt() throws IOException {
		int c;
		do {
			c = System.in.read();
		} while (c <= 32);
		boolean flag = (c == 45);
		if (flag)
			c = System.in.read();
		int n = c & 15;
		c = System.in.read();
		while (c > 47) {
			n = (n << 3) + (n << 1) + (c & 15);
			c = System.in.read();
		}
		return flag ? -n : n;
	}

	static class Pos {
		int left, right;

		public Pos(int left, int right) {
			this.left = left;
			this.right = right;
		}

	}
}
