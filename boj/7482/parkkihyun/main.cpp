// C++의 절망편

// 처음에는 cout.precision(11)인가로 풀었음
// 근데 오답처리 되길래 printf로 변경

// 다른 사람도 다 잘 풀었겠지만 미분을 해서 극소값(극솟값?)을 찾는 문제

// a, b로 길이를 잡고 부피를 구하면 V(b) = (a - 2b)^2 * b 가 나옴
// V(b)의 극솟값(극소값?)을 찾기 위해선 b에 대해서 미분을 한 뒤, b의 해를 찾으면 됨

// 그러면, b = a / 2 or a / 6 이 나옴.
// 근데 사실 a/2가 안 되는 이유는 기억이 나지 않아서 그냥 1/6이 정답이길래 이렇게 함..
#include <bits/stdc++.h>
using namespace std;

int N;
double a;

int main() {

    scanf("%d", &N);

    while (N--) {
        scanf("%lf", &a);

        printf("%.10lf\n", a/6);
    }

    return 0;
}
