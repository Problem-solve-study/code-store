// 24692KB 328ms

import java.util.*;
import java.io.*;

/**
 * 이분탐색
 * 
 * S의 길이가 백만, T의 길이가 10만
 * 
 * S는 못 줄일 것 같고... T를 줄여야 할 것 같은데
 * S가 ab일 때, T에서 a 다음에 오는 b의 위치를 O(1)로 알 수 없나?
 * = b의 위치값 중에 a의 위치값보다 느린 애가 있으면 유지
 * = b의 위치값 중에 a의 위치값보다 느린 애가 없으면 새 문장 추가 & 가장 빠른 a가 현재 커서
 * 
 * 현재 위치값보다 큰데 가장 작은 수가 다음 커서 위치
 * -> 이분탐색으로 log_2(100,000)로 탐색
 */
class Main {
    static final int SIZE = 26;
    static List<List<Integer>> indexes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        String t = br.readLine();

        indexes = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            indexes.add(new ArrayList<>());
        }
        for (int i = 0; i < t.length(); i++) {
            indexes.get(t.charAt(i) - 'a').add(i);
        }
        
        int index = -1;
        int count = 1;
        for (int i = 0; i < s.length(); i++) {
            int targetId = s.charAt(i) - 'a';

            int size = indexes.get(targetId).size();
            if (size == 0) {
                System.out.println(-1);
                return;
            }

            if (indexes.get(targetId).get(size - 1) <= index) {
                index = indexes.get(targetId).get(0);
                count++;
                continue;
            }

            index = findLowerBound(targetId, 0, size - 1, index);
        }

        System.out.println(count);        
    }

    // bound보다 큰 값 중에 가장 작은 값을 반환
    static int findLowerBound(int target, int left, int right, int bound) {
        if (left >= right) {
            return indexes.get(target).get(left);
        }

        int mid = (left + right) / 2;
        if (indexes.get(target).get(mid) <= bound) {
            return findLowerBound(target, mid + 1, right, bound);
        }
        return findLowerBound(target, left, mid, bound);
    }
}
