// 30936 KB, 204 ms
/*
 * trie 자료구조 사용해 탐색
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) throws IOException {
        Trie trie = new Trie();
        TreeSet<String> res = new TreeSet<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]), M = Integer.parseInt(input[1]);

        // 듣도 못한 사람 입력
        for (int i = 0; i < N; i++)
            trie.insert(br.readLine());

        // 보도 못한 사람 입력, 듣도 못한 사람에 있으면 사전순 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            String str = br.readLine();
            if (trie.search(str))
                res.add(str);
        }
        sb.append(res.size()).append('\n');
        while (!res.isEmpty())
            sb.append(res.pollFirst()).append('\n');

        System.out.println(sb.toString());
    }

    static class Trie {
        Node root = new Node();

        void insert(String str) {
            Node node = root;
            int len = str.length(), idx;
            for (int i = 0; i < len; i++) {
                idx = str.charAt(i) - 'a';
                if (node.link[idx] == null)
                    node.link[idx] = new Node();
                node = node.link[idx];
            }
            node.endOfWord = true;
        }

        boolean search(String str) {
            Node node = root;
            int len = str.length(), idx;
            for (int i = 0; i < len; i++) {
                idx = str.charAt(i) - 'a';
                if (node.link[idx] == null)
                    return false;
                node = node.link[idx];
            }
            return node.endOfWord;
        }
    }

    static class Node {
        boolean endOfWord;
        Node[] link = new Node[26];
    }
}
