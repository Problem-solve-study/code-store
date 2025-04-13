import java.io.*;
import java.util.*;

/*
19324KB, 212ms

그림판으로 슥슥 해보면서 짠거라 글로 적기가 굉장히 까다로운데,
대략적인 로직만 간략히 설명하자면 n이 10억으로 일일히 다 더하면 시간초과날게 분명하다고 생각해서
어느 정도의 그룹으로 묶어야겠다고 생각했다.

6번째 줄의 조건의 j가 늘어나는 추이를 보면 1부터 시작하여 공차가 i인 등차수열의 꼴로 나타나고,
마지막 항이 n보다 작거나 같은 등차수열의 항의 개수만큼이 실행되게 되는데
대략 적당히 작은 수를 잡고 i가 1 ~ 10일 때까지 항의 개수가 몇 개가 나오는지를 살펴보면 대략적인 규칙이 보인다.

나는 24를 예시로 잡고 돌려봤는데,
항의 개수가 2인 수의 범위는 12 ~ 23
항의 개수가 3인 수의 범위는 8 ~ 11
항의 개수가 4인 수의 범위는 6 ~ 7이다.

이쯤에서 24의 약수를 구해보면
1       2       3       4       6       8       12      24가 나오게 되는데,
저 수의 범위가 약수들 사이의 범위를 가지는 것을 알 수 있다.
따라서 약수 사이의 범위로 그룹을 짓고 한 번에 더한다.

근데 단순히 약수만 가지고 판별해서는 안된다.
9의 경우 약수는 1, 3, 9밖에 나오지 않지만 2의 경우가 빠져있게 되므로 모든 경우의 수를 판별하는게 아니게 된다.
따라서 약수를 구하면서 나누어 떨어지지 않는 수에 대해서도 유사 약수를 구해서 동시에 그룹핑할 수 있게 만들어준다.
(어차피 그룹핑할 목적으로 구하는 것이니 엄밀한 약수가 아니여도 상관이 없다)
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws Exception {
        int n = nextInt();
        if (n == 1) {
            System.out.print(1);
            return;
        }

        //약수, 유사 약수를 구함
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 1; true; i++) {
            int a = (int)Math.ceil((double)n / i);
            if (set.contains(i) || set.contains(a)) break;
            set.add(i);
            set.add(a);
        }

        ArrayList<Integer> list = new ArrayList<>(set);
        long res = 0;
        int l = 0; int r = list.size() - 1;
        //구한 수들을 그룹핑하여 한 번에 계산
        while (l <= r) {
            if (r == list.size() - 1) {
                res += (n / list.get(l));
                res += (n / list.get(r));
            } else {
                long a = list.get(l);
                long b = list.get(r + 1) - list.get(r);
                if (l != r)
                    res += (int)Math.ceil((double)n / list.get(l));
                res += (a * b);
            }
            l++;
            r--;
        }

        //구한 수들이 짝수라면 세지 못한 수들이 남아있으니 마저 세준다.
        if (list.size() % 2 == 0) {
            for (int i = list.get(r) + 1; i < list.get(l); i++) {
                res += (int) Math.ceil((double) n / i);
            }
        }

        System.out.print(res);
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
