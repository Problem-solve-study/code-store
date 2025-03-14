// 84596KB	552ms
import java.io.*;
import java.util.*;
/*
 * 알고리즘 자체는 쉽지만 구현하기가 좀 까다로웠음.
 * 다른 것보다 dfs를 돌 때 처음에 단순 for문을 통해 구현했는데 자식 노드가 2, 3이 있을 경우 3을 갔다가 다시 2를 돌아야
 * 하는 경우를 구하는 게 어려웠음. 그래서 for문->while문으로 바꾼 뒤 노드의 자식 노드에 다음 값과 동일한 게 있는지
 * 확인하는 방식으로 구현함.
 * 또한 72%에서 시간초과가 터지는 문제가 있었는데, 이는 인접 리스트의 서치 시간 복잡도가 O(n)이라 발생하는 문제였음.
 * 이를 시간 복잡도가 O(1)인 hashSet으로 바꿔서 해결함.
 */
public class Main{
    static int N, cnt, res;
    static int[] answer;
    static boolean[] visited;
    //static ArrayList<Integer>[] graph;
    static HashSet<Integer>[] graph;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        visited = new boolean[N+1];
        graph = new HashSet[N+1];
        for(int i=1; i<=N; i++){
            graph[i] = new HashSet<>();
        }
        int x, y;
        for(int i=0; i<N-1; i++){
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            graph[x].add(y);
            graph[y].add(x);
        }
        answer = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            answer[i] = Integer.parseInt(st.nextToken());
        }

        res = 0;
        dfs(1);
        System.out.println(res);
    }

    static void dfs(int x){
        if(cnt == N-1){
            res = 1;
            return;
        }
        if(x!=answer[cnt]) return;
        
        visited[x] = true;
        
        while(graph[x].contains(answer[cnt+1])){
            if(!visited[answer[cnt+1]]){
                cnt++;
                dfs(answer[cnt]);
            }
            if(cnt==N-1) break;
        }
    }
}
