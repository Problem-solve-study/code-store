// 105276KB 896ms

import java.io.*;
import java.util.*;

/**
 * 그리디
 * 
 * 기본적으로 기관총을 사용하되, 바로 앞의 좀비가 기관총으로 안 죽어서 지뢰를 사용해야 할 때만 지뢰 사용
 * 바로 앞의 좀비가 기관총으로 안 죽는데 지뢰가 없으면 NO, 아니면 진행
 * 끝까지 진행되면 YES
 * 
 * 기관총으로 입히는 데미지의 양 = 기관총 대미지 * (기관총 사거리 - 사거리 내에서 앞에서 사용한 폭탄 개수)
 */
class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    
    public static void main(String[] args) throws IOException {
        int L = next();
        int gunLength = next();
        int gunDamage = next();
        int bombCount = next();

        int[] zombies = new int[L];
        for (int i = 0; i < L; i++) {
            zombies[i] = next();
        }

        int usedBombCount = 0;      // 현재까지 사용한 지뢰 총 개수
        Deque<Integer> usedBombPartition = new ArrayDeque<>();  // 기관총 사거리 내에서 사용한 지뢰의 위치들
        for (int i = 0; i < L; i++) {
            // 지뢰 사용 위치가 기관총 사거리에서 벗어나면 제외
            if (usedBombPartition.peek() != null && usedBombPartition.peek() < i - gunLength) {
                usedBombPartition.poll();
            }
            
            // 대미지 = 기관총 대미지 * (기관총 사거리 - 사거리 내에서 앞에서 사용한 폭탄 개수)
            int damageSum = (Math.min(i + 1, gunLength) - usedBombPartition.size()) * gunDamage;
            if (zombies[i] <= damageSum) {
                continue;
            }

            // 좀비를 기관총으로 죽이지 못하는 경우, 지뢰 사용
            usedBombCount++;
            usedBombPartition.add(i);
            if (usedBombCount > bombCount) {    // 사용할 지뢰가 없으면 종료
                System.out.print("NO");
                return;
            }

        }

        System.out.print("YES");
    }

    static int next() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
