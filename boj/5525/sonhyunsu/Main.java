//제출번호: 91629480	
//메모리:   20528 KB
//실행시간: 172 ms
import java.io.*;

//Pn을 찾았을 때 계산한 값을 최대한 활용해야하는 문제
//Pn-1은 다시 찾을 필요가 없으므로 Pn-1부터 다시 Pn이 있는 지 살펴보면 됨
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        String line = br.readLine();
        char[] words = {'I', 'O'};

        int oCnt = 0;
        int res = 0;
        int nextIdx = 0;
        for (int i = 0; i < m; i++) {
            char c = line.charAt(i);

            //지금 나타나야 할 문자가 동일하다면
            if (c == words[nextIdx]) {
                //만약 나타난 문자가 O라면 O의 개수를 증가
                if (c == 'O') {
                    oCnt++;
                } else if (oCnt == n) {
                    //만약 나타난 문자가 I이면서 이미 이전에 O가 n개만큼 나왔으면
                    //Pn의 문자열이 완성된 것임
                    res++;

                    //Pn-1의 문자열부터 시작
                    oCnt--;
                }

                nextIdx ^= 1;
            } else {
                //나타나야 할 문자열이 다르다면 처음부터 찾아야 함
                oCnt = 0;
                //이 때 지금 문자가 I라면 다음에 나타나야 할 문자가 O가 되고
                //(다음에 찾을 문자열에서 이미 I는 가지고 있는 것이니까)
                //지금 문자가 O라면 다음에 나타나야 할 문자가 I가 됨
                nextIdx = c == 'I' ? 1 : 0;
            }
        }

        System.out.print(res);
    }


}