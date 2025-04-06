//Memory 37196KB Time 492ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;

public class Main {
	static class Path{
		int dest;
		int color;
		
		Path(int dest, int color){
			this.dest = dest;
			this.color = color;
		}
	}
	
	
	static int card[];
	static int t, n;
	
	static ArrayList<ArrayList<Path>> town = new ArrayList<>();
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	static int switchColor(String c) {
		switch(c) {
		case "R":
			return 0;
		case "G":
			return 1;
		case "B":
			return 2;
		}
		return -1;
	}
	
	static void input() throws NumberFormatException, IOException {
		n = nextInt();
		card = new int[n+1];

		for(int i =1;i<=n;i++) {
			card[i] = switchColor(nextString());
		}
		
		t= nextInt();
		int p = nextInt();
		for(int i = 0;i<t+1;i++)
			town.add(new ArrayList<Path>());
		
		
		for(int i=0;i<p;i++) {
			int start = nextInt();
			int end = nextInt();
			int color = switchColor(nextString());
			town.get(start).add(new Path(end, color));
			town.get(end).add(new Path(start, color));
		}
		
	}
	
	static void solution() {
		int[][] dp = new int[n+1][t+1]; // 카드 index - 타운에 대한 점수 저장
		for(int i=0;i<=n;i++) {
			for(int j =0;j<=t;j++) {
				dp[i][j] = -1;
			}
		}
		
		dp[0][1] = 0;
		for(int i=1;i<=n;i++) {
			for(int j =1;j<=t;j++) {
				int cur = dp[i-1][j];
				if(cur== -1) continue;
				for (Path next : town.get(j)) { // 현재 마을에서 이동 가능한 모든 경로
					if(dp[i][next.dest] >= cur + 10) continue;
                    int score = (card[i] == next.color) ? 10 : 0;
                    dp[i][next.dest] = Math.max(dp[i][next.dest], cur + score); //최댓값만 저장
                }
			}
		}
		
		int result = 0;
        for (int i = 1; i <= t; i++) {
            result = Math.max(result, dp[n][i]);
        }
        System.out.println(result);
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		input();
		solution();
	}
	
	static int nextInt() throws IOException{
		st.nextToken();
		return (int) st.nval;
	}
	
	static String nextString() throws IOException{
		st.nextToken();
		return st.sval;
	}
}
