//제출번호: 89149461
//메모리:	19592 KB
//실행시간:	180 ms
import java.util.*;

public class Main
{
    static int[][][] d = new int[61][61][61];
    static int[] scv = new int[3];
    static Queue<Triple> q = new LinkedList<>();
	public static void main(String[] args) {
	    Scanner scan = new Scanner(System.in);
	    
	    int n = scan.nextInt();
	    for (int i = 0; i < n; i++) {
	        scv[i] = scan.nextInt();
	    }
	    
	    q.add(new Triple(scv[0], scv[1], scv[2]));
	    d[scv[0]][scv[1]][scv[2]] = 1;
	    while (!q.isEmpty()) {
	        Triple tri = q.poll();
	        
	        int cnt = d[tri.f][tri.s][tri.t];
	        attackScv(new Triple(tri.f - 9, tri.s - 3, tri.t - 1), cnt + 1);
	        attackScv(new Triple(tri.f - 9, tri.s - 1, tri.t - 3), cnt + 1);
	        attackScv(new Triple(tri.f - 3, tri.s - 9, tri.t - 1), cnt + 1);
	        attackScv(new Triple(tri.f - 3, tri.s - 1, tri.t - 9), cnt + 1);
	        attackScv(new Triple(tri.f - 1, tri.s - 9, tri.t - 3), cnt + 1);
	        attackScv(new Triple(tri.f - 1, tri.s - 3, tri.t - 9), cnt + 1);
	    }
	    
	    System.out.println(d[0][0][0] - 1);
	    
	    scan.close();
	}
	
	public static void attackScv(Triple tri, int cnt) {
	    if (d[tri.f][tri.s][tri.t] > 0) {
	        return;
	    }
	    
	    q.add(tri);
	    d[tri.f][tri.s][tri.t] = cnt;
	    return;
	}
	
	public static class Triple {
	    int f;
	    int s;
	    int t;
	    
	    Triple(int f, int s, int t) {
	        this.f = Math.max(f, 0);
	        this.s = Math.max(s, 0);
	        this.t = Math.max(t, 0);
	    }
	}
}