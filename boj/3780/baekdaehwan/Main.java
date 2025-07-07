/**
 * 30584KB	320ms
 * 
 * [사고흐름]
 * 문제 서술이 이상해서 예제를 보고 이해했습니다.
 * 이해하고나니 그냥 분리집합 문제같아서 조금 변형해서 풀었습니다.
 * 
 * [알고리즘 설명]
 * 기본 분리집합은 아니고, 노드 간 거리가 추가된 분리집합 문제입니다.
 * 경로단축은 루트까지 거리를 잴 때만 사용해도 문제없을것 같아서 대충 풀었는데 풀리네요.
 * 정석적으로 풀고싶다면, getDist함수에서 방문하는 경로들도 같이 단축해주는게 좋을 것 같습니다.
 */

import java.io.*;

public class Main {
    static int TC, N;
    static Node[] p;

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static void main(String[] args) throws Exception {
        TC = ni();
        StringBuilder res = new StringBuilder();
        for (int tc=0; tc<TC; ++tc) {
            N = ni();
            p = new Node[N];
            for (int i=0; i<N; ++i) p[i] = new Node(i, 0);

            while (true) {
                char opr = ns().charAt(0);

                if (opr == 'E') {
                    res.append(getDist(ni()-1)).append('\n');
                } else if (opr == 'I') {
                    int i = ni()-1;
                    int j = ni()-1;
                    p[i].pi = j;
                    p[i].d = Math.abs(i-j) % 1000;
                } else if (opr == 'O') {
                    break;
                }
            }
        }
        System.out.print(res);
    }

    static int getDist(int ci) {
        int dist = 0;
        int origin = ci;
        while (ci != p[ci].pi) {
            dist += p[ci].d;
            ci = p[ci].pi;
        }
        p[origin].pi = ci;
        p[origin].d = dist;
        return dist;
    }

    static class Node {
        int pi, d;
        Node(int pi, int d) {
            this.pi = pi;
            this.d = d;
        }
    }

    static String ns() throws Exception {
        st.nextToken();
        return st.sval;
    }

    static int ni() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}