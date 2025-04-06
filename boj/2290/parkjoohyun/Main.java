//Memory 14184kb Time 112ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    
    static int s, sz;
    static String n, horizon, empty;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        s = Integer.parseInt(input[0]);
        n = input[1];
        sz = n.length();
        
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<s;i++) {
        	sb.append('-');
        	horizon = sb.toString();
        }
        sb = new StringBuilder();
        for(int i=0;i<s;i++) {
        	sb.append(' ');
        	empty = sb.toString();
        }
    }

    static void firstHorizonalLine() {
    	StringBuilder sb = new StringBuilder();
    	for(int i = 0 ;i<sz;i++) {
    		char c= n.charAt(i);
    		switch(c) {
    		case '1':
    		case '4':
    			sb.append("  ").append(empty);
    			break;
    		default:
    			sb.append(" ").append(horizon).append(" ");
    			break;
    		}
    		sb.append(" ");
    	}
    	System.out.println(sb);
    }
    
    static void secondHorizonalLine() {
    	StringBuilder sb = new StringBuilder();
    	for(int i = 0 ;i<sz;i++) {
    		char c= n.charAt(i);
    		switch(c) {
    		case '0':
    		case '1':
    		case '7':
    			sb.append("  ").append(empty);
    			break;
    		default:
    			sb.append(" ").append(horizon).append(" ");
    			break;
    		}
    		sb.append(" ");
    	}
    	System.out.println(sb);
    }
    
    static void thirdHorizonalLine() {
    	StringBuilder sb = new StringBuilder();
    	for(int i = 0 ;i<sz;i++) {
    		char c= n.charAt(i);
    		switch(c) {
    		case '1':
    		case '4':
    		case '7':
    			sb.append("  ").append(empty);
    			break;
    		default:
    			sb.append(" ").append(horizon).append(" ");
    			break;
    		}
    		sb.append(" ");
    	}
    	System.out.println(sb);
    }
    
    static void firstVerticalLine() {
    	
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ;i<sz;i++) {
    		char c= n.charAt(i);
    		switch(c) {
    		case '1':
    		case '2':
    		case '3':
    		case '7':
    			sb.append(" ").append(empty).append('|');
    			break;
    		case '5':
    		case '6':
    			sb.append('|').append(empty).append(" ");
    			break;
    		default:
    			sb.append('|').append(empty).append('|');
    			break;
    		}
    		sb.append(" ");
    	}
    	for(int i=0;i<s;i++) {
    		System.out.println(sb);
    	}
    }
    
    static void secondVerticalLine() {
    	StringBuilder sb = new StringBuilder();
    		for(int i = 0 ;i<sz;i++) {
        		char c= n.charAt(i);
        		switch(c) {
        		case '1':
        		case '3':
        		case '4':
        		case '5':
        		case '7':
        		case '9':
        			sb.append(" ").append(empty).append('|');
        			break;
        		case '2':
        			sb.append('|').append(empty).append(" ");
        			break;
        		default:
        			sb.append('|').append(empty).append('|');
        			break;
        		}
        		sb.append(" ");
        	}

    	for(int i=0;i<s;i++) {
    		System.out.println(sb);
    	}
    }
    
    static void solution() {
    	firstHorizonalLine();
    	firstVerticalLine();
    	secondHorizonalLine();
    	secondVerticalLine();
    	thirdHorizonalLine();
    	
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }
}
