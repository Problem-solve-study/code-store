// 18540 KB, 192 ms
/*
 * 한 단어가 다른 단어의 접두사가 되지 않는 집합의 최대 크기
 * 길이가 긴 순서대로 정렬해 접두사인지 판별하고 삽입.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] inStr = new String[N];
        for (int i = 0; i < N; i++)
            inStr[i] = br.readLine();
        // 문자열 길이 순서 내림차순 정렬
        Arrays.sort(inStr, (o1, o2) -> o2.length() - o1.length());

        // 트라이에 문자열 삽입
        int cnt = 0;
        Trie trie = new Trie();
        for (int i = 0; i < N; i++) {
            boolean isExist = trie.search(inStr[i]);
            if (isExist)
                continue;
            trie.insert(inStr[i]);
            cnt++;
        }
        System.out.println(cnt);
    }

    static class Trie {
        Node root = new Node();

        void insert(String str) {
            Node node = root;
            int len = str.length(), idx;
            for (int i = 0; i < len; i++) {
                idx = str.charAt(i) - 'a';
                if (node.child[idx] == null)
                    node.child[idx] = new Node();
                node = node.child[idx];
            }
            node.endOfWord = true;
        }

        boolean search(String str) {
            Node node = root;
            int len = str.length(), idx;
            for (int i = 0; i < len; i++) {
                idx = str.charAt(i) - 'a';
                if (node.child[idx] == null)
                    return false;
                node = node.child[idx];
            }
            return true;
        }
    }

    static class Node {
        Node[] child = new Node[26];
        boolean endOfWord;
    }
}
