//제출번호: 94196600
//메모리:   11900 KB
//실행시간: 76 ms
import java.io.*;

//T초에 모든 쥐를 잡을 수 없는 최대 정사각형의 크기를 구하는 함수를 만들고
//삼분 탐색을 통해서 모든 시간에 대해서 쥐를 잡을 수 없는 최대 정사각형의 크기를 찾아야 함
public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int n;
    static int[] px, py, vx, vy;

    public static void main(String[] args) throws IOException {
        n = nextInt();

        px = new int[n];
        py = new int[n];
        vx = new int[n];
        vy = new int[n];

        //위치와 방향을 입력을 받음
        for (int i = 0; i < n; i++) {
            px[i] = nextInt();
            py[i] = nextInt();
            vx[i] = nextInt();
            vy[i] = nextInt();
        }

        //삼분 탐색
        double left = 0, right = 1e9, mid1, mid2;
        for (int i = 0; i < 500; i++) {
            mid1 = (2 * left + right) / 3; //왼쪽 mid
            mid2 = (left + 2 * right) / 3; //오른쪽 mid

            double lVal = simulate(mid1); //mid1에서 최대 정사각형 크기를 구함
            double rVal = simulate(mid2); //mid2에서 최대 정사각형 크기를 구함

            //만약 왼쪽이 더 작다면 left ~ mid2 구간을 다시 봐야 함
            if (lVal <= rVal) {
                right = mid2;
            }
            
            //만약 오른쪽이 더 작다면 mid1 ~ right 구간을 다시 봐야 함
            if (lVal >= rVal) {
                left = mid1;
            }
            
            //lVal == rVal인 경우 left와 right 모두 변경됨
        }

        //찾은 지점(시간)의 최대 정사각형 크기를 구해서 출력 
        System.out.print(simulate(left));
    }

    static double simulate(double time) {
        double minX = Double.MAX_VALUE, maxX = -Double.MAX_VALUE;
        double minY = Double.MAX_VALUE, maxY = -Double.MAX_VALUE;
        
        for (int i = 0; i < n; i++) {
            //time초 후의 쥐의 x, y 좌표를 구하고
            double x = px[i] + vx[i] * time;
            double y = py[i] + vy[i] * time;

            //좌표들의 사각형 구간을 계산함
            minX = Math.min(minX, x);
            maxX = Math.max(maxX, x);
            minY = Math.min(minY, y);
            maxY = Math.max(maxY, y);
        }

        //경계에 있는 쥐는 잡을 수 없다고 했으므로 구간의 길이 중 큰 값만큼이
        //모든 쥐를 잡을 수 없는 최대 정사각형의 길이임
        //이 값보다 조금이라도 커지는 경우 모든 쥐를 잡을 수 있게 됨
        return Math.max(maxX - minX, maxY - minY);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}