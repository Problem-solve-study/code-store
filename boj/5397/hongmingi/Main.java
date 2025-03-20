// 134720KB	868ms
import java.io.*;
import java.util.*;
/*
 * stack 2개를 이용해서 커서 앞, 뒤의 문자들을 비교하는 형태로 구현.
 */
public class Main{
    static int N, M;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Stack<Character> st1, st2;
        StringBuilder sb;

        for(int t=0; t<N; t++){
            st1 = new Stack<>(); // 기본적으로 입력될 때의 커서 앞 문자들이 저장되는 스택
            st2 = new Stack<>(); // < 입력 시 커서 뒤의 문자들이 저장되는 스택
            sb = new StringBuilder();
            char[] L = br.readLine().toCharArray();
            char c;
            for(int i=0; i<L.length; i++){
                c = L[i];
                if(c=='<'){
                    if(!st1.isEmpty()){
                        st2.push(st1.pop());
                    }
                }else if(c=='>'){
                    if(!st2.isEmpty()){
                        st1.push(st2.pop());
                    }
                }else if(c=='-'){
                    if(!st1.isEmpty()){
                        st1.pop();
                    }
                }
                else{
                    st1.add(c);
                }
            }
            while(!st2.isEmpty()){
                st1.add(st2.pop());
            }
            for(char s:st1){
                sb.append(s);
            }
            System.out.println(sb);
        }
    }
}