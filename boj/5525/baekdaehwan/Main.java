/**
 * 	20532KB	160ms
 * 
 * [사고 흐름]
 * 누적합...? 아 그냥 ioi개수 세면 되겠구나
 */

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    	int N = Integer.parseInt(br.readLine());
    	int M = Integer.parseInt(br.readLine());
    	String S = br.readLine();
    	
    	int res = 0;
    	int cnt = 0;
    	char l = '-';
    	char ll = '-';
    	for (int i=0; i<M; ++i) {
    		char c = S.charAt(i);
    		if (ll=='I'&&l=='O'&&c=='I') {
    			++cnt;
    			if (N <= cnt) ++res;
    		} else if (ll=='O'&&l=='I'&&c=='O') {
    			
    		} else {
    			cnt = 0;
    		}
    		ll = l;
    		l = c;
    	}
    	System.out.println(res);
    }
}
