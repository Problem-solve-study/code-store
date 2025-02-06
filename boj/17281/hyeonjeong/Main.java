// 103421KB, 960ms

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {
    static List<List<Integer>> permutations = new ArrayList<>();
    static boolean[] visited = {false, false, false, false, false, false, false, false, false};
    static final int FIX_INDEX = 3;
    
    static final int FIRST = 0;
    static final int SECOND = 1;
    static final int THIRD = 2;
    static int max = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        dfs(new int[9], 0);

        int n = Integer.parseInt(br.readLine());
        int[][] scores = new int[n][9];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                scores[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (List<Integer> permutation : permutations) {
            play(permutation, n, scores);
        }

        System.out.println(max);
    }

    static void dfs(int[] sequence, int size) {
        if (size == 9) {
            permutations.add(Arrays.stream(sequence).boxed().collect(Collectors.toList()));
            return;
        }

        if (size == FIX_INDEX)
            size++;

        for (int i = 1; i < 9; i++) {
            if (!visited[i]) {
                visited[i] = true;
                sequence[size++] = i;
                dfs(sequence, size);
                sequence[--size] = -1;
                visited[i] = false;
            }
        }
    }

    static void play(List<Integer> sequence, int n, int[][] scores) {

        int score = 0;
        int idx = -1;
        for (int inning = 0; inning < n; inning++){
            int[] bases = new int[3];
            int out = 0;
            while (out < 3) {
                int current = scores[inning][sequence.get((++idx) % 9)];

                if (current == 0) {
                    out++;
                    continue;
                }

                if (current == 1) {
                    score += bases[THIRD];
                    bases[THIRD] = bases[SECOND];
                    bases[SECOND] = bases[FIRST];
                    bases[FIRST] = 1;
                    continue;
                }

                if (current == 2) {
                    score += bases[THIRD] + bases[SECOND];
                    bases[THIRD] = bases[FIRST];
                    bases[SECOND] = 1;
                    bases[FIRST] = 0;
                    continue;
                }

                if (current == 3) {
                    score += bases[THIRD] + bases[SECOND] + bases[FIRST];
                    bases[THIRD] = 1;
                    bases[SECOND] = 0;
                    bases[FIRST] = 0;
                    continue;
                }

                if (current == 4) {
                    score += bases[THIRD] + bases[SECOND] + bases[FIRST] + 1;
                    bases[THIRD] = 0;
                    bases[SECOND] = 0;
                    bases[FIRST] = 0;
                    continue;
                }
            }
        }

        if (score > max){
            max = score;
        }
    }
}