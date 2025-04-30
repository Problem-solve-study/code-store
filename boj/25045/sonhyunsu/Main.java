//제출번호: 93734753
//메모리:   25980 KB
//실행시간: 460 ms
import java.io.*;
import java.util.*;

//가장 큰 아이템을 가장 작은 고객에게 제공하면 풀 수 있음
//정렬해서 가장 큰 아이템을 가장 큰 고객에게 제공하는 방법으로 풀 수 있을 줄 알았는데 2틀했음
public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        int n = nextInt();
        int m = nextInt();

        int[] items = new int[n];
        int[] costs = new int[m];

        for (int i = 0; i < n; i++) {
            items[i] = nextInt();
        }

        for (int i = 0; i < m; i++) {
            costs[i] = nextInt();
        }

		//아이템과 고객 가치를 정렬
        Arrays.sort(items);
        Arrays.sort(costs);

        long res = 0;
        for (int i = n-1, j = 0; i >= 0 && j < m; i--, j++) {
            res += Math.max(0, items[i] - costs[j]); //가장 큰 아이템을 가장 작은 고객에게 제공
        }

        System.out.print(res);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}