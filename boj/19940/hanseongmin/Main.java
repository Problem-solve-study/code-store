import java.io.*;

/*
12388KB, 244ms

0.25초에 추가 시간이 없어 자바 기준 시간이 좀 빡빡한듯. 스트림 때문에 시간초과나는걸 처음으로 겪었다.
BFS 돌릴까 싶었는데 메모리가 좀 부족하려나 싶어서 그리디로 선회
모든 버튼을 눌러보고 N까지 가장 가까워지는 버튼을 선택하는 것을 반복
N이 60 이상이라면 일단 60을 다 누르고 시작한다면 시간을 더 줄일 수 있을듯
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws Exception {
        int T = nextInt();
        StringBuilder sb = new StringBuilder();
        while (T --> 0) {
            int N = nextInt();
            int t = 0;
            int[] res = new int[5];

            while (t != N) {
                int tempT = t + 60;
                int idx = 0;
                if (Math.abs(N - tempT) >= Math.abs(N - (t + 10))) {
                    tempT = t + 10;
                    idx = 1;
                }
                if (Math.abs(N - tempT) >= Math.abs(N - Math.max(t - 10, 0))) {
                    tempT = Math.max(t - 10, 0);
                    idx = 2;
                }
                if (Math.abs(N - tempT) >= Math.abs(N - (t + 1))) {
                    tempT = t + 1;
                    idx = 3;
                }
                if (Math.abs(N - tempT) >= Math.abs(N - Math.max(t - 1, 0))) {
                    tempT = Math.max(t - 1, 0);
                    idx = 4;
                }
                t = tempT;
                res[idx]++;
            }

            for (int i = 0; i < res.length; i++) {
                sb.append(res[i]).append(' ');
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
