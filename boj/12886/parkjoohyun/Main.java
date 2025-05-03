//Memory 80756kb Time 204ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static StreamTokenizer st= new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	static int a,b,c, total;
	static boolean[][] visited = new boolean[1501][1501];
	
    public static void main(String[] args) throws IOException{
    	input();
    	if(solution()) System.out.println(1);
    	else System.out.println(0);
    }
    
    public static boolean solution() {
    	total = a+b+c;
    	if(total%3!=0) return false;
    	
    	return bfs(a,b,c);
    }
    
    public static boolean bfs(int a, int b, int c) {
        if (total % 3 != 0) return false;

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{a, b});
        visited[a][b] = true;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int x = now[0];
            int y = now[1];
            int z = total - x - y;

            if (x == y && y == z) return true;

            int[] stones = new int[]{x, y, z};

            // 3가지 쌍 조합
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i == j || stones[i] == stones[j]) continue;

                    int[] next = stones.clone();
                    int small = Math.min(stones[i], stones[j]);
                    int large = Math.max(stones[i], stones[j]);

                    next[i] = small + small;
                    next[j] = large - small;

                    int na = next[0];
                    int nb = next[1];
                    int nc = next[2];

                    int[] sorted = new int[]{na, nb, nc};
                    Arrays.sort(sorted);
                    na = sorted[0]; nb = sorted[1];

                    if (!visited[na][nb]) {
                        visited[na][nb] = true;
                        q.offer(new int[]{na, nb});
                    }
                }
            }
        }
        return false;
    }
    
    public static void input() throws IOException {
    	a = nextInt();
    	b = nextInt();
    	c = nextInt();
    }
    
    public static int nextInt() throws IOException {
    	st.nextToken();
    	return (int)st.nval;
    }
}