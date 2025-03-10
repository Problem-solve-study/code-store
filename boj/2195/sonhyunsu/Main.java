
//제출번호: 91132768	
//메모리:   11828 KB
//실행시간: 72 ms
import java.io.*;

//처음엔 뭔가 dp로 접근해야 하는 하는 것인 줄 알았는데
//s, p의 길이가 작고 dp를 이용하기는 어려워 보여서
//그냥 p의 부분문자열이 s에 있는지만 검사해보기로 함 (시간복잡도 계산이 잘 안 돼서 시초 각오하고)
//s에 있는 지 검사하는 것을 빠르게 해보기 위해서 TreeSet을 적용해보니까 멤초..
//Set에 저장하지 않고 그냥 바로 contains로 있는 지 검사하니 통과, 생각보다 시간이 짧아서 당황함
//직관적으로 보면 while은 p의 문자열 길이 만큼만 반복함 (찾으면 찾은 길이만큼 건너뛰므로)
//그리고 p의 부분 문자열에 대해서 s에 있는 지 확인해야 하니까 이 때 O(s의 문자열 길이)의 시간복잡도가 발생
//따라서 총 시간복잡도는 O(p의 문자열 길이 * s의 문자열 길이) 로 추측함
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String s = br.readLine();
        String p = br.readLine();

        int cnt = 0;
        int start = 0, end;
        // start는 p의 길이가 넘지 않게
        while (start < p.length()) {

            // substring은 [start, end)의 부분 문자열을 반환하므로 end는 start보다 커야 함
            end = start + 1;

            // end는 p의 문자열 길이까지 가야 모든 부분 문자열을 탐색할 수 있음
            while (end <= p.length() && s.contains(p.substring(start, end))) {
                end++;
            }

            cnt++;
            // end - 1까지 부분 문자열을 봤을 때 s에 없는 것이므로
            // 새로운 start는 일치하지 않았던 end - 1 위치부터
            start = end - 1;
        }

        System.out.print(cnt);
    }
}