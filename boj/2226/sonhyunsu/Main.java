//제출번호:	95008731
//메모리:	11728 KB
//실행시간:	68 ms

/* n = 7까지 한 번 전개해봄
1																				0
01																				1
1001																			1
0110 1001																		3
1001 0110 0110 1001																5
0110 1001 1001 0110 1001 0110 0110 1001											11
1001 0110 0110 1001 0110 1001 1001 0110 0110 1001 1001 0110 1001 0110 0110 1001	21

4개씩 끊어서 보면 1001, 0110이 반복되는 것을 볼 수 있음
이거 점화식이 나오겠구나 싶었고 주어진 수를 가지고 가장 쉬운 점화식을 세워보니까 f(n) = 2f(n-2) + f(n-1)이 나옴
 */

//점화식에 2를 계속 곱하니까 f(1000)은 거의 2^1000 비슷하게 나올 거라 생각했음
//long에는 다 못 담기니까 BigInteger를 써야하나 싶었는데
//한 번 직접 구현해보자 하고 구현해봤음
//f(n)당 총 17칸의 배열을 사용하고 각 원소에는 18자리의 숫자가 들어감
//18자리를 넘어가면 다음 배열에 넘기는 방식으로 구현했음 
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
	    long[][] dp = new long[3][17];
	    dp[2][0] = 1;
        
	    int n = nextInt();
	    for (int i = 3; i <= n; i++) {
	        next(dp[i%3], dp[(i+1)%3], dp[(i+2)%3]);
	    }
	    
	    StringBuilder sb = new StringBuilder();
	    boolean isNumArea = false;
	    long[] res = dp[n%3];
	    
	    for (int i = 16; i >= 0; i--) {
	        if (res[i] != 0 || isNumArea || i == 0) {
				//첫 자리는 있는 숫자만큼만 출력, 그 뒤부터는 항상 18자리를 출력
	            sb.append(String.format(isNumArea?"%018d":"%d", res[i]));
	            isNumArea = true;
	        }
	    }
	    
	    System.out.print(sb);
	}
	
    static final long DIV = (long) 1e18;
	static void next(long[] d, long[] s1, long[] s2) {
	    long adder = 0;
	    for (int i = 0; i < 17; i++) {
			//f(n) = 2f(n-2) + f(n-1)
	        d[i] = (adder += (s1[i] << 1) + s2[i]) % DIV;
	        adder /= DIV;
	    }
	}
	
	static int nextInt() throws IOException {
	    int n = System.in.read() & 15, c;
	    while ((c = System.in.read()) > 47)
	        n = (n << 3) + (n << 1) + (c & 15);
	    return n;
	}
}