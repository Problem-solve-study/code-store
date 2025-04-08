import java.io.*;
import java.util.*;
/*
 * 모듈러 연산의 이해도가 높아야 풀 수 있었던 문제.
 * 스택을 사용한 분할 정복 연산을 통해 연산 수를 최소화.
 * 제곱 연산 중에 값이 터지는 걸 고려해서 모든 연산값에 모듈러 연산을 수행함.
 */
public class Main {
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());

      long answer = 1;
      Stack<Integer> stack = new Stack<>();
      while(b>=1){
        if(b%2==1){ 
          stack.push(1);
          b--;
        }else{
          stack.push(2);
          b/=2;
        }
      }

      while(!stack.empty()){
        answer %= c;
        if(stack.pop()==2) answer = (int)answer * answer % c;
        else answer = answer * a % c ;
      }
      System.out.println(answer);
    }
}
