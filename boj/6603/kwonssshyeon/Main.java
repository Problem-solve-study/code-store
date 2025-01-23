import java.io.*;
import java.util.*;

class Main {
    static int[] nums;
    static int n;
    static StringBuilder sb = new StringBuilder();

	public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            if (n == 0) break;
            nums = new int[n];
            for (int i=0; i<n; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }
            dfs(new ArrayList<>(), 0);
            sb.append("\n");
        }
        System.out.print(sb.substring(0, sb.length()-1));
	}

    static void dfs(ArrayList<Integer> lottos, int start) {
        if (lottos.size() == 6) {
            for (int lotto : lottos) {
                sb.append(lotto+" ");
            }
            sb.append("\n");
        }

        for (int i=start; i<n; i++) {
            lottos.add(nums[i]);
            dfs(lottos, i+1);
            lottos.remove(lottos.size()-1);
        }
    }
}