// 11724KB 68ms

import java.util.*;
import java.io.*;

/**
 * 하노이 탑에서 해당 조건이 사라져서, 두 원판들 중 최대값을 세번째로 옮기고 그 외에는 첫번째-두번째 줄에서 이동하는 게 최적입니다.
 * 
 * 첫번째 줄과 두번째 줄을 스택으로 구성
 * 두 줄에서의 최대값(== 현재 세번째 줄에 들어가야 할 원판)을 찾은 후에
 * 최대값의 위쪽에 있는 원판을 모두 반대편 스택으로 옮긴 후 최대값을 세번째에 이동
 * 
 * 위 과정을 세번째 줄이 모두 찰 때까지 반복
 */
class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static StringBuilder sb = new StringBuilder();

    static int[] first;
    static int[] second;
    static int ftop = 0;
    static int stop = 0;
    static int currentTop;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        int n = next();
        first = new int[n];
        second = new int[n];
        currentTop = n + 1;

        for (int i = 0; i < n; i++) {
            first[ftop++] = next();
        }

        while (currentTop != 1) {
            int[] mi = findMaxIndex();
            
            // display();

            move(mi[0] == 1, mi[1]);

            // display();
        }
        sb.insert(0, count + "\n");

        System.out.print(sb);
    }

    static void move(boolean isFirst, int mi) {
        if (isFirst) {
            while (ftop > mi + 1) {
                second[stop++] = first[--ftop];
                count++;
                sb.append("1 2\n");
            }
            
            currentTop = first[--ftop];
            count++;
            sb.append("1 3\n");
            return;
        }
        
        while (stop > mi + 1) {
            first[ftop++] = second[--stop];
            count++;
            sb.append("2 1\n");
        }
        currentTop = second[--stop];
        count++;
        sb.append("2 3\n");
    }

    static int[] findMaxIndex() {
        int max = 0;
        int index = -1;
        int lineId = 1;
        for (int i = 0; i < ftop; i++) {
            if (first[i] > max) {
                max = first[i];
                index = i;
            }
        }
        for (int i = 0; i < stop; i++) {
            if (second[i] > max) {
                max = second[i];
                index = i;
                lineId = 2;
            }
        }

        return new int[]{lineId, index};
    }

    static void display() {
        System.out.print("first: ");
        for (int i = 0; i < ftop; i++) {
            System.out.print(first[i] + ", ");
        }
        System.out.println();
        System.out.print("second: ");
        for (int i = 0; i < stop; i++) {
            System.out.print(second[i] + ", ");
        }
        System.out.println();
        System.out.println();
    }

    static int next() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
