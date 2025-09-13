
/*
 * 바구니에 최대 5kg 까지 담을 수 있음.
 * 물건은 1kg, 2kg, 3kg, 4kg, 5kg 의 개수가 주어짐
 * 
 * 5kg 물건은 한 바구니에 담아야 함.
 * 4kg 물건은 추가로 1kg 담을 수 있음.
 * 3kg 물건은 2kg, 없으면 1kg 꽉꽉 담기
 * 2kg 남아있으면, 2kg, 1kg 꽉꽉 담기.
 * 나머지 1kg 전부 담기.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
    static int[] nums, opts = new int[4], perm;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        int A = Integer.parseInt(in[0]);
        int B = Integer.parseInt(in[1]);
        int C = Integer.parseInt(in[2]);
        int D = Integer.parseInt(in[3]);
        int E = Integer.parseInt(in[4]);

        int res = E;

        res += D;
        A = A - D > 0 ? A - D : 0;

        res += C;
        int t = C;
        if (B >= C)
            B -= t;
        else {
            t -= B;
            B = 0;
            A = A - t * 2 > 0 ? A - t * 2 : 0;
        }

        if (B > 0) {
            t = B / 2; // 만큼 바구니 담고, A도 담고
            B -= t * 2;
            A = A - t > 0 ? A - t : 0;
            res += t;

            t = B;
            B -= t;
            A = A - t * 3 > 0 ? A - t * 3 : 0;
            res += t;
        }

        if (A > 0) {
            res += (A + 4) / 5;
        }

        System.out.println(res);
    }
}