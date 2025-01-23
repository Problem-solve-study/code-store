import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    static List<Integer> numbers;
    static Set<Integer> visited;
    static int k;
    static final int LOTTO_SIZE = 6;

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        while (true) {
            String input = br.readLine();
            if ("0".equals(input))
                break;

            numbers = Arrays.asList(input.split(" ")).stream().map(Integer::parseInt).collect(Collectors.toList());
            k = numbers.get(0);
            visited = new LinkedHashSet<>();

            dfs(0, 0);
            System.out.println();
        }

    }

    static void dfs(int prevIdx, int depth) {
        if (depth == LOTTO_SIZE) {
            visited.forEach(e -> System.out.printf("%d ", e));
            System.out.println();
            return;
        }

        for (int i = prevIdx + 1; i <= k; i++) {
            int next = numbers.get(i);
            if (!visited.contains(next)) {
                visited.add(next);
                dfs(i, depth + 1);
                visited.remove(next);
            }
        }
    }
}
