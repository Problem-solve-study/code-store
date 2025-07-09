import java.io.*;

/*
11484KB, 84ms

정사각형의 개수는 2의 거듭제곱 꼴이고
초콜릿을 나눌 때 반드시 2로 나누므로
우리가 사용할 수 있는 수는 1을 포함한 2의 거듭제곱꼴들의 수들.

이때 가장 작은 초콜릿과 함께 최소 몇번 쪼개야 하는지를 구해야하므로
주어진 K보다 같거나 큰 가장 작은 2의 거듭제곱을 구하고
해당 수보다 작은 수의 합으로 K를 만들면 된다.
이때 몇 번 나눴는지 카운팅하여 같이 출력한다.
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws Exception {
        int K = nextInt();
        //주어진 K보다 같거나 큰 가장 작은 2의 거듭제곱을 구함
        //이것이 가장 작은 초콜릿
        int target = (int) Math.pow(2, (int)(Math.log(K) / Math.log(2)));
        if (target != K) target *= 2;

        StringBuilder sb = new StringBuilder();
        sb.append(target).append(' ');
        int cnt = 0;
        while (true) {
            //현재 초콜릿의 크기가 K보다 작다면 자신이 먹음
            if (K >= target) {
                K -= target;
            }
            //자신이 모두 먹었다면 종료
            if (K == 0) break;

            //남아 있는 초콜릿을 쪼갬
            target /= 2;
            cnt++;
        }
        sb.append(cnt);
        System.out.print(sb);
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}