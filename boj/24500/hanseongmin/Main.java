import java.io.*;

/*
11512KB, 68ms

수를 XOR해서 나올 수 있는 수는 현재 N의 비트 자릿수를 넘을 수 없다.
또한 그 이전의 수를 적절히 이용하여 N개의 모든 비트를 반드시 1개로 만들 수 있으므로
N을 입력받고 모든 비트를 1로 만드는 다른 수를 찾아서 출력한다.

모든 비트를 한 번에 켜면 되는데 비트를 하나씩 켜려다 1번 틀림
 */

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //N을 입력받자마자 이진수로 변환
        String nBinary = Long.toBinaryString(Long.parseLong(br.readLine()));
        //모든 비트를 1로 만드는 다른 수를 탐색
        StringBuilder n2 = new StringBuilder();
        for (int i = 0; i < nBinary.length(); i++) {
            //N의 현재 비트가 0이면 n2는 현재 비트가 1이 되어야 켜짐
            if (nBinary.charAt(i) == '0') {
                n2.append(1);
            } else {
                //마찬가지의 이유로 n2의 현재 비트가 0이 되어야 켜짐
                n2.append(0);
            }
        }

        StringBuilder sb = new StringBuilder();
        //만일 n2가 0이라면 N만으로 모든 비트가 1인 경우이므로 N만 출력
        if (Long.parseLong(n2.toString(), 2) == 0) {
            sb.append(1).append('\n');
        } else {
            //0이 아닌 다른 정수라면 N2와 XOR해야지 모든 비트가 1이 되므로 n2도 같이 출력
            sb.append(2).append('\n');
            sb.append(Long.parseLong(n2.toString(), 2)).append('\n');
        }
        sb.append(Long.parseLong(nBinary, 2));
        System.out.print(sb);
    }
}
