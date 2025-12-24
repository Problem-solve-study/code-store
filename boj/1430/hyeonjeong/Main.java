// 12020KB 72ms

import java.util.*;
import java.io.*;

/**
 * 모든 탑들에 대해
 * 다른 탑으로 사정거리 r 이하로 이동을 반복해서, 적을 사정거리 안으로 넣을 수 있는 이동 횟수의 최소값 구하기
 * 
 * 그래프 이론? n이 작길래 n^2 완탐으로 풀었습니다.
 * 
 * Top
 * - x, y: 탑의 좌표 정보
 * - transferCount: 적을 사정거리 안으로 넣기 위한 이동 횟수. 초기값 0
 */
class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        int n = nextInt();
        int r = nextInt();
        r *= r;     // 계산상의 편의를 위해 모든 거리값을 제곱으로 사용
        int d = nextInt();
        int x = nextInt();
        int y = nextInt();

        List<Top> in = new ArrayList<>();   // 적을 사정거리 안으로 넣는 이동 횟수를 구한 탑들
        List<Top> out = new LinkedList<>(); // 적을 사정거리 안으로 넣지 못한 탑들
        for (int i = 0; i < n; i++) {
            int xi = nextInt();
            int yi = nextInt();

            int dx = Math.abs(x - xi);
            int dy = Math.abs(y - yi);

            // 적과 탑의 거리가 사정거리 안이면 in에, 밖이면 out에 추가
            if (dx * dx + dy * dy <= r) {
                in.add(new Top(xi, yi));
                continue;
            }
            out.add(new Top(xi, yi));
        }

        // 1. out을 순회하면서, in에 있는 탑들에 닿을 수 있는지 확인
        // 2. 닿을 수 있으면 out에서 빼서 in으로 이동
        // out에서 뺄 게 없을 때까지 1-2 반복
        int prevInSize = 0;     // 닿을 수 없는 탑을 다음 탐색에서 제외하기 위해, 이전에 확인한 in의 마지막 위치 기억
        int inSize = in.size();
        while (!out.isEmpty()) {
            int prevOutSize = out.size();

            Iterator<Top> outIterator = out.iterator();
            while (outIterator.hasNext()) {
                Top outTop = outIterator.next();

                for (int i = prevInSize; i < inSize; i++) {
                    int dx = Math.abs(outTop.x - in.get(i).x);
                    int dy = Math.abs(outTop.y - in.get(i).y);
                    int dist = dx * dx + dy * dy;

                    if (dist <= r) {
                        in.add(new Top(outTop.x, outTop.y, in.get(i).transferCount + 1));
                        outIterator.remove();
                        break;
                    }
                }
            }
            
            if (out.size() == prevOutSize) {
                break;
            }
            
            prevInSize = inSize;
            inSize = in.size();
        }

        // 각 탑의 이동 횟수만큼 초기값을 나눠서 더함
        double energy = 0;
        for (Top top : in) {
            energy += top.getEnergy(d);
        }

        System.out.printf("%.2f", energy);
    }

    static class Top {
        int x;
        int y;
        int transferCount;

        Top(int i, int j) {
            this.x = i;
            this.y = j;
        }

        Top(int i, int j, int transferCount) {
            this(i, j);
            this.transferCount = transferCount;
        }

        double getEnergy(int d) {
            double energy = d;
            for (int i = 0; i < transferCount; i++) {
                energy /= 2;
            }
            return energy;
        }

        @Override
        public String toString() {
            return String.format("TOP=[%d, %d, %d]", x, y, transferCount);
        }
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
