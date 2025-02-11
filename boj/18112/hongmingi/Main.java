// 66124KB	348ms
import java.io.*;
import java.util.*;
/*
 * 최단 경로 탐색이라 바로 BFS 사용.
 * 시간 초과 때문에 많이 애먹었다.
 * 처음에는 visited를 ArrayList로 사용했더니 input 크기가 커지면 시간초과가 나서 HashSet으로 변경.(성민씨 코드 참고함)
 * 이진수 1자리 보수 만드는 부분은 감을 못 잡아서 인터넷의 도움을 받음.
 * BFS 연습 뿐만이 아니라 XOR 연습을 할 수 있었던 문제
 */
public class Main {
    static int n, target; 
    static HashSet<Integer> visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine(), 2);
        target = Integer.parseInt(br.readLine(), 2);
        Queue<int[]> q = new LinkedList<>();
        visited = new HashSet<>();
        int cnt;
        int n1, n2, n3;
        int[] route = new int[]{n, 0};
        q.offer(route);

        visited.add(n);
        while(!q.isEmpty()){
            route = q.poll();
            n = route[0];
            cnt = route[1];
            if(n==target){
                System.out.println(cnt);
                break;
            }

            for(int i=0;Math.pow(2, i)<(Integer.highestOneBit(n)); i++){
                n1 = n^(1<<i);
                if(!visited.contains(n1)){
                    visited.add(n1);
                    q.offer(new int[]{n1, cnt+1});
                }
            }
            n2 = n+1;
            if(!visited.contains(n2)){
                visited.add(n2);
                q.offer(new int[]{n2, cnt+1});
            }
            n3 = n-1;
            if(n3>0 && !visited.contains(n3)){
                q.offer(new int[]{n3, cnt+1});
            }
            
        }
    }
}
