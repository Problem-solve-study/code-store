import java.io.*;
/**
 * 11544KB	64ms
 * 스택 개념 (인데 파라미터 스택 넣기 싫어서 괄호 개수 세기)
 * 조건1 | '(' 여는 괄호보다 ')' 닫는 괄호가 더 많아지면 안됨.
 * 조건2 | index==N되면 '(' 개수 - ')' 개수는 0이 되어야함.
 * 조건 1, 2 지키면서 되는 경우 DFS & 백트래킹 해주기
 */
public class Main {
    static int N;
    static char[] arr;
    static String answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = br.readLine().toCharArray();
        answer = "";
        dfs(0, 0, "");
        System.out.println(answer);
    }
    // 값 찾으면 return true 해서 조기 종료
    static boolean dfs(int index, int cnt, String str){ // DFS with 백트래킹
        if(index == N){
            if(cnt == 0){
                answer = str;
                return true;
            }
            return false;
        }

        if(arr[index] == '('){
            if(dfs(index + 1, cnt + 1, str + '('))
                return true;
        } else if(arr[index] == ')'){
            if(cnt > 0 && dfs(index + 1, cnt - 1, str + ')'))
                return true;
        } else { // G 고추장 묻음
            if(cnt > 0 && dfs(index + 1, cnt - 1, str + ')')) return true;
            if(dfs(index + 1, cnt + 1, str + '(')) return true;
        }

        return false;
    }
}
