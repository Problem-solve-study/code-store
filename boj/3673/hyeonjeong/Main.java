// 272652KB 396ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

// 원래 누적합 - d로 나눠지는 개수 쌍을 맵에 저장했는데 메모리 초과
// 배열을 써야 할 것 같은데 누적합 범위는 배열 인덱스로 표현이 안 됨
// -> 나머지가 같은 애들을 빼게 됨 -> 나머지로 개수 저장
class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        int t = nextInt();
        for (; t > 0; t--) {
            int d = nextInt();
            int n = nextInt();

            int[] count = new int[1_000_001];
            int totalCount = 0;
            long sum = 0L;

            for (int i = 0; i < n; i++) {
                sum += nextInt();
                int r = (int) (sum % d);

                totalCount += count[r];
                if (r == 0) {
                    totalCount++;
                }

                count[r]++;
            }

            sb.append(totalCount).append('\n');
        }
        
        System.out.println(sb);
    }

    static int nextInt() throws IOException {
        st.nextToken();

        return (int) st.nval;
    }
}
