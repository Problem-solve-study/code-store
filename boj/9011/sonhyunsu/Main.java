//제출번호: 89339095
//메모리:	29944 KB
//실행시간:	312 ms
import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
	    BufferedReader re = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
	    
	    int T = Integer.parseInt(re.readLine());
	    for (int t = 0; t < T; t++) {
	        int n = Integer.parseInt(re.readLine());
	        int[] r = new int[n];
	        int[] s = new int[n];
	        int[] cnt = new int[n];
	        
	        StringTokenizer st = new StringTokenizer(re.readLine());
	        for (int i = 0; i < n; i++) {
	            r[i] = Integer.parseInt(st.nextToken());
	        }
	        
	        int j = 0;
	        for (int i = 1; i <= n; i++) {
	            for (j = n-1; j >= 0; j--) {
	                if (r[j] == cnt[j]) {
	                    s[j] = i;
	                    cnt[j]++;
	                    break;
	                }
	                cnt[j]++;
	            }
	            
	            if (j == -1) {
	                break;
	            }
	        }
	        
	        if (j == -1) {
	            wr.write("IMPOSSIBLE\n");
	        } else {
	            for (int i = 0; i < n; i++) {
	                wr.write(String.format("%d ", s[i]));
	            }
	            wr.write('\n');
	        }
	    }
	    
	    wr.flush();
	}
}