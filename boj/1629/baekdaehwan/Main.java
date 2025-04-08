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