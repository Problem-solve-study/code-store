//22560KB	164ms
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
* BFS를 연습할 수 있었던 문제. 아직 익숙하지 않은 부분이라 조건문을 거는 과정에서 살짝 애먹었다.
*/

public class Main {
    static int cnt;
    public static void main(String[] arge) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int result=-1;
        boolean[] visited = new boolean[100001];
        
        Queue<Integer[]> queue = new LinkedList<>();

        queue.add(new Integer[]{n, 0});
        
        while(!queue.isEmpty()){
            Integer[] q = queue.poll();

            if(q[0]==k){
                result = q[1];
                break;
            }
            if(q[0]+1<100001 && !visited[q[0]+1]){
                queue.add(new Integer[]{q[0]+1 , q[1]+1});
                visited[q[0]+1] = true;
            }
            if(q[0]-1>=0 && !visited[q[0]-1]){
                queue.add(new Integer[]{q[0]-1 , q[1]+1});
                visited[q[0]-1] = true;
            }
            if(2*q[0]<100001&&!visited[2*q[0]]){
                queue.add(new Integer[]{2*q[0] , q[1]+1});
                visited[2*q[0]] = true;
            }
        }
        System.out.println(result);
    }
}
