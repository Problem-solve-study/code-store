import java.io.*;
import java.util.*;

/*
 * 14492KB, 332ms
 * 
 * 그냥 구현하면 적당히 나오겠다 싶었음.
 * 소용돌이 배열이야 싸피에서도 몇 번 한 적이 있었으니 그대로 하면 되겠다 싶었는데
 * 문제에서 보면 음수 인덱스도 나오고 하니 그냥 배열에 담지말고 map에 담아야겠다고 생각함
 * 
 * 근데 시행착오가 많았다.
 * 한 3번 정도는 전체 배열의 값을 모두 담다가 메모리 초과가 떴다.
 * 요때까지는 단순히 TreeMap을 써서 메모리 초과가 난 줄 알았음.
 * 
 * 근데 생각해보니 전체 배열을 담을 필요가 없이 출력할 부분만 담으면 된다는 것을 깨닫고
 * 고쳐서 내니까 갑자기 출력 초과가 뜸
 * 
 * 다시 곰곰히 생각해보니 전체 배열에서 나올 수 있는 수를 기준으로 숫자의 길이를 맞추는게 아니라
 * 출력할 부분에서 가장 긴 수를 기준으로 숫자의 길이를 맞춰야 한다는 것을 깨달음
 * 
 * 그렇게 고쳐서 AC
 */

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {1, 0, -1, 0};
	
    //TreeMap의 Key에 들어갈 클래스
	static class Pos implements Comparable<Pos> {
		int r;
		int c;
		
		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public int compareTo(Pos o) {
			int comp = Integer.compare(r, o.r);
			if (comp != 0) return comp;
			return Integer.compare(c, o.c);
		}
	}
    //각 좌표에 어떤 값이 들어갈지를 담고 있는 map
	static TreeMap<Pos, Integer> map = new TreeMap<>();
	
	public static void main(String[] args) throws Exception {
		int r1 = nextInt(); int c1 = nextInt(); 
		int r2 = nextInt(); int c2 = nextInt();
		int rStart = Math.max(Math.abs(r1), Math.abs(r2));
		int cStart = Math.max(Math.abs(c1), Math.abs(c2));
		int start = Math.max(rStart, cStart);
		int len = start * 2 + 1;
		int max = 0;
		
		int r = 0; int c = 0; int d = 0;
		int cnt = 1; int curLen = 2;
        //소용돌이 식으로 값을 시뮬레이션
        //출력할 부분에만 map에 put
        //Map이나 Set의 메소드들은 복잡도가 낮긴한데 상수가 매우 큰 편이라
        //메소드를 덜 호출할 수록 좋음
		for (int i = 1; i <= len * len; i++) {
			if ((r1 <= r && r <= r2) && (c1 <= c && c <= c2)) {
				map.put(new Pos(r, c), i);
				max = i;
			}
			
            //현재 방향으로 모든 수를 찍었으면 방향을 바꿈
			if (cnt == curLen) {
                //cnt(현재 방향에 수를 몇 번 찍었는가)를 1로 초기화하고 방향을 바꿔줌
				cnt = 1; d = (d + 1) % 4;
                //이때 좌, 우로 이동해야한다면 찍어야하는 수의 길이가 1 증가
				if (d == 0 || d == 2) curLen++;
			}
			
			r += dr[d]; c += dc[d];
			cnt++;
		}
		
        //현재 찍힌 수들 중 최댓값의 길이를 구하고
		int maxNumSize = String.valueOf(max).length();
		StringBuilder sb = new StringBuilder();
		for (int i = r1; i <= r2; i++) {
			for (int j = c1; j <= c2; j++) {
                //동적으로 패딩하여 출력
				sb.append(String.format("%" + maxNumSize + "d", map.get(new Pos(i, j))));
                //맨 끝에는 공백을 삽입하면 안됨
				if (j != c2) {
					sb.append(" ");
				}
			}
			sb.append('\n');
		}
		System.out.print(sb);
	}
	
	static int nextInt() throws Exception {
		st.nextToken();
		return (int) st.nval;
	}
	
}