// 19984KB	112ms
import java.io.*;

public class Main {
    static String num;
    static boolean[] visited;
    static int[] answer;
    static int n;
    static boolean find;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        num = br.readLine();
        n = (num.length() < 10) ? num.length() : (num.length() + 9) / 2;
        visited = new boolean[n+1];
        answer = new int[n];
        dfs(0, 0);
        
    }

    static void dfs(int idx, int cnt) {
        if (find) return;
        if (cnt == n) {
            find = true;
            StringBuilder sb = new StringBuilder();
            for (int i=0; i<n; i++) {
                sb.append(answer[i]).append(" ");
            }
            System.out.print(sb);
            return;
        }
        if (idx <= num.length()-1) {
            int next = Integer.parseInt(num.substring(idx, idx + 1));
            if (next != 0 && next <= n && !visited[next]) {
                visited[next] = true;
                answer[cnt] = next;
                dfs(idx + 1, cnt + 1);
                visited[next] = false;
            }
        }
        
        if (idx <= num.length()-2) {
            int next = Integer.parseInt(num.substring(idx, idx + 2));
            if (next >= 10 && next <= n && !visited[next]) {
                visited[next] = true;
                answer[cnt] = next;
                dfs(idx + 2, cnt + 1);
                visited[next] = false;
            }
        }
    }
}