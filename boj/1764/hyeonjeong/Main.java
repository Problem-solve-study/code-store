// 25744KB 204ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 해시 써서 풀었습니다.
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        Set<String> people = new HashSet<>();
        for (int i = 0; i < n; i++) {
            people.add(br.readLine());
        }

        List<String> unknwons = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            String person = br.readLine();
            if (people.contains(person)) {
                unknwons.add(person);
            }
        }

        Collections.sort(unknwons);

        StringBuilder sb = new StringBuilder();
        sb.append(unknwons.size()).append('\n');
        for (String person : unknwons) {
            sb.append(person).append('\n');
        }

        System.out.println(sb);
    }
}
