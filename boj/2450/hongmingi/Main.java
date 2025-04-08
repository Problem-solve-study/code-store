// 23492KB	184ms
import java.io.*;
import java.util.*;
/*
 * 개어려웠다.
 * 도형 종류가 3개밖에 안되기 때문에 나올 수 있는 모든 순열의 경우의 수를 계산한 다음 이를 비교하는 형태로 구현.
 * 다른 게 아니라 최소 스왑 카운트 계산이 이해가 안되서 드럽게 오래걸렸음.
 * 처음에는 스왑 카운트를 기존 순서와 순열 순서를 비교해서 전체 다른 cnt의 cnt/2+cnt%2로 구했는데 이게
 * 틀렸다는 거임. 아직도 반례를 모름. 아는사람 있으면 말좀.
 * 그래서 순열에서 첫번째 도형과 다른 cnt를 세고 두번째, 세번째 도형은 첫번째 도형과 바뀐 거 말고 
 * 다른 개수를 센 다음 그중 더 큰 값을 세어서 cnt를 더하는 형태로 구현함.
 * 그렇게 6개 순열 중 최소 카운트인 개수를 반환.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        int[][] perms = {{1, 2, 3}, {1, 3, 2}, {2, 1, 3}, {2, 3, 1}, {3, 1, 2}, {3, 2, 1}};
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] polygonIdx = new int[4];
        for(int i=0; i<n; i++) polygonIdx[arr[i] = Integer.parseInt(st.nextToken())]++;
        int minCnt = Integer.MAX_VALUE;
        int cnt;
        for(int[] perm :perms){
          int idx = 0;
          int[] swapCnt = new int[3];
          for(int i=0; i<polygonIdx[perm[0]]; i++){
            if(arr[i]!=perm[0]) swapCnt[0]++;
          }
          idx+=polygonIdx[perm[0]];
          for(int i=idx; i<idx+polygonIdx[perm[1]]; i++){
            if(arr[i]!=perm[1] && arr[i]!= perm[0]) swapCnt[1]++;
          }
          idx+=polygonIdx[perm[1]];
          for(int i=idx; i<idx+polygonIdx[perm[2]]; i++){
            if(arr[i]!=perm[0] && arr[i]!= perm[2]) swapCnt[2]++;
          }
          cnt = swapCnt[0] + Math.max(swapCnt[1], swapCnt[2]);
          minCnt = Math.min(minCnt, cnt);
        }
        
        System.out.println(minCnt);
        
    }

    static int checkDiff(int[] arr1, int[] arr2){
      int cnt = 0;
      for(int i=0; i<arr1.length; i++){
        if(arr1[i]!=arr2[i]) cnt++;
      }
      return cnt;
    }

}
