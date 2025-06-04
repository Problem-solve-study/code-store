import java.io.*;
import java.util.*;

/*
233728KB, 1976ms

트리를 만들려고 트라맵으로 이리저리 하다보니 위상정렬스럽게 되어버림
자신의 직계 후손을 알아야하니 위상정렬을 수행하여 직계 후손만 남겨버린 후
출력하면 됨
 */

public class Main {
    static TreeMap<String, Node> map = new TreeMap<>();
    static class Node implements Comparable<Node> {
        String name;
        TreeSet<Node> parents = new TreeSet<>();
        TreeSet<Node> children = new TreeSet<>();

        public Node(String name) {
            this.name = name;
        }

        @Override
        public int compareTo(Node o) {
            return name.compareTo(o.name);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            String name = st.nextToken();
            map.put(name, new Node(name));
        }

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            String[] input = br.readLine().split(" ");
            Node child = map.get(input[0]);
            Node parent = map.get(input[1]);

            child.parents.add(parent);
            parent.children.add(child);
        }

        ArrayDeque<Node> s = new ArrayDeque<>();
        for (Node n : map.values()) {
            if (n.parents.isEmpty()) {
                s.add(n);
            }
        }

        //루트를 찾음
        StringBuilder rootSb = new StringBuilder();
        rootSb.append(s.size()).append('\n');
        for (Node root : s) {
            rootSb.append(root.name).append(' ');
        }
        rootSb.append('\n');

        //스택에서 노드들을 꺼낸 후
        while (!s.isEmpty()) {
            Node cur = s.removeLast();
            ArrayList<Node> removeTargets = new ArrayList<>();
            //인엣지들을 지워줌
            for (Node c : cur.children) {
                c.parents.remove(cur);
                if (c.parents.isEmpty()) {
                    //인엣지가 없다면 스택에 다시 넣고
                    s.addLast(c);
                } else {
                    //있다면 해당 자식은 직계 자식이 아니므로 제거
                    removeTargets.add(c);
                }
            }
            removeTargets.forEach(cur.children::remove);
        }

        //문제에서 요구한대로 출력하기
        StringBuilder sb = new StringBuilder();
        for (Node n : map.values()) {
            sb.append(n.name).append(' ').append(n.children.size()).append(' ');
            for (Node c : n.children) {
                sb.append(c.name).append(' ');
            }
            sb.append('\n');
        }

        System.out.print(rootSb);
        System.out.print(sb);
    }
}
