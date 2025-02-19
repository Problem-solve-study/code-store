//80552KB	1836ms
import java.io.*;
import java.util.*;
/*
 * 재귀를 통해 구현. ArrayList를 통해 구현했는데 굳이 그러지 말고 처음부터 N 길이 배열 설정한뒤 
 * stack처럼 pop push했어도 됐을듯?
 */
public class Main {
	static int N, M;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[N+1];
        ArrayList<Integer> nums = new ArrayList<>();
        permutation(nums, 0);
	}
    static void permutation(ArrayList<Integer> nums, int cnt){
        if(cnt==M){
            for(int n:nums){
                System.out.print(n+" ");
            }
            System.out.println();
            return;
        }

        for(int i=1; i<=N; i++){
            if(visited[i])    continue;
            ArrayList<Integer> num = (ArrayList<Integer>)nums.clone();
            num.add(i);
            visited[i] = true;
            permutation(num, cnt+1);
            visited[i] = false;
        }
        
    }
}
