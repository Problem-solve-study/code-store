//	22828KB	108ms
import java.io.*;

/**
 * 이름 두개를, 둘 중 길이가 짧은쪽 길이까지 전부 탐색
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] names = new String[n];
        for (int i = 0; i < n; i++) {
            names[i] = br.readLine();
        }
        int answer = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int flag = 0;
                for (int k = 1; k <= Math.min(names[i].length(), names[j].length()); k++) {
                    if (names[i].endsWith(names[j].substring(0, k)) || names[j].endsWith(names[i].substring(0, k))) {
                        flag = 1;
                        break;
                    }
                }
                if (flag == 1) {
                    answer++;
                }
            }
        }
        System.out.print(answer);
    }
}