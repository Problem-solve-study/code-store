import java.util.*;
import java.io.*;


public class Main {
    static int n;
    static int[] R, S;
    static boolean[] visited;

	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int test_case=0; test_case<T; test_case++) {
            n = Integer.parseInt(br.readLine());
            R = new int[n];
            S = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i=0; i<n; i++) {
                R[i] = Integer.parseInt(st.nextToken());
            }
            
            boolean isPossible = true;
            visited = new boolean[n+1];
            for (int i=n-1; i>=0; i--) {
                if (calcValueAt(i) == false) {
                    isPossible = false;
                    break;
                }
            }
            if (!isPossible) {
                sb.append("IMPOSSIBLE");
            } else {
                for (int s : S) {
                    sb.append(s+" ");
                }
            }
            sb.append("\n");
            
        }
        System.out.print(sb.toString());
	}


    static boolean calcValueAt(int idx) {        
        for (int i = R[idx] + 1; i <= n; i++) {
            if (visited[i]) continue;

            int rightSmallerNum = 0;
            for (int j = idx + 1; j < n; j++) {
                if (i > S[j]) {
                    rightSmallerNum++;
                }
            }

            if (i - rightSmallerNum > R[idx]) { // 나 - 이미 사용한 개수 > 필요한 개수
                S[idx] = i;
                visited[i] = true;
                return true;
            }
        }
        return false;
    }
}

/**
 * 1. S[i] 에 가능한 가장 작은수 R[i] + 1
 * 2-1. 해당 숫자에 방문한 적이 있는 경우 (i++)
 * 2-2. S[i] 뒤에 있는 수 중 S[i] 보다 작은 수가 있어서 S[i] 앞에 R[i] 만큼의 작은 수가 올 수 없는 경우 (i++)
 * 3. S[i]에 결정되는 수가 없는 경우 불가능한 것임.
 */