//문제: 9489번 사촌
//메모리: 161460 KB
//시간: 652 ms

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		while (true) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());

			if (n == 0)
				break;

			st = new StringTokenizer(br.readLine());
			int[] parent = new int[n];
			int prev = -1; //이전 값(k가 1이상이므로 초기값을 -1로
			int idx = -2; //부모 인덱스
			int targetParent = -1;
			for (int i = 0; i < n; i++) {
				int now = Integer.parseInt(st.nextToken());
				if (now - prev > 1) //k가 1보다 크게 차이나면
					idx++; //부모 인덱스 이동
				parent[i] = idx; //현재 노드의 부모 초기화
				if (now == k) //현재 노드가 타겟이면
					targetParent = parent[i]; //타겟부모 초기화
				prev = now; //이전 값을 현재 노드로
			}

			int result = 0;
			if (targetParent > 0) { //부모가 인덱스가 0 이하면 사촌이 없음
				int targetGrandParent = parent[targetParent]; //조부모 초기화
				for (int i = 1; i < n; i++) //사촌 카운트
					if (targetParent != parent[i] && targetGrandParent == parent[parent[i]])
						result++;
			}

			System.out.println(result);

		}
	}
}
