import java.io.*;
import java.util.*;

// 조합, 정렬
// 말뚝으로 나올 수 있는 밑변의 길이를 탐색하면서,
// 가능한 기둥 중 가장 긴 기둥만 취급하면서 최대값 뽑아내기
class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    
    public static void main(String[] args) throws IOException {
        int n = next();
        int m = next();
        int r = next();

        // 말뚝 입력
        int[] posts = new int[n];
        for (int i = 0; i < n; i++) {
            posts[i] = next();
        }
        Arrays.sort(posts);

        // 말뚝으로 만들 수 있는 밑변의 집합
        Set<Integer> bases = new TreeSet<>();
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                bases.add(posts[j] - posts[i]);
            }
        }

        // 기둥 입력
        int[] pillars = new int[m];
        for (int i = 0; i < m; i++) {
            pillars[i] = next();
        }
        Arrays.sort(pillars);

        // 밑변마다 기둥을 탐색하며 최대값 찾기
        double max = 0;
        int pi = m - 1;    // 현재 사용할 수 있는 가장 긴 기둥 인덱스
        for (int base : bases) {
            // 넓이가 r 이하가 나오는 기둥 찾기
            while (pi >= 0 && base * pillars[pi] / 2.0 > r) {
                pi--;
            }

            // 사용할 수 있는 기둥이 없으면 종료
            if (pi < 0) {
                break;
            }

            double e = base * pillars[pi] / 2.0;
            if (e > max) {
                max = e;
            }
        }

        if (max == 0) {
            System.out.println(-1);
            return;
        }

        System.out.printf("%.1f", max);
    }
    
    static int next() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
