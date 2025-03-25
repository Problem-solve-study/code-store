// 16228KB	160ms
import java.io.*;

public class Main {
    static int n;
    static int[] num = new int[7];
    static boolean[] visited = new boolean[10];
    static int a, b;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dfs(0);
        if (a == 0 && b == 0) {
            System.out.print("No Answer");
        }
        else {
            int div = 1, len = 0;
            while (b / div != 0) {
                div*=10;
                len++;
            }
            System.out.printf("%"+(len+2)+"d\n", a);
            System.out.printf("+ %"+len+"d\n", b);
            System.out.println("-".repeat(len+2));
            System.out.printf("%"+(len+2)+"d\n", n);
        }
    }

    static boolean checked = false;
    static void dfs(int cnt) {
        if (checked) return;
        if (cnt == 7) {
            int hello = getHello();
            int world = getWorld();
            if (hello + world == n) {
                if (hello > world) {
                    a = world; b = hello;
                }
                else {
                    a = hello; b = world;
                }
                checked = true;
            }
            return;
        }

        for (int i=0; i<=9; i++) {
            if (i==0) {
                if (cnt == 2 || cnt == 6) continue;
            }
            if (!visited[i]) {
                visited[i] = true;
                num[cnt] = i;
                dfs(cnt + 1);
                visited[i] = false;
            }
        }
    }

    static int getHello() {
        int hello = 0;
        hello += 10000 * num[2];
        hello += 1000 * num[1];
        hello += 110 * num[3];
        hello += num[4];
        return hello;
    }

    static int getWorld() {
        int world = 0;
        world += 10000 * num[6];
        world += 1000 * num[4];
        world += 100 * num[5];
        world += 10 * num[3];
        world += num[0];
        return world;
    }
}