import java.io.*;

/*
11496KB, 72ms

순위와 선수의 번호를 적절히 관리하여 메달권에 들어온 선수 번호 출력하기
약간 귀찮은 구현임
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws Exception {
        int N = nextInt();
        int M = nextInt();
        //실제 번호, 등수
        int[] rank = new int[M + 1];
        for (int i = 1; i <= N; i++) {
            int v = nextInt();
            if (v <= M) {
                insert(rank, v, i);
            }
        }

        int[] medal = new int[4];
        for (int i = M; i >= 1; i--) {
            int v = nextInt();
            if (v <= 3) {
                insert(medal, v, rank[i]);
            }
        }

        System.out.print("" + medal[1] + '\n' + medal[2] + '\n' + medal[3]);
    }

    static void insert(int[] arr, int pos, int value) {
        for (int i = arr.length - 1; i > pos; i--) {
            arr[i] = arr[i - 1];
        }
        arr[pos] = value;
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}