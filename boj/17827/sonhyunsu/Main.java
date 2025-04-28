//제출번호: 93659514
//메모리:   31768 KB
//실행시간: 400 ms
import java.io.*;

//달팽이 노드에 맞춰서 어디에 있는 지 계산만 하면 됨
public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        int n = nextInt();
        int m = nextInt();
        int v = nextInt();

        int[] nodes = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            nodes[i] = nextInt();
        }

		//사이클 범위를 계산함
        int cycle = n - v + 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int k = nextInt() + 1; //1번 칸부터 시작해서 +1함
			//사이클에 들어가지 않은 인덱스면
            if (k < v) {
                sb.append(nodes[k]); //일렬로 했을 때 기준으로 출력
            } else {
				//사이클에 들어간 인덱스면 나머지를 이용해서 사이클 위치를 찾고, 거기에 맞는 값 출력
                sb.append(nodes[(k - v) % cycle + v]);
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}