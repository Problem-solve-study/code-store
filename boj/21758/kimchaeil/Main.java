//문제: 21758번 꿀 따기
//메모리: 12924 KB
//시간: 104 ms

/*
 * 벌통을 기준으로 2마리의 벌이 모두 왼쪽, 모두 오른쪽인 경우와 벌통이 두 벌 사이에 있는 경우로 총 3가지로 나누어서 생각한다.
 * 벌통이 두 벌 사이에 있는 경우는 벌이 양 끝에 있고 양 끝을 제외한 나머지 중 최댓값인 곳에 벌통이 있는 경우가 최대
 * 벌이 모두 왼쪽에 있는 경우는 벌통이 가장 오른쪽에 있고, 벌 중 하나는 가장 왼쪽에 있을 때가 최대이므로 나머지 벌의 위치만 찾으면 된다.
 * 벌이 모두 오른쪽에 있는 경우는 좌우만 바뀌었을 뿐, 모두 왼쪽에 있는 경우와 동일하다.
 * 위 3가지 경우에 대해 각각 최대를 구하고 셋 중 최댓값이 답이다.
 */

public class Main {
    public static void main(String[] args) throws Exception{
        int n = nextInt(), limit = n-1;
        int[] arr = new int[n], sum = new int[n];
        int maxval=0;
        arr[0]=sum[0]=nextInt();
        for(int i=1;i<limit;i++){
            sum[i]=sum[i-1]+(arr[i]=nextInt());
            maxval = Math.max(arr[i], maxval);
        }
        sum[n-1]=sum[n-2]+(arr[n-1]=nextInt());
        int result = sum[n-2] - arr[0] + maxval;
        int left = sum[n-2], right = sum[limit]-arr[0];
        int lmax = Integer.MIN_VALUE, rmax = Integer.MIN_VALUE;
        for(int i = 1;i<limit;i++){
            lmax = Math.max(lmax, sum[i-1]-arr[i]);
            rmax = Math.max(rmax, sum[limit]-sum[i]-arr[i]);
        }
        System.out.println(Math.max(result, Math.max(left+lmax,right+rmax)));
    }
    static int nextInt() throws Exception{
        int n,c;
        while((c=System.in.read())<48);
        n = c&15;
        while((c=System.in.read())>47)
            n = (n<<3) + (n<<1) + (c&15);
        return n;
    }
}
