// 45740KB 380ms

import java.io.*;
import java.util.*;

/**
 * 왼쪽, 오른쪽에서 각각 진행
 * 스택에 현재 빌딩보다 큰 빌딩을 오름차순으로 저장하도록 순회
 * -> 현재 빌딩보다 작거나 같은 빌딩은 이후 다른 빌딩에 무의미
 */
class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] heights = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }

        int count = 0;
        int nearest = 1;
        int[][] answer = new int[n][2];

        int index = 0;
        int height = 1;
        List<int[]> stack = new ArrayList<>();
        stack.add(new int[]{ 0, heights[0] });
        for (int i = 1; i < n; i++) {
            while (!stack.isEmpty() && stack.get(stack.size() - 1)[height] <= heights[i]) {
                stack.remove(stack.size() - 1);
            }

            // 왼쪽에 현재 건물보다 큰 건물이 없음
            if (stack.isEmpty()) {
                stack.add(new int[]{ i, heights[i] });
                continue;
            }

            // 왼쪽에 보이는 건물 카운팅
            answer[i][count] = stack.size();
            answer[i][nearest] = stack.get(stack.size() - 1)[index];
            stack.add(new int[]{ i, heights[i] });
        }
        
        // System.out.println(Arrays.deepToString(answer));
        
        stack.clear();
        stack.add(new int[]{ n - 1, heights[n - 1] });
        for (int i = n - 2; i >= 0; i--) {
            while (!stack.isEmpty() && stack.get(stack.size() - 1)[height] <= heights[i]) {
                stack.remove(stack.size() - 1);
            }

            // 오른쪽에 현재 건물보다 큰 건물이 없음
            if (stack.isEmpty()) {
                stack.add(new int[]{ i, heights[i] });
                continue;
            }

            // 왼쪽에 보이는 건물이 없었으면 가장 가까운 건물 설정
            if (answer[i][count] == 0) {
                answer[i][nearest] = stack.get(stack.size() - 1)[index];
            }
            // 왼쪽에 보이는 건물이 있었으면, 거리 비교 후 가장 가까운 건물 업데이트
            else {
                int currentDist = stack.get(stack.size() - 1)[index] - i;
                int prevDist = i - answer[i][nearest];
                if (currentDist < prevDist) {
                    answer[i][nearest] = stack.get(stack.size() - 1)[index];
                }
            }
            // 오른쪽에 보이는 건물 카운팅
            answer[i][count] += stack.size();
            
            stack.add(new int[]{ i, heights[i] });
        }

        // System.out.println(Arrays.deepToString(answer));

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(answer[i][count]);
            if (answer[i][count] > 0) {
                sb.append(' ').append(answer[i][nearest] + 1);
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }
}
