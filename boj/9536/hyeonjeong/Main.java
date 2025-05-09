// 11924KB 72ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (; t > 0; t--) {
            String[] input = br.readLine().split(" ");
            List<String> sounds = new LinkedList<>();
            for (int i = 0; i < input.length; i++) {
                sounds.add(input[i]);
            }

            // 동물 이름 실존 -> what이라는 동물 없음
            input = br.readLine().split(" ");
            while (!input[0].equals("what")) {
                int i = 0;
                while (i < sounds.size()) {
                    if (sounds.get(i).equals(input[2])) {
                        sounds.remove(i);
                        continue;
                    }
                    i++;
                }
                input = br.readLine().split(" ");
            }

            for (int i = 0; i < sounds.size(); i++) {
                sb.append(sounds.get(i)).append(' ');
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append('\n');
        }

        System.out.println(sb);
    }
}
