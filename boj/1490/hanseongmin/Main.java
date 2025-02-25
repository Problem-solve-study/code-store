import java.io.*;
import java.util.*;

/*
 * 11752KB, 68ms
 * 
 * 수학적으로 우아하게 구할 수 있는 방법이 있는지 고민해봄
 * -> 딱히 떠오르지 않음
 * -> 예제들을 잘 관찰해보니 생각보다 낮은 시행횟수로 구할 수 있는 것 같아 보임
 * -> 브루트포스를 한 번 돌려보자 뚫릴 수도 있겠다.
 * -> 맞음
 */

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StreamTokenizer st = new StreamTokenizer(br);
    static HashSet<Long> set = new HashSet();

    public static void main(String[] args) throws Exception {
    	long n = nextLong();
        //n의 자릿수를 저장
    	long temp = n;
    	while (temp != 0) {
    		long cur = temp % 10;
    		temp /= 10;
    		set.add(cur);
    	}
    	
    	if (check(n)) {
    		bw.write(String.valueOf(n));
    	} else {
            //모든 경우를 탐색
	    	outer:
	    	for (int i = 1; true; i++) {
	    		int limit = (int)Math.pow(10, i);
	    		for (int j = 0; j < limit; j++) {
	    			long cur = n * limit + j;
	    			if (check(cur)) {
	    				bw.write(String.valueOf(cur));
	    				break outer;
	    			}
	    		}
	    	}
    	}
    	
    	bw.flush();
    }
    
    static boolean check(long n) {
        //주어진 수가 n의 모든 자릿수로 나눌 수 있는지 판별
    	for (long e : set) {
    		if (e == 0) continue;
    		if (n % e != 0) {
    			return false;
    		}
    	}
    	
    	return true;
    }
    
    static long nextLong() throws Exception {
        st.nextToken();
        return (long) st.nval;
    }
}