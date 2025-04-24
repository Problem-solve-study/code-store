// 12136KB 72ms

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
 * DFS
 * 모음만, 자음만, 둘 다 올 수 있는 경우와 L 포함 여부에 따라 다르게 탐색하기
 * total과 count 모두 long이어야 되네요
 */
class Main {
    static final Set<Character> vow = new HashSet<>(Arrays.asList('A', 'E', 'I', 'O', 'U'));
    static char[] path;
    static long total;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        path = br.readLine().toCharArray();
        boolean isContainsL = false;
        for (int i = 0; i < path.length; i++) {
            if (path[i] == 'L') {
                isContainsL = true;
                break;
            }
        }

        dfs(0, 1L, isContainsL);

        System.out.println(total);
    }

    static void dfs(int pi, long count, boolean isContainsL) {
        while (pi < path.length && path[pi] != '_') {
            pi++;
        }

        if (pi == path.length) {
            if (isContainsL) {
                total += count;
            }
            return;
        }

        int candidates = check(pi);
        if (candidates == 0) {
            return;
        }

        // 01: 자음만
        if (candidates == 1) {
            if (isContainsL) {
                path[pi] = 'B';
                dfs(pi, count * 21, isContainsL);
                path[pi] = '_';
                return;
            }

            path[pi] = 'B';
            dfs(pi, count * 20, isContainsL);
            path[pi] = 'L';
            dfs(pi, count, true);
            path[pi] = '_';
            return;
        }

        // 10: 모음만
        if (candidates == 2) {
            path[pi] = 'A';
            dfs(pi, count * 5, isContainsL);
            path[pi] = '_';
            return;
        }

        // 11
        if (candidates == 3) {
            // 모음
            path[pi] = 'A';
            dfs(pi, count * 5, isContainsL);
            path[pi] = '_';

            // 이미 L이 있음: 아무 자음이나
            if (isContainsL) {
                path[pi] = 'B';
                dfs(pi, count * 21, isContainsL);
                path[pi] = '_';
                return;
            }

            // 아직 L이 없음: L을 넣는 경우 & L 제외 자음 넣는 경우
            path[pi] = 'B';
            dfs(pi, count * 20, isContainsL);
            path[pi] = 'L';
            dfs(pi, count, true);
            path[pi] = '_';
            return;
        }
    }

    static int check(int i) {
        // 모음 자음
        int result = 3;

        if (i > 1) {
            boolean isVow1 = vow.contains(path[i - 1]);
            boolean isVow2 = vow.contains(path[i - 2]);
            if (isVow1 == isVow2) {
                // 모음 못 옴
                if (isVow1) {
                    result = result & (1 << 0);
                }
                // 자음 못 옴
                else {
                    if (path[i - 1] != '_' && path[i - 2] != '_') {
                        result = result & (1 << 1);
                    }
                }
            }
        }

        if (i > 0 && i < path.length - 1) {
            boolean isVow1 = vow.contains(path[i - 1]);
            boolean isVow2 = vow.contains(path[i + 1]);
            if (isVow1 == isVow2) {
                // 모음 못 옴
                if (isVow1) {
                    result = result & (1 << 0);
                }
                // 자음 못 옴
                else {
                    if (path[i - 1] != '_' && path[i + 1] != '_') {
                        result = result & (1 << 1);
                    }
                }
            }
        }

        if (i < path.length - 2) {
            boolean isVow1 = vow.contains(path[i + 1]);
            boolean isVow2 = vow.contains(path[i + 2]);
            if (isVow1 == isVow2) {
                // 모음 못 옴
                if (isVow1) {
                    result = result & (1 << 0);
                }
                // 자음 못 옴
                else {
                    if (path[i + 1] != '_' && path[i + 2] != '_') {
                        result = result & (1 << 1);
                    }
                }
            }
        }

        return result;
    }
}
