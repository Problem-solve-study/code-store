/**
 * 모니터에는 1이 쓰여 있음
 * 
 * 모니터에 쓰여 있는 수의 약수를 선택해서 모니터에 있는 값에 더한다
 * 제한 K를 [초과]한 사람이 패배하게 된다
 * 
 * 최선의 전력으로 플레이 한다
 * K가 주어졌을 때, 누가 이기는가?
 * 
 * 칼리부터 먼저
 * 
 * Kali -> Kali
 * Ringo -> Ringo
 * 
 * 그렇다면 무엇이 최선인 지를 알아야겠내
 * 
 * Tcase1)
 * K -> (1 -> 2) (칼리))
 * R ->
 * 
 * Tcase2)
 * K -> (1 -> 2) 칼리
 * R -> (2 -> 3) 링고
 * 
 * 게임이론? 
 *      상대방이 할 수 없는 것을 주는 것이 좋은 수 아님?
 * 일단 약수 중에 더해서 K를 만들 수 있는가? -> 필승
 * 약수 중에 가장 큰 값을 더하는 것이 best인가?
 * 
 * 언제가 가장 best일까? s
 *      가장 큰 값? -> NO
 *          K가 17이다
 *          8 
 *          1 2 4 8
 *          8을 더해서 16을 준다
 *          상대에서 1을 더해버리면? 내가 진다
 *      
 *      다음 턴에 약수 중에 더하는 값 중에서 K가 안나오게 하면 되잖아?
 *      그럼 상대방이 안되는 수들 중에서는 어떤 것을 줘야하지?
 *      위의 case에서는 {1,2,4} 중에서 어떤 것을 줘야하는가?
 * 
 *      12
 *      1 2 3 4 6 12
 */

#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
using ull = unsigned long long;

int K = 0, num = 1;

vector<int> get_div_numbers(ull number) {
    vector<int> div;
    
    for (int i = 1; i * i <= number; ++i) {
        if (number % i == 0) {
            div.push_back(i);
            if (i != (number / i))
                div.push_back(number / i);
        }
    }
    sort(div.begin(), div.end());

    return div;
}

int main(void) {
    ios::sync_with_stdio(0);
    cin.tie(0); cout.tie(0);
    
    cin >> K;
    vector<bool> win = vector<bool>(K+1, false);

    for (int i = K; i >=1; --i) {
        bool flag = false;

        auto div = get_div_numbers(i);

        for (const auto& div_num: div) {
            if (i + div_num <= K) {
                int nextVal = i + div_num;
                if (win[nextVal] == false) {
                    flag = true;
                    break;
                }
            }
        }
        
        win[i] = flag;
    }


    win[1] ? cout << "Kali" : cout << "Ringo";


    return (0);
}