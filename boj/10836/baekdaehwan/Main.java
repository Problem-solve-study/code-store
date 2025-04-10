/**
 * 44664KB	1112ms
 * 
 * [사고흐름]
 * 누적합이랑 패턴이 비슷한데 단순 시뮬레이션 돌리기에는 700^2 * 1e6이기 때문에 TLE날 것이라고 생각했습니다.
 * 
 * 그렇다면 문제에 힌트가 있을것이라고 생각했고, 
 * '입력에서 이렇게 읽은 값들은 감소하지 않는 형태'라는 부분에서 답을 알게되었습니다.
 * 
 * 입력을 모두 행이 증가하는 방향으로 더하더라도 위 규칙이 지켜지기 때문입니다.
 * ex)
 *    0 0 1 1 2 2
 *    0 1 1 2 2 2
 * +  1 1 1 1 1 2
 * -------------- 
 *    1 2 3 4 5 6 => 규칙이 지켜짐
 * 
 * 애벌레의 성장은 좌, 좌상, 상의 애벌레에 의해 결정됩니다. 
 * 하지만 위 규칙에 의해서 애벌레는 자신의 위 애벌레에 의해서 크기가 정해지게 됩니다.
 * 
 * 따라서 입력을 모두 행이 증가하는 방향으로 더한 값에, 각 격자의 윗방향 애벌레 크기만 할당하면 됩니다.
 * 이를 구현하면 아래와 같습니다. 위 규칙에 의해서 map은 필요없지만, 출력의 편의상 사용했습니다.
 */

import java.io.*;
import java.util.*;


public class Main {
    static int N, M;
    static int[][] map;
    static int[] A;
    
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static void main(String[] args) throws Exception {
        N = ni();
        M = ni();
        map = new int[N][N];
        A = new int[(N<<1)-1];
        Arrays.fill(A, 1);
        
        for (int i=0; i<M; ++i) {
        	int ni = 0;
        	for (int j=0; j<3; ++j) {
        		for (int k=ni(); k>0; --k) A[ni++] += j;
        	}
        }
        
        int ni = 0;
        for (int r=N-1; r>0; --r) map[r][0] = A[ni++];
        for (int c=0; c<N; ++c) map[0][c] = A[ni++];
        for (int r=1; r<N; ++r) {
        	for (int c=1; c<N; ++c) map[r][c] = map[r-1][c];
        }
        
        StringBuilder res = new StringBuilder();
        for (int r=0; r<N; ++r) {
        	for (int c=0; c<N; ++c) res.append(map[r][c]).append(' ');
        	res.append('\n');
        }
        System.out.print(res);
    }

    static int ni() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}