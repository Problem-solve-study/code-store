//제출번호: 94176217	
//메모리:   11660 KB
//실행시간: 68 ms
import java.io.*;

//한 팀이 x점을 획득할 확률은 18Cx * (a/100)^x * (1 - a/100)^(18-x)임
//P(A합B) = P(A) + P(B) - P(A교B) 이므로 각각의 확률만 잘 구해주면 됨
public class Main {
    
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    
	public static void main(String[] args) throws IOException {

        //조합 계산
	    int[] comb = new int[19];
	    comb[0] = 1;
	    for (int i = 1; i <= 18; i++) {
	        for (int j = i; j > 0; j--) {
	            comb[j] = comb[j] + comb[j-1]; 
	        }
	    }
	    
        //a, b 입력받고, 100 - a, 100 - b도 계산해둠
	    int a = nextInt(), ra = 100 - a;
	    int b = nextInt(), rb = 100 - b;
	    
        //모든 소수에 대해서 
	    int[] primes = {2, 3, 5, 7, 11, 13, 17};
	    double[] aps = new double[19], bps = new double[19];
	    for (int prime : primes) {
            //a, b팀의 소수 득점 확률을 구함
	        aps[prime] = 1.0 * comb[prime] * pow(a, prime) * pow(ra, 18-prime);
	        bps[prime] = 1.0 * comb[prime] * pow(b, prime) * pow(rb, 18-prime);
	    }
	    
	    double res = 0;
	    for (int prime : primes) {
            //결과에 a, b팀의 소수 득점 확률을 구하고
	        res += aps[prime] + bps[prime];
	        for (int tPrime : primes) {
                //a팀과 b팀 모두 소수 점수를 득점할 확률을 빼줌
	            res -= aps[prime] / 1e36 * bps[tPrime];
	        }
	    }
	    
        //실제 확률 자릿수에 맞춰서 출력 
	    System.out.print(res / 1e36);
	}
	
    //x^y 계산
	static double pow(int x, int y) {
	    double res = x;
	    for (int i = 1; i < y; i++) {
	        res *= x;
	    }
	    return res;
	}
	
	static int nextInt() throws IOException {
	    st.nextToken();
	    return (int) st.nval;
	}
}