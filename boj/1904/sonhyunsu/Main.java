import java.io.*;

public class Main {
    
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    
	public static void main(String[] args) throws IOException {
	    int n = nextInt();
	    
	    int[] d = {0, 1, 2};
	    for (int i = 3; i <= n; i++) {
	        d[i%3] = (d[(i-1)%3] + d[(i-2)%3]) % 15746;
	    }
	    
	    System.out.print(d[n%3]);
	}
	
	static int nextInt() throws IOException {
	    st.nextToken();
	    return (int) st.nval;
	}
}