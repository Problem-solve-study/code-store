// 	20380KB	216ms
import java.io.*;
import java.util.*;

/**
 * 입력 문자열을 거꾸로 뒤집은 뒤에
 * trie로 try해봤습니다
 */
public class Main {

    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isEndOfWord = false;
    }

    static class Trie {
        TrieNode root = new TrieNode();
        Set<String> cheeseSet = new HashSet<>();

        void insert(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                node = node.children.computeIfAbsent(c, k -> new TrieNode());
            }
            node.isEndOfWord = true;
            cheeseSet.add(word);
        }

        int countCheeseToppings(String suffix) {
            TrieNode node = root;
            for (char c : suffix.toCharArray()) {
                if (!node.children.containsKey(c)) {
                    return 0;
                }
                node = node.children.get(c);
            }
            Set<String> results = new HashSet<>();
            dfs(node, new StringBuilder(suffix), results);
            return results.size();
        }

        private void dfs(TrieNode node, StringBuilder path, Set<String> result) {
            if (node.isEndOfWord) {
                result.add(path.toString());
            }
            for (char c : node.children.keySet()) {
                path.append(c);
                dfs(node.children.get(c), path, result);
                path.deleteCharAt(path.length() - 1);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Trie trie = new Trie();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            String topping = st.nextToken();
            String reversed = new StringBuilder(topping).reverse().toString();
            trie.insert(reversed);
        }

        String reversedCheese = new StringBuilder("Cheese").reverse().toString();
        int cheeseCount = trie.countCheeseToppings(reversedCheese);

        if (cheeseCount >= 4) {
            System.out.println("yummy");
        } else {
            System.out.println("sad");
        }
    }
}
