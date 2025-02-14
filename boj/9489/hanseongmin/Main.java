import java.io.*;
import java.util.*;

/*
212972KB, 1224ms

트리를 만들고 조부모가 같으면서 부모가 다른 노드의 개수를 세면 된다고 생각
메모리 제한이 빡빡해서 Node에서 children 레퍼런스를 유지하면 메모리가 터지는듯
부모 레퍼런스만 유지하며 모든 노드를 순회하여 카운팅하도록 함.
 */

public class Main {
    static class Node {
        int num;
        Node parent;

        Node(int n, Node p) {
            num = n;
            parent = p;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = -1;
        int k = -1;

        StringBuilder sb = new StringBuilder();
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            //프로그램 종료 조건
            if (n == 0 && k == 0)
                break;

            //노드의 배열을 생성
            Node[] nodes = new Node[n];
            st = new StringTokenizer(br.readLine());
            //현재 입력받는 연속된 수의 부모 인덱스를 저장
            int parentIdx = -1;
            //k가 어떤 인덱스인지를 저장
            int targetIdx = 0;
            //연속된 수인지 아닌지를 판별하기 위한 변수
            int prev = Integer.parseInt(st.nextToken());
            //맨 처음 수의 경우 반드시 루트이니 루프 밖에서 먼저 할당
            nodes[0] = new Node(prev, null);
            for (int i = 1; i < n; i++) {
                int cur = Integer.parseInt(st.nextToken());
                if (cur == k) 
                    targetIdx = i;
                //연속된 수가 아니라면 부모 인덱스 1 증가
                if (cur - prev > 1) 
                    parentIdx++;

                nodes[i] = new Node(cur, nodes[parentIdx]);
                prev = cur;
            }

            //부모나 조부모가 없으면 답은 0
            if (nodes[targetIdx].parent == null || nodes[targetIdx].parent.parent == null) {
                sb.append(0);
            } else {
                int res = 0;
                Node grandparent = nodes[targetIdx].parent.parent;
                Node parent = nodes[targetIdx].parent;

                for (Node node : nodes) {
                    if (node.parent == null || node.parent.parent == null)
                        continue;

                    //모든 노드를 순회하며 조부모는 같으면서 부모는 다른 노드의 개수를 카운팅
                    if (node.parent.num != parent.num && node.parent.parent.num == grandparent.num) {
                        res++;
                    }
                }
                sb.append(res);
            }
            sb.append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
    }
}