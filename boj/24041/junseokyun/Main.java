
//312512KB 1984ms
import java.util.*;
import java.io.*;

public class Main {
    static int N, G, K; // 재료의 수, 최대 세균, 뺄수있는 재료의 수
    static List<Node> list;

    static class Node {
        int S, L, O; // 부패속도, 유통기한, 중요도(0 or 1)
        long score;

        Node(int S, int L, int O, long score) {
            this.S = S;
            this.L = L;
            this.O = O;
            this.score = score;
        }

        public Long getScore(long day) {
            long score = 0;
            score = (long) this.S * Math.max(1, day - this.L);
            return score;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            int O = Integer.parseInt(st.nextToken());
            list.add(new Node(S, L, O, 0));
        }

        long left = 1;
        long right = 2 * 1000000000;
        long mid = 0;
        while (left <= right) {
            mid = (right + left) / 2;
            long germSum = 0;
            List<Long> scoreList = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                list.get(i).score = list.get(i).getScore(mid); // 부패 속도 갱신
                germSum += list.get(i).score; // 세균의 합
                if (list.get(i).O == 1) { // 중요하지 않은 재료의 부패 속도
                    scoreList.add(list.get(i).score);
                }
            }
            if (germSum > G) { // 세균의 수가 현재 날짜기준으로 임계치보다 높을 때
                // 내림차순으로 정렬 => 부패속도가 큰값을 전체 세균 수에서 K개만큼 뺀다
                Collections.sort(scoreList, Collections.reverseOrder());
                // 제거해야 하는 재료의 수가 저장되어있는 값보다 많다면 저장되어 있는 값들을 전체 세균수에서 다 뺀다
                if (scoreList.size() < K) {
                    for (long i : scoreList) {
                        germSum -= i;
                    }
                } else { // K개보다 많다면
                    for (int i = 0; i < K; i++) {
                        germSum -= scoreList.get(i);
                    }
                }
                // 재료를 제거했는대도 전체 세균수가 임계치 보다 높다면 날짜를 줄인다.
                if (germSum <= G) {
                    left = mid + 1;
                } else if (germSum > G) { // 전체 세균수가 임계치 보다 낮다면 날짜를 다시 올린다.
                    right = mid - 1;
                }
            } else if (germSum <= G) { // 전체 세균수가 임계치 보다 낮다면 날짜를 다시 올린다.
                left = mid + 1;
            }
        }
        System.out.println(right);
    }
}