import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] input = br.readLine().toCharArray();
        int[] cnt = new int[26];
        for(char c: input) //alphabet�� ���� ���ϱ�
            cnt[c-'A']++;

        int oddCnt =0;
        char mid = ' ';
        for(int i =0;i<26;i++){ //alphabet�� Ȧ���� ���
            if((cnt[i] & 1) == 1){
                oddCnt++;
                mid = (char)(i+'A');
                cnt[i]--;
            }
        }

        if(oddCnt > (input.length & 1)){ //¦�� ���� ���ĺ��� �� Ȧ���� ��� & Ȧ�� ���� ���ĺ��� 2�� �̻��� ���
            System.out.println("I'm Sorry Hansoo");
            return;
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i =0;i<26;i++){
            while(cnt[i] > 0){
                sb.append((char)(i+'A'));
                cnt[i] -= 2;
            }
        }

        StringBuilder result = new StringBuilder();
        result.append(sb.toString());
        if(mid != ' ')
            result.append(mid);
        result.append(sb.reverse().toString());
        System.out.println(result);
    }
}