// 96504KB 388ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/** 
 * 동적 누적합 -> 세그트리
 * x가 20억이면 long으로 받아야 할 듯
 */
class Main {
    static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        int n = nextInt();
        int q = nextInt();

        Node root = new Node(1, n);

        for (int i = 0; i < q; i++) {
            int op = nextInt();
            if (op == 1) {
                int date = nextInt();
                int amount = nextInt();
                root.update(date, amount);
                continue;
            }

            int start = nextInt();
            int end = nextInt();
            long result = root.search(start, end);
            sb.append(result).append('\n');
        }

        System.out.print(sb);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}

class Node {
    long value;
    int start;
    int end;
    Node leftChild;
    Node rightChild;

    Node(int start, int end) {
        this.start = start;
        this.end = end;

        if (start == end) {
            return;
        }

        int mid = (start + end) / 2;
        this.leftChild = new Node(start, mid);
        this.rightChild = new Node(mid + 1, end);
    }

    void update(int target, int amount) {
        this.value += amount;

        if (this.start == this.end) {
            return;
        }

        if (target <= this.leftChild.end) {
            this.leftChild.update(target, amount);
            return;
        }

        this.rightChild.update(target, amount);
    }

    long search(int start, int end) {
        if (this.start == start && this.end == end) {
            return this.value;
        }

        if (this.leftChild.end >= end) {
            return this.leftChild.search(start, end);
        }

        if (this.rightChild.start <= start) {
            return this.rightChild.search(start, end);
        }

        return this.leftChild.search(start, leftChild.end) + this.rightChild.search(rightChild.start, end);
    }
}
