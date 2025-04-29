//문제: BOJ 17827 달팽이 리스트
//메모리: 26224 KB
//시간: 240 ms

/*
 *  달팽이 모양이 아니라 원형이라고 생각하면 i번 이동했을때의 노드 번호는 i%(사이클길이)+1이다.(노드번호가 1번부터 시작이라면)
 *  이를 이용하여 노드 번호를 계산한다.
 */

public class Main {
	public static void main(String[] args) throws Exception {
		int n = nextInt(), m = nextInt(), v = nextInt() - 1;
		int cyclelength = n - v;
		int[] arr = new int[n];
		for (int i = 0; i < n; i++)
			arr[i] = nextInt();
		StringBuilder sb = new StringBuilder();
		while (m-- > 0) {
			int k = nextInt();
			sb.append(arr[k < v ? k : (k - v) % cyclelength + v]).append('\n');
		}
		System.out.print(sb);
	}

	static int nextInt() throws Exception {
		int n, c;
		while ((c = System.in.read()) < 48)
			;
		n = c & 15;
		while ((c = System.in.read()) > 47)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}
}
