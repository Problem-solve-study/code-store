//문제: 12779번 상품 is 뭔들
//메모리: 11496 KB
//시간: 68 ms

/*
 * 약수의 개수는 소인수분해를 한 후 각 소인수의 지수에 1을 더한 값들을 곱하여 구한다.
 * 약수의 개수가 홀수이기 위해서는 모든 소인수의 지수가 짝수여야 한다.
 * 소인수의 지수가 모두 짝수여야 1을 더한 값이 모두 홀수가 되어 곱이 홀수가 되기때문이다.
 * 소인수의 지수가 모두 짝수라면 제곱근을 구했을 때 정수가 나와야한다.
 * 제곱근을 구하는 것은 모든 소인수의 지수를 2로 나누는 것과 같기 때문이다.
 * 즉, 약수의 개수가 홀수인 수는 제곱수다.
 * 문제에서 구해야 하는 것은 결국 범위내의 제곱수의 개수이다.
 * (a,b] 구간 내의 제곱수의 개수를 구하기 위해서는
 * a 보다 큰 최소의 제곱수(이하 최소 제곱수)와 b 보다 작거나 같은 최대의 제곱수(이하 최대 제곱수)를 구하면 된다.
 * 최소 제곱수는 (ceil(sqrt(a+1)))^2으로 구할 수 있고 최대 제곱수는 (floor(sqrt(b)))^2으로 구할 수 있다.
 * [최소 제곱수, 최대 제곱수] 범위의 제곱수의 개수는 각각의 제곱근(이하 최솟값, 최댓값)으로 이루어진 닫힌 구간내의 정수 개수와 동일하다.
 * 즉, [최솟값, 최댓값] 내의 정수의 개수이므로 최댓값 - 최솟값 + 1로 구할 수 있다.
 * 이 값이 분자가 된다.
 * 분모는 (a,b] 범위 내의 정수의 개수가 되므로 b - a가 된다.
 * 기약 분수로 나타내기 위해 gcd를 구하고 각각을 gcd로 나누어 출력한다.
 * 만약, 분자가 0이라면 그냥 0 으로 출력한다. (문제의 조건)
 */

public class Main {
    public static void main(String[] args) throws Exception{
        long a = nextLong(), b = nextLong();
        long total = b - a;
        double min = Math.ceil(Math.sqrt(a+1)), max = Math.floor(Math.sqrt(b));
        long count = (long)(max - min + 1);
        StringBuilder sb = new StringBuilder();
        if(count==0){
            sb.append('0');
        }else{
            long gcd = GCD(count, total);
            sb.append(count/gcd).append('/').append(total/gcd);
        }
        System.out.println(sb);
    }
    static long GCD(long a, long b){
        long n;
        while(b!=0){
            n = a%b;
            a = b;
            b = n;
        }
        return a;
    }
    static long nextLong() throws Exception{
        long n,c;
        while((c=System.in.read())<48);
        n = c&15;
        while((c=System.in.read())>47)
            n = (n<<3) + (n<<1) + (c&15);
        return n;
    }
}
