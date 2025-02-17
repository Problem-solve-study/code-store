//제출번호: 90094652
//메모리:   11528 KB
//실행시간: 68 ms
import java.io.*;
import java.util.*;

//양 끝이 1이면서 가운데로 갈수록 1씩 증가하는 그래프를 생각해보자
//날짜마다 갈 수 있는 최대 거리가 있을 때
//(정답 - 1)일의 최대 이동거리 < 목표 이동거리 <= (정답)일의 최대 이동거리
//의 수식이 성립한다.
//목표 이동거리를 맞추는 방법은 가운데부터 평탄화하면서 이동거리를 맞추면 된다.
public class Main {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());

	    int monkey = Integer.parseInt(st.nextToken());
	    int dog = Integer.parseInt(st.nextToken());

	    int day = 0;
	    long maxHeight = 0;

	    for (int i = 0; monkey + maxHeight < dog; i++) {
	        maxHeight += i / 2 + 1;
	        day++;
	    }

	    System.out.print(day);
	}
}