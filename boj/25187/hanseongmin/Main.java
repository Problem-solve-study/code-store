// 76060KB, 660ms

import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int n;
        int zeroCnt;
        int oneCnt;
        Node parent;

        Node(int n) {
            this.n = n;
        }

        public Node getParent() {
            if (parent == this) {
                return this;
            }
            return parent = parent.getParent();
        }
    }
    static Node[] nodes;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        int[] water = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            water[i] = Integer.parseInt(st.nextToken());
        }
        
        //노드들 생성
        nodes = new Node[n + 1];
        for (int i = 1; i <= n; i++) {
            nodes[i] = new Node(i);
            nodes[i].parent = nodes[i];
        }
        
        //유니온 연산
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            Node p1 = nodes[v1].getParent();
            Node p2 = nodes[v2].getParent();
            p1.parent = p2;
        }
        
        //경로 압축 수행
        for (int i = 1; i <= n; i++) {
            nodes[i].getParent();
        }

        //루트에 고인물과 청정수 카운팅
        for (int i = 1; i <= n; i++) {
            nodes[i].parent.oneCnt += water[i] == 1 ? 1 : 0;
            nodes[i].parent.zeroCnt += water[i] == 0 ? 1 : 0;
        }

        //루트를 기반으로 비교
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            int v = Integer.parseInt(br.readLine());
            Node root = nodes[v].getParent();
            sb.append(root.oneCnt > root.zeroCnt ? 1 : 0).append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
    }
}