//제출번호: 92497469
//메모리:   11716 KB
//실행시간: 68 ms
import java.io.*;
import java.util.*;

//그냥 문제에서 요구하는 대로 BFS를 구현함
//처음에 BFS 안 쓰고 구현하려다가 삑나서 변경함.. ㅋㅋ
//이렇게 돌려도 68ms가 나와서 놀람
//곱셈할 때 int 범위를 넘어갈 수 있으므로 주의할 것
//잘 보면 만들 수 있는 수는 2^i * n^j (0 <= i, j) 또는 0 밖에 없다는 것을 알 수 있음
public class Main {
    
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

	public static void main(String[] args) throws IOException {
		long n = nextInt();
		long m = nextInt();
		final int LIMIT = 1 << 30;

		StringBuilder sb = new StringBuilder();
		Map<Long, StringBuilder> store = new HashMap<>();
		Queue<Long> q = new ArrayDeque<>();

		store.put(n, new StringBuilder());
		q.add(n);

		while (!q.isEmpty()) {
			long num = q.poll();
			StringBuilder cmd = store.get(num);

			if (num == m) {
				break;
			}

            //우선순위에 따라 곱셈, 덧셈, 뺄셈, 나눗셈 순으로 진행
			long target = num * num;
			if (target < LIMIT && !store.containsKey(target)) {
				store.put(target, new StringBuilder(cmd).append('*'));
				q.add(target);
			}

			target = num + num;
			if (target < LIMIT && !store.containsKey(target)) {
				store.put(target, new StringBuilder(cmd).append('+'));
				q.add(target);
			}

			target = 0;
			if (target < LIMIT && !store.containsKey(target)) {
				store.put(target, new StringBuilder(cmd).append('-'));
				q.add(target);
			}

			target = 1;
			if (!store.containsKey(target)) {
				store.put(target, new StringBuilder(cmd).append('/'));
				q.add(target);
			}
		}

        //만약 방문하지 않은 위치면 불가능한 것
		if (!store.containsKey(m)) {
			sb.append(-1);
		} else {
			StringBuilder cmd = store.get(m);

            //만약 연산 명령어가 0이면 바로 구할 수 있는 것
			if (cmd.length() == 0) {
				sb.append(0);
			} else {
                //그 외는 연산 명령어 출력
				sb.append(cmd);
			}
		}

		System.out.print(sb);
	}

	static int nextInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
}
