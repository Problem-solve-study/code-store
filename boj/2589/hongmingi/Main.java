//218292KB	496ms
import java.io.*;
import java.util.*;
/*
 * N의 크기가 최대 50밖에 되지 않았기 때문에 지점마다 BFS를 돌려가며 최대거리를 찾아서 풀었음.
 * bfs할때 visited를 queue에 add하자마자 true로 바꾸지 않았었는데 이거 못찾아서 메모리가 6번 터졌음.
 * 다음부터 add하자마자 visited바꾸는걸로.
 */
public class Main {
    static int[][] route;
    static boolean[][] map;
    static int r, c;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new boolean[r][c];
        route = new int[r][c];

        for(int i=0; i<r; i++){
            char[] line = br.readLine().toCharArray();
            for(int j=0; j<c; j++){
                if(line[j]=='W')    map[i][j] = true;
                //else route[i][j] = -1;
            }
        }

        Queue<int[]> queue = new ArrayDeque<>();;
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(map[i][j]) continue;
                boolean[][] visited = new boolean[r][c];
                queue.add(new int[]{i,j,0});
                visited[i][j] = true;
                bfs(visited, queue);
            }
        }

        
        int maxL = 0;
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(!map[i][j]) maxL = Math.max(maxL, route[i][j]);
            }
        }

        System.out.println(maxL);

    }

    static void bfs(boolean[][] visited, Queue<int[]> queue){
        int[] rr = {1,0,-1,0};
        int[] cc = {0,1,0,-1};
        while(!queue.isEmpty()){
            int[] node = queue.poll();
            route[node[0]][node[1]] = Math.max(route[node[0]][node[1]], node[2]);
            for(int i=0; i<4; i++){
                if(node[0]+rr[i]>=0 && node[0]+rr[i]<r && node[1]+cc[i]>=0 && node[1]+cc[i]<c && !map[node[0]+rr[i]][node[1]+cc[i]] && !visited[node[0]+rr[i]][node[1]+cc[i]]){
                    queue.add(new int[]{node[0]+rr[i], node[1]+cc[i], node[2]+1});
                    visited[node[0]+rr[i]][node[1]+cc[i]] = true;
                }
            }
            
        }
    }

}
