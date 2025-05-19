// 진짜 벙찐 문제

// 진짜 확률을 구하라는건가? 싶어서 검색해서 맞다는 걸 확인하고 풂
// 그냥 시뮬 돌려서 풀었는데 다른 방법으로 푼것이 있다는 것이 놀라울 따름.

// 1 - (모두 소수가 아닌 경우)를 생각했으나 도저히 못 구하겠어서 패스패스패스
#include <bits/stdc++.h>
using namespace std;

bool isPrime[] = {0, 0, 1, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0};

double A, B;
double dp[20][20][20];

double solve(int round, int aScore, int bScore) {
    if (round == 18) {
        if (isPrime[aScore] || isPrime[bScore]) {
            return 1.0;
        }
        return 0.0;
    }

    double& ret = dp[round][aScore][bScore];
    if (ret != -1.0) return ret;
    ret = 0.0;
    
    ret += A * B * solve(round+1, aScore+1, bScore+1);
    ret += A * (1 - B) * solve(round+1, aScore+1, bScore);
    ret += (1 - A) * B * solve(round+1, aScore, bScore+1);
    ret += (1 - A) * (1 - B) * solve(round+1, aScore, bScore);

    return ret;
}

int main() {
    scanf("%lf\n%lf", &A, &B);

    A /= 100;
    B /= 100;

    for (int i = 0; i < 20; i++) {
        for (int j = 0; j < 20; j++) {
            for (int k = 0; k < 20; k++) {
                dp[i][j][k] = -1.0;
            }
        }
    }

    printf("%.10lf", solve(0, 0, 0));

    return 0;
}
