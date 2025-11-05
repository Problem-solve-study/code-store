import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    // 알파벳 26개 기준 (소문자 가정)
    static int[][] pos = new int[26][];
    static int[] ptr = new int[26]; // 각 알파벳별 다음 사용할 위치의 인덱스

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String T = br.readLine();

        // 1) 문자별 개수 세기
        int n = S.length();
        int[] cntByChar = new int[26];
        for (int i = 0; i < n; i++) {
            cntByChar[S.charAt(i) - 'a']++;
        }

        // 2) 정확한 크기의 배열을 만들고 채울 위치 인덱스 준비
        int[] fillIdx = new int[26];
        for (int c = 0; c < 26; c++) {
            pos[c] = new int[cntByChar[c]];
            fillIdx[c] = 0;
            ptr[c] = 0;
        }

        // 3) 위치 채우기
        for (int i = 0; i < n; i++) {
            int c = S.charAt(i) - 'a';
            pos[c][fillIdx[c]++] = i;
        }

        // 4) T 만들기 반복
        int made = 0;
        while (makeOnce(T))
            made++;

        System.out.println(made);
    }

    // T를 한 번 만들 수 있으면 true, 아니면 false
    private static boolean makeOnce(String T) {
        if (T.isEmpty())
            return false; // 문제 조건상 필요 없지만 안전장치

        int prev = -1; // 직전 선택한 인덱스
        int lenT = T.length();

        for (int i = 0; i < lenT; i++) {
            int c = T.charAt(i) - 'a';
            int[] arr = pos[c];
            int p = ptr[c];

            // prev보다 큰 첫 위치를 찾도록 포인터 전진
            // (배열은 S에서의 등장 위치가 오름차순이므로 선형 전진만 하면 됨)
            while (p < arr.length && arr[p] <= prev)
                p++;

            if (p == arr.length) {
                // 이 문자를 더 이상 prev보다 뒤에서 찾을 수 없음 => T를 더 못 만듦
                return false;
            }

            // 위치 소비
            prev = arr[p];
            ptr[c] = p + 1; // 다음 번에는 그 다음 위치부터 시작
        }
        return true;
    }
}
