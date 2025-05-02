//제출번호: 93813242
//메모리:   11528 KB
//실행시간: 64 ms
import java.io.*;

//G 위치에 여는 괄호, 닫는 괄호를 한 번씩 넣어보고 올바른 괄호가 되면 출력
public class Main {

    static int n;
    static byte[] brackets;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        String line = br.readLine();
        brackets = new byte[n];
        for (int i = 0; i < n; i++) {
            switch (line.charAt(i)) {
				//여는 괄호는 1, G는 0, 닫는 괄호는 -1로 바꿔서 저장
                case '(': brackets[i] = 1; break;
                case 'G': brackets[i] = 0; break;
                case ')': brackets[i] = -1; break;
            }
        }

		//괄호를 만들어 봄 - 항상 가능한 경우만 준다고 했으니 make를 수행하면 항상 true가 나옴
        make(0);
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < n; i++) {
			//숫자를 여는 괄호, 닫는 괄호에 맞게 바꿔서 출력
            sb.append(brackets[i] == 1 ? '(' : ')');
        }

        System.out.print(sb);
    }

    static boolean make(int cnt) {
		//끝까지 왔다면 올바른 괄호인지 체크
        if (cnt == n) {
            return check();
        }

		//고추장이 발려져 있다면
        if (brackets[cnt] == 0) {
            brackets[cnt] = 1; //여는 괄호로 설정
            if (make(cnt+1)) {
                return true;
            }

            brackets[cnt] = -1; //닫는 괄호로 설정
            if (make(cnt+1)) {
                return true;
            }

            brackets[cnt] = 0; //원복함
        } else {
			//괄호가 정해져 있다면 바로 다음으로
            if (make(cnt+1)) {
                return true;
            }
        }

        return false;
    }

    static boolean check() {
        int res = 0;
        for (int i = 0; i < n; i++) {
            res += brackets[i];

			//음수가 되면 여는 괄호와 매칭되지 않는 닫는 괄호가 생긴다는 뜻이므로 불가능
            if (res < 0) {
                return false;
            }
        }

		//0이 아니라면 아직 안 닫힌 여는 괄호가 있다는 뜻이므로 불가능
        return res == 0;
    }
}