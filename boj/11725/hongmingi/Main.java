//82032KB	1192ms
/*
 * DFS로 순회하면서 부모 노드를 체크.
 * 처음에 map을 check할 때 인접행렬을 사용했는데 메모리 초과가 뜸. (n 크기가 컸던게 문제)
 * 이후에 인접 리스트로 바꿔서 제출하니 통과.
 */
import java.io.*;
import java.util.*;

public class Main {
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> map;
    static int[] parent;
    static int n;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int a, b;
        map = new ArrayList<>();
        visited = new boolean[n+1];

        for(int i=0; i<=n; i++){
            map.add(new ArrayList<>());
        }
        for(int i=1; i<n; i++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            map.get(a).add(b);
            map.get(b).add(a);
        }
        parent = new int[n+1];

        dfs(1);
        for(int i=2; i<=n; i++){
            System.out.println(parent[i]);
        }
    }

    static void dfs(int i){
        visited[i] = true;
        for(Integer j:map.get(i)){
            if(!visited[j]){
                parent[j] = i;
                dfs(j);
            }
        }
    }
}
