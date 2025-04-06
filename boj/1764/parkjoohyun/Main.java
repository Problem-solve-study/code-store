//Memory 26148 Time 296ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.TreeSet;

public class Main {
    static int n,m;
    
    static TreeSet<String> list = new TreeSet<>();
    static TreeSet<String> ans = new TreeSet<>();
    
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	String[] input = br.readLine().split(" ");
    	n = Integer.parseInt(input[0]);
    	m = Integer.parseInt(input[1]);
    	
    	for(int i =0;i<n;i++) {
    		list.add(br.readLine());
    	}
    	StringBuilder sb = new StringBuilder();
    	
    	int cnt =0;
    	for(int i=0;i<m;i++) {
    		String name = br.readLine();
    		if(!list.contains(name)) continue;
    		ans.add(name);
    		cnt++;
    	}
    	Iterator<String> it = ans.iterator();
    	for(int i = 0;i<ans.size();i++) {
    		sb.append(it.next()).append("\n");
    	}
    	
    	
    	System.out.println(cnt);
    	System.out.println(sb);
    	
    }
}
