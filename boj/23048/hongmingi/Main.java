// 99352KB	2260ms
import java.io.*;
import java.util.*;
/*
 * 에라토스테네스의 체 활용해서 소수를 구하면 그만큼 사용한 최소 색의 수를 구할 수 있다.
 * 하지만 다시 보니 세상 비효율적으로 구현해놨다.
 * 각 숫자마다 소수인지 아닌지 확인하면서 O(n^2)만큼 완탐을 조졌는데
 * 그럴 필요 없이 2부터 차례차례 소수들의 배수는 소수 아닌걸로 판단하고 소수랑 같은 숫자로 만들어버리면
 * 훨씬 탐색 수가 줄어드는데 생각을 안하고 구현을 했다.
 * 근데 2260ms가 통과되는게 더 신기하긴 하다.
 */
public class Main{
        public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N+1];
        arr[1] = 1;
        int cnt = 1;
        loop1: for(int i=2; i<=N; i++){
            for(int j=2; j<=Math.sqrt(i); j++){
                if(i%j==0){
                    arr[i] = arr[j];
                    continue loop1;
                }
            }
            cnt++;
            arr[i] = cnt;
        }

        System.out.println(cnt);
        for(int i=1; i<=N; i++){
            System.out.print(arr[i]+" ");
        }
    }
}