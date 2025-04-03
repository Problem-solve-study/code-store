// 12520KB	100ms
import java.io.*;
import java.util.*;
/*
 * 문제 누가내써
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        char[] n = st.nextToken().toCharArray();
        
        char[][] lcd = new char[2*s+3][n.length*(s+3)];
        for(int i=0; i<2*s+3; i++) Arrays.fill(lcd[i], ' ');
        char[][] num = new char[2*s+3][s+2];
        int k=0;
        for(char c:n){
          num = new char[2*s+3][s+2];
          for(int i=0; i<2*s+3; i++) Arrays.fill(num[i], ' ');
          switch(c){
            case '0':
            for(int i=1; i<(2*s+2); i++) if(i!=s+1) num[i][0] = num[i][s+1] = '|'; //세로
            for(int i=1; i<(s+1); i++) num[0][i] = num[2*s+2][i] = '-';  // 가로
            break;
            case '1':
            for(int i=1; i<(2*s+2); i++) if(i!=s+1) num[i][s+1] = '|';
            break;
            case '2':
            for(int i=1; i<(s+1); i++) num[i][s+1] = num[2*s+2-i][0] = '|';
            for(int i=1; i<(s+1); i++) num[0][i] = num[s+1][i] = num[2*s+2][i] = '-';
            break;
            case '3':
            for(int i=1; i<(2*s+2); i++) if(i!=s+1) num[i][s+1] = '|';
            for(int i=1; i<(s+1); i++) num[0][i] = num[s+1][i] = num[2*s+2][i] = '-';
            break;
            case '4':
            for(int i=1; i<(s+1); i++) num[i][s+1] = num[(2*s+2)-i][s+1] = num[i][0] = '|';
            for(int i=1; i<(s+1); i++) num[s+1][i] = '-';
            break;
            case '5':
            for(int i=1; i<(s+1); i++) num[i][0] = num[2*s+2-i][s+1] = '|';
            for(int i=1; i<(s+1); i++) num[0][i] = num[s+1][i] = num[2*s+2][i] = '-';
            break;
            case '6':
            for(int i=1; i<(s+1); i++) num[i][0] = num[2*s+2-i][0] = num[2*s+2-i][s+1] = '|';
            for(int i=1; i<(s+1); i++) num[0][i] = num[s+1][i] = num[2*s+2][i] = '-';
            break;
            case '7':
            for(int i=1; i<(2*s+2); i++) if(i!=s+1) num[i][s+1] = '|';
            for(int i=1; i<(s+1); i++) num[0][i] = '-';
            break;
            case '8':
            for(int i=1; i<(2*s+2); i++) if(i!=s+1) num[i][0] = num[i][s+1] = '|';
            for(int i=1; i<(s+1); i++) num[0][i] = num[s+1][i] = num[2*s+2][i] = '-';
            break;
            case '9':
            for(int i=1; i<(s+1); i++) num[i][0] = num[i][s+1] = num[2*s+2-i][s+1] = '|';
            for(int i=1; i<(s+1); i++) num[0][i] = num[s+1][i] = num[2*s+2][i] = '-';
            break;
          }
          for(int i=0; i<2*s+3; i++){
            for(int j=0; j<(s+2); j++){
              lcd[i][(s+3)*k+j+1] = num[i][j];
            }
          }
          k++;
        }

        for(int i=0; i<2*s+3; i++){
          for(int j=1; j<k*(s+3); j++){
            System.out.print(lcd[i][j]);
          }
          System.out.println();
        }
    }
}