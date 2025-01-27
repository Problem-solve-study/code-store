import java.io.*;

public class Main {
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int s, l;
        s = (N+1)/2;
        l = N*2/3;
        System.out.println(s+" "+l);
    }
}
