#include <bits/stdc++.h>
using namespace std;

using Matrix = vector<vector<int>>;

int X, Y, A0, A1, N;

Matrix multiply(const Matrix& a, const Matrix& b) {
    Matrix result(2, vector<int>(2));

    for (int i = 0; i < 2; i++) {
        for (int j = 0; j < 2; j++) {
            for (int k = 0; k < 2; k++) {
                result[i][j] += (a[i][k] * b[k][j]) % 100;
            }
        }
    }

    return result;
}

Matrix power(Matrix base, int K) {
    Matrix result = {{1, 0,}, {0, 1}};

    while (K > 0) {
        if (K & 1) result = multiply(result, base);
        base = multiply(base, base);
        K >>= 1;
    }

    return result;
}

int main() {
    cin.tie(0)->ios::sync_with_stdio(0);

    cin >> X >> Y >> A0 >> A1 >> N;

    if (N == 0) {
        cout << setw(2) << setfill('0') << A0 << '\n';
        return 0;
    }

    if (N == 1) {
        cout << setw(2) << setfill('0') << A1 << '\n';
        return 0;
    }

    Matrix base = {{X, Y}, {1, 0}};
    Matrix result = power(base, N-1);
    int ans = (result[0][0] * A1 + result[0][1] * A0) % 100;

    cout << setw(2) << setfill('0') << ans;

    return 0;
}
