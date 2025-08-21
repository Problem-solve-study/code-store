//문제: 15559번 내 선물을 받아줘
//메모리: 45916 KB
//시간: 328 ms

/*
 * 지도에서 움직이다 보면 어떠한 위치에 모일 것이라고 생각
 * -> 그러한 위치가 여러개가 될수도 있다
 * -> 특정 위치로 모이는 시작점들을 하나의 집합에 넣는다
 * -> 집합의 개수를 세면 문제에서 요구하는 답
 * -> 유니온 파인드
 */

import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken()), size = n * m;
        parent = new int[size];
        for (int i = 0; i < size; i++) parent[i] = i;
        for (int i = 0; i < n; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                int idx = i * m + j, target = idx;
                switch(line[j]){
                    case 'N': target -= m; break;
                    case 'S': target += m; break;
                    case 'W': target--; break;
                    case 'E': target++; break;
                }
                union(idx,target);
            }
        }
        Set<Integer> set = new HashSet<>();
        for(int i=0;i<size;i++)
            set.add(find(i));
        System.out.println(set.size());
    }

    static void union(int a, int b) {
        parent[find(b)] = find(a);
    }

    static int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }
}
