/**
 * 	29316KB	232ms
 * 
 * [사고흐름]
 * 어... 이거 정석적인 풀이 방법이 있던거 같던데 기억이 안나네요.
 * 예전에 전위표기와 중위표기를 가지고 어떻게 원본 트리를 복원할지 
 * 방법을 생각해본 적이 있어서, 비교적 쉽게 풀었습니다
 * 
 * [알고리즘 설명]
 * 중위표기대로 노드를 배열로 나타낸 후, 전위표기 순서대로 각 노드를 탐색합니다
 * 각 노드를 중위 표기로 나타냈을 때 가장 가까운 자식이 없는 노드를 자신의 부모로 삼습니다.
 * 
 * 아마 이 방법이 N log N 이었던거 같은데, 맞나 모르겠네요
 */

import java.io.*;


public class Main {
    static int N;
    static int[] in, idx, pre;
    static Node[] node;
    static StringBuilder res;

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static void main(String[] args) throws Exception {
        int TC = ni();
        StringBuilder sb = new StringBuilder();
        for (int tc=0; tc<TC; ++tc) {
            res = new StringBuilder();
            N = ni();
            in = new int[N];
            idx = new int[N];
            pre = new int[N];
            node = new Node[N];

            for (int i=0; i<N; ++i) pre[i] = ni()-1;
            for (int i=0; i<N; ++i) in[i] = ni()-1;
            for (int i=0; i<N; ++i) idx[in[i]] = i;

            int rootIdx = idx[pre[0]];
            node[rootIdx] = new Node();

            for (int i=1; i<N; ++i) {
                int ci = idx[pre[i]];
                node[ci] = new Node();

                int li = ci;
                while (0<=--li && node[li]==null);
                if (0<=li && node[li].r==-1) node[li].r = ci;
                else {
                    int ri = ci;
                    while (++ri<N && node[ri]==null);
                    if (node[ri].l==-1) node[ri].l = ci;
                }
            }
            postorder(rootIdx);
            sb.append(res.toString()).append('\n');
        }
        System.out.print(sb);
    }

    static void postorder(int c) {
        if (node[c].l != -1) postorder(node[c].l);
        if (node[c].r != -1) postorder(node[c].r);
        res.append(in[c]+1).append(' ');
    }

    static class Node {
        int l=-1, r=-1;
    }

    static int ni() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
