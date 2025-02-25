import java.io.*;
import java.util.*;

public class Main {
    static int n, num[], copy[];
    static int answer = Integer.MAX_VALUE;

    /**
     * 맨 앞 두 원소는 공차와의 차이가 최대 2까지 날 수 있다. (첫함 + 1, 둘째항 -1 을 하는 경우)
     * 그래서 맨 앞 두 원소의 차, 공차 d 의 차이는 +-2, +-1, 0 5가지이고, 이 5개에 대해 완탐
     * ! 주의할 점은 맨 앞 두개의 차와 공차의 차이가 1인 경우, 첫항에 +-1을 하는 경우, 둘째항에 +-1을 하는 경우 2가지가 있을 수 있다.
     * ! 또, 맨 앞에 두개의 차와 공차의 차이가 0인 경우, 그대로 두는 경우, 첫항과 둘째항에 모두 +-1 하는 경우 3가지가 있을 수 있다.
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        num = new int[n]; copy = new int[n];
        for (int i=0; i<n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        int minDiff = Integer.MAX_VALUE, maxDiff = Integer.MIN_VALUE;
        for (int i=1; i<n; i++) {
            minDiff = Math.min(minDiff, num[i-1] - num[i]);
            maxDiff = Math.max(maxDiff, num[i-1] - num[i]);
        }
        
        if (maxDiff - minDiff > 4) answer = -1;
        else if (n <= 2) answer = 0;
        else {
            for (int d : new int[] {(maxDiff + minDiff)/2,(maxDiff + minDiff)/2+1,(maxDiff + minDiff)/2-1}) { // 최대차, 최소차의 평균과 +-1에 대해 확인한다.
                int temp = simulate(d);
                if (temp != -1) answer = Math.min(answer, temp);
            }   
        }

        System.out.print(answer==Integer.MAX_VALUE ? -1 : answer);
    }

    // 원본 배열 복사
    static void copyArray() { 
        for (int i=0; i<n; i++) {
            copy[i] = num[i];
        }
    }

    static int simulate(int d) {
        copyArray();
        int count = 0, first, second, third;
        int expected = copy[0] - d; 
        if (copy[1] == expected) {
            first = calcLast(d); // 0,1번째 그대로 진행
            if (first == -1) first = Integer.MAX_VALUE;
            copyArray();

            copy[0]-=1; copy[1]-=1; // 0,1번째 둘 다 -1
            second = calcLast(d) + 2;
            if (second == 1) second = Integer.MAX_VALUE;

            copyArray();
            copy[0]+=1; copy[1]+=1; // 0,1번째 둘 다 +1
            third = calcLast(d) + 2;
            if (third == 1) third = Integer.MAX_VALUE;

            count = Math.min(first, Math.min(second, third)); // 최솟값 갱신
            if (count == Integer.MAX_VALUE) count = -1;
        }
        else if (copy[1] == expected + 1) {
            first = calcLast(d) + 1; // 0번째를 +1

            copyArray();
            copy[1] -= 1; // 1번째를 -1
            second = calcLast(d) + 1;

            // 최솟값 갱신
            if (first > 0 && second > 0) {
                count = Math.min(first, second);
            } else {
                count = (first > 0) ? first : (second > 0) ? second : -1;
            }
        }
        else if (copy[1] == expected - 1) {
            first = calcLast(d) + 1; // 0번째를 -1

            copyArray();
            copy[1] += 1; // 1번째를 +1
            second = calcLast(d) + 1;

            // 최솟값 갱신
            if (first > 0 && second > 0) {
                count = Math.min(first, second);
            } else {
                count = (first > 0) ? first : (second > 0) ? second : -1;
            }
        }
        else if (copy[1] == expected + 2) {
            copy[0] += 1;
            copy[1] -= 1;
            
            count = calcLast(d) + 2;
            if (count == 1) count = -1;
        }
        else if (copy[1] == expected - 2) {
            copy[0] -= 1;
            copy[1] += 1;

            count = calcLast(d) + 2;
            if (count == 1) count = -1;
        }
        else { count = -1; }


        return count;
    }


    // 2번째 항부터 끝까지 확인하는 함수
    static int calcLast(int d) {
        int count = 0;
        for (int i=2; i<n; i++) {
            int expected = copy[i-1] - d;
            if (copy[i] == expected) continue;
            else if (Math.abs(copy[i] - expected) == 1) {
                copy[i] = expected;
                count++;
            }            
            else {
                return -1;
            }
        }
        return count;
    }
}