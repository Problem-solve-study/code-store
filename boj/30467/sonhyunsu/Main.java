//제출번호: 96122825
//메모리:   32944 KB
//실행시간: 392 ms
import java.io.*;
import java.util.*;

//높이가 무제한이니까 a b높이의 등불에 대해서 a < b이면 언젠가는 b가 a를 앞지르는 순간이 생김
//그래서 세그트리를 이용한 증가부분수열이 생각남
//그런데 s로 구간을 제한하니까 구간 밖으로 나갈 때 추가적인 처리가 필요하다고 생각함

//증가부분수열의 길이가 아닌, 결과값을 저장하고
//s구간을 스위핑으로 이동하면서 결과값을 업데이트
//구간 중 최댓값이 최종 정답

//업데이트를 할 때는
//s 구간 안에서 지금 빠지는 수보다 더 큰 수의 개수만큼 결과값에서 빠지고
//새로운 수가 s 구간 안에 들어올 때 새로운 수보다 작은 수의 개수만큼 결과값에 더함

//높이는 범위가 1~1e9이지만 개수는 10만개이기 때문에 좌표압축을 이용하면 10만개 안에서 충분히 연산 가능
public class Main {
    public static void main(String[] args) throws IOException {
        int n = nextInt(), s = nextInt();
        int[] arr = new int[n];

        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            set.add(arr[i] = nextInt());
        }

        Map<Integer, Integer> index = new HashMap<>();
        int idx = 0;
        for (int val : set) {
            index.put(val, ++idx);
        }

        int[] fwTree = new int[index.size() + 1];
        long val = 0;
        for (int i = 0; i < s; i++) {
            int pos = index.get(arr[i]);
            update(fwTree, pos, 1);
            val += get(fwTree, pos-1);
        }

        long res = val;
        for (int cur = s; cur < n; cur++) {
            int prevPos = index.get(arr[cur - s]);
            update(fwTree, prevPos, -1);
            val -= get(fwTree, idx) - get(fwTree, prevPos);

            int curPos = index.get(arr[cur]);
            update(fwTree, curPos, 1);
            val += get(fwTree, curPos-1);

            res = Math.max(res, val);
        }

        System.out.print(res);
    }

    static void update(int[] fwTree, int idx, int val) {
        while (idx < fwTree.length) {
            fwTree[idx] += val;
            idx += idx & -idx;
        }
    }

    static long get(int[] fwTree, int idx) {
        long val = 0;
        while (idx != 0) {
            val += fwTree[idx];
            idx -= idx & -idx;
        }

        return val;
    }

    static int nextInt() throws IOException {
        int n = System.in.read() & 15, c;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}