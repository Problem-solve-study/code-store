// 12632KB  108ms
import java.io.*;
import java.util.*;
/*
 * hashmap을 사용하여 들어온 차들의 순서를 index로 저장한 뒤 나가는 차들의 순서를 비교하는 형태로 구현.
 */
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        HashMap<String, Integer> inCars = new HashMap<>(n);
        String[] outCars = new String[n];

        for(int i=0; i<n; i++){
            inCars.put(br.readLine(), i);
        }
        for(int i=0; i<n; i++){
            outCars[i] = br.readLine();
        }

        int cnt = 0;

        boolean[] cars = new boolean[n];
        for(int i=0; i<n; i++){
            for(int j=0; j<inCars.get(outCars[i]); j++) {
                if(!cars[j]){
                    cnt++;
                    break;
                }
            }
            cars[inCars.get(outCars[i])] = true;
        }
        System.out.println(cnt);
    }
}