// 	17936KB	228ms
import java.io.*;
import java.util.*;
/*
 * stack을 통해 (, [가 입력될 때 add하고 ), ]가 입력될 때 pop을 한 다음 값을 비교하는 형태로 구현.
 * 마지막 글자까지 탐색했을 때 stack이 비어 있지 않다면 false 반환.
 */
public class Main{
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    while(true){
      String sentence = br.readLine();
      if(sentence.equals(".")) break;
      char[] arr = sentence.toCharArray();

      boolean balance = true;
      Stack<Character> stack = new Stack<>();
      for(char c:arr){
        if(c=='(' || c=='['){
          stack.add(c);
        }
        else if(c==')'){
          if(stack.isEmpty() || stack.pop()!='('){
            balance= false;
            break;
          }
        }
        else if(c==']'){
          if(stack.isEmpty() || stack.pop()!='['){
            balance = false;
            break;
          }
        }
      }
      if(!stack.isEmpty()) balance=false;
      if(balance) System.out.println("yes");
      else System.out.println("no");
    }
  }
}

