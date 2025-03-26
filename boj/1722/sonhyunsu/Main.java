import java.io.*;
import java.util.*;

public class Main {
    
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
	public static void main(String[] args) throws IOException {
	    int n = Integer.parseInt(br.readLine());
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    
	    long[] fact = new long[n + 1];
	    
	    List<Integer> nums = new ArrayList<>();
	    fact[0] = 1;
	    for (int i = 1; i <= n; i++) {
	        fact[i] = fact[i - 1] * i;
	        nums.add(i);
	    }
	    
	    StringBuilder sb = new StringBuilder();
	    int type = Integer.parseInt(st.nextToken());
	    if (type == 1) {
	        
	        long order = Long.parseLong(st.nextToken()) - 1;
	        
	        while (n-- > 0) {
	            int idx = (int) (order / fact[n]);
	            order %= fact[n];
	            sb.append(nums.get(idx)).append(' ');
	            nums.remove(idx);
	        }
	        
	    } else {
	        long order = 1;
	        
	        while (n-- > 0) {
	            Integer num = Integer.parseInt(st.nextToken());
	            order += fact[n] * nums.indexOf(num);
	            nums.remove(num);
	        }
	        
	        sb.append(order);
	    }
	    
	    System.out.print(sb);
	}
}