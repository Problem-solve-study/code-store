#include <bits/stdc++.h>
using namespace std;

using ll = long long;

int N;
int arr[1010];

ll ans = 0;

int main() {
    cin.tie(0)->ios::sync_with_stdio(0);

    cin >> N;
    for (int i = 0; i < N; i++) {
        cin >> arr[i];
    }

    sort(arr, arr+N);
    
    for (int i = 1; i < N; i++) {
        ans += abs(arr[i] - arr[i-1]);
    }

    ans += abs(arr[0] - arr[N-1]);

    cout << ans;

    return 0;
}
