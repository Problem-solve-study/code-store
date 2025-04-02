//제출번호: 92353891
//메모리:   11560 KB
//실행시간: 68 ms
import java.io.*;

/*
...
12 13C1 * (48C8 - 12C1 * 44C4 + 12C2 * 40C0) + 13C2 * (44C4 - 11C1 * 40C0) + 13C3 * 40C0
11 13C1 * (48C7 - 12C1 * 44C3) + 13C2 * (44C3)
10 13C1 * (48C6 - 12C1 * 44C2) + 13C2 * (44C2)
9 13C1 * (48C5 - 12C1 * 44C1) + 13C2 * (44C1)
8 13C1 * (48C4 - 12C1 * 44C0) + 13C2 * (44C0)
7 13C1 * (48C3)
6 13C1 * (48C2)
5 13C1 * (48C1)
4 13C1 * (48C0)
3 0
2 0
1 0

위 수식을 잘 정리하면 아래처럼 나온대요
 */

public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int[][] combDp = new int[53][53];

    public static void main(String[] args) throws IOException {
        int n = nextInt();

        long res = 0;
        if (n > 3) {
            boolean doMinus = false;
            //i = 48, 44, 40, ... | r = n - 4, n - 8, n - 12 ... | k = 1, 2, 3 ... | doMinus = false, true, false ...
            for (int i = 48, r = n - 4, k = 1; r >= 0; i -= 4, r -= 4, k++, doMinus ^= true) {
                //홀수항은 더하고 짝수항은 뺀다.
                //13Ck * iCr 이라고 생각하면 편함
                //13Ck는 포카드를 선택하는 경우이고, iCr은 남은 카드들 i개 중 r개를 포카드를 만들지 않으면서 선택하는 경우
                //빼는 경우는 앞의 경우에서 중복되기 때문에 빼는 것
                //더하는 경우는 뺄 때 중복으로 빠져서 다시 더하는 것
                res += (doMinus ? -1 : 1) * comb(i, r) * comb(13, k);
                res %= 10007;
            }
        }

        //마지막에 큰 값을 빼서 res가 음수일 수 있으므로, 만약 음수라면 양수로 바꿔준다.
        if (res < 0) {
            res += 10007;
        }

        System.out.print(res);
    }

    //nCr 계산기, 메모이제이션으로 값 저장
    static int comb(int n, int r) {
        if (combDp[n][r] != 0) {
            return combDp[n][r];
        }

        if (r == 0 || n == r) {
            return combDp[n][r] = 1;
        }

        if (n == 0) {
            return combDp[n][r] = 0;
        }

        return combDp[n][r] = (comb(n - 1, r) + comb(n - 1, r - 1)) % 10007;
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}