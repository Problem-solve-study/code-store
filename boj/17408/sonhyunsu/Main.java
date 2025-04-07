//제출번호: 92711914
//메모리:   91700 KB
//실행시간: 436 ms (입출력 최적화 -> 352ms)
import java.io.*;

//그냥 세그먼트 트리를 만들고
//트리의 노드에 구간 중 가장 큰 값 2개만 저장하도록 업데이트만 하면 됨
//그리고 조회할 때 잘 불러오면 됨
public class Main {

	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	static int[][] segTree;

	public static void main(String[] args) throws IOException {
		int n = nextInt();
		segTree = new int[2][n << 2];

		for (int i = 1; i <= n; i++) {
			int num = nextInt();
            //들어오는 값을 세그트리에 업데이트
			update(1, 1, n, i, num);
		}

		int m = nextInt();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m; i++) {
			int a = nextInt();
			int l = nextInt();
			int r = nextInt();

			if (a == 1) {
                //a가 1이면 업데이트문이므로 l 위치에 r 값을 업데이트
				update(1, 1, n, l, r);
			} else {
                //a가 2면 조회문이므로 [l, r] 구간의 최대값 2개를 골라 더한 후 출력
				int[] max = query(1, 1, n, l, r);
				sb.append(max[0] + max[1]).append('\n');
			}
		}

		System.out.print(sb);
	}

	static void update(int idx, int start, int end, int pos, int val) {
        //pos가 [start, end] 사이에 없으면 계산할 필요가 없음
		if (pos < start || end < pos) {
			return;
		}

        //기저에 도달하면 직접 업데이트
		if (start == end) {
			segTree[0][idx] = val;
			return;
		}

		int mid = (start + end) / 2;
		int leftIdx = idx << 1;
		int rightIdx = leftIdx + 1;
        
        //구간을 반씩 나누면서 업데이트함
		update(leftIdx, start, mid, pos, val);
		update(rightIdx, mid + 1, end, pos, val);

        //각각 절반 구간이 업데이트되었으니까 그걸 바탕으로 현재 구간을 업데이트 함
		segTree[0][idx] = Math.max(segTree[0][leftIdx], segTree[0][rightIdx]);
		segTree[1][idx] = segTree[0][leftIdx] > segTree[0][rightIdx]
						? Math.max(segTree[0][rightIdx], segTree[1][leftIdx])
						: Math.max(segTree[0][leftIdx], segTree[1][rightIdx]);
	}

    //짧게 구현하기 위해서 메모리를 항상 새로 할당하니까 메모리 크기가 많이 늘어났음 
	static int[] query(int idx, int start, int end, int left, int right) {
        //주어진 구간 바깥의 범위는 계산에 들어가면 안 됨
		if (right < start || end < left) {
			return new int[2];
		}

        //구간 안에 들어가는 범위면 바로 return
		if (left <= start && end <= right) {
			return new int[]{segTree[0][idx], segTree[1][idx]};
		}

		int mid = (start + end) / 2;
		int leftIdx = idx << 1;
		int rightIdx = leftIdx + 1;

        //왼쪽 구간의 최댓값 2개와 오른쪽 구간의 최댓값 2개를 구해서
		int[] leftMax = query(leftIdx, start, mid, left, right);
		int[] rightMax = query(rightIdx, mid + 1, end, left, right);

        //현재 구간의 최댓값 2개를 뽑아냄
		if (leftMax[0] > rightMax[0]) {
			return new int[] {leftMax[0], Math.max(leftMax[1], rightMax[0])};
		} else {
			return new int[] {rightMax[0], Math.max(rightMax[1], leftMax[0])};
		}
	}

	static int nextInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
}

/* === 세그트리를 비재귀로 구현한 버전 ===
//제출번호: 92711694
//메모리:   26384 KB
//실행시간: 380 ms (입출력 최적화 -> 284ms)
import java.io.*;

public class Main {

	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	
	public static void main(String[] args) throws IOException {
		int n = nextInt();

        //n을 모두 다 담을 수 있는 2^k 형태인 p를 찾음
        int p = 1;
		while (n > p) {
			p <<= 1;
		}

        //세그먼트 트리를 저장할 공간 생성
        // n < p 이므로 필요한 공간은 모두 생성됨
		int[][] segTree = new int[2][p << 1];

		for (int i = 0; i < n; i++) {
			int num = nextInt();
            //세그먼트의 리프 노드에 num을 모두 기록함
			segTree[0][p + i] = num;
		}

        //세그먼트 트리를 한 번에 업데이트함
		for (int idx = p - 1; idx > 0; idx--) {
			int left = idx << 1;
			int right = left + 1;

			segTree[0][idx] = Math.max(segTree[0][left], segTree[0][right]);
			segTree[1][idx] = segTree[0][left] > segTree[0][right]
							? Math.max(segTree[0][right], segTree[1][left])
							: Math.max(segTree[0][left], segTree[1][right]);
		}

		int m = nextInt();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m; i++) {
			int a = nextInt();
			int l = nextInt();
			int r = nextInt();

			if (a == 1) { //a가 1이면 업데이트 
				int idx = p + l - 1; //리프 노드의 위치를 계산함
				segTree[0][idx] = r; //리프 노드에 val(=r)을 저장함

                //트리의 부모로 한 칸씩 올라가면서 노드를 업데이트 함
				while ((idx >>>= 1) > 0) {
					int left = idx << 1;
					int right = left + 1;

					segTree[0][idx] = Math.max(segTree[0][left], segTree[0][right]);
					segTree[1][idx] = segTree[0][left] > segTree[0][right]
									? Math.max(segTree[0][right], segTree[1][left])
									: Math.max(segTree[0][left], segTree[1][right]);
				}
			} else { //a가 2면 값 찾기
                //구간의 시작과 끝의 리프 노드의 위치를 각각 계산함
				l += p - 1;
				r += p - 1;

				int[] max = new int[2];

                //모든 구간을 찾을 때까지 반복
				while (l <= r) {
                    //만약 left가 트리의 오른쪽 엣지에 있다면
                    //left의 부모는 주어진 [left, right] 구간 안에 포함되지 않으므로
                    //left 노드의 값으로 최댓값을 업데이트한다. 
					if ((l & 1) == 1) {
						int lMax0 = segTree[0][l];
						int lMax1 = segTree[1][l];

						if (lMax0 > max[0]) {
							max[1] = Math.max(max[0], lMax1);
							max[0] = lMax0;
						} else if (lMax0 > max[1]) {
							max[1] = lMax0;
						}

                        //left의 오른편으로 이동한다.
                        //(이러면 left는 트리의 왼쪽 엣지에 위치함)
						l++;
					}

                    //만약 right가 트리의 왼쪽 엣지에 있다면
                    //right의 부모는 주어진 [left, right] 구간 안에 포함되지 않으므로
                    //right 노드의 값으로 최댓값을 업데이트 한다.
					if ((r & 1) == 0) {
						int rMax0 = segTree[0][r];
						int rMax1 = segTree[1][r];

						if (rMax0 > max[0]) {
							max[1] = Math.max(max[0], rMax1);
							max[0] = rMax0;
						} else if (rMax0 > max[1]) {
							max[1] = rMax0;
						}

                        //right의 왼편으로 이동한다.
                        //(이러면 right는 트리의 오른쪽 엣지에 위치함)
						r--;
					}

                    //현재 노드의 부모로 올라가서 다시 계산
					l >>>= 1;
					r >>>= 1;
				}

                //최종적으로 max에는 [l, r]구간의 최댓값 2개를 가지고 있음
				sb.append(max[0] + max[1]).append('\n');
			}
		}

		System.out.print(sb);
	}

	static int nextInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
}
 */