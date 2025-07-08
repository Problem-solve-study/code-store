import java.io.*;

/*
41984KB, 304ms

I가 반드시 센터로 들어온다는걸 늦게 봐서 좀 헤맸음
I가 센터가 아니라면 이미 경로압축이 적용된 경우에서의 구하기가
상당히 까다로워짐

기본적인 골자는 분리 집합
E일 때는 부모를 찾아가면서 거리를 갱신하고
I일 때는 병합

I, J의 거리 관계를 알아야 하므로 병합할 때
부모끼리 병합하는것이 아닌 I, J와 직접 병합
거리 갱신은 추후 부모를 찾을 때 수행하도록 했음
(어차피 경로압축 때문에 부모까지 가야하니)
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static class Data {
        Node n;
        int dis;

        public Data(Node n, int dis) {
            this.n = n;
            this.dis = dis;
        }
    }

    static class Node {
        Node parent;
        int num;
        int dis;

        public Node(Node parent, int num, int dis) {
            this.parent = parent;
            this.num = num;
            this.dis = dis;
        }

        Data getDis() {
            if (parent != null) {
                Data d = parent.getDis();
                parent = d.n;
                dis += d.dis;
                return new Data(parent, dis);
            } else {
                return new Data(this, 0);
            }
        }

        void union(Node n) {
            parent = n;
            dis = Math.abs(num - n.num) % 1000;
        }
    }

    public static void main(String[] args) throws Exception {
        int T = nextInt();
        StringBuilder sb = new StringBuilder();
        while (T --> 0) {
            int N = nextInt();
            Node[] nodes = new Node[N + 1];
            for (int i = 0; i <= N; i++) {
                nodes[i] = new Node(null, i, 0);
            }

            while (true) {
                String order = nextToken();
                if (order.equals("E")) {
                    int i = nextInt();
                    sb.append(nodes[i].getDis().dis).append('\n');
                } else if (order.equals("I")) {
                    int i = nextInt();
                    int j = nextInt();
                    nodes[i].union(nodes[j]);
                } else {
                    break;
                }
            }
        }
        System.out.print(sb);
    }

    static String nextToken() throws Exception {
        st.nextToken();
        return st.sval;
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}