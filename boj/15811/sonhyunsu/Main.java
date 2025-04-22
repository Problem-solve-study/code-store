//제출번호: 93438638
//메모리:   15000 KB
//실행시간: 496 ms
import java.io.*;
import java.util.*;

//알파벳에 직접 숫자를 대입해보고, 계산식이 맞는 지만 확인하면 됨
//만약 알파벳 개수가 11개 이상이 되면 숫자를 대입할 수 없기 때문에 수식을 만들 수 없음
//그 외는 직접 대입해보면서 찾아보면 됨
public class Main {
    
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static List<Character> alphabets = new ArrayList<>();
    static boolean[] used = new boolean[26];
    static int[] nums = new int[26];
    static char[] a, b, c;

    public static void main(String[] args) throws IOException {
        a = nextString().toCharArray();
        b = nextString().toCharArray();
        c = nextString().toCharArray();

		//a, b, c 문자열에 있는 알파벳을 alphabets에 기록하고, 그 개수를 셈
        int cnt = addAlphabets(a) + addAlphabets(b) + addAlphabets(c);

        for (int i = 0; i < 10; i++) {
            used[i] = false;
        }

		//알파벳 개수가 11개 이상이면 불가능, 그 외는 한 번 대입해본 뒤 출력
        System.out.print(cnt < 11 && perm(0) ? "YES" : "NO");
    }

    static int addAlphabets(char[] list) {
        int cnt = 0;
        for (char tmp : list) {
            if (!used[tmp - 'A']) {
                used[tmp - 'A'] = true;
                alphabets.add(tmp);
                cnt++;
            }
        }

        return cnt;
    }

	//문자열을 숫자로 변환하는 함수
    static long getNum(char[] list) {
        long num = 0;
        for (char tmp : list) {
			//nums에 기록된 숫자를 이용해서 num을 만듦
            num = num * 10 + nums[tmp - 'A'];
        }
        return num;
    }

	//순열을 이용해서 모든 경우를 탐색
    static boolean perm(int cnt) {
		//모든 알파벳에 숫자를 대입했으면 
        if (cnt == alphabets.size()) {
			//수식이 맞는 지 확인
            return getNum(a) + getNum(b) == getNum(c);
        }

        int aIdx = alphabets.get(cnt) - 'A';
        for (int i = 0; i < 10; i++) {
            if (used[i]) {
                continue;
            }

			//아직 사용하지 않은 숫자에 대해서 사용했다고 기록을 남기고,
            used[i] = true;
            nums[aIdx] = i; //현재 알파벳이 i 숫자를 사용한다고 기록한 뒤
            if (perm(cnt + 1)) { //다음 알파벳에 숫자를 대입해봄
                return true;
            }
            used[i] = false;
        }

		//모두 불가능하면 false
        return false;
    }

    static String nextString() throws IOException {
        st.nextToken();
        return st.sval;
    }
}