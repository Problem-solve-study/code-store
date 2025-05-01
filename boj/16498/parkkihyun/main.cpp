#include <bits/stdc++.h>
using namespace std;

// 재완이가 알려준 풀이

using pii = pair<int, int>;

int A, B, C;
int contain[3];
vector<pii> cards;

bool impossible() {
    return contain[0] == 0 || contain[1] == 0 || contain[2] == 0;
}

int main() {
    cin.tie(0)->ios::sync_with_stdio(0);

    cin >> A >> B >> C;
    for (int i = 0; i < A; i++) {
        int a; cin >> a;
        cards.push_back({a, 0});
    }

    for (int i = 0; i < B; i++) {
        int b; cin >> b;
        cards.push_back({b, 1});
    }

    for (int i = 0; i < C; i++) {
        int c; cin >> c;
        cards.push_back({c, 2});
    }

    sort(cards.begin(), cards.end());

    int l = 0, r = 0;
    multiset<int> nums;

    int ans = 0x7f7f7f;

    for (int l = 0; l < cards.size(); l++) {
        while (r < cards.size() && impossible()) {
            pii& card = cards[r++];
            contain[card.second]++;
            nums.insert(card.first);
        }

        if (impossible()) break;
        int mx = *nums.rbegin();
        int mn = *nums.begin();

        ans = min(ans, abs(mx - mn));

        contain[cards[l].second]--;
        nums.erase(nums.find(cards[l].first));
    }

    cout << ans;

    return 0;
}
