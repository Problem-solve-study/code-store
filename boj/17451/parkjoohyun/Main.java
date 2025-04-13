//Memory	57316KB
//Time	300ms

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int[] arr;
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   
    	n = Integer.parseInt(br.readLine());
    	arr = new int[n];
    	
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	for(int i = 0; i < n; i++) {
    		arr[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	long speedMin = 0;
 
    	for(int i = n-1; i >=0; i--) {
    		if(speedMin <= arr[i]) {
    			speedMin = arr[i];
    		} else if(speedMin > arr[i]) {
    			long div = (speedMin / arr[i]);
    			if(speedMin%arr[i]!=0) {
    				div++;
    			}
    			speedMin = arr[i] * div;
    		}
    	}
    	System.out.println(speedMin);
        br.close();
    }
    	
 

}