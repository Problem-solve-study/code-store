/** 11524KB	64ms (와 1등)
 * 
 *  노드 개수가 32개 이하여서 비트마스킹으로 풀었음.
 *  문제의 요지는 마약 수사대가 마약 공급책을 체포한 후, 마약 원산지를 제외한 마약 공급책의 수를 찾는 것.
 *  인접 리스트를 사용하는 것이 일반적이지만 노드의 수가 적기 때문에 인접 배열로 만들었고, 이를 비트마스킹으로 표현함.
 *  굳이.. 비트마스킹을 쓸 이유는 없음..
 */

import java.io.*;
import java.util.*;

public class Main {
	static int N, M, K;
	static int[] adj = new int[26];
    static int root;
    static int in;
    static int visited;
    static int res;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i=0; i<M; ++i) {
            String str = br.readLine();
            int n1 = str.charAt(0)-'A';
            int n2 = str.charAt(2)-'A';
            adj[n1] |= 1<<n2;
            in |= 1<<n2;
        }
        int root = 0;
        for (int i=0; i<N; ++i) {
            if ((in&(1<<i)) == 0) {
                root |= 1<<i;
            }
        }

		st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        int remove = 0;
        for (int i=0; i<K; ++i) {
            int n = st.nextToken().charAt(0)-'A';
            remove |= 1<<n;
        }
        root &= (-1^remove);
        for (int i=0; i<N; ++i) {
            adj[i] &= (-1^remove);
        }
        for (int i=0; i<N; ++i) {
            if ((root&(1<<i)) != 0) {
                dfs(i);
                res--;
            }
        }

        System.out.println(res);
	}
    public static void dfs(int i) {
        visited |= 1<<i;
        res++;
        for (int n=0; n<N; ++n) {
            if ((adj[i]&(1<<n)) != 0 && ((visited & (1<<n)) == 0)) dfs(n);
        }
    }
}
