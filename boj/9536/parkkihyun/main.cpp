// ([녹음소리] - [동물 울음소리]) * 테스트케이스
// 인데 돔울 울음소리를 1회만 받고 풀어서 계속 틀렸던 문제
// string tokenize를 연습할 수 있어서 좋은 문제였음
#include <bits/stdc++.h>
using namespace std;

int main() {
    cin.tie(0)->ios::sync_with_stdio(0);
    
    int T; cin >> T;
    cin.ignore();
    for (int t = 0; t < T; t++) {
        string sound; getline(cin, sound);

        vector<string> sounds;
        stringstream ss(sound);
        
        string word;
        while (ss >> word) sounds.push_back(word);

        set<string> animals;
        while (1) {
            string line;
            getline(cin, line);
            if (line == "what does the fox say?") break;

            stringstream ls(line);
            string a, g, s;
            ls >> a >> g >> s;
            animals.insert(s);
        }

        for (string s : sounds) {
            if (animals.find(s) == animals.end()) {
                cout << s << ' ';
            }
        }
        cout << '\n';
    }
    
    return 0;
}
