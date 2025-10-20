//문제: 28250번 이브, 프시케 그리고 푸른 MEX의 아내
//메모리: 12244 KB
//시간: 104 ms

/*
 * 두 숫자가 (0,1)이라면 2
 * 두 숫자가 (0,1이 아닌 수)라면 1
 * 두 숫자가 (0이 아닌 수,0이 아닌 수)라면 0
 */

public class Main {
    public static void main(String[] args) throws Exception {
        int n = nextInt(), v;
        long[] cnt = new long[2];
        for (int i = 0; i < n; i++)
            if ((v = nextInt()) <= 1) cnt[v]++;
        long result = ((cnt[0] * cnt[1]) << 1) + ((cnt[0] * (cnt[0] - 1)) >> 1) + cnt[0] * (n - cnt[0] - cnt[1]);
        System.out.println(result);
    }
    static int nextInt() throws Exception {
        int n, c;
        while ((c = System.in.read()) < 48) ;
        n = c & 15;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
