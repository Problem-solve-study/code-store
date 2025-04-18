//제출번호:	93275308
//메모리:	22656 KB
//실행시간:	104 ms
import java.io.*;

//그냥 s, t 문자열이 있을 때
//s의 접두사들 중 t의 접미사가 있는 지
//t의 접두사들 중 s의 접미사가 있는 지
//확인만 하면 풀 수 있는 문제 
public class Main {
    
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        int n = nextInt();
        String[] names = new String[n];

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            names[i] = nextString();
            for (int j = 0; j < i; j++) {
                boolean canConnect = false;

				//두 문자열의 길이 중 더 짧은 것을 고른다.
                int len = Math.min(names[i].length(), names[j].length());
				//길이를 하나씩 늘려보면서
                for (int k = 1; k <= len; k++) {
					//i의 접미사가 j의 접두사인지 확인하고
					//j의 접미사가 i의 접두사인지 확인한다.
                    if (names[i].endsWith(names[j].substring(0, k))
                        || names[j].endsWith(names[i].substring(0, k))) {

						//만약 가능하면 연결 가능한 것이고, 더 이상 확인할 필요가 없으므로 break
                        canConnect = true;
                        break;
                    }
                }

				//만약 연결 가능하면 (i, j) 쌍이 연결 가능한 조합임
                if (canConnect) {
                    cnt++;
                }
            }
        }

        System.out.print(cnt);
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