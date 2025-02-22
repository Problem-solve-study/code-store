// 27796KB	264ms
import java.io.*;
import java.util.*;

/**
 * 결국 모든 점 사이의 거리를 구해야 하는 문제 N이 100이기 때문에 플로이드 워셜을 이용
 * 1. map 배열의 초기값을 1000으로 잡아서 1틀
 * 2. 자기자신까지의 거리도 무한으로 잡아서 2틀
 */

public class Main {
    static int n,m,k;
    static int[][] map;
    static int[] pos;
    static int answer, min;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());
        for (int tc=0; tc<TC; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            map = new int[n+1][n+1];
            for (int i=1; i<=n; i++) {
                // 초기값의 크기는 최대비용(1000) * 노드개수-1(99) = 99,000보다 크면 된다. 
                // 큰 수 계산일수록 느리므로 백만 정도로 잡음.
                Arrays.fill(map[i], 100_000);
                map[i][i] = 0; // 자기자신 까지는 거리가 0 !!
            }
            for (int i=0; i<m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                map[a][b] = c;
                map[b][a] = c;
            }
            k = Integer.parseInt(br.readLine());
            pos = new int[k];
            st = new StringTokenizer(br.readLine());
            for (int i=0; i<k; i++) {
                pos[i] = Integer.parseInt(st.nextToken());
            }

            for (int c=1; c<=n; c++) {
                for (int i=1; i<=n; i++) {
                    for (int j=1; j<=n; j++) {
                        // 현재 저장된 값보다 c 를 거쳐서 가는게 빠르다면 map을 갱신
                        map[i][j] = Math.min(map[i][j], map[i][c] + map[c][j]);
                    }
                }
            }

            min = Integer.MAX_VALUE;
            for (int i=1; i<=n; i++) {
                int temp = 0;
                for (int c=0; c<k; c++) {
                    temp += map[i][pos[c]]; // i -> c번째 친구위치까지 거리를 합함.
                }
                if (min > temp) { // 거리가 같은게 있을 경우 가장 작은 번호를 출력하기 때문에 >= (X) / > (O)
                    min = temp;
                    answer = i;
                }
            }
            sb.append(answer).append("\n");
        }
        System.out.print(sb);
    }
}