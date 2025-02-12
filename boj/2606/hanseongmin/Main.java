import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/*
18456KB, 204ms

풀이 1. 1번 정점을 기준으로 그래프 탐색을 수행하며 방문한 정점의 개수를 카운팅
풀이 2. 그래프를 입력받으면 유니온 연산을 수행한 후 배열 전체를 순회하며 
부모가 1의 부모와 같은 정점을 카운팅

2번이 더 간단할 것 같아서 2번으로 구현
 */

public class Main {
    //분리 집합
    static int[] set;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        set = new int[n + 1];
        IntStream.rangeClosed(0, n).forEach(e -> set[e] = e);
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            //입력 받으면서 동시에 유니온 연산 수행
            union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        int res = 0;
        for (int i = 1; i <= n; i++) {
            //1번과 같은 집합인 정점의 개수를 카운팅
            if (find(i) == find(1)) {
                res++;
            }
        }
        
        //1번 정점은 포함하면 안되니 1 빼줌
        bw.write(String.valueOf(res - 1));
        bw.flush();
    }

    static int find(int c) {
        if (set[c] == c) {
            return c;
        }

        //경로 압축
        return set[c] = find(set[c]);
    }

    static void union(int c1, int c2) {
        set[find(c1)] = find(c2); 
    }
}