// 13328 KB, 96 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		int[][] cntStudents = new int[M + 1][2]; // 학생의 추천수, 등록된 순서 저장
		int num = 0; // 현재 게시된 사진 수
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int t = Integer.parseInt(st.nextToken());
			if (cntStudents[t][0] > 0) // 게시된 상태면
				cntStudents[t][0]++;
			else {
				if (num < N) { // 사진 추가할수 있는 상태
					num++;
				} else if (num >= N) { // 꽉 찬 상태면 추천 가장 적은 학생 삭제, 두 명 이상인 경우에는 가장 오래된 사진 삭제
					int min = Integer.MAX_VALUE, minIdx = -1; // 삭제할 사진의 minIdx 찾아서 cntStudents 를 0으로 만들기
					for (int j = 1; j <= M; j++) {
						if (cntStudents[j][0] > 0) {
							if (cntStudents[j][0] == min) { // 같은 경우에 더 오래된 사진을 선택
								if (cntStudents[j][1] < cntStudents[minIdx][1])
									minIdx = j;
							}
							if (cntStudents[j][0] < min) {
								min = cntStudents[j][0];
								minIdx = j;
							}
						}
					}
					cntStudents[minIdx][0] = 0;
				}

				cntStudents[t][0]++;
				cntStudents[t][1] = i; // 등록된 순서 i번쨰
			}
		}
		for (int i = 1; i <= M; i++) {
			if (cntStudents[i][0] > 0)
				System.out.printf("%d ", i);
		}
	}
}
