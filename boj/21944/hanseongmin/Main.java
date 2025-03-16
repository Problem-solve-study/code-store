import java.io.*;
import java.util.*;

/*
43808KB, 588ms

B형 느낌이 풀풀 풍기는 문제라서 풀기전에 어떻게 풀지 약간의 설계를 머릿속에서 하고 들어갔다.
힙으로도 구현할 수 있는것 같은데 트리셋, 맵이 먼저 떠올라서 그걸로 구현
맵 1개, 셋 2개로 구현했는데 그냥 셋3개 써도 풀렸을 것 같다.
TreeSet의 메소드를 적절히 이용하면 쉽게 풀 수 있는듯. 트리셋이 빠르니 복잡도도 크지 않다.
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int globalId;
    static int N;
    static int M;
    static StringBuilder sb = new StringBuilder();
    //난이도 분류 별 문제를 담아두는 맵. 쿼리를 해결하기 위해 선언
    static TreeMap<Integer, TreeSet<Problem>> classificationMap = new TreeMap<>();
    //문제 번호 기준 정렬하는 셋. 추후 문제를 삭제하기 위해 선언
    static TreeSet<Problem> setSortedByP = new TreeSet<>((p1, p2) -> {
        int comp1 = Integer.compare(p1.P, p2.P);
        if (comp1 != 0) return comp1;
        int comp2 = Integer.compare(p1.L, p2.L);
        if (comp2 != 0) return comp2;
        int comp3 = Integer.compare(p1.G, p2.G);
        if (comp3 != 0) return comp3;
        return Integer.compare(p1.id, p2.id);
    });
    //문제 난이도 기준 정렬하는 셋. 쿼리를 해결하기 위해 선언
    static TreeSet<Problem> setSortedByL = new TreeSet<>();

    static class Problem implements Comparable<Problem> {
        int id;
        int P;
        int L;
        int G;

        public Problem(int p, int l, int g) {
            id = globalId++;
            P = p;
            L = l;
            G = g;
        }

        @Override
        public int compareTo(Problem o) {
            int comp1 = Integer.compare(L, o.L);
            if (comp1 != 0) return comp1;
            int comp2 = Integer.compare(P, o.P);
            if (comp2 != 0) return comp2;
            int comp3 = Integer.compare(G, o.G);
            if (comp3 != 0) return comp3;
            return Integer.compare(id, o.id);
        }
    }

    public static void main(String[] args) throws Exception {
        init();
        M = nextInt();
        for (int i = 0; i < M; i++) {
            String order = nextToken();
            if (order.equals("recommend")) {
                sb.append(recommend(nextInt(), nextInt())).append('\n');
            } else if (order.equals("recommend2")) {
                sb.append(recommend2(nextInt())).append('\n');
            } else if (order.equals("recommend3")) {
                sb.append(recommend3(nextInt(), nextInt())).append('\n');
            } else if (order.equals("add")) {
                add(nextInt(), nextInt(), nextInt());
            } else { //solved
                solved(nextInt());
            }
        }
        System.out.print(sb);
    }

    static void init() throws Exception {
        N = nextInt();
        for (int i = 0; i < N; i++) {
            add(nextInt(), nextInt(), nextInt());
        }
    }

    static int recommend(int G, int x) {
        //분류 맵에서 셋을 가져오고 1, -1 여부에 따라 last, first 호출
        TreeSet<Problem> set = classificationMap.get(G);
        if (x == 1) {
            return set.last().P;
        } else {
            return set.first().P;
        }
    }

    static int recommend2(int x) {
        //전체 문제가 대상이므로 난이도 정렬 셋에서 1, -1 여부에 따라 last, first 호출
        if (x == 1) {
            return setSortedByL.last().P;
        } else {
            return setSortedByL.first().P;
        }
    }

    static int recommend3(int x, int L) {
        //문제 난이도 정렬 셋에서 적절히 이분탐색하여 출력
        Problem target;
        if (x == 1) {
            target = setSortedByL.ceiling(new Problem(0, L, 0));
        } else {
            target = setSortedByL.lower(new Problem(0, L, 0));
        }

        if (target == null) return -1;
        else return target.P;
    }

    static void add(int P, int L, int G) {
        Problem problem = new Problem(P, L, G);
        setSortedByL.add(problem);
        setSortedByP.add(problem);
        classificationMap.putIfAbsent(problem.G, new TreeSet<>());
        classificationMap.get(problem.G).add(problem);
    }

    static void solved(int P) {
        //문제를 삭제할 때는 문제 번호 기준 정렬 셋에서 tailSet으로 자신과 같거나 큰 원소들을 모두 불러오고
        //문제 번호가 P와 같은 문제들만 list에 담은 후 삭제
        ArrayList<Problem> list = new ArrayList<>();
        TreeSet<Problem> tailSet = (TreeSet<Problem>) setSortedByP.tailSet(new Problem(P, 0, 0) ,true);
        for (Problem p : tailSet) {
            if (p.P == P) {
                list.add(p);
            } else {
                break;
            }
        }

        //IntelliJ가 removeAll보다 forEach가 더 빠르다고 함.
        //removeAll에는 contains 메소드를 추가 호출하는 과정이 있어서 그런 것으로 추정
        list.forEach(setSortedByL::remove);
        list.forEach(setSortedByP::remove);
        for (Problem p : list) {
            classificationMap.get(p.G).remove(p);
        }
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }

    static String nextToken() throws Exception {
        st.nextToken();
        return st.sval;
    }
}
