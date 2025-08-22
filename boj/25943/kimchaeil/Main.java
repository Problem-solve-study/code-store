//문제: 25943번 양팔저울
//메모리: 12024 KB
//시간: 68 ms

/*
 * 양쪽 팔의 무게 차이가 항상 줄어들게 자갈을 올린다.
 * 문제의 1번 자갈을 왼쪽, 2번 자갈을 오른쪽에 올리는 것도 위에서 말한 규칙으로 인해 진행되는 것이다.
 * 즉, 좌우 팔의 무게를 계산하지 않고 무게 차이를 계산한다.
 * 그 후 자갈 개수를 구한다.
 */

public class Main {
    public static void main(String[] args) throws Exception {
        int[] weight = {100, 50, 20, 10, 5, 2, 1};
        int n = nextInt(), def = 0, result =0;
        for (int i = 0; i < n; i++)
            def += def < 0 ? nextInt() : -nextInt();

        def = def < 0 ? -def : def;
        while (def > 0) {
            for (int i = 0; i < 7; i++) {
                result += def / weight[i];
                def %= weight[i];
            }
        }
        System.out.println(result);
    }

    static int nextInt() throws Exception {
        int n, c;
        while ((c = System.in.read()) < 48) ;
        n = c & 15;
        while ((c = System.in.read()) > 47) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
