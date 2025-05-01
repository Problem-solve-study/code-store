/*
    처음에는 1부터 1억까지 소수를 모두 구하고 팰린드롬을 구하려고 했음
    그러나 1억까지 소수를 구하는데 에라토스테네스의 체를 사용해도 시간이 너무 오래 걸려 무리라고 판단.

    한 수를 소수라고 판단하는데 걸리는 시간 복잡도는 O(log N) 이므로 팰린드롬을 만들고 소수를 판단하는걸로 변경

    팰린드롬은 최대 7글자 (1억 이하, 1억은 팰린드롬이 아니므로 최대 99999999)
    숫자 4개만 뽑으면 그걸로 팰린드롬을 만들 수 있음
*/

import java.io.*;


class Main {

    static int A, B;
    static int[] sel = new int[8];
    
    public static void main(String[] args) throws Exception {
        A = ni(); B = ni();

        for (int l = 1; l <= 7; l++) {
            makePalin(0, l);
        }

        sb.append(-1);

        System.out.println(sb.toString());
    }

    static void makePalin(int cnt, int len) {
        // 선택한 숫자로 충분히 팰린드롬을 만들 수 있을 경우
        if ((cnt << 1) >= len) {
            int num = 0, idx = 0;

            // 수를 이용한 팰린드롬 만들기
            while (idx < cnt) {
                num *= 10;
                num += sel[idx++];
            }

            // 만들어야하는 팰린드롬의 길이가 홀수라면 마지막 글자는 들어가면 안 됨
            if (len % 2 == 1) idx--;

            // 만들기...
            while (idx > 0) {
                num *= 10;
                num += sel[--idx];
            }

            // 만든 팰린드롬이 A <= num <= B이고 소수이면 추가
            if (A <= num && num <= B && isPrime(num)) sb.append(num).append('\n');
            return;
        }

        for (int i = 0; i < 10; i++) {
            sel[cnt] = i;
            makePalin(cnt+1, len);
            sel[cnt] = 0;    // 사실 없어도 됨
        }
    }

    // 소수 판단
    static boolean isPrime(int num) {
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    static StringBuilder sb = new StringBuilder();
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int ni() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
