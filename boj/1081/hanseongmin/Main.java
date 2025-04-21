import java.io.*;

/*
11568KB, 104ms

그냥 백만 단위로 한꺼번에 수를 계산하면 시간내에 구할 수 있을 것 같았다.
구현이 살짝 까다로워서 출근하고 살짝 하다가 때려치고 집에 와서 풀었다.

0 ~ 999_999까지의 모든 자릿수의 합을 그냥 브루트포스로 구해보면 27000000이 나온다.
백만 단위로 한꺼번에 점프하면서 자릿수의 합을 계산해보자.
수는 xx______의 꼴이 될 것이다. ______에는 우리가 아까 계산해둔 0 ~ 999_999가 들어갈 자리이다.
만일 1______라면 0 ~ 999_999의 합인 27000000에 1이 백만번 들어가 있으므로 (1 * 백만) + 27000000개만큼 있다.
2~~~의 꼴이라면 27000000에 2가 백만번 들어가있으므로 (2 * 백만) + 27000000개만큼 있게 된다.
쭉 가다가
10~~~~의 꼴이면 10*백만 + 27000000일까? 아니다. 10에 해당하는 자릿수는 1이므로 다시 (1 * 백만) + 27000000개가 된다.
이를 일반화해보면, 수의 맨 뒤 6자리를 고정시켜두고 앞의 자리의 자릿수의 합을 구하자 그리고 이를 a라고 할 때
자릿수는 a * 백만 + 27000000이 된다.

위의 식을 이용해서 백만 단위로 건너뛰며 자릿수를 계산한다. 그러다가 어느순간 주어진 수를 넘어서게 되는 때가 오는데
그때는 그냥 브루트포스로 구한다.

값의 범위가 20억이지만 백만씩 건너뛰며 한번에 계산하므로 충분히 시간 내에 돌고 브루트포스로 구할 때도
기껏해야 백만만큼만 돌면 되므로(이래서 뛰어넘는 단위를 백만으로 정했다) 충분한 시간 내에 구할 수 있다.
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws Exception {
        int L = nextInt();
        int U = nextInt();
        //답은 누적합처럼 구해준다.
        System.out.print(getSum(U) - getSum(L - 1));
    }

    static long getSum(int num) {
        //백만번의 연산 안에 구할 수 있다면 브루트포스로 구한다.
        if (num < 1_000_000) {
            return rangeDigitSum(0, num);
        }

        //i를 백만부터 시작할 것이므로 0 ~ 999_999의 합은 미리 초기화해둔다.
        long sum = 27000000;
        long v = 1;
        //백만부터 시작하여 자릿수를 한꺼번에 계산하기 시작한다.
        int i = 1_000_000;
        for (; true; i += 1_000_000) {
            //백만번의 연산 안에 구할 수 있다면 브루트포스로 구한다.
            if (i + 1_000_000 >= num) {
                return sum + rangeDigitSum(i, num);
            }
            //위에서 구한 공식대로 자릿수를 계산한다.
            sum += (digitSum(v++) * 1_000_000 + 27000000);
        }
    }

    static long rangeDigitSum(int start, int end) {
        long sum = 0;
        for (int i = start; i <= end; i++) {
            sum += digitSum(i);
        }
        return sum;
    }

    static long digitSum(long num) {
        long sum = 0;
        while (num != 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
