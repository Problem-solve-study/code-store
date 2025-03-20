// 33644KB 332ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

// DFS + prunning
public class Main {
    static char[] input;
    static Set<Integer> visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        input = br.readLine().toCharArray();
        visited = new LinkedHashSet<>();

        dfs(0, 0);
    }

    static boolean dfs(int id, int depth) {
        // 한 경로 탐색이 끝났으면 1 ~ N까지 있는지 확인
        if (id == input.length) {
            int prev = 0;
            int[] numbers = Arrays.stream(visited.toArray()).mapToInt((obj) -> (int) obj).sorted().toArray();
            for (int number : numbers) {
                if (number != prev + 1) {
                    return false;
                }
                prev = number;
            }

            for (Object number : visited.toArray()) {
                System.out.printf("%d ", (int) number);
            }

            return true;
        }

        boolean result = false;
      
        // 0으로 시작하는 수는 탐색 X
        int nextNumber = input[id] - '0';
        if (nextNumber == 0) {
            return result;
        }

        if (!visited.contains(nextNumber)) {
            visited.add(nextNumber);
            result = dfs(id + 1, depth + 1);
            visited.remove(nextNumber);

            if (result) {
                return result;
            }
        }


        if (id == input.length - 1) {
            return result;
        }

        nextNumber = (input[id] - '0') * 10 + (input[id + 1] - '0');
        if (nextNumber > 50 || visited.contains(nextNumber)) {
            return result;
        }


        visited.add(nextNumber);
        result = dfs(id + 2, depth + 1);
        visited.remove(nextNumber);

        return result;
    }
}
