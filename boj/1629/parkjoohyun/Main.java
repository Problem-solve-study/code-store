import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main{
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	
	static long a,b,c;
	static void input() {
		a = nextInt();
		b = nextInt();
		c = nextInt();
	}
	
	static long exponent(long a, long b, long c) {
		if(b==0) return 1;
		
		long half = exponent(a,b/2,c);
		half = (half*half)%c;
		if(b%2==0) return half;
		else return (half*a)%c;
	}
	
	
	public static void main(String[] args) {
		input();
		System.out.println(exponent(a,b,c));
	}
	
	static int nextInt() {
		try {
			st.nextToken();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (int)st.nval;
	}
}