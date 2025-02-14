//제출번호: 90050066
//메모리:   157484 KB
//실행시간: 756 ms

import java.io.*;
import java.util.*;

//사촌을 알기 위해서는 부모, 조부모의 노드가 누구인지 알아야 했음
//부모 정보는 전체 배열 중 부모의 위치를 기록함
//연속된 배열은 같은 부모이기 때문에 연속되지 않은 숫자가 나오면 부모 위치 + 1을 함으로써
//다음 숫자를 부모로 가리키게 만듦
//사촌의 수는 부모가 다르면서 조부모가 같은 노드의 개수
public class Main {

    static int[] parent = new int[1001], d = new int[1001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;
        while (true) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            if (n == 0 && k == 0) {
                break;
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                d[i] = Integer.parseInt(st.nextToken());
            }
            int prevNum = d[0];
            int nodeParent = -1;
            int targetParent = -1;

            parent[0] = -1; //0번째 인덱스의 부모는 없음
            for (int i = 1; i < n; i++) {
                if (prevNum + 1 < d[i]) { //연속되지 않은 숫자가 나오면
                    nodeParent++; //부모가 달라지므로 1 증가시킴
                }
                parent[i] = nodeParent; //i번째 숫자의 부모를 저장하고
                prevNum = d[i]; //연속을 확인하기 위해 이전 숫자도 저장함

                if (d[i] == k) { //만약 사촌을 찾아야 하는 수라면 
                    targetParent = parent[i]; //k의 부모 인덱스가 누구인지 기록함
                }
            }

            //targetParent가 -1이면 루트 노드가 k인거고
            //targetParent가 0이면 루트 노드의 자식인거니까
            //두 경우 모두 사촌이 없음
            if (targetParent <= 0) {
                bw.append("0\n");
                continue;
            }

            //k의 조부모 노드는 항상 존재하므로 조부모 노드 인덱스를 저장함
            int targetGrandParent = parent[targetParent];

            int sibling = 0;
            for (int i = 1; i < n; i++) {
                //만약 부모가 다르면서, 조부모가 같으면 사촌인 관계이므로 사촌의 수를 1 더함
                if (targetParent != parent[i] && targetGrandParent == parent[parent[i]]) {
                    sibling++;
                }
            }

            bw.append(String.format("%d\n", sibling));
        }

        bw.flush();
    }
}

/*
//제출번호: 90051620
//메모리:   23888 KB
//실행시간: 416 ms

//메모리를 줄이기 위해 StringTokenizer + Integer.parseInt를 직접 구현.
//buffer와 pos, size를 이용해서 데이터를 받고 int로 파싱해서 넘겨줌
import java.io.*;
import java.util.*;

public class Main {

    static int[] parent = new int[1001], d = new int[1001];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static char[] buf = new char[8192];
    static int pos = 0;
    static int size = 0;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            int n = nextInt();
            int k = nextInt();

            if (n == 0 && k == 0) {
                break;
            }

            for (int i = 0; i < n; i++) {
                d[i] = nextInt();
            }

            int prevNum = d[0];
            int nodeParent = -1;
            int targetParent = -1;

            parent[0] = -1;
            for (int i = 1; i < n; i++) {
                if (prevNum + 1 < d[i]) {
                    nodeParent++;
                }
                parent[i] = nodeParent;
                prevNum = d[i];

                if (d[i] == k) {
                    targetParent = parent[i];
                }
            }

            if (targetParent <= 0) {
                bw.append("0\n");
                continue;
            }

            int targetGrandParent = parent[targetParent];

            int sibling = 0;
            for (int i = 1; i < n; i++) {
                if (targetParent != parent[i] && targetGrandParent == parent[parent[i]]) {
                    sibling++;
                }
            }

            bw.append(String.format("%d\n", sibling));
        }

        bw.flush();
    }

    static int nextInt() throws IOException {
        if (pos == size) {
            updateBuf();
            if (size == -1) {
                return 0;
            }
        }

        while (buf[pos] < '0' || '9' < buf[pos]) {
            pos++;
            if (pos == size) {
                updateBuf();
                if (size == -1) {
                    return 0;
                }
            }
        }

        int res = 0;

        while ('0' <= buf[pos] && buf[pos] <= '9') {
            res = res * 10 + (buf[pos++] - '0');
            if (pos == size) {
                updateBuf();    
                if (size == -1) {
                    return res;
                }
            }
        }

        return res;
    }

    static void updateBuf() throws IOException {
        size = br.read(buf);
        pos = 0;
    }
}
 */