// 69088KB	592ms
import java.io.*;
import java.util.*;

/**
 * (난이도, id) 로 정렬되어야 한다.
 * recommend 로 인해 알고리즘 분류별로 (난이도, id) 가 정렬되어 있어야 한다. => HashMap<알고리즘, TreeSet<(난이도,id)> 자료구조를 이용
 * recommend2, 3은 알고리즘 구분 없이 전체에서 (난이도, id) 정렬이 필요하다 => TreeSet<(난이도,id)>
 * solved 에서 id로 노드를 특정할 수 있어야 한다. => HashMap<id, (알고리즘, 난이도)>
 * 
 * (난이도, id) 노드는 중복되어 저장되므로 객체를 한개만 생성하고 링크를 2곳에서 하여 메모리를 절약할 수 있다.
 */
public class Main {
    static class Node implements Comparable<Node> {
        int id, level;
        Node (int id, int level) {
            this.id = id;
            this.level = level;
        }
        @Override
        public int compareTo(Node o) {
            if (this.level == o.level) {
                return Integer.compare(this.id, o.id);
            }
            return Integer.compare(this.level, o.level);
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;
            Node node = (Node) o;
            return this.id == node.id && this.level == node.level;
        }
        @Override
        public int hashCode() {
            return Objects.hash(id, level);
        }
    }
    static class Info {
        int level, category;
        Info (int level, int category) {
            this.level = level;
            this.category = category;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Info)) return false;
            Info info = (Info) o;
            return this.category == info.category && this.level == info.level;
        }
        @Override
        public int hashCode() {
            return Objects.hash(category, level);
        }
    }
    static HashMap<Integer, TreeSet<Node>> map = new HashMap<>();
    static TreeSet<Node> sortedSet = new TreeSet<>();
    static HashMap<Integer, Info> idMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            add(p, l, g);
        }
        int m = Integer.parseInt(br.readLine());
        for (int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            int result = 0;
            switch (cmd) {
                case "recommend":
                    result = recommend(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                    sb.append(result).append("\n");
                    break;
                case "recommend2":
                    result = recommend2(Integer.parseInt(st.nextToken()));
                    sb.append(result).append("\n");
                    break;
                case "recommend3":
                    result = recommend3(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                    sb.append(result).append("\n");
                    break;
                case "add":
                    add(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                    break;
                case "solved":
                    solved(Integer.parseInt(st.nextToken()));
                    break;
            }
        }
        System.out.print(sb);
    }

    static void add(int p, int l, int g) {
        Node node = new Node(p, l);
        // 1. 알고리즘 분류에 추가
        if (map.get(g) == null) {
            map.put(g, new TreeSet<>());
        }
        map.get(g).add(node);
        // 2. 난이도 정렬 set에 추가
        sortedSet.add(node);
        // 3. id 맵에 추가
        idMap.put(p, new Info(l, g));
    }

    static void solved(int p) {
        Info info = idMap.get(p);
        Node node = new Node(p, info.level);
        // 1. 알고리즘 분류에서 제거
        map.get(info.category).remove(node);
        // 2. 난이도 정렬 set에서 제거
        sortedSet.remove(node);
        // 3. id 맵에서 삭제
        idMap.remove(p);
    }

    static int recommend(int group, int op) {
        if (op == 1) {
            return map.get(group).last().id;
        }
        else {
            return map.get(group).first().id;
        }
    }

    static int recommend2(int op) {
        if (op == 1) {
            return sortedSet.last().id;
        }
        else {
            return sortedSet.first().id;
        }
    }

    static int recommend3(int op, int level) {
        if (op == 1) {
            Node node = null;
            if ((node = sortedSet.ceiling(new Node(0, level))) != null) {
                return node.id;
            }
            else return -1;
        }
        else {
            Node node = null;
            if ((node = sortedSet.lower(new Node(0, level))) != null) {
                return node.id;
            }
            else return -1;
        }
    }
}
