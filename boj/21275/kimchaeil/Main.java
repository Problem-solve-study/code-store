//문제: BOJ 21275번 폰 호석만
//메모리: 11728 KB
//시간: 64 ms

/*
 *  브루트포스
 *  A진법으로 표시한 값에서 나온 숫자 또는 알파벳을 통하여 A의 최소값을 구할 수 있다.
 *  B진법으로 표시한 값 또한 마찬가지이다.
 *  A의 최소값과 B의 최소값부터 시작한다.
 *  만약, i진법에서 2^63 이상(long 타입의 최대값 초과)라면 i+1진법은 확인하지 않는다.
 *  진법이 커지면 10진법으로 변환했을 때의 값이 커지기 때문이다.
 */

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(new BufferedReader(new InputStreamReader(System.in)).readLine());
		char[] strA = st.nextToken().toCharArray();
		char[] strB = st.nextToken().toCharArray();

		int aMinForm = 0, bMinForm = 0;
		int[] aNums = new int[strA.length];
		int[] bNums = new int[strB.length];

		//대응되는 10진법 숫자로 변환
		//변환하면서 최소 진법을 찾는다.
		for (int i = 0; i < strA.length; i++) { 
			aNums[i] = strA[i] < 58 ? (strA[i] & 15) : strA[i] - 'a' + 10;
			aMinForm = aMinForm < aNums[i] ? aNums[i] : aMinForm;
		}
		for (int i = 0; i < strB.length; i++) {
			bNums[i] = strB[i] < 58 ? (strB[i] & 15) : strB[i] - 'a' + 10;
			bMinForm = bMinForm < bNums[i] ? bNums[i] : bMinForm;
		}
		aMinForm++; bMinForm++;

		//최대 진법의 초기값은 37;
		int aMaxForm = 37, bMaxForm = 37;
		double[] XfromA = new double[37];
		double[] XfromB = new double[37];
		long limit = Long.MAX_VALUE; //2^63 -1
		
		for (int i = aMinForm; i < 37; i++) {
			for (int j = 0; j < aNums.length; j++) {
				XfromA[i] *= i;
				XfromA[i] += aNums[j];
			}
			if (XfromA[i] > limit) {
				aMaxForm = i;
				break;
			}
		}
		for (int i = bMinForm; i < 37; i++) {
			for (int j = 0; j < bNums.length; j++) {
				XfromB[i] *= i;
				XfromB[i] += bNums[j];
			}
			if (XfromB[i] > limit) {
				bMaxForm = i;
				break;
			}
		}
		int cnt = 0, A = 0, B = 0;
		long X = 0;

		for (int i = aMinForm; i < aMaxForm; i++) {
			for (int j = bMinForm; j < bMaxForm; j++) {
				if (i == j) //A와 B는 같지 않음
					continue;
				if (XfromA[i] == XfromB[j]) { //10진법으로 변환한 값이 같다면 결과 저장
					cnt++;
					A = i;
					B = j;
					X = (long) XfromA[i];
				} else if (XfromA[i] < XfromB[j])
					break;
			}
		}
		
		//10진법으로 변환한 값이 같은 경우가
		if (cnt == 0) { //하나도 없다면 Impossible
			System.out.print("Impossible");
		} else if (cnt > 1) { //2 이상이라면 Multiple
			System.out.print("Multiple");
		} else { //하나라면 X,A,B 출력
			System.out.printf("%d %d %d", X, A, B);
		}
	}
}
