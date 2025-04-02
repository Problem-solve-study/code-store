//제출번호: 92428784
//메모리:   65272 KB
//실행시간: 820 ms
import java.io.*;

//처음에는 큐로 구현해볼까 했는데 사이클을 어떻게 판단할 지 몰라서 접음
//두 번째는 n이 작아서 플로이드-워셜로 구현해볼까 했는데 똑같이 사이클 판단이 안 돼서 접음
//힌트를 받고 인접 행렬을 곱하면 나오는 결과물을 생각하니까 행렬을 K번 곱하면 풀 수 있겠다 싶었음
//가능한 이유를 최대한 쉽게 얘기하면 다음과 같음

//플로이드 워셜을 계산할 때 ik + kj 를 기준으로 업데이트 함
//이것의 의미는 i->j일 때 k을 거쳐 가는 경로가 있다면 업데이트 하는 것
//행렬을 곱하면 ij 대해서 sigma k = 0 to n -1, ik * kj 가 됨
//ij 값이 1 이상이 된다면 i->j를 갈 때 k를 거쳐가는 경로가 있다는 뜻임
//ij 값이 0 이라면 i->k->j를 만족하는 k가 없다는 뜻
//여기서 반드시 k 위치를 지나간다는 점을 기억하자.
//행렬을 N제곱한 결과에는 반드시 N개의 위치를 지나서 도착하는 경우만 1이상의 값을 가짐
//그래서 K제곱한 결과 행렬에서 [a][b]를 보고 1 이상이면 a에서 출발해서 정확히 K번 만에 b에 도착하는 경우가 있다는 뜻  
public class Main {
    
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int n;
	public static void main(String[] args) throws IOException {
		n = nextInt();
		int k = nextInt();
		int m = nextInt();
		
		boolean[][] map = new boolean[n][n];

        //인접 행렬을 생성함
		for (int i = 0; i < n; i++) {
			map[i][nextInt() - 1] = map[i][nextInt() - 1] = true;
		}

        //인접 행렬을 K제곱한 결과를 가져옴
		map = calcMatrix(k, map);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m; i++) {
			int a = nextInt() - 1;
			int b = nextInt() - 1;

            //만약 갈 수 있다면 death, 갈 수 없다면 life
			sb.append(map[a][b] ? "death" : "life").append('\n');
		}

		System.out.print(sb);
	}

    //재귀를 이용해서 K제곱 하는 함수
	static boolean[][] calcMatrix(int k, boolean[][] map) {
        //k가 1이라면 인접 행렬 map 그대로임
		if (k == 1) {
			return map;
		}

        //(K/2) 제곱을 먼저 구함
		boolean[][] divRes = calcMatrix(k >> 1, map);

        //(2 * k/2) 제곱을 구함
		boolean[][] res = mulMatrix(divRes, divRes);

        //만약 K가 홀수라면 map을 한 번 더 곱함
		if ((k & 1) == 1) {
			res = mulMatrix(res, map);
		}

		return res;
	}

    //행렬 제곱 함수
	static boolean[][] mulMatrix(boolean[][] a, boolean[][] b) {
		boolean[][] res = new boolean[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
                //i -> k -> j 인 경로가 하나라도 존재한다면 isPossible은 true
				boolean isPossible = false;
				for (int k = 0; k < n; k++) {
					isPossible |= a[i][k] && b[k][j];
				}
				res[i][j] = isPossible;
			}
		}

		return res;
	}


	static int nextInt() throws IOException {
	    st.nextToken();
	    return (int) st.nval;
	}
}
