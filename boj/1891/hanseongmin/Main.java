import java.io.*;
import java.util.*;

/*
 * 11532KB, 68ms
 * 
 * 사분면 -> 분할정복 국룰 키워드. 그리고 문제에서 그냥 분할정복 쓰고 싶지않아?? 라고 말하고있다.
 * x, y의 값을 보면 2^50까지 들어올 수 있어서 일일히 조각을 움직이는 걸로 계산하는 걸론 어림도 없고 거의 O(1)에 가깝게 구해야한다.
 * 그러니까 현재 조각의 위치를 기반으로 좌표를 찾고 x, y 값을 더하여 다음 조각의 좌표를 구한 다음
 * 다음 조각의 좌표를 기반으로 답을 역추적 해내가면 되겠다 싶어서 그대로 구현
 * 
 * 위치 -> 좌표 찾기 및 좌표 -> 위치 찾기 모두 분할 정복으로 간단히 구현 가능
 * d의 길이를 갖는 다음 조각의 위치를 찾아야하므로 시간복잡도는 O(d)
 */

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static long d, x, y, len;
	static String pos;
	static StringBuilder sb = new StringBuilder();
	static long curR, curC, nextR, nextC;
	
	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		d = Long.parseLong(st.nextToken());
		pos = st.nextToken();
		len = (long) Math.pow(2, d);
		st = new StringTokenizer(br.readLine());
		x = Long.parseLong(st.nextToken());
		y = Long.parseLong(st.nextToken());
		
        //현재 위치를 기반으로 좌표 탐색
		findCurRC(0, 1, len, 1, len);
        //다음 좌표를 기반으로 위치 탐색
		findNextPos(0, 0, 1, len, 1, len);
        //만일 위치를 탐색한 원래 자릿수와 일치하지 않다면 존재하지 않는 사분면인 경우
		if (sb.length() != d) {
			System.out.print(-1);
		} else {
			System.out.print(sb);
		}
	}
	
	static void findCurRC(int posIdx, long rs, long re, long cs, long ce) {
        //만일 모든 자릿수를 봤다면 좌표가 확정
		if (posIdx == pos.length()) {
			curR = rs;
			curC = cs;
			nextR = curR - y;
			nextC = curC + x;
			return;
		}
		
        //현재 사분면 계산
		int curPos = pos.charAt(posIdx) - '0';
        //현재 구간 크기 계산
		long curRange = re - rs + 1;
        //다음 구간 크기 계산
		long nextRange = curRange / 2;

        //각각의 사분면에 맞게 r와 c의 범위를 줄여감
		if (curPos == 1) {
			findCurRC(posIdx + 1, rs, re - nextRange, cs + nextRange, ce);
		} else if (curPos == 2) {
			findCurRC(posIdx + 1, rs, re - nextRange, cs, ce - nextRange);
		} else if (curPos == 3) {
			findCurRC(posIdx + 1, rs + nextRange, re, cs, ce - nextRange);
		} else {
			findCurRC(posIdx + 1, rs + nextRange, re, cs + nextRange, ce);
		}
	}
	
	static void findNextPos(int idx, int num, long rs, long re, long cs, long ce) {
        //만일 다음 좌표가 현재 메소드의 구간 내부에 존재한다면 사분면 기록
		if ((rs <= nextR && nextR <= re) && (cs <= nextC && nextC <= ce)) {
			if (idx != 0)
				sb.append(num);
		} else {
			return;
		}
		
        //모든 자릿수를 다 봤다면 나옴
		if (idx == d) {
			return;
		}
		
        //현재 구간 크기 계산
		long curRange = re - rs + 1;
        //다음 구간 크기 계산
		long nextRange = curRange / 2;

        //몇 사분면인지 모르므로 모든 사분면에 대해 탐색
        //적절하지 않은 사분면이라면 바로 나오게 되고 적절한 사분면일 때만 들어가서 추가 탐색을 진행
		findNextPos(idx + 1, 1, rs, re - nextRange, cs + nextRange, ce);
		findNextPos(idx + 1, 2, rs, re - nextRange, cs, ce - nextRange);
		findNextPos(idx + 1, 3, rs + nextRange, re, cs, ce - nextRange);
		findNextPos(idx + 1, 4, rs + nextRange, re, cs + nextRange, ce);
	}
}