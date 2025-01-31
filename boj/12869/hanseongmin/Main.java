//예전에 BFS로 한 번 푼 적이 있어서 DP로 새로 풀었습니다.
//BFS 코드는 아래에 주석으로 첨부

//19660KB, 140ms
import java.io.*;
import java.util.*;

public class Main {
    static int[][][] dp = new int[61][61][61];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        init(9, 3, 1);
        init(9, 1, 3);
        init(3, 9, 1);
        init(3, 1, 9);
        init(1, 3, 9);
        init(1, 9, 3);

        br.readLine();
        bw.write(getAns(Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray()) + "");
        bw.flush();
    }

    public static void init(int a, int b, int c) {
        for (int i = a; i >= 0; i--) {
            for (int j = b; j >= 0; j--) {
                for (int k = c; k >= 0; k--) {
                    dp[i][j][k] = 1;
                }
            }
        }
    }

    public static int getAns(int[] param) {
        int idx1 = Math.max(param[0], 0);
        int idx2 = param.length > 1 ? Math.max(param[1], 0) : 0;
        int idx3 = param.length > 2 ? Math.max(param[2], 0) : 0;

        if (dp[idx1][idx2][idx3] != 0) return dp[idx1][idx2][idx3];

        if (param.length == 1) {
            dp[idx1][idx2][idx3] = getAns(new int[] {idx1 - 9}) + 1;
        } else if (param.length == 2) {
            dp[idx1][idx2][idx3] = getMin(
                    getAns(new int[] {idx1 - 9, idx2 - 3}),
                    getAns(new int[] {idx1 - 3, idx2 - 9})) + 1;
        } else {
            dp[idx1][idx2][idx3] = getMin(
                    getAns(new int[] {idx1 - 9, idx2 - 3, idx3 - 1}),
                    getAns(new int[] {idx1 - 9, idx2 - 1, idx3 - 3}),
                    getAns(new int[] {idx1 - 3, idx2 - 9, idx3 - 1}),
                    getAns(new int[] {idx1 - 3, idx2 - 1, idx3 - 9}),
                    getAns(new int[] {idx1 - 1, idx2 - 3, idx3 - 9}),
                    getAns(new int[] {idx1 - 1, idx2 - 9, idx3 - 3})) + 1;
        }

        return dp[idx1][idx2][idx3];
    }

    public static int getMin(int... args) {
        return Arrays.stream(args).min().orElse(0);
    }
}

//BFS 풀이
//18296KB, 156ms
/*
import java.io.*;
import java.util.*;

public class Main {
    public static class Node {
        int[] scvs;
        int count;

        public Node(int[] scvs, int count) {
            this.scvs = scvs;
            this.count = count;
        }

        public boolean isEnd() {
            for (int i = 0; i < scvs.length; i++) {
                if (scvs[i] > 0)
                    return false;
            }

            return true;
        }

        public ArrayList<Node> getNextNodes() {
            ArrayList<Node> list = new ArrayList<>();
            for (int[] value : dv) {
                int[] temp = new int[scvs.length];
                for (int i = 0; i < scvs.length; i++) {
                    temp[i] = scvs[i] + value[i];
                }

                for (int i = 0; i < scvs.length; i++)
                    if (temp[i] < 0)
                        temp[i] = 0;

                list.add(new Node(temp, count + 1));
            }

            return list;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.deepEquals(scvs, node.scvs);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(scvs);
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[][] dv = {
            { -9, -3, -1 },
            { -9, -1, -3 },
            { -3, -9, -1 },
            { -3, -1, -9 },
            { -1, -9, -3 },
            { -1, -3, -9 }
    };

    public static void main(String[] args) throws Exception {
        br.readLine();
        int[] scvs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        bw.write(String.valueOf(getAns(new Node(scvs, 0))));
        bw.flush();
    }

    public static int getAns(Node init) {
        HashSet<Node> visited = new HashSet<>();
        Queue<Node> q = new LinkedList<>();
        q.add(init);
        visited.add(init);

        while (!q.isEmpty()) {
            Node cur = q.remove();
            if (cur.isEnd()) {
                return cur.count;
            }

            for (Node next : cur.getNextNodes()) {
                if (!visited.contains(next)) {
                    q.add(next);
                    visited.add(next);
                }
            }
        }

        return -1;
    }
}
 */
