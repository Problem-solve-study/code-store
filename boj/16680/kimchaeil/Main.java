//메모리: 12544KB
//시간: 120ms
//문제 풀이 발상이 길어져 코드 아래에 적었습니다.

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        long n;
        for (int test_case = 0; test_case < T; test_case++) {
            n = Long.parseLong(br.readLine());
            System.out.println(n * 999_999_999);
        }
    }
}

/*
결론부터 말하면 우연히 발견했습니다...
'배수'와 '각 자리 숫자의 합'이라는 키워드때문에
어릴 때, 구구단에서 9단의 결과값의 각 자리 숫자의 합은 항상 9인 것과 한 자리수 n에 9를 곱하면 십의 자리수는 n-1이라는 규칙을 찾은 기억이 났습니다.
문제를 풀다말고 두 규칙에 연관이 있는지 궁금해져서 생각해보기 시작했습니다.

10a+b = 9n (단, n은 9 이하의 자연수)에서
9n = 10n-n 이고 b는 9n을 10으로 모듈러 연산한 값이므로
b = 9n mod 10 = (10n-n) mod 10 = ((10n mod 10) - (n mod 10)) mod 10
= (0-n) mod 10 = -n mod 10 = 10-n
즉, b = 10 - n과 같습니다.

b = 10-n을 10a+b = 9n 에 대입하면
10a + 10-n = 9n
10a + 10 = 10n
a+1 = n
a = n-1
즉, a는 n-1과 같습니다.
이것으로 위에서 언급한 두번째 규칙을 증명했습니다. (맞는 증명인지는 모릅니다)

위에서 구한 것들을 통해
a+b = n-1 + 10-n = 9 라는 것을 알 수 있었습니다.
이것으로 위에서 언급한 첫번째 규칙을 증명했습니다.

여기서 확장된 해서 두 자리 수도 비슷한 규칙이 있지 않을까 생각했습니다.
위 증명에서 얻은
10a+b = 9n일 때, b=10-n, a=n-1을 사용하면 성립할까? 라는 생각을 했습니다.
임의의 두자리 수인 27로 예를 들면
b=-17, a=26, 9*27 = 26*10 - 17= 243으로 위에서 알아낸 규칙을 사용 가능하다는 사실을 알았습니다.
b = 10-n이라는 사실을 알아낼 때
9 이하라는 조건이 없어도 상관 없는 유도 방식이라 그런것 같다고 생각을 했습니다.

이때, 'a의 계수가 10의 배수이기만 하면 되지않을까?' 하고 생각했습니다.
그래서 만약, 100a+b에 위에서 얻은 b=10-n, a=n-1을 사용하면 어떤 값이 나올까 생각해봤고
100a+b = 100(n-1)+10-n = 100n-100+10-n = 99n-90

여기서 저는 99n 부분에 눈이 갔고
100a+b = 99n 이라고 식을 잡고 위와 같은 흐름으로 진행해보았습니다.

a의 계수가 100이 되었고 99n = 100n-n 입니다.
100이 겹쳤습니다. 그래서 이번에는
b = 99n mod 100으로 시작했습니다.

b = 99n mod 100 = (100n-n) mod 100 = -n mod 100 = 100-n

100a+b = 99n
100a+100-n = 99n
100a+100=100n
a+1=n
a=n-1

a+b= n-1 + 100-n = 99
a는 floor(99n/100)으로 표현 가능합니다.
여기서 a가 꼭 2자리일 필요는 없다고 생각했습니다.
즉, a<10으로 조건을 걸면 n<11입니다.
위 조건을 만족하는 임의의 수 n을 3으로 잡아보겠습니다.
3*99=297
맞는 것 같습니다.
그런데 저는 십의 자리가 9인것을 발견했습니다.
우연인가 싶어 다른 n들도 확인했습니다.
n : n*99
1   99
2   198
3   297
4   396
5   495
6   594
7   693
8   792
9   891
10  990
모든 결과의 십의 자리가 9인것을 발견했습니다.

위에서 사용했던 a 와 b에 대한 n의 식에서
a의 계수를 10으로 잡으면 n의 계수는 9 (10-1)
a의 계수를 100으로 잡았으면 n의 계수는 99 (100-1)
n의 계수를를 10^k - 1로 잡으면 규칙이 있을것 같았습니다.
k를 3으로 잡아보겠습니다. n의 계수가 999가 되었습니다.
n : n*999
1   999
2   1998
3   2997
4   3996
5   4995
6   5994
7   6993
8   7992
9   8991
10  9990
가운데에 9의 개수가 늘어났습니다.

그럼 k가 커질수록 가운데 9의 개수가 늘어날 것 같았습니다.
11*999도 확인해보니 10989였습니다.
11*99 = 1089인데 가운데에 9가 생겼습니다.
여기서 가운데의 9의 개수는 k-(n의 길이)라고 보면 일단 맞는것 같았습니다.
k를 크게 잡아 7로 잡았습니다.
9999999*11=109999989, 7-2=5, 가운데의 9의 개수도 5개
여기서 가운데 9들을 제외하면 10과 89가 남고 둘을 더하면 99가 되었습니다.
가운데에 늘어난 9들을 제외한 부분의 각 자리의 수를 다 더하면 9*(n의 길이) 였습니다.
여기서는 생략하였지만 휴대폰 계산기를 통해 이를 쉽게 알아낼수 있었습니다.

그렇다면 n * (10^k - 1)의 각 자리수의 합은
9*(n의 길이) + max(9*(k-(n의 길이)),0) 입니다.
(n의 길이)가 k보다 작거나 같다면
9*((n의 길이)+k-(n의 길이)) = 9*k 입니다.
즉, n의 길이가 k보다 작거나 같기만 한다면 n의 값과 상관없이
k가 짝수라면 각 자리수의 합은 짝수이고
k가 홀수라면 각 자리수의 합은 홀수입니다.

각 자리수의 합이 홀수인 수는? 문제에서 말하는 안수빈수
여기서부터 안수빈수 문제로 돌아왔습니다.

안수빈수 문제의 입력값인 N의 최댓값을 살펴보니 100,000,000으로 숫자의 길이가 9입니다.
그렇다면 k가 9 이상이면 되니 k를 9로 잡습니다.
제가 위에서 유도한 부분들이 참이라면
임의의 값 n에 대해 n*(10^9 -1) = n*(999,999,999)의 각 자리수의 합은 홀수인 안수빈수입니다.
이를 믿고 코드를 짜서 제출했더니 정답이었습니다.

결론은 안수빈수 문제 풀다가 딴 짓 한다고 숫자 가지고 놀다가 우연히 발견했습니다.
*/