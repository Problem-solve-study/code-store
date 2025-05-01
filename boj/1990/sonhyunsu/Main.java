//제출번호: 93806020
//메모리:   11608 KB
//실행시간: 80 ms
import java.io.*;

//1부터 9999까지 숫자를 가지고 펠린드롬 수를 먼저 만든 후,
//그 수가 소수인지 판별하면 쉬움
public class Main {
    
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    
	public static void main(String[] args) throws IOException {
	    int a = nextInt();
	    int b = nextInt();
	    
	    StringBuilder sb = new StringBuilder();
	    for (int i = 1, j = 10; i < 10000; i *= 10, j *= 10) {
			//홀수 펠린드롬을 만들고 판별
	        for (int n = i; n < j; n++) {
	            int num = makeOddPalindrome(n);
				//소수이면서 a <= num <= b라면 출력
	            if (isPrime(num) && a <= num && num <= b) {
	                sb.append(num).append('\n');
	            }
	        }
	        
			//짝수 펠린드롬을 만들고 판별
	        for (int n = i; n < j; n++) {
	            int num = makeEvenPalindrome(n);
				//소수이면서 a <= num <= b라면 출력
	            if (isPrime(num) && a <= num && num <= b) {
	                sb.append(num).append('\n');
	            }
	        }
	    }
	   
		//마지막에 -1 넣고 출력
	    sb.append(-1);
	    System.out.print(sb);
	}
	
	//홀수 펠린드롬 생성
	static int makeOddPalindrome(int n) {
	    int res = 0, tmp = n, mul = 1;

		//res는 n을 뒤집은 수를 가지고 있음
	    while (tmp > 0) {
	        res = res * 10 + tmp % 10;
	        tmp /= 10;
	        mul *= 10;
	    }
	    
		//적당히 나누고 곱하면 펠린드롬 수를 만들 수 있음
	    return n / 10 * mul + res;
	}
	
	//짝수 펠린드롬 생성
	static int makeEvenPalindrome(int n) {
	    int res = 0, tmp = n, mul = 1;
	    while (tmp > 0) {
	        res = res * 10 + tmp % 10;
	        tmp /= 10;
	        mul *= 10;
	    }
	    
	    return n * mul + res;
	}
	
	//소수 판별
	static boolean isPrime(int n) {
		//0, 1은 소수가 아님
	    if (n < 2) {
	        return false;
	    }
	    
		//2는 소수임
	    if (n == 2) {
	        return true;
	    }
	    
		//2를 제외한 2의 배수는 소수가 아님
	    if ((n & 1) == 0) {
	        return false;
	    }
	    
		//sqrt(n)까지 홀수만 보면서
	    for (int i = 3; i * i <= n; i += 2) {
			//i로 나누어 떨어지면 소수가 아님
	        if (n % i == 0) {
	            return false;
	        }
	    }
	    
		//여기까지 오면 소수임
	    return true;
	}
	
	static int nextInt() throws IOException {
	    st.nextToken();
	    return (int) st.nval;
	}
}