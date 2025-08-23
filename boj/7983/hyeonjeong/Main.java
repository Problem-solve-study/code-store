// 65668KB 1244ms

import java.io.*;
import java.util.*;

// 그리디
// 마감이 느린 숙제부터 수행하면서 숙제가 끝난 날짜 구하기
class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        int n = nextInt();
        List<int[]> homeworks = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int duration = nextInt();
            int deadline = nextInt();

            homeworks.add(new int[]{ duration, deadline });
        }

        // 데드라인 내림차순으로 정렬
        homeworks.sort((h1, h2) -> h2[1] - h1[1]);
        
        int lastTime = homeworks.get(0)[1];
        for (int[] homework : homeworks) {
            // 현재 시간이 데드라인보다 느리면, 데드라인에 끝나도록 숙제 수행
            if (lastTime >= homework[1]) {
                lastTime = homework[1] - homework[0];
                continue;
            }

            // 현재 시간이 데드라인보다 빠르면, 현재 시간에 끝나도록 숙제 수행
            lastTime = lastTime - homework[0];
        }

        System.out.println(lastTime);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
