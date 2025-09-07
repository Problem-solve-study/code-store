import java.io.*;

/*
 * 15600KB, 148ms
 * 
 * 예전에 SSAFY에서 풀었던 문제와 매우 유사함
 * 
 * 관건은 1은 반드시 2와 함께 없어져야 하므로 문제는 결국 '1을 모두 없앨 수 있는가?'가 되며 따라서 2의 개수가 많아지도록 수를 나눠야한다.
 * 따라서 주어진 수들을 % 2, / 2를 통해 1과 2의 조합으로 나누어준다.
 * 1은 숫자별로 1개 혹은 0개 늘어나게 되고, 2는 몫만큼 늘어나게 되므로 2의 개수가 많아지게 된다.
 * 
 * 이후 나누어진 1과 2를 짝지어서 없애준다.
 * - 만일 1이 남게되면 더 이상 1을 없앨 수단이 없으므로 불가능하다.
 * - 남아있는 2들이 3의 배수가 아닌 경우에는 도리어 1이 없어 2를 지우지 못하는 경우가 발생하므로 이 경우 또한 불가능하다.
 * 
 * 따라서 위 경우를 판별하여 답을 출력하면 된다.
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws Exception {
        int N = nextInt();
        int count1 = 0;
        int count2 = 0;
        for (int i = 0; i < N; i++) {
            int n = nextInt();
            count1 += (n % 2);
            count2 += (n / 2);
        }

        count2 -= count1;
        if (count2 >= 0 && count2 % 3 == 0) {
            System.out.print("YES");
        } else {
            System.out.print("NO");
        }
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}