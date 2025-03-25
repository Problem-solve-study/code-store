import java.io.*;
import java.util.*;

/*
 * 14120KB, 80ms
 * 
 * a와 b의 제한이 36밖에 안되니 브루트포스로 조건 탐색하기
 * 진법 변환은 parseLong을 사용하면 아주 쉽게 가능하다.
 * parseLong은 진법 변환할 때 radix보다 큰 수가 들어오면 예외를 발생시키므로 이를 try-catch로 처리
 * x 값이 2^63 이상일 때는 오버플로우 덕분에 같은 값이 나오는 경우가 없지 않을까 싶어서 처리를 안했는데 안해도 되는 것 같다.
 */

public class Main {
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	String aStr = st.nextToken();
    	String bStr = st.nextToken();
    	
    	long cnt = 0;
    	long x = 0;
    	long a = 0;
    	long b = 0;
    	outer: //a와 b 진법을 바꿔가며 탐색
    	for (int i = 2; i <= 36; i++) {
    		for (int j = 2; j <= 36; j++) {
    			if (i == j) continue; //A != B라는 조건이 문제에 명시되어 있음
    			try {
    				//a를 i진법으로 변환한 수와 b를 j진법으로 변환한 수가 동일하다면 x를 찾은 것
	    			if (Long.parseLong(aStr, i) == Long.parseLong(bStr, j)) {
	    				cnt++;
	    				x = Long.parseLong(aStr, i);
	    				a = i;
	    				b = j;
	    				//만일 cnt가 2 이상이라면 더 이상 탐색할 필요없이 Multiple 출력하면 됨
	    				if (cnt == 2) {
	    					break outer;
	    				}
	    			}
    			} catch (Exception e) {}
    		}
    	}
    	
    	if (cnt <= 0) {
    		System.out.print("Impossible");
    	} else if (cnt == 1) {
    		System.out.print(x + " " + a + " " + b);
    	} else {
    		System.out.print("Multiple");
    	}
    }
}