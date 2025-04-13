import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main{
	
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	
	static int[][] friends;
	static int n, m;
	public static void main(String[] args) {
		input();
		floidWarshall();
		printKevinBacon();
	}
	
	static void printKevinBacon() {
		int min = Integer.MAX_VALUE;
		int idx = 0;
		for(int i = 1;i<=n;i++) {
			int value =0;
			for(int j = 1;j<=n;j++) {
				value += friends[i][j];
				if(value > min) break;
			}
			if(min > value)
				idx = i;
			min = Math.min(min, value);
			
		}
		
		System.out.println(idx);
	}
	
	static void floidWarshall() {
		for(int k = 1;k<=n;k++) {
			for(int i = 1;i<=n;i++) {
				if(i==k || friends[i][k] == Integer.MAX_VALUE) continue;
				for(int j = 1;j<=n;j++) {
					if(j==i || j==k || friends[k][j] == Integer.MAX_VALUE) continue;
					friends[i][j] = Math.min(friends[i][j], friends[i][k] + friends[k][j]);
				}
			}
		}
	}
	
	static void input() {
		n = nextInt();
		m = nextInt();
		friends = new int[n+1][n+1];
		for(int i = 0;i<=n;i++) {
			for(int j = 0;j<=n;j++) {
				if(i==j) continue;
				friends[i][j] = Integer.MAX_VALUE;
			}
		}
		for(int i =0;i<m;i++) {
			int u = nextInt();
			int v = nextInt();
			friends[u][v] = 1;
			friends[v][u] = 1;
		}
	}
	static int nextInt() {
		try {
			st.nextToken();
			return (int)st.nval;
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return -1;
	}

}