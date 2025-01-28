//제출번호: 89253459
import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static List<Pair> balloons = new ArrayList<>();
    public static int gcd(int a, int b) {
        int t = 0;
        while (b != 0) {
            t = a % b;
            a = b;
            b = t;
        }
        
        return a;
    }
    
	public static void main(String[] args) throws IOException {
	    BufferedReader re = new BufferedReader(new InputStreamReader(System.in));
	    int n = Integer.parseInt(re.readLine());
	    
	    for (int i = 0; i < n; i++) {
	        StringTokenizer st = new StringTokenizer(re.readLine());
	        int x = Integer.parseInt(st.nextToken());
	        int y = Integer.parseInt(st.nextToken());
	        int g = gcd(Math.abs(x), Math.abs(y));
	        
            balloons.add(new Pair(x/g, y/g));
	    }
	    
	    balloons.sort(Pair::compareTo);
	    
	    int res = 0;
	    int x = 0, y = 0, cnt = 0;
	    for (Pair p : balloons) {
	        //System.out.println(p.f + " " + p.s);
	        if (p.f == x && p.s == y) {
	            cnt++;
	        } else {
	            res = Math.max(res, cnt);
	            cnt = 1;
	        }
	        
	        x = p.f;
	        y = p.s;
	    }
	    res = Math.max(res, cnt);
	    
	    System.out.print(res);
	}
	
	public static class Pair implements Comparable<Pair> {
	    int f;
	    int s;
	    
	    Pair (int f, int s) {
	        this.f = f;
	        this.s = s;
	    }
	    
	    public int compareTo(Pair ano) {
	        if (this.f == ano.f) {
	            return Integer.compare(this.s, ano.s);
	        }
	        
	        return Integer.compare(this.f, ano.f);
	    }
	}
}