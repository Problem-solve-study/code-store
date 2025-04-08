/**
 * 11468KB	68ms
 * 
 * 분할정복을 이용한 거듭제곱 쓰면 될 것 같아서, 비재귀로 구현했습니다
 */

import java.io.*;

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static void main(String[] args) throws Exception {
    	System.out.println(pow(ni(), ni(), ni()));
    }
    
    static long pow(long a, long n, long mod) {
    	long r = 1;
        for (; n!=0; n>>=1) {
    		if ((n&1) == 1) r = r*a % mod;
    		a = a*a % mod;
    	}
    	return r;
    }

    static int ni() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}