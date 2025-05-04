//Memory 384964KB Time 2076MS

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {
	static StreamTokenizer st= new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	static int min, max;
	static boolean[] primes;
    public static void main(String[] args) throws IOException{
    	input();
    	solution();
    }
    
    public static void solution() {
    	prime();
    	StringBuilder sb = new StringBuilder();
    	for(int i =min;i<=max;i++) {
    		if(primes[i] && isPalindrome(i))
    			sb.append(i).append('\n');
    	}
    	sb.append("-1");
    	System.out.println(sb);
    }
    
    public static boolean isPalindrome(int n) {
    	String num = String.valueOf(n);
    	int left = 0, right=num.length()-1;
    	
    	while(left<right) {
    		if(num.charAt(left) != num.charAt(right)) return false;
    		left++;
    		right--;
    	}
    	return true;
    }
    
    public static void prime() {
    	for(int i = 2;i*i<=max;i++) {
    		if(primes[i]) {
    			for(int j=i*i;j<=max;j+=i) {
    				primes[j] = false;
    			}
    		}
    	}
    }
    
    public static void input() throws IOException {
    	min = nextInt();
    	max = nextInt();
    	
    	primes = new boolean[max+1];
    	
    	for(int i =2;i<=max;i++) {
    		primes[i] = true;
    	}
    }
    
    public static int nextInt() throws IOException {
    	st.nextToken();
    	return (int)st.nval;
    }
}