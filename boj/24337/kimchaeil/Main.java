//문제: BOJ 24337번 가희와 탑
//시간: 144 ms
//메모리: 17056 KB

/*
 * a와 b의 합이 n+1 보다 크면 불가능
 * 모든 건물중 최고 높이는 a와 b 중 큰 값
 * a-1개의 건물은 가희로 인해, b-1개의 건물은 단비에 의해 결정
 * a가 1보다 크면: (1,1,1,...,1)(가희로 인해 결정)(최고 높이 건물)(단비에 의해 결정)
 * a가 1이면: (최고 높이 건물),(1,1,1,...,1,1),(최고 높이 건물)(단비로 인해 결정)
 */

public class Main {

	public static void main(String[] args) throws Exception {
		int n = nextInt(), a = nextInt(), b = nextInt();
		if (n < a + b - 1) // a와 b의 합이 n+1보다 크면 불가능
			System.out.println(-1);
		else {
			StringBuilder sb = new StringBuilder();
			n -= a + b - 1; //a와 b로 인해 채워지는 부분을 제외하고 1로 채워질 부분의 길이
			boolean flag = a == 1;
			if (flag) //a가 1이면
				sb.append(b).append(' '); //가장 높은 건물이 제일 앞에
			while (n-- > 0) //1 채우기
				sb.append(1).append(' ');
			if (!flag) { //a가 1이 아니면
				for (int i = 1; i < a; i++) // 왼쪽에서 a-1 만큼 보이게 채우기
					sb.append(i).append(' ');
				sb.append(a > b ? a : b).append(' '); // a와 b중 큰 값으로
			}
			while (b-- > 1)
				sb.append(b).append(' '); // 오른쪽에서 b-1만큼 보이게 채우기
			System.out.println(sb); //출력
		}
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

