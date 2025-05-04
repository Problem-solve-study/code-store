//Memory 14076KB Time 92MS

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Main {
	static StreamTokenizer st= new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	static int[] aList, bList, cList;
	static int a, b, c;
	
    public static void main(String[] args) throws IOException{
    	input();
    	solution();
    }
    
    public static void solution() {
    	int aIdx = 0, bIdx = 0, cIdx = 0;
    	int minResult = Integer.MAX_VALUE;
    	
    	while(aIdx<a && bIdx<b && cIdx<c) {
    		int max = Math.max(aList[aIdx], Math.max(bList[bIdx], cList[cIdx]));
    		int min = Math.min(aList[aIdx], Math.min(bList[bIdx], cList[cIdx]));
    		
    		minResult = Math.min(minResult, max - min);
    		
    		if(min == aList[aIdx]) aIdx++;
    		else if(min == bList[bIdx]) bIdx++;
    		else cIdx++;
    	}
    	
    	System.out.println(minResult);
    }
    
    public static void input() throws IOException {
    	a = nextInt();
    	b = nextInt();
    	c = nextInt();
    	
    	aList = new int[a];
    	bList = new int[b];
    	cList = new int[c];
    	
    	for(int i=0;i<a;i++) {
    		aList[i] = nextInt();
    	}
    	
    	for(int i=0;i<b;i++) {
    		bList[i] = nextInt();
    	}
    	
    	for(int i=0;i<c;i++) {
    		cList[i] = nextInt();
    	}
    	
    	Arrays.sort(aList);
    	Arrays.sort(bList);
    	Arrays.sort(cList);
    }
    
    public static int nextInt() throws IOException {
    	st.nextToken();
    	return (int)st.nval;
    }
}