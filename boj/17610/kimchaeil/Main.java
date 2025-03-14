//문제: BOJ 17610번
//메모리: 14584 KB
//시간: 92 ms

/*
 * 추를 쓰지 않는 경우, 추를 왼쪽에 놓는 경우, 추를 오른쪽에 놓는 경우
 * 위 3가지 경로로 DFS
 */

import java.io.*;
import java.util.*;

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int k;
    static int[] arr;
    static boolean[] visited;
    static int result;
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        k=nextInt();
        arr = new int[k];
        int s = 0;
        for(int i=0;i<k;i++)
            s+=arr[i]=nextInt();
        result = s; //result의 초기값을 s로
        visited = new boolean[s+1];
        DFS(0,0);
        System.out.println(result);
    }
    static void DFS(int depth, int sum){
        if(depth==k){
            if(sum>0&&!visited[sum]){ //처음 확인된 무게라면 result 감소
                result--;
                visited[sum]=true; //방문체크
            }
            return;
        }
        DFS(depth+1,sum+arr[depth]);
        DFS(depth+1,sum);
        DFS(depth+1,sum-arr[depth]);
    }
    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
