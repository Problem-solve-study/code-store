//제출번호:	92706972
//메모리:	15188 KB
//실행시간:	124 ms
import java.io.*;

//처음에 맞바꾸기 최솟값을 어떻게 바로 구하나 고민했었는데
//어짜피 같은 모양으로 정돈하기 위해서는 (모양)(모양)(모양) 꼴로 정리해야 함
//그래서 정돈하는 모양 순서를 정해놓고 거기에 맞춰서 맞바꾸는 횟수를 구해보기로 함 
public class Main {

	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

	public static void main(String[] args) throws IOException {
		int n = nextInt();
		int[] arr = new int[n];
		int[] shapes = new int[4];
		for (int i = 0; i < n; i++) {
			//arr에 모양을 담아두고, 각 모양당 개수를 세어둠
			shapes[arr[i] = nextInt()]++;
		}

		boolean[] visited = new boolean[4];
		int[] swapShapes = new int[4];
		int min = Integer.MAX_VALUE;
		for (int i = 1; i < 4; i++) {
			visited[i] = true;
			for (int j = 1; j < 4; j++) {
				if (visited[j]) {
					continue;
				}

				//이중 for문을 이용해서 모양의 순서를 선택함
				//k = 6 - i - j로 구할 수는 있는데 아래에서 쓰지 않아서 굳이 선언하진 않음

				//맞바꾸는 횟수와 맞바꾸는 모양을 저장할 공간을 초기화
				int swap = 0;
				swapShapes[1] = swapShapes[2] = swapShapes[3] = 0;

				//가장 앞에 나와야 하는 모양을 먼저 배치해 봄
				for (int idx = 0; idx < shapes[i]; idx++) {
					//만약 모양이 다르다면 바꿔야 하기 때문에 나중에 바꿀 모양을 기록해 둠
					if (arr[idx] != i) {
						swap++;
						swapShapes[arr[idx]]++;
					}
				}

				//중간에 나와야 하는 모양을 배치해 봄
				for (int cnt = 0, idx = shapes[i]; cnt < shapes[j]; cnt++, idx++) {
					//만약 모양이 같다면 아래 조건을 계산할 필요가 없음
					if (arr[idx] == j) {
						continue;
					}

					//만약에 i 모양이면서,
					//i 모양을 배치할 때 j 모양이 바뀌었다면
					//(swapShape[j]가 1 이상이면 i 모양을 배치할 때 j 모양이 나온 것)
					//그 둘만 바꾸면 되고 이 때 바꾼 횟수는 이미 계산되었음 
					if (arr[idx] == i && swapShapes[j] > 0) {
						swapShapes[j]--;
					} else {
						//i 모양이 아니거나
						//i 모양이었지만 j 모양을 다 썼다면
						//k 모양이 지금 자리에 있는 것이기 때문에 바꿔야 함 
						swap++;
					}
				}

				//계산한 최소횟수를 min에 업데이트
				min = Math.min(min, swap);
			}
			visited[i] = false;
		}

		System.out.print(min);
	}

	static int nextInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
}

