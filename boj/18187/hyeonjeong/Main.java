// 	11524KB 64ms

import java.io.*;

/**
 * 그리디하게 풀었습니다
 * 
 * 선분을 교차시켜서 면을 분할할 때, 면이 가장 많이 분할됨
 * -> 매 선마다 이전 선들이랑 가장 많이 교차시킬 수 있는 게 현재의 답
 * 평행한 선은 교차되지 않음
 * -> 이번 선으로 추가되는 분할의 개수 = 나와 기울기가 다른 선들의 개수 + 1
 */
class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    
    public static void main(String[] args) throws IOException {
        int n = next();

        int count = 1;      // 선이 1개일 때만, 교차 없는 분할이라 추가되는 분할의 개수 기존 식보다 1개 더 많아서 초기값 세팅
        int[] lineCount = new int[3];   // 각 기울기 별 선분의 개수
        for (int i = 1; i <= n; i++) {
            int otherLineSum = lineCount[(i + 1) % 3] + lineCount[(i + 2) % 3];

            lineCount[i % 3]++;
            count += otherLineSum + 1;
        }

        System.out.print(count);
    }

    static int next() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
