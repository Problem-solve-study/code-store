//문제: BOJ 17262번 팬덤이 넘쳐흘러
//메모리: 12080 KB
//시간: 100 ms

/*
 *  가장 먼저 집에 가는 학생의 집에 가는 시간(minGo)부터
 *  가장 늦게 학교에 오는 학생의 학교에 오는 시간(maxCome)까지
 *  학교에 머무르면 된다
 *  minGo가 maxCome보다 크거나 같다면 모든 학생이 학교에 있는 시간이 있다는 것이므로 0
 */

public class Main {
	public static void main(String[] args) throws Exception {
		int n = nextInt();
		int minGo = Integer.MAX_VALUE, maxCome = 0;
		while (n-- > 0) {
			maxCome = Math.max(maxCome, nextInt());
			minGo = Math.min(minGo, nextInt());
		}
		System.out.println(Math.max(maxCome - minGo, 0));
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
