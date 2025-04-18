// 38424KB	148ms
import java.io.*;
import java.util.*;
/*
 * 진짜 정직하게 구현함.
 * 적혀있는대로 문자열들의 길이에 따라 시작과 끝의 substring을 서로 비교해가면서 겹치는게 있을 경우 cnt+1.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // front, end, cnt, now
        int n = Integer.parseInt(br.readLine());
        String[] names = new String[n];
        for(int i=0; i<n; i++) names[i] = br.readLine();
        
        int cnt = 0;
        
        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++){
                if(findPair(names[i], names[j])){
                    cnt++;
                } 
            }
        }
        System.out.println(cnt);
        
    }

    public static boolean findPair(String s1, String s2){
        int length = Math.min(s1.length(), s2.length());
        for(int i=1; i<=length; i++){
            if(s1.substring(0, i).equals(s2.substring(s2.length()-i, s2.length()))) return true;
            if(s2.substring(0, i).equals(s1.substring(s1.length()-i, s1.length()))) return true;
        }
        return false;
    }
}
