import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Map<String, Integer> map = new HashMap<>();

        int cnt = 0;
        while (true) {
            String tree = br.readLine();
            if (tree == null)
                break;
            
            Integer value = map.get(tree);
            map.put(tree, value == null ? 1 : value + 1);

            cnt++;
        }

        List<String> trees = new ArrayList<>(map.keySet());
        Collections.sort(trees);

        for (String tree : trees) {
            bw.write(String.format("%s %.4f\n", tree, map.get(tree) * 100.0 / cnt));
        }
        bw.flush();
        bw.close();
    }
}
