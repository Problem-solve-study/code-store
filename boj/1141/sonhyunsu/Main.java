import java.io.*;
import java.util.*;

public class Main {
    
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String[] words;
    
	public static void main(String[] args) throws IOException {
	    int n = Integer.parseInt(br.readLine());
	    words = new String[n];
	    for (int i = 0; i < n; i++) {
	        words[i] = br.readLine();
	    }
	    
	    Arrays.sort(words);
	    
	    int cnt = n;
	    for (int i = 1; i < n; i++) {
	        if (words[i].startsWith(words[i - 1])) {
	            cnt--;
	        }
	    }
	    
	    System.out.print(cnt);
	} 
}