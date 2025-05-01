/**
 * 109404KB	2644ms
 * 
 * [사고 흐름]
 * 질문 게시판에 1000만 이상의 펠린드롬 소수는 없다는 글을 보고, 장난삼아 제출했더니 AC받았습니다. 이거 왜 통과되는거죠..?
 * 에라토스테네스의 체를 사용하여 풀었으며, 탐색 구간의 최댓값이 약 1천만이므로 제 코드에서의 시간복잡도는 다음과 같습니다.
 * 
 * x = 0
 * for i in range(1, 100000000):
 *     x += (100000000-i)//i
 * 
 * x => 1,757,511,568
 * 여기에 펠린드롬 구하는데 log10(x)/2 가 소요되므로, 대충 50억정도라고 가정하겠습니다.
 * 2600ms가 소요되었으니 뭐... 큰 테케가 있는것은 확실하긴 한데, 이런 풀이가 통과될거라곤 생각하지 못했네요...
 */

import java.io.*;

public class Main {
    static int N, A, B;
    static boolean[] isNotPrime;

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static void main(String[] args) throws Exception {
        A = ni();
        B = ni();
        N = Math.min(B, 100_000_000)+1;
        isNotPrime = new boolean[N];
        for (int i=2; i<N; ++i) {
            if (isNotPrime[i]) continue;
            for (int j=i+i; j<N; j+=i) isNotPrime[j] = true;
        }

        StringBuilder res = new StringBuilder();
        for (int i=A; i<=B; ++i) {
            if (isNotPrime[i]) continue;
            if (isPalindrome(i)) res.append(i).append('\n');
        }
        res.append(-1);
        System.out.println(res);
    }

    static boolean isPalindrome(int x) {
        int y = 0;
        while (y<x) {
            y = y*10 + x%10;
            x = x/10;
        }
        return x==y || x==y/10;
    }

    static int ni() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}