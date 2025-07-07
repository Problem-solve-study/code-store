import java.io.*;
import java.util.*;

/*
11384KB, 72ms

현재 수에서 바로 나음으로 오는 단어를 찾아야 하므로 next_permutation 구현하기
 */

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T --> 0) {
            char[] str = br.readLine().toCharArray();
            //피벗 인덱스 찾기
            //배열의 오른쪽부터 순회하여 오름차순 정렬을 유지하는 최초의 원소 탐색
            int pivotIdx = -1;
            for (int i = str.length - 2; i >= 0; i--) {
                if (str[i] < str[i + 1]) {
                    pivotIdx = i;
                    break;
                }
            }

            //피벗 인덱스를 못찾은 경우는 현재가 마지막 단어인 경우
            if (pivotIdx == -1) {
                sb.append(str).append('\n');
                continue;
            }

            //교환할 인덱스 찾기
            //pivotIdx보다 오른쪽의 원소들을 대상으로 피봇 값보다 큰 값의 최솟값을 탐색
            int swapIdx = pivotIdx + 1;
            for (int i = pivotIdx + 1; i < str.length; i++) {
                if (str[pivotIdx] < str[i] && str[swapIdx] > str[i]) {
                    swapIdx = i;
                }
            }

            //피봇과 교환할 대상을 스왑하고
            char temp = str[pivotIdx];
            str[pivotIdx] = str[swapIdx];
            str[swapIdx] = temp;

            //피봇 오른쪽의 요소들을 정렬
            Arrays.sort(str, pivotIdx + 1, str.length);
            sb.append(str).append('\n');
        }
        System.out.print(sb);
    }
}
