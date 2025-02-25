//14236KB	104ms
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Integer.parseInt(br.readLine());
        //1에서 9까지 중에 어떤 수가 있는지 boolean 배열을 통해 체크크
        boolean[] nums = new boolean[10];
        long K = N;
        while(K>0){
            nums[(int)K%10] = true;
            K/=10;
        }
        
        boolean isDivide = false;
        long num = N;
        //N이 바로 모든 수로 나누어떨어지면 그대로 출력
        if(check(nums, num)){
            System.out.println(num);
            return;
        }

        //아닌 경우 cnt를 통해 *10을 하면서 범위안에 나누어떨어지는 숫자가 있는지 확인.
        int cnt = 0;
        while(!isDivide){
            cnt++;
            num*=10;
            long temp;
            for(int i=0; i<Math.pow(10, cnt); i++){
                temp = num+i;
                isDivide = check(nums, temp);
                if(isDivide){
                    System.out.println(temp);
                    return;
                }
            }
        }
        
    }
    
    // 숫자로 나누어떨어지는지 체크하는 메소드
    static boolean check(boolean[] nums, long num){
        boolean c = true;
        for(int i=1; i<10; i++){
            if(!nums[i]) continue;
            if(num%i!=0){
                c=false;
                break;
            } 
        }
        return c;
    }
}
