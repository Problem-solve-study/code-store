//제출번호: 93815258
//메모리:   24948 KB
//실행시간: 252 ms
import java.io.*;
import java.util.*;

//처음에 문제를 잘못 이해해서 이거 왜이렇게 어렵지 생각했음
//2번 예제의 abaaaba 에서 가장 긴 문자열이 baaab가 안 되는 것을 보고 문제를 제대로 이해함
//어떤 문자를 정확히 K를 가지는 가장 짧은 문자열을 고르기 위해서는 항상 양 끝이 어떤 문자로 이루어져 있어야 함
//그러니까 구하고자 하는 가장 짧은 문자열과 가장 긴 문자열은 항상 양 끝이 어떤 문자로 이루어져 있음
//다시 말하면 조건에 맞는 문자열을 모두 구하고 그 문자열의 길이의 최소 최대만 구하면 됨
public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {

		//각 문자의 위치를 저장할 공간을 만듦 
        List<List<Integer>> alphabetIdx = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            alphabetIdx.add(new ArrayList<>());
        }

        int T = nextInt();
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            char[] w = nextString().toCharArray();
            int k = nextInt();

			//알파벳 위치 공간 초기화
            for (int i = 0; i < 26; i++) {
                alphabetIdx.get(i).clear();
            }

            int min = Integer.MAX_VALUE;
            int max = 0;

            for (int i = 0; i < w.length; i++) {
                int aIdx = w[i] - 'a';
                List<Integer> aIdxs = alphabetIdx.get(aIdx);
                aIdxs.add(i); //현재 알파벳의 위치를 저장함

                int size = aIdxs.size();
                if (size >= k) { //만약 알파벳 개수가 K개 이상이라면 조건에 맞는 문자열을 만들 수 있음
					//조건에 맞는 문자열의 길이는 (size - 1)번째 위치 - (size - k)번째 위치 + 1 
                    int len = aIdxs.get(size - 1) - aIdxs.get(size - k) + 1;

					//최대 최소 업데이트
                    min = Math.min(min, len);
                    max = Math.max(max, len);
                }
            }

			//조건에 맞는 문자열이 없다면 -1
            if (max == 0) {
                sb.append(-1);
            } else {
				//있다면 최소 최대 출력
                sb.append(min).append(' ').append(max);
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    static String nextString() throws IOException {
        st.nextToken();
        return st.sval;
    }
}