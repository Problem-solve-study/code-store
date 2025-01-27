import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static ArrayList<Integer>[] map;
    static int[] dist;
    static boolean[] visited;

    static class Node {
        int node, cnt;
        Node(int node, int cnt) {
            this.node = node;
            this.cnt = cnt;
        }
    }
    

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new ArrayList[n+1];
        for (int i=1; i<=n; i++) {
            map[i] = new ArrayList<>();
        }
        for (int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a].add(b);
            map[b].add(a);
        }
        dist = new int[n+1];
        Arrays.fill(dist, -1);
        visited = new boolean[n+1];

        findRing(1,0, new Stack<>());

        for (int i=1;i<=n; i++) {
            if (dist[i]==0 && map[i].size() >=3) {
                findBranch(i);
            }
        }
        
        for (int i=1; i<=n; i++) {
            System.out.print(dist[i]+" ");
        }
    }


    static void findBranch(int start) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(start,0));
        
        while (!queue.isEmpty()) {
            Node now = queue.poll();

            if (dist[now.node]!=0) {
                dist[now.node] = now.cnt;
            }

            for (int next : map[now.node]) {
                if (dist[next]==-1) {
                    queue.add(new Node(next, now.cnt+1));
                }
            }
        }
    }


    static boolean findCycle;
    static void findRing(int now, int from, Stack<Integer> stack) {
        if (findCycle) return;
        
        stack.add(now);
        visited[now] = true;
                
        for (int next : map[now]) {
            if (!visited[next]) {
                findRing(next, now, stack);
                visited[next] = false;
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            }

            else if (next != from) {
                findCycle = true;
                while (!stack.isEmpty()) {
                    int top = stack.pop();
                    dist[top] = 0; 
                    if (top == next) {
                        break;
                    }
                }
                return;
            }
        }
    }   
}